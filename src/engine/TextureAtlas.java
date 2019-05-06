package engine;

public class TextureAtlas
{
//  Allows for objects to extract texture coordinates from a single texture atlas (more efficient)
    public static Texture texture;

    public static final float atlasWidth = 512f, atlasHeight = 512f;
    public static final float bgWidth = 100f, bgHeight = 100f, bgStartX = 0f;
    public static final float birdWidth = 125f, birdHeight = 88f, birdStartX = 336f, birdStartY = 42f;
    public static final float pipeWidth = 52f, pipeHeight = 320f, pipeStartX = 372f, pipeStartY = 192f;
    public static final float groundWidth = 10f, groundHeight = 10f, groundStartX = 100f, groundStartY = 10f;
    public static final float grassWidth = 10f, grassHeight = 6f, grassStartX = 100f, grassStartY = 20f;
    public static final float playerWidth = 10f, playerHeight = 10f, playerStartX = 100f, playerStartY = 56;

    public static void loadTextureAtlas(String file)
    {
        texture = new Texture(file);
    }

    public Texture getTexture()
    {
        return texture;
    }

    public static float[] getPipeTexture()
    {
        return new float[]
                {
                        ((pipeStartX)/atlasWidth), ((pipeStartY)/atlasHeight),
                        ((pipeStartX + pipeWidth)/atlasWidth), ((pipeStartY)/atlasHeight),
                        ((pipeStartX + pipeWidth)/atlasWidth), ((pipeStartY + pipeHeight)/atlasHeight),
                        ((pipeStartX)/atlasWidth), ((pipeStartY + pipeHeight)/atlasHeight)
                };
    }

    public static float[] getPipeTexture(float h)
    {
        if(h > pipeHeight)
            h = pipeHeight;
        return new float[]
                {
                        ((pipeStartX)/atlasWidth), ((pipeStartY)/atlasHeight),
                        ((pipeStartX + pipeWidth)/atlasWidth), ((pipeStartY)/atlasHeight),
                        ((pipeStartX + pipeWidth)/atlasWidth), ((pipeStartY + h)/atlasHeight),
                        ((pipeStartX)/atlasWidth), ((pipeStartY + h)/atlasHeight)
                };
    }

    public static float[] getBirdTexture()
    {
        return new float[]
                {
                    ((birdStartX)/atlasWidth), ((birdStartY)/atlasHeight),
                    ((birdStartX + birdWidth)/atlasWidth), ((birdStartY)/atlasHeight),
                    ((birdStartX + birdWidth)/atlasWidth), ((birdStartY + birdHeight)/atlasHeight),
                    ((birdStartX)/atlasWidth), ((birdStartY + birdHeight)/atlasHeight)

                };
    }

    public static float[] getBackgroundTexture()
    {
        return new float[]
                {
                        bgStartX, 0f,
                        (bgWidth/atlasWidth), 0f,
                        (bgWidth/atlasWidth), (bgHeight/ atlasHeight),
                        bgStartX, (bgHeight/ atlasHeight)
//                        0, 0,
//                        (float)(284f/512f), 0,
//                        (float)(284f/512f), 1,
//                        0, 1

//0,0,
//1,0,
//1,1,
//0,1,
                };

    }

    public static float[] getGroundTexture()
    {
        return new float[]
                {
                        ((groundStartX)/atlasWidth), ((groundStartY)/atlasHeight),
                        ((groundStartX + groundWidth)/atlasWidth), ((groundStartY)/atlasHeight),
                        ((groundStartX + groundWidth)/atlasWidth), ((groundStartY + groundHeight)/atlasHeight),
                        ((groundStartX)/atlasWidth), ((groundStartY + groundHeight)/atlasHeight)
                };
    }

    public static float[] getGrassTexture()
    {
        return new float[]
                {
                        ((grassStartX)/atlasWidth), ((grassStartY)/atlasHeight),
                        ((grassStartX + grassWidth)/atlasWidth), ((grassStartY)/atlasHeight),
                        ((grassStartX + grassWidth)/atlasWidth), ((grassStartY + grassHeight)/atlasHeight),
                        ((grassStartX)/atlasWidth), ((grassStartY + grassHeight)/atlasHeight)
                };
    }

    public static float[] getPlayerTexture()
    {
        return new float[]
                {
                        ((playerStartX)/atlasWidth), ((playerStartY)/atlasHeight),
                        ((playerStartX + playerWidth)/atlasWidth), ((playerStartY)/atlasHeight),
                        ((playerStartX + playerWidth)/atlasWidth), ((playerStartY + playerHeight)/atlasHeight),
                        ((playerStartX)/atlasWidth), ((playerStartY + playerHeight)/atlasHeight)
                };
    }
}
