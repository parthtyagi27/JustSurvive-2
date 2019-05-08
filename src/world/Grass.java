package world;

import core.Main;
import engine.*;
import org.lwjgl.glfw.GLFW;

import java.util.Random;

public class Grass extends Entity
{

    private static final float WIDTH = TextureAtlas.grassWidth, HEIGHT = TextureAtlas.grassHeight;
    private static Mesh mesh;

    @Override
    public boolean usingModelMatrix()
    {
        return true;
    }

    public Grass(float groundXPosition)
    {
        super();
        camera = Main.camera;
        shader = Shader.grassShader;
        float[] vertices =
                {
                        0, HEIGHT, 0,
                        WIDTH, HEIGHT, 0,
                        WIDTH, 0, 0,
                        0, 0, 0
                };


        mesh = new Mesh(vertices, TextureAtlas.getGrassTexture());

        positionVector.x = new Random().nextInt((int)((Main.WIDTH + groundXPosition - WIDTH*4) + 1 - groundXPosition)) + groundXPosition;
        positionVector.y = Ground.HEIGHT;

        scale  = 4;
    }

    @Override
    public void render()
    {
        shader.bind();
        shader.setUniform("sampler", 0);
        shader.setUniform("projection", camera.getProjectionMatrix());
        shader.setUniform("model", Transformation.createTransformation(positionVector).scale(4));
        Renderer.drawMesh(mesh);
        shader.unbind();
    }

    @Override
    public void update()
    {
        if(Handler.isKeyDown(GLFW.GLFW_KEY_A))
            positionVector.x += Ground.xSPEED;
        else if(Handler.isKeyDown(GLFW.GLFW_KEY_D))
            positionVector.x -= Ground.xSPEED;
    }

    @Override
    public Mesh getMesh()
    {
        return mesh;
    }

    @Override
    public Texture getTexture()
    {
        return TextureAtlas.texture;
    }
}
