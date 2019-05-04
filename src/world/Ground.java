package world;

import core.Main;
import engine.*;
import org.lwjgl.glfw.GLFW;

import java.util.Random;

public class Ground extends Entity
{
    public static final float HEIGHT = 200f;
    public static final float xSPEED = 2f;
    public int grassCount;
    private Grass[] grass;

    public Ground(float xPosition)
    {
        super();
        camera = Main.camera;
        shader = Shader.groundShader;

        float[] vertices =
                {
                        0, HEIGHT, 0,
                        Main.WIDTH, HEIGHT, 0,
                        Main.WIDTH, 0, 0,
                        0,0,0
                };

        mesh = new Mesh(vertices, TextureAtlas.getGroundTexture());
        positionVector.x = xPosition;
        positionVector.y = 0;

        grassCount = new Random().nextInt(6 - 2) + 2;
        grass = new Grass[grassCount];
        for(int i = 0; i < grass.length; i++)
        {
            grass[i] = new Grass(positionVector.x());
        }
    }
    @Override
    public void render()
    {
        shader.bind();
        shader.setUniform("sampler", 0);
        shader.setUniform("projection", camera.getProjectionMatrix());
        shader.setUniform("model", Transformation.createTransformation(positionVector));
        Renderer.drawMesh(mesh);
        shader.unbind();

        for(Grass g : grass)
            g.render();
    }

    @Override
    public void update()
    {
        if(Handler.isKeyDown(GLFW.GLFW_KEY_A))
            positionVector.x += xSPEED;
        else if(Handler.isKeyDown(GLFW.GLFW_KEY_D))
            positionVector.x -= xSPEED;

        for(Grass g : grass)
            g.update();
    }
}
