#version 120

uniform sampler2D sampler;
uniform int red;
varying vec2 textureCoordinates;

void main()
{
    vec4 tex = texture2D(sampler, textureCoordinates);
    if(red == 1)
    {
        gl_FragColor = vec4(tex.r + 0.2, tex.g, tex.b + 0.1, tex.a);
    }else
    {
        gl_FragColor = tex;
    }
}