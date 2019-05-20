package ui;

import core.Main;
import engine.*;
import entities.Player;

public class HealthBar extends Entity
{
    private static Mesh mesh;
    public static final float WIDTH = 200f, HEIGHT = 20f;
    private float xScale = 1f;

    public HealthBar(float x, float y)
    {
        super();
        camera = Main.camera;
        shader = Shader.healthBarShader;

        float[] vertices =
                {
                        0, HEIGHT, 0, //0
                        WIDTH, HEIGHT, 0,//1
                        WIDTH, 0, 0,//2
//                        WIDTH, 0, 0,//2
                        0,0,0,//3
//                        0, HEIGHT, 0, //0

                };
        mesh = new Mesh(vertices);
        positionVector.x = x;
        positionVector.y = y;
        System.out.println(mesh.getVertexCount());
    }

    @Override
    public void render()
    {
        shader.bind();
        shader.setUniform("sampler", 0);
        shader.setUniform("projection", camera.getProjectionMatrix());
        shader.setUniform("model", Transformation.createTransformation(positionVector).scaleXY(xScale, 1));
        Renderer.drawMesh(mesh);
        shader.unbind();
    }

    @Override
    public void update()
    {

    }

    public void update(Player player)
    {
        if(player.health >= 0)
            xScale = (player.health / 100f);
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
        return null;
    }
}
