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
//    private static Texture[] animationTexture = new Texture[3];
    private static boolean facingLeft = true;

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
//                        0, HEIGHT, 0,
//                        WIDTH, HEIGHT, 0,
//                        WIDTH, 0, 0,
//                        0, 0, 0
                        -WIDTH/2, HEIGHT/2, 0,
                        WIDTH/2, HEIGHT/2, 0,
                        WIDTH/2, -HEIGHT/2, 0,
                        -WIDTH/2, -HEIGHT/2, 0
                };

        mesh = new Mesh(vertices, TextureAtlas.getPlayerTexture(animationIndex));
        positionVector.x = (Main.WIDTH - WIDTH)/2;
        positionVector.y = Ground.HEIGHT + HEIGHT * 1.5f;
//        animationTexture[0] = new Texture("/resources/survivor_idle.png");
//        animationTexture[1] = new Texture("/resources/survivor_runningF2Left.png");
//        animationTexture[2] = new Texture("/resources/survivor_runningF3Left.png");
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
//        TODO: Edit Player Texture coordinate and make sure they are bound before rendering!
//        animationTexture[animationIndex].bind();
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
        System.out.println("Player facing left = " + facingLeft);
    }

    public static void setFacingRight()
    {
        facingLeft = false;
        System.out.println("Player facing left = " + facingLeft);
    }

    @Override
    public Texture getTexture()
    {
//        return animationTexture[animationIndex];
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
}
