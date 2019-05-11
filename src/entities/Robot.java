package entities;

import core.Main;
import engine.*;
import world.Ground;

import java.util.Random;

public class Robot extends Entity
{
    public static float WIDTH, HEIGHT;
    private static Mesh[] meshes = new Mesh[3];
    private int meshIndex = 0;
    private boolean isLeft, reflected;
    private static Random random = new Random();

    public Robot()
    {
        super();
        camera = Main.camera;
        shader = Shader.grassShader;
        scale = 3f;
        WIDTH = TextureAtlas.robotWidth * scale;
        HEIGHT = TextureAtlas.robotHeight * scale;

        float[] vertices =
                {
                        -WIDTH/2, HEIGHT/2, 0,
                        WIDTH/2, HEIGHT/2, 0,
                        WIDTH/2, -HEIGHT/2, 0,
                        -WIDTH/2, -HEIGHT/2, 0
                };

        for(int i = 0; i < meshes.length; i++)
        {
            meshes[i] = new Mesh(vertices, TextureAtlas.getRobotTexture(i));
        }

        isLeft = random.nextBoolean();

        if(isLeft)
        {
            reflected = true;
            positionVector.x = -(random.nextInt(100) + 50);
        }else
        {
            reflected = false;
            positionVector.x = random.nextInt(100) + Main.WIDTH;
        }
        positionVector.y = Ground.HEIGHT + HEIGHT * 1.5f;
    }
    @Override
    public void render()
    {
        shader.bind();
        shader.setUniform("sampler", 0);
        shader.setUniform("projection", camera.getProjectionMatrix());
        if(isLeft)
            shader.setUniform("model", Transformation.createTransformation(positionVector).scale(scale).reflect(1, 0, 0, 0));
        else
            shader.setUniform("model", Transformation.createTransformation(positionVector).scale(scale).reflect(0, 0, 0, 0));

        Renderer.drawMesh(meshes[meshIndex]);
        shader.unbind();
    }

    @Override
    public void update()
    {
        if(isLeft)
            positionVector.x += 2f;
        else
            positionVector.x -= 2f;
    }

    @Override
    public boolean usingModelMatrix()
    {
        return true;
    }

    @Override
    public Mesh getMesh()
    {
        return null;
    }

    @Override
    public Texture getTexture()
    {
        return null;
    }
}
