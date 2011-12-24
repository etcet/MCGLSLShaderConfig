#version 120

/*

More realistic depth-of-field by Azraeil.
God Rays by Blizzard
Bloom shader by CosmicSpore (Modified from original source: http://myheroics.wordpress.com/2008/09/04/glsl-bloom-shader/)
Cross-Processing by an anonymous user.
Shaders 2.0 port of Yourself's Cell Shader, port by an anonymous user.
Bug Fixes by Kool_Kat.

*/

// Place two leading Slashes in front of the following '#define' lines in order to disable an option.
#define USE_DOF
//#define GODRAYS
//#define GODRAYS_EXPOSURE 0.5
//#define GODRAYS_SAMPLES 32
//#define GODRAYS_DECAY 0.95
//#define GODRAYS_DENSITY 0.5
#define BLOOM
#define BLOOM_AMOUNT 9.5
#define BLOOM_RANGE 4
#define CEL_SHADING
#define CEL_SHADING_THRESHOLD 0.4
#define CEL_SHADING_THICKNESS 0.004
//#define USE_HIGH_QUALITY_BLUR
//#define CROSSPROCESS
//#define CROSSPROCESS_R color.r*2.0
//#define CROSSPROCESS_G color.g*1.2
//#define CROSSPROCESS_B color.b*0.75+0.10

// DOF Constants - DO NOT CHANGE
// HYPERFOCAL = (Focal Distance ^ 2)/(Circle of Confusion * F Stop) + Focal Distance
#ifdef USE_DOF
const float HYPERFOCAL = 3.132;
const float PICONSTANT = 3.14159;
#endif


//uniform sampler2D texture;
//uniform sampler2D gcolor;
uniform sampler2D gdepth;
uniform sampler2D composite;
uniform sampler2D gnormal;

uniform vec3 sunPosition;

uniform float worldTime;
uniform float aspectRatio;
uniform float near;
uniform float far;

varying vec4 texcoord;

float samples = 0.0;
vec2 space;

// Standard depth function.
float getDepth(vec2 coord) {
    return 2.0 * near * far / (far + near - (2.0 * texture2D(gdepth, coord).x - 1.0) * (far - near));
}

#ifdef USE_DOF
	vec4 getSampleWithBoundsCheck(vec2 offset) {
		vec2 coord = texcoord.st + offset;
		if (coord.s <= 1.0 && coord.s >= 0.0 && coord.t <= 1.0 && coord.t >= 0.0) {
			samples += 1.0;
			return texture2D(composite, coord);
		} else {
			return vec4(0.0);
		}
	}

	vec4 getBlurredColor() {
		vec4 blurredColor = vec4(0.0);
		float depth = getDepth(texcoord.xy);
		vec2 aspectCorrection = vec2(1.0, aspectRatio) * 0.005;

		vec2 ac0_4 = 0.4 * aspectCorrection;	// 0.4
	#ifdef USE_HIGH_QUALITY_BLUR
		vec2 ac0_4x0_4 = 0.4 * ac0_4;			// 0.16
		vec2 ac0_4x0_7 = 0.7 * ac0_4;			// 0.28
	#endif
		
		vec2 ac0_29 = 0.29 * aspectCorrection;	// 0.29
	#ifdef USE_HIGH_QUALITY_BLUR
		vec2 ac0_29x0_7 = 0.7 * ac0_29;			// 0.203
		vec2 ac0_29x0_4 = 0.4 * ac0_29;			// 0.116
	#endif
		
		vec2 ac0_15 = 0.15 * aspectCorrection;	// 0.15
		vec2 ac0_37 = 0.37 * aspectCorrection;	// 0.37
	#ifdef USE_HIGH_QUALITY_BLUR
		vec2 ac0_15x0_9 = 0.9 * ac0_15;			// 0.135
		vec2 ac0_37x0_9 = 0.37 * ac0_37;		// 0.1369
	#endif
		
		vec2 lowSpace = texcoord.st;
		vec2 highSpace = 1.0 - lowSpace;
		space = vec2(min(lowSpace.s, highSpace.s), min(lowSpace.t, highSpace.t));
			
		if (space.s >= ac0_4.s && space.t >= ac0_4.t) {

			blurredColor += texture2D(composite, texcoord.st + vec2(0.0, ac0_4.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(ac0_4.s, 0.0));   
			blurredColor += texture2D(composite, texcoord.st + vec2(0.0, -ac0_4.t)); 
			blurredColor += texture2D(composite, texcoord.st + vec2(-ac0_4.s, 0.0)); 
			
	#ifdef USE_HIGH_QUALITY_BLUR
			blurredColor += texture2D(composite, texcoord.st + vec2(ac0_4x0_7.s, 0.0));       
			blurredColor += texture2D(composite, texcoord.st + vec2(0.0, -ac0_4x0_7.t));     
			blurredColor += texture2D(composite, texcoord.st + vec2(-ac0_4x0_7.s, 0.0));     
			blurredColor += texture2D(composite, texcoord.st + vec2(0.0, ac0_4x0_7.t));
		
			blurredColor += texture2D(composite, texcoord.st + vec2(ac0_4x0_4.s, 0.0));
			blurredColor += texture2D(composite, texcoord.st + vec2(0.0, -ac0_4x0_4.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(-ac0_4x0_4.s, 0.0));
			blurredColor += texture2D(composite, texcoord.st + vec2(0.0, ac0_4x0_4.t));
	#endif

			blurredColor += texture2D(composite, texcoord.st + vec2(ac0_29.s, -ac0_29.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(ac0_29.s, ac0_29.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(-ac0_29.s, ac0_29.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(-ac0_29.s, -ac0_29.t));
		
	#ifdef USE_HIGH_QUALITY_BLUR
			blurredColor += texture2D(composite, texcoord.st + vec2(ac0_29x0_7.s, ac0_29x0_7.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(ac0_29x0_7.s, -ac0_29x0_7.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(-ac0_29x0_7.s, ac0_29x0_7.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(-ac0_29x0_7.s, -ac0_29x0_7.t));
			
			blurredColor += texture2D(composite, texcoord.st + vec2(ac0_29x0_4.s, ac0_29x0_4.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(ac0_29x0_4.s, -ac0_29x0_4.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(-ac0_29x0_4.s, ac0_29x0_4.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(-ac0_29x0_4.s, -ac0_29x0_4.t));
	#endif		
			
			blurredColor += texture2D(composite, texcoord.st + vec2(ac0_15.s, ac0_37.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(-ac0_37.s, ac0_15.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(ac0_37.s, -ac0_15.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(-ac0_15.s, -ac0_37.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(-ac0_15.s, ac0_37.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(ac0_37.s, ac0_15.t)); 
			blurredColor += texture2D(composite, texcoord.st + vec2(-ac0_37.s, -ac0_15.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(ac0_15.s, -ac0_37.t));

	#ifdef USE_HIGH_QUALITY_BLUR
			blurredColor += texture2D(composite, texcoord.st + vec2(ac0_15x0_9.s, ac0_37x0_9.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(-ac0_37x0_9.s, ac0_15x0_9.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(ac0_37x0_9.s, -ac0_15x0_9.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(-ac0_15x0_9.s, -ac0_37x0_9.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(-ac0_15x0_9.s, ac0_37x0_9.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(ac0_37x0_9.s, ac0_15x0_9.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(-ac0_37x0_9.s, -ac0_15x0_9.t));
			blurredColor += texture2D(composite, texcoord.st + vec2(ac0_15x0_9.s, -ac0_37x0_9.t));
	#endif

	#ifdef USE_HIGH_QUALITY_BLUR
			blurredColor /= 41.0;
	#else
			blurredColor /= 16.0;
	#endif
			
		} else {
			
			blurredColor += getSampleWithBoundsCheck(vec2(0.0, ac0_4.t));
			blurredColor += getSampleWithBoundsCheck(vec2(ac0_4.s, 0.0));   
			blurredColor += getSampleWithBoundsCheck(vec2(0.0, -ac0_4.t)); 
			blurredColor += getSampleWithBoundsCheck(vec2(-ac0_4.s, 0.0)); 
			
	#ifdef USE_HIGH_QUALITY_BLUR
			blurredColor += getSampleWithBoundsCheck(vec2(ac0_4x0_7.s, 0.0));       
			blurredColor += getSampleWithBoundsCheck(vec2(0.0, -ac0_4x0_7.t));     
			blurredColor += getSampleWithBoundsCheck(vec2(-ac0_4x0_7.s, 0.0));     
			blurredColor += getSampleWithBoundsCheck(vec2(0.0, ac0_4x0_7.t));
		
			blurredColor += getSampleWithBoundsCheck(vec2(ac0_4x0_4.s, 0.0));
			blurredColor += getSampleWithBoundsCheck(vec2(0.0, -ac0_4x0_4.t));
			blurredColor += getSampleWithBoundsCheck(vec2(-ac0_4x0_4.s, 0.0));
			blurredColor += getSampleWithBoundsCheck(vec2(0.0, ac0_4x0_4.t));
	#endif

			blurredColor += getSampleWithBoundsCheck(vec2(ac0_29.s, -ac0_29.t));
			blurredColor += getSampleWithBoundsCheck(vec2(ac0_29.s, ac0_29.t));
			blurredColor += getSampleWithBoundsCheck(vec2(-ac0_29.s, ac0_29.t));
			blurredColor += getSampleWithBoundsCheck(vec2(-ac0_29.s, -ac0_29.t));
		
	#ifdef USE_HIGH_QUALITY_BLUR
			blurredColor += getSampleWithBoundsCheck(vec2(ac0_29x0_7.s, ac0_29x0_7.t));
			blurredColor += getSampleWithBoundsCheck(vec2(ac0_29x0_7.s, -ac0_29x0_7.t));
			blurredColor += getSampleWithBoundsCheck(vec2(-ac0_29x0_7.s, ac0_29x0_7.t));
			blurredColor += getSampleWithBoundsCheck(vec2(-ac0_29x0_7.s, -ac0_29x0_7.t));
			
			blurredColor += getSampleWithBoundsCheck(vec2(ac0_29x0_4.s, ac0_29x0_4.t));
			blurredColor += getSampleWithBoundsCheck(vec2(ac0_29x0_4.s, -ac0_29x0_4.t));
			blurredColor += getSampleWithBoundsCheck(vec2(-ac0_29x0_4.s, ac0_29x0_4.t));
			blurredColor += getSampleWithBoundsCheck(vec2(-ac0_29x0_4.s, -ac0_29x0_4.t));
	#endif
					
			blurredColor += getSampleWithBoundsCheck(vec2(ac0_15.s, ac0_37.t));
			blurredColor += getSampleWithBoundsCheck(vec2(-ac0_37.s, ac0_15.t));
			blurredColor += getSampleWithBoundsCheck(vec2(ac0_37.s, -ac0_15.t));
			blurredColor += getSampleWithBoundsCheck(vec2(-ac0_15.s, -ac0_37.t));
			blurredColor += getSampleWithBoundsCheck(vec2(-ac0_15.s, ac0_37.t));
			blurredColor += getSampleWithBoundsCheck(vec2(ac0_37.s, ac0_15.t)); 
			blurredColor += getSampleWithBoundsCheck(vec2(-ac0_37.s, -ac0_15.t));
			blurredColor += getSampleWithBoundsCheck(vec2(ac0_15.s, -ac0_37.t));
			
	#ifdef USE_HIGH_QUALITY_BLUR
			blurredColor += getSampleWithBoundsCheck(vec2(ac0_15x0_9.s, ac0_37x0_9.t));
			blurredColor += getSampleWithBoundsCheck(vec2(-ac0_37x0_9.s, ac0_15x0_9.t));
			blurredColor += getSampleWithBoundsCheck(vec2(ac0_37x0_9.s, -ac0_15x0_9.t));
			blurredColor += getSampleWithBoundsCheck(vec2(-ac0_15x0_9.s, -ac0_37x0_9.t));
			blurredColor += getSampleWithBoundsCheck(vec2(-ac0_15x0_9.s, ac0_37x0_9.t));
			blurredColor += getSampleWithBoundsCheck(vec2(ac0_37x0_9.s, ac0_15x0_9.t));
			blurredColor += getSampleWithBoundsCheck(vec2(-ac0_37x0_9.s, -ac0_15x0_9.t));
			blurredColor += getSampleWithBoundsCheck(vec2(ac0_15x0_9.s, -ac0_37x0_9.t));
	#endif
		
			blurredColor /= samples;
			
		}

		return blurredColor;
	}
#endif

#ifdef GODRAYS
	vec4 addGodRays(vec4 nc, vec2 tx) {
		float threshold = 0.99 * far;
//		bool foreground = false;
		float depthGD = getDepth(tx);
		if ( (worldTime < 14000 || worldTime > 22000) && (sunPosition.z < 0) && (depthGD < threshold) ) {
			vec2 lightPos = sunPosition.xy / -sunPosition.z;
			lightPos.y *= aspectRatio;
			lightPos = (lightPos + 1.0)/2.0;
			//vec2 coord = tx;
			vec2 delta = (tx - lightPos) * GODRAYS_DENSITY / float(GODRAYS_SAMPLES);
			float decay = -sunPosition.z / 100.0;
			vec3 colorGD = vec3(0.0);
			
			for (int i = 0; i < GODRAYS_SAMPLES; i++) {
				tx -= delta;
				if (tx.x < 0.0 || tx.x > 1.0) {
					if (tx.y < 0.0 || tx.y > 1.0) {
						break;
					}
				}
				vec3 sample = vec3(0.0);
				if (getDepth(tx) > threshold) {
					sample = texture2D(composite, tx).rgb;
				}
				sample *= vec3(decay);
				if (distance(tx, lightPos) > 0.05) {
					sample *= 0.2;
				}
					colorGD += sample;
					decay *= GODRAYS_DECAY;
			}
			return (nc + GODRAYS_EXPOSURE * vec4(colorGD, 0.0));
        } else {
			return nc;
		}
	}
#endif 

#ifdef BLOOM
	vec4 addBloom(vec4 c, vec2 t) {
		int j;
		int i;
		vec4 bloom = vec4(0.0);
		vec2 loc = vec2(0.0);
		float count = 0.0;
		
		for( i= -BLOOM_RANGE ; i < BLOOM_RANGE; i++ ) {
			for ( j = -BLOOM_RANGE; j < BLOOM_RANGE; j++ ) {
				loc = t + vec2(j, i)*0.004;
				
				// Only add to bloom texture if loc is on-screen.
				if(loc.x > 0 && loc.x < 1 && loc.y > 0 && loc.y < 1) {
					bloom += texture2D(composite, loc) * BLOOM_AMOUNT;
					count += 1;
				}
			}
		}
		bloom /= vec4(count);
		
		if (c.r < 0.3)
		{
			return bloom*bloom*0.012;
		}
		else
		{
			if (c.r < 0.5)
			{
				return bloom*bloom*0.009;
			}
			else
			{
				return bloom*bloom*0.0075;
			}
		}
	}
#endif

#ifdef CEL_SHADING
	float getCellShaderFactor(vec2 coord) {
		float d = getDepth(coord);
		vec3 n = normalize(vec3(getDepth(coord+vec2(CEL_SHADING_THICKNESS,0.0))-d,getDepth(coord+vec2(0.0,CEL_SHADING_THICKNESS))-d , CEL_SHADING_THRESHOLD));
		return n.z; //clamp(n.z*3.0,0.0,1.0);
	}
#endif


// Main ------------------------------------------------------------
void main() {

	vec4 color = texture2D(composite, texcoord.st);
	
#ifdef USE_DOF
	float depth = getDepth(texcoord.st);
	    
	float cursorDepth = getDepth(vec2(0.5, 0.5));
    
    // foreground blur = 1/2 background blur. Blur should follow exponential pattern until cursor = hyperfocal -- Cursor before hyperfocal
    // Blur should go from 0 to 1/2 hyperfocal then clear to infinity -- Cursor @ hyperfocal.
    // hyperfocal to inifity is clear though dof extends from 1/2 hyper to hyper -- Cursor beyond hyperfocal
    
    float mixAmount = 0.0;
    
    if (depth < cursorDepth) {
    	mixAmount = clamp(2.0 * ((clamp(cursorDepth, 0.0, HYPERFOCAL) - depth) / (clamp(cursorDepth, 0.0, HYPERFOCAL))), 0.0, 1.0);
	} else if (cursorDepth == HYPERFOCAL) {
		mixAmount = 0.0;
	} else {
		mixAmount =  1.0 - clamp((((cursorDepth * HYPERFOCAL) / (HYPERFOCAL - cursorDepth)) - (depth - cursorDepth)) / ((cursorDepth * HYPERFOCAL) / (HYPERFOCAL - cursorDepth)), 0.0, 1.0);
	}
    
    if (mixAmount != 0.0) {
		color = mix(color, getBlurredColor(), mixAmount);
   	}
#endif

#ifdef GODRAYS
	color = addGodRays(color, texcoord.st);
#endif

#ifdef BLOOM
	color += addBloom(color, texcoord.st);
#endif

#ifdef CEL_SHADING
	color.rgb *= (getCellShaderFactor(texcoord.st));
#endif

#ifdef CROSSPROCESS
	color.r = CROSSPROCESS_R;
	color.g = CROSSPROCESS_G;
	color.b = CROSSPROCESS_B;
#endif

	gl_FragColor = color;
	
// End of Main. -----------------
}
