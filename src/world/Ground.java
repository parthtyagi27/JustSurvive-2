package world;

import core.Main;
import engine.*;

public class Ground extends Entity
{
    public static final float HEIGHT = 200f;
    public Ground()
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
        positionVector.x = 0;
        positionVector.y = 0;
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
    }

    @Override
    public void update()
    {

    }
}
