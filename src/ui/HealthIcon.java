package ui;

import core.Main;
import engine.*;

public class HealthIcon extends Entity
{
    private static Mesh mesh;
    public static float WIDTH = TextureAtlas.healthBarWidth * 3, HEIGHT = TextureAtlas.healthBarHeight * 3;
    private static float[] vertices =
            {
                    0, HEIGHT, 0,
                    WIDTH, HEIGHT, 0,
                    WIDTH, 0, 0,
                    0,0,0
            };
    public HealthIcon(float x, float y)
    {
        super();
        camera = Main.camera;
        shader = Shader.playerShader;
//        scale = 2;
        float[] vertices =
                {
                        0, HEIGHT, 0,
                        WIDTH, HEIGHT, 0,
                        WIDTH, 0, 0,
                        0,0,0
                };
        mesh = new Mesh(vertices, TextureAtlas.getHealthBarTexture());
        positionVector.x = x;
        positionVector.y = y;


    }

    @Override
    public void render()
    {

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
