#version 120

//#define BUMP_MAPPING

uniform sampler2D texture;
uniform sampler2D lightmap;

varying vec4 color;
varying vec4 texcoord;
varying vec4 lmcoord;
#ifdef BUMP_MAPPING
varying vec3 normal;
#endif

void main() {
	gl_FragData[0] = texture2D(texture, texcoord.st) * texture2D(lightmap, lmcoord.st) * color;
	gl_FragData[1] = vec4(0.0);
  #ifdef BUMP_MAPPING
	gl_FragData[2] = vec4(normal * 0.5 + 0.5, 1.0);
	gl_FragData[4] = vec4(0.0, 0.0, 0.0, 1.0);
  #endif
}
