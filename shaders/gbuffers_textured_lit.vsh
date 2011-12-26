#version 120

//#define BUMP_MAPPING
//#define CURVY_WORLD
//#define CURVY_WORLD_RADIUS 30.0
//#define CURVY_WORLD_RADIUS_SQUARED 10000.0
//#define ACID

varying vec4 color;
varying vec4 texcoord;
varying vec4 lmcoord;
#ifdef BUMP_MAPPING
varying vec3 normal;
#endif
#ifdef ACID
uniform int worldTime;
#endif

void main() {
  vec4 position = gl_ModelViewMatrix * gl_Vertex;
  #ifdef CURVY_WORLD
    float distanceSquared = position.x * position.x + position.z * position.z;
    position.y -= (CURVY_WORLD_RADIUS - sqrt(max(1.0 - distanceSquared / CURVY_WORLD_RADIUS_SQUARED, 0.0)) * CURVY_WORLD_RADIUS);
  #endif
  #ifdef ACID
    float distanceSquared = position.x * position.x + position.z * position.z;
    position.y += 5*sin(distanceSquared*sin(float(worldTime)/143.0)/1000);
    float y = position.y;
    float x = position.x;
    float z = position.z * ( (sin(float(worldTime)/256.0) + 1.75) / 2 );
    float om = sin(distanceSquared*sin(float(worldTime)/256.0)/5000) * sin(float(worldTime)/200.0);
    position.y = x*sin(om)+y*cos(om);
    position.x = x*cos(om)-y*sin(om);
    position.z = z;
  #endif
  gl_Position = gl_ProjectionMatrix * position;
	
	color = gl_Color;
	
	texcoord = gl_TextureMatrix[0] * gl_MultiTexCoord0;

	lmcoord = gl_TextureMatrix[1] * gl_MultiTexCoord1;

  #ifdef BUMP_MAPPING
  normal = gl_Normal;
  #endif

	gl_FogFragCoord = gl_Position.z;
}
