#version 120

//#define BUMP_MAPPING

varying vec4 color;
varying vec4 texcoord;
varying vec4 lmcoord;
#ifdef BUMP_MAPPING
varying vec3 normal;
#endif

void main() {
	gl_Position = ftransform();
	
	color = gl_Color;
	
	texcoord = gl_TextureMatrix[0] * gl_MultiTexCoord0;

	lmcoord = gl_TextureMatrix[1] * gl_MultiTexCoord1;

  #ifdef BUMP_MAPPING
  normal = gl_Normal;
  #endif
}
