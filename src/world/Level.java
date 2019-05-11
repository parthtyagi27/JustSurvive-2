package world;

import core.Main;
import engine.Entity;
import engine.EntityBatch;
import engine.Handler;
import engine.Renderer;
import entities.Bullet;
import entities.Player;
import entities.Robot;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

public class Level
{
    private static final float deltaBulletDistance = 25f;
    private static Ground[] grounds = new Ground[3];
    private static ArrayList<Entity> bulletList;

    private Background background;
    private Player player;
    private EntityBatch entityBatch;

    public static float deltaGroundMovement = 0;
    private static float bulletLeftThreshold = 0, bulletRightThreshold = 0;

    private Robot robot;

    public Level()
    {
        entityBatch = new EntityBatch(6);
        background = new Background();
        bulletList = new ArrayList<Entity>();

        for(int i = 0; i < grounds.length; i++)
        {
            grounds[i] = new Ground(-Main.WIDTH + (i * Main.WIDTH));
            System.out.println("Ground at: " + i + ", grassCount = " + grounds[i].grassCount);
        }
        grounds[0].positionVector.x = -Main.WIDTH;
        grounds[2].positionVector.x = Main.WIDTH;

        player = new Player();

        entityBatch.addEntity(background);
        entityBatch.addEntities(grounds);
        for(Ground ground : grounds)
            entityBatch.addEntities(ground.getGrassArray());

        bulletLeftThreshold = Player.XPOSITION - deltaBulletDistance - Bullet.gunOffset;
        bulletRightThreshold = Player.XPOSITION + Bullet.gunOffset + deltaBulletDistance + Bullet.WIDTH;

        robot = new Robot();
    }

    public void render()
    {
        entityBatch.render();
        player.render();
        Renderer.drawEntities(bulletList);
        robot.render();
    }

    public void update()
    {
        for(Ground ground : grounds)
            ground.update();

        if(Handler.isKeyDown(GLFW.GLFW_KEY_SPACE))
        {
            if(bulletList.isEmpty())
            {
                Bullet bullet = new Bullet(Player.isFacingLeft());
                bulletList.add(bullet);
            }else
            {
                Entity previousBullet = bulletList.get(bulletList.size() - 1);
                if(previousBullet.positionVector.x() <= bulletLeftThreshold || previousBullet.positionVector.x() >= bulletRightThreshold)
                    bulletList.add(new Bullet(Player.isFacingLeft()));
            }
        }

        for(int i = 0; i < bulletList.size(); i++)
        {
            if(bulletList.get(i).positionVector.x() <= -Bullet.WIDTH * 5 || bulletList.get(i).positionVector.x() >= (Main.WIDTH + Bullet.WIDTH) * 5)
                bulletList.remove(i);
            else
                bulletList.get(i).update();
        }
        player.update();

        robot.update();
    }
}
