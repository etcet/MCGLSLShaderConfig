#version 120

//#define BUMP_MAPPING

varying vec4 color;
#ifdef BUMP_MAPPING
varying vec3 normal;
#endif

void main() {
	gl_Position = ftransform();
	
	color = gl_Color;

  #ifdef BUMP_MAPPING
  normal = gl_Normal;
  #endif

	gl_FogFragCoord = gl_Position.z;
}
