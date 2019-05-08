package entities;

import core.Main;
import engine.*;
import world.Ground;
import world.Level;

public class Player extends Entity
{
    public static  float WIDTH, HEIGHT;

    public int animationIndex = 0;

    private static Mesh mesh;
    private static Mesh[] meshes = new Mesh[3];
//    private static Texture[] animationTexture = new Texture[3];

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
                        0, HEIGHT, 0,
                        WIDTH, HEIGHT, 0,
                        WIDTH, 0, 0,
                        0, 0, 0
                };

        mesh = new Mesh(vertices, TextureAtlas.getPlayerTexture(animationIndex));
        positionVector.x = (Main.WIDTH - WIDTH)/2;
        positionVector.y = Ground.HEIGHT;
//        animationTexture[0] = new Texture("/resources/survivor_idle.png");
//        animationTexture[1] = new Texture("/resources/survivor_runningF2Left.png");
//        animationTexture[2] = new Texture("/resources/survivor_runningF3Left.png");
        meshes[0] = mesh;
        meshes[1] = new Mesh(vertices, TextureAtlas.getPlayerTexture1());
        meshes[2] = new Mesh(vertices, TextureAtlas.getPlayerTexture2());
    }
    @Override
    public void render()
    {
        shader.bind();
//        TODO: Edit Player Texture coordinate and make sure they are bound before rendering!
//        animationTexture[animationIndex].bind();
        shader.setUniform("sampler", 0);
        shader.setUniform("projection", camera.getProjectionMatrix());
        shader.setUniform("model", Transformation.createTransformation(positionVector).scale(scale));
        Renderer.drawMesh(mesh);
        shader.unbind();
    }

    @Override
    public void update()
    {
        if(Level.deltaGroundMovement % 5*10 == 0 && Ground.isMoving)
        {
//            animate();
            if(animationIndex >= meshes.length)
                animationIndex = 0;
            else
                animationIndex++;
            mesh = meshes[animationIndex];
        } else if(!Ground.isMoving)
        {
            mesh = meshes[0];
            animationIndex = 0;
        }
    }

    @Override
    public Texture getTexture()
    {
//        return animationTexture[animationIndex];
        return TextureAtlas.texture;
    }

    public void animate()
    {
        if(animationIndex < meshes.length)
            animationIndex++;
        else
            animationIndex = 0;

        samplerIndex = animationIndex;
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
