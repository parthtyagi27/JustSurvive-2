#version 120

attribute vec3 vertices;
attribute vec2 textures;

varying vec2 textureCoordinates;

uniform mat4 projection;
uniform mat4 model;

void main()
{
    textureCoordinates = textures;
    gl_Position = projection * model * vec4(vertices,1);
}