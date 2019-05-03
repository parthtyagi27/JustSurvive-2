package world;

public class Level
{
    private Background background;

    public Level()
    {
        background = new Background();
    }

    public void render()
    {
        background.render();
    }

    public void update()
    {

    }
}
