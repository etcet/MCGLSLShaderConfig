#version 120

//#define BUMP_MAPPING
#define WAVY_WHEAT
#define WAVY_GRASS
#define WAVY_LEAVES
//#define CURVY_WORLD
#define WORLD_RADIUS 30.0
#define WORLD_RADIUS_SQUARED 10000.0

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

void main() {
	texcoord = gl_TextureMatrix[0] * gl_MultiTexCoord0;
	
	vec4 position = gl_ModelViewMatrix * gl_Vertex;
 
  #ifdef WAVY_GRASS
	if (mc_Entity.x == 31.0 && texcoord.t < 0.15) {
		float magnitude = sin(worldTime * 3.14159265358979323846264 / 172.0) * 0.2;
		position.x += sin(worldTime * 3.14159265358979323846264 / 86.0) * magnitude;
		position.z += sin(worldTime * 3.14159265358979323846264 / 72.0) * magnitude;
	}
  #endif
  #ifdef WAVY_WHEAT
	if (mc_Entity.x == 59.0 && texcoord.t < 0.35) {
		float magnitude = sin(worldTime * 3.14159265358979323846264 / 172.0) * 0.2;
		position.x += sin(worldTime * 3.14159265358979323846264 / 82.0) * magnitude;
		position.z += sin(worldTime * 3.14159265358979323846264 / 78.0) * magnitude;
	}
  #endif
  #ifdef WAVY_LEAVES
	if (mc_Entity.x == 18.0 && texcoord.t < 0.20) {
		float magnitude = sin(worldTime * 3.14159265358979323846264 / 172.0) * 0.2;
		position.x += sin(worldTime * 3.14159265358979323846264 / 86.0) * magnitude;
		position.z += sin(worldTime * 3.14159265358979323846264 / 78.0) * magnitude;
	}
  #endif
  
  #ifdef CURVY_WORLD
    if (gl_Color.a != 0.8) { //not a cloud
      float distanceSquared = position.x * position.x + position.z * position.z;
      position.y -= WORLD_RADIUS - sqrt(max(1.0 - distanceSquared / WORLD_RADIUS_SQUARED, 0.0)) * WORLD_RADIUS;
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

