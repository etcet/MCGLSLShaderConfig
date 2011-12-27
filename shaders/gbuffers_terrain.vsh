#version 120

//#define BUMP_MAPPING
#define WAVY_WHEAT
#define WAVY_GRASS
#define WAVY_LEAVES
#define WAVY_PLANTS
//#define CURVY_WORLD
//#define CURVY_WORLD_RADIUS 30.0
//#define CURVY_WORLD_RADIUS_SQUARED 10000.0
//#define ACID
//#define WAVY_LAVA
//#define WAVY_LAVA_PITCH 50.0

varying vec4 color;
varying vec4 texcoord;
varying vec4 lmcoord;

attribute vec4 mc_Entity;

uniform int worldTime;

#ifdef BUMP_MAPPING
varying vec3 viewVector;

varying vec3 normal;
varying vec3 tangent;
varying vec3 binormal;

varying float distance;
#endif

#ifdef WAVY_LAVA
const float WAVE_PITCH = WAVY_LAVA_PITCH; //Decrease to grow wave effect
const float PI = 3.1415926535897932384626433832795;
#endif

const float PI2 = 6.283185307179586476925286766559;

void main() {
	texcoord = gl_TextureMatrix[0] * gl_MultiTexCoord0;
	
	vec4 position = gl_ModelViewMatrix * gl_Vertex;

  #ifdef WAVY_LAVA
  if (mc_Entity.x == 11) {
    float t1 = float(mod(worldTime, 1000))/400.0;
    vec2 pos1 = position.xz/16.0;
    position.y += (cos((PI*2.0)*(2.0 * (pos1.x + pos1.y) + PI * t1)) + 0.2)/WAVE_PITCH;
  }
  #endif

  #ifdef WAVY_GRASS
  if (mc_Entity.x == 31.0) {
    float t = float(mod(worldTime, 300))/300.0;
    vec2 pos = position.xz/16.0;
    if (round(16.0*gl_MultiTexCoord0.t) <= floor(16.0*gl_MultiTexCoord0.t)) {
      position.x -= (sin(PI2*(2.0*pos.x + pos.y - 3.0*t)) + 0.6)/12.0;
    }
  }
  #endif

  #ifdef WAVY_WHEAT
  if (mc_Entity.x == 59.0) {
    float t = mod(float(worldTime), 200.0)/200.0;
    vec2 pos = position.xz/16.0;
    if (floor((16.0*gl_MultiTexCoord0.t)+0.5) <= floor(16.0*gl_MultiTexCoord0.t)) {
      position.x += (sin(PI2*(2.0*pos.x + pos.y - 3.0*t)) + 0.6)/20.0;
    }
  }
  #endif

  #ifdef WAVY_LEAVES
	if (mc_Entity.x == 18.0) {
    float t = mod(float(worldTime), 800.0)/800.0;
    vec2 pos = position.xz/16.0;
    if (floor(8.0*gl_MultiTexCoord0.t+0.5) <= floor(16.32*gl_MultiTexCoord0.t)) {
      position.x -= (sin(PI2*(2.0*pos.x + pos.y - 3.0*t)) + 0.6)/24.0;
      position.y -= (sin(PI2*(3.0*pos.x + pos.y - 4.0*t)) + 1.2)/32.0;
      position.z -= (sin(PI2*(1.0*pos.x + pos.y - 1.5*t)) + 0.3)/8.0;
    }
	}
  #endif

  #ifdef WAVY_PLANTS
  if (mc_Entity.x == 32 || mc_Entity.x == 37 || mc_Entity.x == 38) {
    float t = mod(float(worldTime), 500.0)/500.0;
    vec2 pos = position.xz/16.0;
    if ( floor((16.0*gl_MultiTexCoord0.t)+0.5) <= floor(16.0*gl_MultiTexCoord0.t) ) {
      position.x -= (sin(PI2*(2.0*pos.x + pos.y - 3.0*t)) + 0.6)/8.0;
    }
  }
  #endif
  
  #ifdef CURVY_WORLD
  if (gl_Color.a != 0.8) { //not a cloud
    float distanceSquared = position.x * position.x + position.z * position.z;
    position.y -= CURVY_WORLD_RADIUS - sqrt(max(1.0 - distanceSquared / CURVY_WORLD_RADIUS_SQUARED, 0.0)) * CURVY_WORLD_RADIUS;
  }
  #endif
  
  #ifdef ACID
  //Modified Gaeel Acid Shader mod *(Modified daxnitro curviture mod)
  if (gl_Color.a != 0.8) 
  {
    float distanceSquared = position.x * position.x + position.z * position.z;
    position.y += 5*sin(distanceSquared*sin(float(worldTime)/143.0)/1000);
    float y = position.y;
    float x = position.x;
    //Easter egg of my version that causes a pulling away from the Z-axis causing a stretching and shrinking sort of effect
    float z = position.z * ( (sin(float(worldTime)/256.0) + 1.75) / 2);
    float om = sin(distanceSquared*sin(float(worldTime)/256.0)/5000) * sin(float(worldTime)/200.0);
    position.y = x*sin(om)+y*cos(om);
    position.x = x*cos(om)-y*sin(om);
    position.z = z;
  }	
  #endif

	gl_Position = gl_ProjectionMatrix * position;
	color = gl_Color;
	
	lmcoord = gl_TextureMatrix[1] * gl_MultiTexCoord1;
	
	gl_FogFragCoord = gl_Position.z;

  #ifdef BUMP_MAPPING
	distance = sqrt(position.x * position.x + position.y * position.y + position.z * position.z);
  normal = normalize(gl_NormalMatrix * gl_Normal);

	if (gl_Normal.x > 0.5) {
		//  1.0,  0.0,  0.0
		tangent  = normalize(gl_NormalMatrix * vec3( 0.0,  0.0, -1.0));
		binormal = normalize(gl_NormalMatrix * vec3( 0.0, -1.0,  0.0));
	} else if (gl_Normal.x < -0.5) {
		// -1.0,  0.0,  0.0
		tangent  = normalize(gl_NormalMatrix * vec3( 0.0,  0.0,  1.0));
		binormal = normalize(gl_NormalMatrix * vec3( 0.0, -1.0,  0.0));
	} else if (gl_Normal.y > 0.5) {
		//  0.0,  1.0,  0.0
		tangent  = normalize(gl_NormalMatrix * vec3( 1.0,  0.0,  0.0));
		binormal = normalize(gl_NormalMatrix * vec3( 0.0,  0.0,  1.0));
	} else if (gl_Normal.y < -0.5) {
		//  0.0, -1.0,  0.0
		tangent  = normalize(gl_NormalMatrix * vec3( 1.0,  0.0,  0.0));
		binormal = normalize(gl_NormalMatrix * vec3( 0.0,  0.0,  1.0));
	} else if (gl_Normal.z > 0.5) {
		//  0.0,  0.0,  1.0
		tangent  = normalize(gl_NormalMatrix * vec3( 1.0,  0.0,  0.0));
		binormal = normalize(gl_NormalMatrix * vec3( 0.0, -1.0,  0.0));
	} else if (gl_Normal.z < -0.5) {
		//  0.0,  0.0, -1.0
		tangent  = normalize(gl_NormalMatrix * vec3(-1.0,  0.0,  0.0));
		binormal = normalize(gl_NormalMatrix * vec3( 0.0, -1.0,  0.0));
	}
	
	mat3 tbnMatrix = mat3(tangent.x, binormal.x, normal.x,
                          tangent.y, binormal.y, normal.y,
                          tangent.z, binormal.z, normal.z);
	
	viewVector = (gl_ModelViewMatrix * gl_Vertex).xyz;
	viewVector = normalize(tbnMatrix * viewVector);
  #endif
}

