package engine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import java.util.ArrayList;


public class Renderer
{
//  Renders all the models and arbitrary rectangles for testing purposes
    public static void drawMesh(Mesh mesh)
    {
        GL20.glEnableVertexAttribArray(0);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, mesh.getVertexID());
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);

        if(mesh.isUsingTexture())
        {
            GL20.glEnableVertexAttribArray(1);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, mesh.getTextureID());
            GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 0, 0);
        }

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIndexID());

        GL15.glDrawElements(GL15.GL_TRIANGLES, mesh.getVertexCount(), GL15.GL_UNSIGNED_INT, 0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        GL20.glDisableVertexAttribArray(0);
        if(mesh.isUsingTexture())
            GL20.glDisableVertexAttribArray(1);
    }

    public static void drawBatch(Entity[] entityArray, Shader entityShader, Camera camera, float scale)
    {
        entityShader.bind();
        entityShader.setUniform("sampler", 0);
        entityShader.setUniform("projection", camera.getProjectionMatrix());

        for(Entity entity : entityArray)
        {
            entityShader.setUniform("model", Transformation.createTransformation(entity.positionVector).scale(scale));
            Renderer.drawMesh(entity.getMesh());
        }
        entityShader.unbind();
    }

    public static void drawEntities(Entity[] entityArray, float scale)
    {
        entityArray[0].shader.bind();
        entityArray[0].shader.setUniform("sampler", 0);
        entityArray[0].shader.setUniform("projection", entityArray[0].camera.getProjectionMatrix());

        Mesh mesh = entityArray[0].getMesh();

        GL20.glEnableVertexAttribArray(0);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, mesh.getVertexID());
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);

        if(mesh.isUsingTexture())
        {
            GL20.glEnableVertexAttribArray(1);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, mesh.getTextureID());
            GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 0, 0);
        }

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIndexID());
        for(int i = 0; i < entityArray.length; i++)
        {
            entityArray[0].shader.setUniform("model", Transformation.createTransformation(entityArray[i].positionVector).scale(scale));
            GL15.glDrawElements(GL15.GL_TRIANGLES, mesh.getVertexCount(), GL15.GL_UNSIGNED_INT, 0);
        }

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        GL20.glDisableVertexAttribArray(0);
        if(mesh.isUsingTexture())
            GL20.glDisableVertexAttribArray(1);
    }

    public static void drawEntities(ArrayList<Entity> entityArrayList)
    {
        if(entityArrayList.isEmpty())
            return;
        entityArrayList.get(0).shader.bind();
        entityArrayList.get(0).getTexture().bind();
        entityArrayList.get(0).shader.setUniform("projection", entityArrayList.get(0).camera.getProjectionMatrix());

        Mesh mesh = entityArrayList.get(0).getMesh();

        GL20.glEnableVertexAttribArray(0);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, mesh.getVertexID());
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);

        if(mesh.isUsingTexture())
        {
            GL20.glEnableVertexAttribArray(1);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, mesh.getTextureID());
            GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 0, 0);
        }

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIndexID());
        for(int i = 0; i < entityArrayList.size(); i++)
        {
            entityArrayList.get(0).shader.setUniform("sampler", entityArrayList.get(i).samplerIndex);
            if(entityArrayList.get(i).usingModelMatrix())
            {
                entityArrayList.get(0).shader.setUniform("model", Transformation.createTransformation(entityArrayList.get(i).positionVector).scale(entityArrayList.get(i).scale));
            }
            GL15.glDrawElements(GL15.GL_TRIANGLES, mesh.getVertexCount(), GL15.GL_UNSIGNED_INT, 0);
        }

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        GL20.glDisableVertexAttribArray(0);
        if(mesh.isUsingTexture())
            GL20.glDisableVertexAttribArray(1);
        entityArrayList.get(0).shader.unbind();
    }


    public static void drawRectangle(float x1, float x2, float y1, float y2)
    {
        GL11.glRectf(x1, y1, x2, y2);
    }
}
