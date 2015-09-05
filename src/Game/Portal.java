package Game;

import org.newdawn.slick.geom.Shape;

public class Portal extends Entity   {
	
	private String id;
	private String destination;
	  
	public Portal(Shape shape, String destination) {
		super(shape);
		this.destination = destination;
		this.collidable = false;
	}
}
