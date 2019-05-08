package world;

import core.Main;
import engine.Entity;
import engine.EntityBatch;
import entities.Player;

import java.util.ArrayList;

public class Level
{
    private static Ground[] grounds = new Ground[3];
    private Background background;
    private Player player;
    private EntityBatch entityBatch;

    public static float deltaGroundMovement = 0;

    public Level()
    {
        entityBatch = new EntityBatch(5);
        background = new Background();

        for(int i = 0; i < grounds.length; i++)
        {
            grounds[i] = new Ground(-Main.WIDTH + (i * Main.WIDTH));
//            entityBatch.addEntities(grounds[i].getGrassArray());
            System.out.println("Ground at: " + i + ", grassCount = " + grounds[i].grassCount);
        }
        grounds[0].positionVector.x = -Main.WIDTH;
        grounds[2].positionVector.x = Main.WIDTH;

        player = new Player();

        entityBatch.addEntity(background);
        entityBatch.addEntities(grounds);
        for(Ground ground : grounds)
            entityBatch.addEntities(ground.getGrassArray());
    }

    public void render()
    {
//        background.render();
//        for(Ground ground : grounds)
//            ground.render();
//        player.render();
        entityBatch.render();
        player.render();
    }

    public void update()
    {
        for(Ground ground : grounds)
            ground.update();

        if(deltaGroundMovement % 9 == 0 && deltaGroundMovement != 0)
        {
            player.animate();
        }
        player.update();
    }
}
