package engine;

import java.util.*;

public class EntityBatch
{
    private Map<Entity, ArrayList<Entity>> entityMap;
    private int renderingIndex;
    private Entity[] renderingOrder;

    public EntityBatch(int batchSize)
    {
        entityMap = new HashMap<Entity, ArrayList<Entity>>();
        renderingOrder = new Entity[batchSize];
        renderingIndex = 0;
    }

    public void addEntity(Entity entity)
    {
        if(!entityMap.containsKey(entity))
        {
            //create new arraylist and add it to the map
            ArrayList<Entity> arrayList = new ArrayList<Entity>();
            arrayList.add(entity);
            entityMap.put(entity, arrayList);
            indexEntity(entity);
        }
        else
        {
            //Add entity to the pre-existing array list
            entityMap.get(entity).add(entity);
        }
    }

    public void addEntities(Entity[] entities)
    {
        if(!entityMap.containsKey(entities[0]))
        {
            //create new arraylist and add it to the map
            ArrayList<Entity> arrayList = new ArrayList<Entity>(Arrays.asList(entities));
            entityMap.put(entities[0], arrayList);
            indexEntity(entities[0]);
        }
        else
        {
            //Add entity to the pre-existing array list
            for(Entity e: entities)
                entityMap.get(entities[0]).add(e);
        }
    }

    public void addEntities(ArrayList<Entity> entities)
    {
        if(!entityMap.containsKey(entities.get(0)))
        {
            //create new arraylist and add it to the map
            entityMap.put(entities.get(0), entities);
            indexEntity(entities.get(0));
        }
        else
        {
            //Add entity to the pre-existing array list
            for(Entity e: entities)
                entityMap.get(entities.get(0)).add(e);
        }
    }

    public void render()
    {
        for(Entity entity : renderingOrder)
        {
            if(entity != null)
                Renderer.drawEntities(entityMap.get(entity));
        }
    }

    private void indexEntity(Entity entity)
    {
        if(renderingIndex >= renderingOrder.length)
            System.err.println("Entity Batch size exceeded...");
        else
        {
            renderingOrder[renderingIndex] = entity;
            renderingIndex++;
        }
    }
}
