package engine;

public class TextureAtlas
{
//  Allows for objects to extract texture coordinates from a single texture atlas (more efficient)
    public static Texture texture;

    public static final float atlasWidth = 512f, atlasHeight = 512f;
    public static final float bgWidth = 100f, bgHeight = 100f, bgStartX = 0f;
    public static final float bulletWidth = 2f, bulletHeight = 2f, bulletStartX = 110f, bulletStartY = 0f;
    public static final float groundWidth = 10f, groundHeight = 10f, groundStartX = 100f, groundStartY = 10f;
    public static final float grassWidth = 10f, grassHeight = 6f, grassStartX = 100f, grassStartY = 20f;
    public static final float playerWidth = 10f, playerHeight = 10f, playerStartX0 = 100f, playerStartY0 = 66f, playerStartY1 = 76f, playerStartY2 = 86f;

    public static void loadTextureAtlas(String file)
    {
        texture = new Texture(file);
    }

    public Texture getTexture()
    {
        return texture;
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

    public static float[] getPlayerTexture0()
    {
        return new float[]
                {
                        ((playerStartX0)/atlasWidth), ((playerStartY0)/atlasHeight),
                        ((playerStartX0 + playerWidth)/atlasWidth), ((playerStartY0)/atlasHeight),
                        ((playerStartX0 + playerWidth)/atlasWidth), ((playerStartY0 + playerHeight)/atlasHeight),
                        ((playerStartX0)/atlasWidth), ((playerStartY0 + playerHeight)/atlasHeight)
                };
    }

    public static float[] getPlayerTexture1()
    {
        return new float[]
                {
                        ((playerStartX0)/atlasWidth), ((playerStartY1)/atlasHeight),
                        ((playerStartX0 + playerWidth)/atlasWidth), ((playerStartY1)/atlasHeight),
                        ((playerStartX0 + playerWidth)/atlasWidth), ((playerStartY1 + playerHeight)/atlasHeight),
                        ((playerStartX0)/atlasWidth), ((playerStartY1 + playerHeight)/atlasHeight)
                };
    }

    public static float[] getPlayerTexture2()
    {
        return new float[]
                {
                        ((playerStartX0)/atlasWidth), ((playerStartY2)/atlasHeight),
                        ((playerStartX0 + playerWidth)/atlasWidth), ((playerStartY2)/atlasHeight),
                        ((playerStartX0 + playerWidth)/atlasWidth), ((playerStartY2 + playerHeight)/atlasHeight),
                        ((playerStartX0)/atlasWidth), ((playerStartY2 + playerHeight)/atlasHeight)
                };
    }

    public static float[] getPlayerTexture(int animationIndex)
    {
        return new float[]
                {
                        ((playerStartX0)/atlasWidth), ((playerStartY0 + 10 * animationIndex)/atlasHeight),
                        ((playerStartX0 + playerWidth)/atlasWidth), ((playerStartY0 + 10 * animationIndex)/atlasHeight),
                        ((playerStartX0 + playerWidth)/atlasWidth), ((playerStartY0 + playerHeight + 10 * animationIndex)/atlasHeight),
                        ((playerStartX0)/atlasWidth), ((playerStartY0 + playerHeight + 10 * animationIndex)/atlasHeight)
                };
    }

    public static float[] getBulletTexture()
    {
        return new float[]
                {
                        ((bulletStartX)/atlasWidth), ((bulletStartY)/atlasHeight),
                        ((bulletStartX + bulletWidth)/atlasWidth), ((bulletStartY)/atlasHeight),
                        ((bulletStartX + bulletWidth)/atlasWidth), ((bulletStartY + bulletHeight)/atlasHeight),
                        ((bulletStartX)/atlasWidth), ((bulletStartY + bulletHeight)/atlasHeight)
                };
    }
}
