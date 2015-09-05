package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.Image;

public class Cursor extends GameObject {
	
	private static Image pointer = null;
	int xpos = 1;
	int ypos = 1;
	Controller controller;
	
	
	public Cursor(Shape shape, Controller controller) {
		super(shape);
		collidable = false;
		this.controller = controller;
		// TODO Auto-generated constructor stub
	}

	
	public void init(GameContainer gc) throws SlickException {
		gc.setMouseGrabbed(true);
		pointer = new Image("resources/sprites/pointer.png");
	}
	
	public void update(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub	
		Input input = gc.getInput();
		xpos = input.getMouseX();
		ypos = input.getMouseY();
		shape.setLocation(xpos, ypos);
		
		if (input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {			
			controller.clicked(xpos, ypos);
//			CollisionDetector.detectCollisions(this);
		}
	}
	
	public void render(GameContainer gc, Graphics g) {
		pointer.draw(xpos, ypos);
	}
	
}
	
