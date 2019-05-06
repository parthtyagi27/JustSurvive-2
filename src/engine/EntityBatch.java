package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntityBatch
{
    private Map<Entity, ArrayList<Entity>> entityMap;

    public EntityBatch()
    {
        entityMap = new HashMap<Entity, ArrayList<Entity>>();
    }

    public void addEntity(Entity entity)
    {
        if(!entityMap.containsKey(entity))
        {
            //create new arraylist and add it to the map
            ArrayList<Entity> arrayList = new ArrayList<Entity>();
            arrayList.add(entity);
            entityMap.put(entity, arrayList);
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
            ArrayList<Entity> arrayList = new ArrayList<Entity>();
            for(Entity e: entities)
                arrayList.add(e);
            entityMap.put(entities[0], arrayList);
        }
        else
        {
            //Add entity to the pre-existing array list
            for(Entity e: entities)
                entityMap.get(entities[0]).add(e);
        }
    }

    public void render()
    {
        if(!entityMap.isEmpty())
        {
            // Render all entities
            for(ArrayList<Entity> arrayList : entityMap.values())
                Renderer.drawEntities(arrayList);
        }
    }
}
