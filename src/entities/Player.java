package entities;

import core.Main;
import engine.*;
import world.Ground;

public class Player extends Entity
{
    public static  float WIDTH, HEIGHT;

    public Player()
    {
        super();

        scale = 3;
        WIDTH = TextureAtlas.playerWidth * scale;
        HEIGHT = TextureAtlas.playerHeight * scale;

        camera  = Main.camera;
        shader = Shader.playerShader;

        float[] vertices =
                {
                        0, HEIGHT, 0,
                        WIDTH, HEIGHT, 0,
                        WIDTH, 0, 0,
                        0, 0, 0
                };

        mesh = new Mesh(vertices, TextureAtlas.getPlayerTexture());
        positionVector.x = (Main.WIDTH - WIDTH)/2;
        positionVector.y = Ground.HEIGHT;
    }
    @Override
    public void render()
    {
        shader.bind();
        shader.setUniform("sampler", 0);
        shader.setUniform("projection", camera.getProjectionMatrix());
        shader.setUniform("model", Transformation.createTransformation(positionVector).scale(scale));
        Renderer.drawMesh(mesh);
        shader.unbind();
    }

    @Override
    public void update()
    {

    }

    @Override
    public boolean usingModelMatrix()
    {
        return true;
    }
}
