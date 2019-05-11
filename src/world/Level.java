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
    private static final float maxRobots = 10;

    private ArrayList<Robot> robots;

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

        robots = new ArrayList<Robot>();
    }

    public void render()
    {
        entityBatch.render();
        player.render();
        Renderer.drawEntities(bulletList);
        Robot.renderRobots(robots);
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

            for(Robot robot : robots)
            {
                if(bulletList.isEmpty() || robots.isEmpty())
                {
                    return;
                }else
                {
                    Entity bullet = bulletList.get(i);
                    if (robot.isLeft() && bullet.positionVector.x() <= robot.positionVector.x() + Robot.WIDTH / 2)
                    {
                        robot.health -= Bullet.damage;
                        bulletList.remove(i);
                    } else if (!robot.isLeft() && bullet.positionVector.x() + Bullet.WIDTH >= robot.positionVector.x() - Robot.WIDTH / 2)
                    {
                        robot.health -= Bullet.damage;
                        bulletList.remove(i);
                    }
                }
            }
        }
        player.update();

        for(int i = 0; i < robots.size(); i++)
        {
            if(robots.get(i).health <= 0)
                robots.remove(i);
            else
                robots.get(i).update(player);
        }
    }

    public void perSecondUpdates()
    {
        if(robots.size() <= maxRobots)
            robots.add(new Robot());
    }
}
