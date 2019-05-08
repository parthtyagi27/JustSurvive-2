package engine;

import java.util.*;

public class EntityBatch
{
    private Map<Entity, ArrayList<Entity>> entityMap;
//    private Queue<Entity> renderingQueue;
    private int renderingIndex;
    private Entity[] renderingOrder;

    public EntityBatch(int batchSize)
    {
        entityMap = new HashMap<Entity, ArrayList<Entity>>();
//        renderingQueue = new LinkedList<Entity>();
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
//            renderingQueue.add(entity);
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
//            renderingQueue.add(entities[0]);
        }
    }

    public void render()
    {
//        if(!entityMap.isEmpty())
//        {
//            // Render all entities
//            for(ArrayList<Entity> arrayList : entityMap.values())
//                Renderer.drawEntities(arrayList);
//        }
//        Queue temp = renderingQueue;
//        if(!temp.isEmpty())
//        {
//            Renderer.drawEntities(entityMap.get(temp.poll()));
//        }

        for(Entity entity : renderingOrder)
        {
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
