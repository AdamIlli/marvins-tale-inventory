package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Shape;

public class Tile extends GameObject   {
	
	public int x;
	public int y;
	
	public boolean walkedOn = false;
	private int size;
	private int availability;
	  
	public Tile(Shape shape) {
		super(shape);
		
		debugFillColor = new Color(11, 130, 130);
	}
	
	public void update() {
		if (walkedOn) {
			debugFillColor = new Color(130, 11, 130);
		}
	}
	
	  
}
