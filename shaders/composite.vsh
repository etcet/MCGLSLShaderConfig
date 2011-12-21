#version 120

//#define BUMP_MAPPING

varying vec4 texcoord;

#ifdef BUMP_MAPPING
uniform vec3 sunPosition;
uniform vec3 moonPosition;

uniform int worldTime;

varying vec3 lightVector;
varying vec3 specMultiplier;

uniform int heldItemId;
uniform int heldBlockLightValue;

varying vec3 heldLightSpecMultiplier;
varying float heldLightMagnitude;
#endif

void main() {
	gl_Position = ftransform();
	
	texcoord = gl_MultiTexCoord0;

  #ifdef BUMP_MAPPING
	if (worldTime < 12000 || worldTime > 23250) {
		lightVector = normalize(sunPosition);
		specMultiplier = vec3(1.0, 1.0, 1.0);
	} else {
		lightVector = normalize(moonPosition);
		specMultiplier = vec3(0.5, 0.5, 0.5);
	}
	
	specMultiplier *= clamp(abs(float(worldTime) / 500.0 - 46.0), 0.0, 1.0) * clamp(abs(float(worldTime) / 500.0 - 24.5), 0.0, 1.0);
	
	heldLightMagnitude = float(heldBlockLightValue);

	if (heldItemId == 50) {
		// torch
		heldLightSpecMultiplier = vec3(1.0, 0.9, 0.5);
	} else if (heldItemId == 76 || heldItemId == 94) {
		// active redstone torch / redstone repeater
		heldLightSpecMultiplier = vec3(1.0, 0.0, 0.0);
	} else if (heldItemId == 89) {
		// lightstone
		heldLightSpecMultiplier = vec3(1.0, 1.0, 0.4);
	} else if (heldItemId == 10 || heldItemId == 11 || heldItemId == 51) {
		// lava / lava / fire
		heldLightSpecMultiplier = vec3(1.0, 0.5, 0.0);
	} else if (heldItemId == 91) {
		// jack-o-lantern
		heldLightSpecMultiplier = vec3(1.0, 0.5, 0.0);
	} else if (heldItemId == 326) {
		// water bucket
		heldLightMagnitude = 2.0;
		heldLightSpecMultiplier = vec3(0.0, 0.0, 0.3);
	} else if (heldItemId == 327) {
		// lava bucket
		heldLightMagnitude = 15.0;
		heldLightSpecMultiplier = vec3(1.0, 0.5, 0.3);
	} else {
		heldLightSpecMultiplier = vec3(0.0);
	}
  #endif
}
