#version 120

//#define BUMP_MAPPING

uniform sampler2D texture;

varying vec4 color;
varying vec4 texcoord;
#ifdef BUMP_MAPPING
varying vec3 normal;
#endif

const int GL_LINEAR = 9729;
const int GL_EXP = 2048;

uniform int fogMode;

void main() {

	gl_FragData[0] = texture2D(texture, texcoord.st) * color;
	gl_FragData[1] = vec4(vec3(gl_FragCoord.z), 1.0);
  #ifdef BUMP_MAPPING
	gl_FragData[2] = vec4(normal * 0.5 + 0.5, 1.0);
	gl_FragData[4] = vec4(0.0, 0.0, 0.0, 1.0);
  #endif
		
	if (fogMode == GL_EXP) {
		gl_FragData[0].rgb = mix(gl_FragData[0].rgb, gl_Fog.color.rgb, 1.0 - clamp(exp(-gl_Fog.density * gl_FogFragCoord), 0.0, 1.0));
	} else if (fogMode == GL_LINEAR) {
		gl_FragData[0].rgb = mix(gl_FragData[0].rgb, gl_Fog.color.rgb, clamp((gl_FogFragCoord - gl_Fog.start) * gl_Fog.scale, 0.0, 1.0));
	}
}
