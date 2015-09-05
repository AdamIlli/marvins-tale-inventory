package Game;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

public class GameObject {
	// Debug
	protected Color debugFillColor = new Color(211, 30, 130);
	
	protected boolean collidable = true;
	protected boolean clickable = false;
	protected Shape shape;
	
	public float x;
	public float y;
	
	
	
	public GameObject(Shape shape) {
		this.shape = shape;
		this.x = shape.getX();
		this.y = shape.getY();
	}
	
	public void render(GameContainer gc, Graphics g) {
		
		shape.setX(this.x);
		shape.setY(this.y);
		
		g.setColor(debugFillColor);
//		g.fill(shape);
//		g.drawRect(x, y, shape.getWidth(), shape.getHeight());
		//g.draw(shape);
	}
	
	public Shape getShape() {
		return this.shape;
	}
	
	public boolean isCollidable() {
		return collidable;
	}
	
	public boolean isClickable() {
		return clickable;
	}
	
}