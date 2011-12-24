#version 120

//#define CURVY_WORLD
//#define CURVY_WORLD_RADIUS 40.0
//#define CURVY_WORLD_RADIUS_SQUARED 10000.0
//#define ACID

varying vec4 color;
varying vec4 texcoord;
varying vec4 lmcoord;
#ifdef ACID
uniform int worldTime;
#endif

void main() {
  #ifdef CURVY_WORLD
    vec4 position = gl_ModelViewMatrix * gl_Vertex;
    float distanceSquared = position.x * position.x + position.z * position.z;
    position.y -= CURVY_WORLD_RADIUS - sqrt(max(1.0 - distanceSquared / CURVY_WORLD_RADIUS_SQUARED, 0.0)) * CURVY_WORLD_RADIUS;
    gl_Position = gl_ProjectionMatrix * position;
  #else
    #ifdef ACID
      vec4 position = gl_ModelViewMatrix * gl_Vertex;
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
        gl_Position = gl_ProjectionMatrix * position;
      }
    #else
      gl_Position = ftransform();
    #endif
  #endif
	
	color = gl_Color;
	
	texcoord = gl_TextureMatrix[0] * gl_MultiTexCoord0;

	lmcoord = gl_TextureMatrix[1] * gl_MultiTexCoord1;

	gl_FogFragCoord = gl_Position.z;
}
