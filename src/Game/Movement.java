package Game;

public class Movement {
	
	public int x = 0;
	public int getX() { return x; }
	private int y = 0;
	public int getY() { return y; }
	
	private int rotation;
	public int getMagnitude() { return magnitude; }
	
	private int magnitude;
	public int getRotation() { return rotation; }
	
	public int DOWN_RIGHT = 45;
	public int DOWN_LEFT = 135;
	public int DOWN = 90;
	public int UP = -90;
	public int UP_LEFT = -135;
	public int UP_RIGHT = -45;
	public int LEFT = 180;
	public int RIGHT = 0;
	
	public void setX(int x) {
		setRotationFromRelativeDirection(x, y);
		this.x = x;
	}
	
	
	public void setY(int y) {
		setRotationFromRelativeDirection(x, y);
		this.y = y;
	}
	
	
	public void setMagnitude(int magnitude) {
		this.x *= magnitude;
		this.y *= magnitude;
		
		System.out.println("Magnitude " + magnitude + " X:" + this.x + " Y:" + this.y);
		this.magnitude = magnitude;
	}
	
	
	public void setRotationFromRelativeDirection(int x, int y) {
		if (x > 0) {
			if (y > 0) {
				rotation = DOWN_RIGHT;
			}
			else if (y < 0) {
				rotation = UP_RIGHT;
			}
			else {
				rotation = RIGHT;
			}
		}
		else if (x < 0) {
			if (y > 0) {
				rotation = DOWN_LEFT;
			}
			else if (y < 0) {
				rotation = UP_LEFT;
			}
			else {
				rotation = LEFT;
			}
		}
		else {
			if (y > 0) {
				rotation = DOWN;
			}
			else if (y < 0) {
				rotation = UP;
			}
			
		}
	}
	

}
