#version 120

//#define BUMP_MAPPING
//#define CURVY_WORLD
#define WORLD_RADIUS 30.0
#define WORLD_RADIUS_SQUARED 10000.0

varying vec4 color;
varying vec4 texcoord;
varying vec4 lmcoord;
#ifdef BUMP_MAPPING
varying vec3 normal;
#endif

void main() {
  #ifdef CURVY_WORLD
  vec4 position = gl_ModelViewMatrix * gl_Vertex;

	float distanceSquared = position.x * position.x + position.z * position.z;
  position.y -= WORLD_RADIUS - sqrt(max(1.0 - distanceSquared / WORLD_RADIUS_SQUARED, 0.0)) * WORLD_RADIUS;
	gl_Position = gl_ProjectionMatrix * position;
  #else
	gl_Position = ftransform();
  #endif
	
	color = gl_Color;
	
	texcoord = gl_TextureMatrix[0] * gl_MultiTexCoord0;

	lmcoord = gl_TextureMatrix[1] * gl_MultiTexCoord1;

  #ifdef BUMP_MAPPING
  normal = gl_Normal;
  #endif

	gl_FogFragCoord = gl_Position.z;
}