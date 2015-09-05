package Game;

import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class PathFinder {
	
	private  Path validPath;

	public void findPath(int startX, int startY, int goalX, int goalY, TileBasedMap map, int maxLength){
		
		AStarPathFinder pathFinder = new AStarPathFinder(map, maxLength, true);
		Path path = pathFinder.findPath(null, startX, startY, goalX, goalY);
        System.out.println(path);
        
        if (path != null) {
        	validPath = path;
        	return;
        	
        }
	}
	
	public Path getValidPath() {
		return validPath;
	}
}
