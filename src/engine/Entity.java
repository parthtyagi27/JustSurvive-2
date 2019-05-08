package engine;

import org.joml.Vector3f;

public abstract class Entity
{
    //Parent class of all entity objects
    public Vector3f positionVector;
    public Vector3f rotationVector;
//    public Mesh mesh;
    public Camera camera;
    public Shader shader;
    public float scale = 1;
    public int samplerIndex = 0;

    public Entity()
    {
        positionVector = new Vector3f();
        rotationVector = new Vector3f();
    }

    public abstract void render();
    public abstract void update();
    public abstract boolean usingModelMatrix();
    public abstract Mesh getMesh();
    public abstract Texture getTexture();
}
