package entities;

import core.Main;
import engine.*;
import world.Ground;

import java.util.ArrayList;
import java.util.Random;

public class Robot extends Entity
{
    public static float WIDTH, HEIGHT;
    private static Mesh[] meshes = new Mesh[3];
    private int meshIndex = 0;
    private boolean isLeft;
    private static Random random = new Random();
    private float XSPEED = 2f;
    private float deltaMovement = 0f;
    public float health = 100f;

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
            positionVector.x = -(random.nextInt(100) + 50);
        }else
        {
            positionVector.x = random.nextInt(100) + Main.WIDTH;
        }
        positionVector.y = Ground.HEIGHT + HEIGHT * 1.5f;
    }

    public static void renderRobots(ArrayList<Robot> robotArrayList)
    {
        if(robotArrayList.isEmpty())
            return;
        Robot firstRobot = robotArrayList.get(0);
        firstRobot.shader.bind();
        firstRobot.shader.setUniform("sampler", 0);
        firstRobot.shader.setUniform("projection", firstRobot.camera.getProjectionMatrix());

        for(Robot robot : robotArrayList)
        {
            if(robot.isLeft)
                firstRobot.shader.setUniform("model", Transformation.createTransformation(robot.positionVector).scale(robot.scale).reflect(1, 0, 0, 0));
            else
                firstRobot.shader.setUniform("model", Transformation.createTransformation(robot.positionVector).scale(robot.scale).reflect(0, 0, 0, 0));

            Renderer.drawMesh(robot.getMesh());
        }
        firstRobot.shader.unbind();
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
            positionVector.x += XSPEED;
        else
            positionVector.x -= XSPEED;

        deltaMovement += XSPEED;

        if(deltaMovement % 9 == 0)
        {
            deltaMovement = 0;
            meshIndex++;
        }

        if(meshIndex >= meshes.length)
            meshIndex = 0;

    }

    public void update(Player player)
    {
        update();
        if(isLeft)
        {
            //Robot is to the left of the player
            if(positionVector.x() + WIDTH/2 >= player.positionVector.x() - Player.WIDTH/2)
                XSPEED = 0;
        }else
        {
            //Robot is to the right of the player
            if(positionVector.x() - WIDTH/2 <= player.positionVector.x() + Player.WIDTH/2)
                XSPEED = 0;
        }
    }

    @Override
    public boolean usingModelMatrix()
    {
        return true;
    }

    @Override
    public Mesh getMesh()
    {
        return meshes[meshIndex];
    }

    @Override
    public Texture getTexture()
    {
        return null;
    }

    public boolean isLeft()
    {
        return isLeft;
    }
}
