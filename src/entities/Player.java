package entities;

import core.Main;
import engine.*;
import world.Ground;
import world.Level;

import java.util.Random;

public class Player extends Entity
{
    public static  float WIDTH, HEIGHT;

    public int animationIndex = 0;

    private static Mesh mesh;
    private static Mesh[] meshes = new Mesh[3];
    public static float XPOSITION;
    private static boolean facingLeft = true;
    public float health = 100;

    public Player()
    {
        super();

        scale = 3;
        XPOSITION = (Main.WIDTH - WIDTH)/2;
        WIDTH = TextureAtlas.playerWidth * scale;
        HEIGHT = TextureAtlas.playerHeight * scale;

        camera  = Main.camera;
        shader = Shader.playerShader;

        float[] vertices =
                {
                        -WIDTH/2, HEIGHT/2, 0,
                        WIDTH/2, HEIGHT/2, 0,
                        WIDTH/2, -HEIGHT/2, 0,
                        -WIDTH/2, -HEIGHT/2, 0
                };

        mesh = new Mesh(vertices, TextureAtlas.getPlayerTexture(animationIndex));
        positionVector.x = XPOSITION;
        positionVector.y = Ground.HEIGHT + HEIGHT * 1.5f;

        meshes[0] = mesh;
        meshes[1] = new Mesh(vertices, TextureAtlas.getPlayerTexture1());
        meshes[2] = new Mesh(vertices, TextureAtlas.getPlayerTexture2());

        facingLeft = new Random().nextBoolean();
        System.out.println("Player facing left = " + facingLeft);
    }
    @Override
    public void render()
    {
        shader.bind();
        shader.setUniform("sampler", 0);
        shader.setUniform("projection", camera.getProjectionMatrix());
        if(facingLeft)
            shader.setUniform("model", Transformation.createTransformation(positionVector).scale(scale).reflect(0, 0, 0, 0));
        else
            shader.setUniform("model", Transformation.createTransformation(positionVector).scale(scale).reflect(1, 0, 0, 0));

        Renderer.drawMesh(mesh);
        shader.unbind();
    }

    @Override
    public void update()
    {
        if(Level.deltaGroundMovement % 5*10 == 0 && Ground.isMoving)
        {
            animationIndex++;

            if(animationIndex >= meshes.length)
                animationIndex = 0;

            mesh = meshes[animationIndex];
        } else if(!Ground.isMoving)
        {
            mesh = meshes[0];
            animationIndex = 0;
        }
    }

    public static void setFacingLeft()
    {
        facingLeft = true;
    }

    public static void setFacingRight()
    {
        facingLeft = false;
    }

    @Override
    public Texture getTexture()
    {
        return TextureAtlas.texture;
    }

    @Override
    public Mesh getMesh()
    {
        return mesh;
    }

    @Override
    public boolean usingModelMatrix()
    {
        return true;
    }

    public static boolean isFacingLeft()
    {
        return facingLeft;
    }
}
