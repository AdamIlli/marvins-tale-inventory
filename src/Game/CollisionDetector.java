package Game;

import java.util.ArrayList;


public class CollisionDetector {
	private static boolean detectedCollision = false;
	private static boolean continualCollisionDetection = false; 
	private static ArrayList<GameObject> collidableObjects = new ArrayList<GameObject>();;
	
	/**
	 * Initierar Collisiondetectorn. Yippie!
	 */
	public static void main(String []args){
		
	}
	
	/**
	 * Lägger till collidable object i listan över tillgängliga collidable object.
	 */
	public static void addCollidableToList(GameObject collidable){
		if (collidable.isCollidable()) {
			collidableObjects.add(collidable);
		}
	}
	
	public static void addCollidablesToList(ArrayList<GameObject> collidables) {
		for (int i = 0; i < collidables.size(); i++) {
			addCollidableToList(collidables.get(i));
		}
	}
	/**
	 * Tömmer listan tillfälligt
	 */
	public static void clearCollidableList() {
		if (collidableObjects.size() > 0) {
			collidableObjects.clear();
		}
	}
	
	/**
	 * Funktion som kallas när ett collidable objekt vill collisiondetectas jämtemot alla 
	 * collidable objects i listan.
	 */
	public static void detectCollisions(GameObject agressor) {
		for (int i = 0; i < collidableObjects.size(); i++) {
			detectedCollision = collidableObjects.get(i).getShape().intersects(agressor.getShape());
			if (detectedCollision == true) {
				CollisionHandler.handleCollision(agressor, collidableObjects.get(i));
			}
		}
	}
	
	/**
	 * TEST'i'KLAR
	 */
	public static boolean dC(GameObject agressor) {
		for (int i = 0; i < collidableObjects.size(); i++) {
			detectedCollision = collidableObjects.get(i).getShape().contains(agressor.getShape());
			if (detectedCollision == true) {
				return true;
			}
			detectedCollision = collidableObjects.get(i).getShape().intersects(agressor.getShape());
			if (detectedCollision == true) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checkar kontinuerligt efter kollisioner.
	 */
	public static void detectCollisionsContinually() {
		if (continualCollisionDetection == false) {
			return;
		}
		ArrayList<GameObject> tempCollidableObjects = collidableObjects;
		GameObject agressor;
		while (tempCollidableObjects.size() > 0) {
			agressor = tempCollidableObjects.get(0);
			for (int i = 0; i < collidableObjects.size(); i++) {
				detectedCollision = collidableObjects.get(i).getShape().contains(agressor.getShape());
				if (detectedCollision == true) {
					CollisionHandler.handleCollision(agressor, collidableObjects.get(i));
				}
			}
			tempCollidableObjects.remove(0);
			
		}	
	}

}
