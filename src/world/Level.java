package world;

import core.Main;

public class Level
{
    private static Ground[] grounds = new Ground[3];
    private Background background;

    public Level()
    {
        background = new Background();

        for(int i = 0; i < grounds.length; i++)
        {
            grounds[i] = new Ground(-Main.WIDTH + (i * Main.WIDTH));
            System.out.println("Ground at: " + i + ", grassCount = " + grounds[i].grassCount);
        }
        grounds[0].positionVector.x = -Main.WIDTH;
        grounds[2].positionVector.x = Main.WIDTH;

    }

    public void render()
    {
        background.render();
        for(Ground ground : grounds)
            ground.render();
    }

    public void update()
    {
        for(Ground ground : grounds)
            ground.update();
    }
}
