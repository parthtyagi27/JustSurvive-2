package world;

import core.Main;
import engine.*;
import org.joml.Vector3f;

public class Background extends Entity
{
    private static Mesh mesh;

    @Override
    public Mesh getMesh()
    {
        return mesh;
    }

    @Override
    public boolean usingModelMatrix()
    {
        return false;
    }

    public Background()
    {
        super();
        camera = Main.camera;
        shader = Shader.backgroundShader;

        float[] vertices =
                {
                        0, Main.HEIGHT, 0,
                        Main.WIDTH, Main.HEIGHT, 0,
                        Main.WIDTH, 0, 0,
                        0,0,0
                };
        mesh = new Mesh(vertices, TextureAtlas.getBackgroundTexture());
        positionVector = new Vector3f();
    }
    @Override
    public void render()
    {
        shader.bind();
        shader.setUniform("sampler", 0);
        shader.setUniform("projection", camera.getProjectionMatrix());
        Renderer.drawMesh(mesh);
        shader.unbind();
    }

    @Override
    public void update()
    {}

    @Override
    public Texture getTexture()
    {
        return TextureAtlas.texture;
    }
}
