package Game;

import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class TileMap implements TileBasedMap {
	
    private int width;
    private int height;
    private int[][] tiles;

        public TileMap(int width, int height, int[][] tiles) {
		// TODO Auto-generated constructor stub
        	this.tiles = tiles;
        	this.height = height;
        	this.width = width;
        }

		@Override
        public boolean blocked(PathFindingContext ctx, int x, int y) {
            return tiles[x][y] != 0;
        }

        @Override
        public float getCost(PathFindingContext ctx, int x, int y) {
            return 1.0f;
        }

        @Override
        public int getHeightInTiles() {
            return height;
        }

        @Override
        public int getWidthInTiles() {
            return width;
        }

        @Override
        public void pathFinderVisited(int x, int y) {}

}


