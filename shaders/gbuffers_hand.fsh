#version 120

uniform sampler2D texture;
uniform sampler2D lightmap;

varying vec4 color;
varying vec4 texcoord;
varying vec4 lmcoord;
varying vec3 normal;

void main() {

	gl_FragData[0] = texture2D(texture, texcoord.st) * texture2D(lightmap, lmcoord.st) * color * vec4(1.0, 1.0, 1.0, 1.0);
	//gl_FragData[1] = vec4(0.0);
	gl_FragData[1] = vec4(vec3(gl_FragCoord.z), 1.0);
	gl_FragData[2] = vec4(normal * 0.5 + 0.5, 1.0);
	gl_FragData[4] = vec4(0.0, 0.0, 0.0, 1.0);
		
}