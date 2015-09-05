package Game;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.geom.Rectangle;

public class TileMapBuilder {

	public static int tileSizeInPixels = 16; 
	private static Shape shape;
	public static ArrayList<Tile> tileList = new ArrayList<Tile>();
	public static TileMap tileMap;
	
	public static void construct(int sceneSizeX, int sceneSizeY){
	
		
		int tileMapSizeInLength = (sceneSizeX/tileSizeInPixels); 
		int tileMapSizeInHeight = (sceneSizeY/tileSizeInPixels); 
		
		int[] singleRow = new int[tileMapSizeInLength];
		int[] currentRow = new int[tileMapSizeInHeight];
		
		int x;
		int y;
		int[][] tiles = new int[tileMapSizeInLength][tileMapSizeInHeight];
		
		for (int i = 0; i < tileMapSizeInLength; i++){
			for (int p = 0; p < tileMapSizeInHeight; p++) {
				x = i * tileSizeInPixels;
				y = p * tileSizeInPixels;
				
				Tile tile = new Tile(shape = new Rectangle(x, y, tileSizeInPixels, tileSizeInPixels));
				tile.x = i;
				tile.y = p;
				if (CollisionDetector.dC(tile) == true){
					tileList.add(tile);
					tiles[i][p] = 0;
					
				} else {
					tiles[i][p] = 1;
				}
				
					
			}
		}
		
		tileMap = new TileMap((sceneSizeX/tileSizeInPixels), (sceneSizeY/tileSizeInPixels), tiles);
	}
	
}
	
