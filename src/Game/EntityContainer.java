package Game;

import java.util.ArrayList;

public final class EntityContainer {
	
	private static ArrayList<GameObject> entities = new ArrayList<GameObject>();
	private static ArrayList<Portal> portals = new ArrayList<Portal>();
	
	public static void add(GameObject entity) {
		
		String entityType = entity.getClass().getSimpleName();
		
		switch(entityType) {
			case "Portal":
				EntityContainer.portals.add((Portal) entity);
				break;
//			case "NPC":
//				EntityContainer.npcs.add((NPC) enity);
//				break;
			default:
				System.out.println("Debug: Entity not supported. " + entityType);
				return;
		}
		entities.add(entity);
	}
	
	public static ArrayList<Portal> getPortals() {
		return portals;
	}

}
