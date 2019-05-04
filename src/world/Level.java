package world;

import core.Main;

public class Level
{
    private static Background[] backgrounds = new Background[3];
    private Ground ground;

    public Level()
    {
        for(int i = 0; i < backgrounds.length; i++)
            backgrounds[i] = new Background();
        backgrounds[0].positionVector.x = -Main.WIDTH;
        backgrounds[2].positionVector.x = Main.WIDTH;

        ground = new Ground();
    }

    public void render()
    {
        for(Background background : backgrounds)
            background.render();
        ground.render();
    }

    public void update()
    {
        for(Background background : backgrounds)
            background.update();
    }
}
