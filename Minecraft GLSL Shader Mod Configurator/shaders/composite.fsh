#version 120

//#define BUMP_MAPPING

uniform sampler2D gcolor;
uniform sampler2D gdepth;

#ifdef BUMP_MAPPING
uniform sampler2D gnormal;
uniform sampler2D gaux1;   // specular map

varying vec3 lightVector;
varying vec3 specMultiplier;

uniform float near;
uniform float far;

uniform mat4 gbufferProjectionInverse;

varying vec3 heldLightSpecMultiplier;
varying float heldLightMagnitude;
#endif

varying vec4 texcoord;

void main() {
	gl_FragData[0] = texture2D(gcolor, texcoord.st);
	gl_FragData[1] = texture2D(gdepth, texcoord.st);

  #ifdef BUMP_MAPPING
  gl_FragData[2] = texture2D(gnormal, texcoord.st);
	gl_FragData[4] = texture2D(gaux1, texcoord.st);
		
	vec4 fragposition = gbufferProjectionInverse * vec4(texcoord.s * 2.0 - 1.0, texcoord.t * 2.0 - 1.0, 2.0 * texture2D(gdepth, texcoord.st).x - 1.0, 1.0);
	fragposition /= fragposition.w;

	vec3 npos = normalize(fragposition.xyz);

	vec3 bump = reflect(npos, texture2D(gnormal, texcoord.st).xyz * 2.0 - 1.0);

	vec3 specularColor = texture2D(gaux1, texcoord.st).rgb;

	float s = max(dot(bump, lightVector), 0.0);
	gl_FragData[3] = vec4(min(texture2D(gcolor, texcoord.st).rgb + specularColor * s * s * s * specMultiplier, 1.0), 1.0);
	
	if (heldLightMagnitude > 0.0) {
		float distance = sqrt(fragposition.x * fragposition.x + fragposition.y * fragposition.y + fragposition.z * fragposition.z);
		if (distance < heldLightMagnitude && distance > 0.1) {
			float intensity = 1.0 - min(distance / heldLightMagnitude, 1.0);
			s = max(dot(bump, -npos), 0.0);
			gl_FragData[3].rgb = min(gl_FragData[3].rgb + intensity * specularColor * s * s * s * heldLightSpecMultiplier, 1.0);
		}
	}
  #else
	gl_FragData[3] = vec4(texture2D(gcolor, texcoord.st).rgb, 1.0);
  #endif
}
