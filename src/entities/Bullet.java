package entities;

import core.Main;
import engine.*;
import world.Ground;

public class Bullet extends Entity
{
    private static Mesh mesh;
    public static float gunOffset = 40f;
    public static float WIDTH, HEIGHT;
    private float xSpeed = 0f;
    private static final float SPEED = 2f;
    private boolean facingLeft;

    public Bullet(boolean facingLeft)
    {
        super();
        scale = 2;
        WIDTH = TextureAtlas.bulletWidth * scale;
        HEIGHT = TextureAtlas.bulletHeight * scale;

        float[] vertices =
                {
                        0, HEIGHT, 0,
                        WIDTH, HEIGHT, 0,
                        WIDTH, 0, 0,
                        0,0,0
                };

        camera = Main.camera;
        shader = Shader.grassShader;

        mesh = new Mesh(vertices, TextureAtlas.getBulletTexture());
        this.facingLeft = facingLeft;
        if(facingLeft)
            positionVector.x = Player.XPOSITION - gunOffset;
        else
            positionVector.x = Player.XPOSITION + gunOffset;
        positionVector.y = Ground.HEIGHT + Player.HEIGHT - 2f;

        if(facingLeft)
            xSpeed = -SPEED;
        else
            xSpeed = SPEED;
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
        positionVector.x += xSpeed;
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

    public boolean isFacingLeft()
    {
        return facingLeft;
    }
}
