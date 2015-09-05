package Game;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Scene {
	  
	private String name;
	private Image bg;
	public int sceneSizeX;
	public int sceneSizeY;
	ArrayList<Portal> portals;
	ArrayList<Ground> grounds;
	ArrayList<GameObject> collidables;
	
	public Scene(String title, String url, ArrayList<Portal> portals, ArrayList<Ground> grounds) {
		
		this.name = title;
		this.portals = portals;
		this.grounds = grounds;
		
		collidables = new ArrayList<GameObject>();
		collidables.addAll(portals);
		collidables.addAll(grounds);
		
		try {
			this.bg = new Image(url);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Create new scene! " + toString());	
	}
	
	public void render(GameContainer gc, Graphics g) {
		bg.draw(0, 0);
		for (int i = 0; i < collidables.size(); i++) {
			GameObject collidable = collidables.get(i);
			collidable.render(gc, g);
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Portal> getPortals() {
		return portals;
	}
	  
	public String toString() {
		return "SceneName: " + name;
	}

	public ArrayList<GameObject> getCollidableObjects() {
		return collidables;
	}
}
