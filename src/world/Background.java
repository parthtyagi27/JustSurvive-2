package world;

import core.Main;
import engine.*;
import org.joml.Vector3f;

public class Background extends Entity
{
    public Background()
    {
        super();
        camera = Main.camera;
        shader = Shader.backgroundShader;

        float[] verticies =
                {
                        0, Main.HEIGHT, 0,
                        Main.WIDTH, Main.HEIGHT, 0,
                        Main.WIDTH, 0, 0,
                        0,0,0
                };
        mesh = new Mesh(verticies, TextureAtlas.getBackgroundTexture());
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
    {

    }
}
