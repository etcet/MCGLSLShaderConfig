#version 120

//#define BUMP_MAPPING
//#define BUMP_16
//#define BUMP_32
//#define BUMP_64
#define BUMP_128
//#define BUMP_256


#ifdef BUMP_16
  const vec3 intervalMult = vec3(0.0039, 0.0039, 4.5);
#endif
#ifdef BUMP_32
  const vec3 intervalMult = vec3(0.0019, 0.0019, 0.5);
#endif
#ifdef BUMP_64
  const vec3 intervalMult = vec3(0.0009765625, 0.0009765625, 0.145);
#endif
#ifdef BUMP_128
  const vec3 intervalMult = vec3(0.00048828125, 0.00048828125, 0.2);
#endif
#ifdef BUMP_256
  const vec3 intervalMult = vec3(0.000244140625, 0.000244140625, 0.1);
#endif

uniform sampler2D normals;
uniform sampler2D specular;
uniform sampler2D texture;
uniform sampler2D lightmap;

varying vec4 color;
varying vec4 texcoord;
varying vec4 lmcoord;

#ifdef BUMP_MAPPING
varying vec3 viewVector;

varying vec3 normal;
varying vec3 tangent;
varying vec3 binormal;

varying float distance;

const float MAX_OCCLUSION_DISTANCE = 100.0;
const int MAX_OCCLUSION_POINTS = 50;
#endif

const int GL_LINEAR = 9729;
const int GL_EXP = 2048;

uniform int fogMode;

void main() {
	gl_FragData[1] = vec4(vec3(gl_FragCoord.z), 1.0);

  #ifdef BUMP_MAPPING
	vec2 adjustedTexCoord = texcoord.st;

	if (distance <= MAX_OCCLUSION_DISTANCE && viewVector.z < 0.0) {
		vec3 coord = vec3(texcoord.st, 1.0);

		if (texture2D(normals, coord.st).a < 1.0) {
			vec2 minCoord = vec2(texcoord.s - mod(texcoord.s, 0.0625), texcoord.t - mod(texcoord.t, 0.0625));
			vec2 maxCoord = vec2(minCoord.s + 0.0625, minCoord.t + 0.0625);
		
			vec3 interval = viewVector * intervalMult;

			for (int loopCount = 0; texture2D(normals, coord.st).a < coord.z && loopCount < MAX_OCCLUSION_POINTS; ++loopCount) {
				coord += interval;
				if (coord.s < minCoord.s) {
					coord.s += 0.0625;
				} else if (coord.s >= maxCoord.s) {
					coord.s -= 0.0625;
				}
				if (coord.t < minCoord.t) {
					coord.t += 0.0625;
				} else if (coord.t >= maxCoord.t) {
					coord.t -= 0.0625;
				}
			}
		}

		adjustedTexCoord = coord.st;
	}

	gl_FragData[0] = texture2D(texture, adjustedTexCoord.st) * texture2D(lightmap, lmcoord.st) * color;

	vec3 bump = texture2D(normals, adjustedTexCoord.st).xyz * 2.0 - 1.0;

	mat3 tbnMatrix = mat3(tangent.x, binormal.x, normal.x,
                          tangent.y, binormal.y, normal.y,
                          tangent.z, binormal.z, normal.z);

	gl_FragData[2] = vec4(bump * tbnMatrix * 0.5 + 0.5, 1.0);

	gl_FragData[4] = vec4(texture2D(specular, adjustedTexCoord.st).rgb, 1.0);
  #else
	gl_FragData[0] = texture2D(texture, texcoord.st) * texture2D(lightmap, lmcoord.st) * color;
  #endif

	if (fogMode == GL_EXP) {
		gl_FragData[0].rgb = mix(gl_FragData[0].rgb, gl_Fog.color.rgb, 1.0 - clamp(exp(-gl_Fog.density * gl_FogFragCoord), 0.0, 1.0));
	} else if (fogMode == GL_LINEAR) {
		gl_FragData[0].rgb = mix(gl_FragData[0].rgb, gl_Fog.color.rgb, clamp((gl_FogFragCoord - gl_Fog.start) * gl_Fog.scale, 0.0, 1.0));
	}
}
