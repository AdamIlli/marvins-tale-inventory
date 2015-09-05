package GameStates;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.*;

import Event.ControllerEventHandler;
import Game.CollisionDetector;
import Game.Controller;
import Game.Cursor;
import Game.Player;
import Game.SceneContainer;
import Game.Tile;
import Game.TileMapBuilder;
 
public class Strolling extends BasicGameState {
	
	
	
	SceneContainer sceneHandler;
	Player player;
	Cursor cursor;
	Controller controller;
	ControllerEventHandler ctrlEventHandler;
	

	public Strolling() {
		
	}
 
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		sceneHandler = new SceneContainer();
		sceneHandler.loadScenes();
		sceneHandler.setActiveScene(1);
		
		player = new Player(new Circle(16, 16, 16));
		
		controller = new Controller();
		ctrlEventHandler = new ControllerEventHandler(player);
		controller.addListener(ctrlEventHandler);
		
		cursor = new Cursor(new Circle(0, 0, 2), controller);
		cursor.init(gc);
	}
 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		sceneHandler.getActiveScene().render(gc, g);
		
		for (int i = 0; i < TileMapBuilder.tileList.size(); i++) {
			TileMapBuilder.tileList.get(i).render(gc, g);
		}
		player.render(gc, g);
		cursor.render(gc, g);
	}
 
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		player.update();
		cursor.update(gc);
		for (int i = 0; i < TileMapBuilder.tileList.size(); i++) {
			TileMapBuilder.tileList.get(i).update();
		}
	}
 
	public int getID() {
		return 2;
	}
}