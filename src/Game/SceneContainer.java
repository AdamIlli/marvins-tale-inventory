package Game;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

public class SceneContainer {
	
	SceneLoader sceneLoader;
	ArrayList<Scene> gameScenes;
	Scene activeGameScene;
	
	public SceneContainer() {
		sceneLoader = new SceneLoader();
		gameScenes = new ArrayList<Scene>();
	}
	
	public void loadScenes() {
		// Load which maps from parameter. 
		// Vi fular och stoppar det här så länge ;)
		ArrayList<String> mapPaths = new ArrayList<String>();
		mapPaths.add("resources/maps/biscuits_corner.xml");
		mapPaths.add("resources/maps/square.xml");
		
		
		// Loop trough all file paths of the maps we want 
		// to have loaded in memory and able to reference.
		// Create maps out of them and put them in gameScenes
		for (int i = 0; i < mapPaths.size(); i++) {
			try {
				gameScenes.add(sceneLoader.loadMap(mapPaths.get(i)));
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
	/**
	 * Get map object
	 * 
	 * @param mapName
	 * @return
	 */
	public Scene getMap(String mapName) {
		Scene gameMap = null;
		for (int i = 0; i < gameScenes.size(); i++) {
			if (gameScenes.get(i).getName() == mapName) {
				return gameScenes.get(i);
			}
			
		}
		return gameMap;
	}
	
	/**
	 * Set the active game scene.
	 * @param i
	 */
	public void setActiveScene(int i) {
		activeGameScene = gameScenes.get(i);
		
		CollisionDetector.clearCollidableList();
		CollisionDetector.addCollidablesToList(activeGameScene.getCollidableObjects());
		
		System.out.println("SceneHander::Set active scene: " + activeGameScene.getName());
		
		
		TileMapBuilder.construct(800, 600);
	}
	
	public Scene getActiveScene() {
		return activeGameScene;
	}
	
	/**
	 * Empties the list of loaded maps
	 */
	public void destroyMapList() {
		// Empty list of maps
		gameScenes.clear();
	}
	
	/**
	 * Returns a list of the active scenes portals.
	 * 
	 * @return ArrayList<Portal>
	 */
	public ArrayList<Portal> getPortals() {
		// TODO Auto-generated method stub
		if (activeGameScene != null) {
			return activeGameScene.getPortals();
		}
		return null;
	}

}
