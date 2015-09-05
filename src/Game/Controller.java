package Game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import Event.ControllerEvent;
import Event.ControllerListener;

public class Controller {
	
	private Set<ControllerListener> listeners;
	
	public final int CLICKED_VOID = -1;
	public final int CLICKED_PORTAL = 0;
	public final int CLICKED_TILE = 1;
	
	
	public Controller() {
		listeners = new HashSet<ControllerListener>();
	}
	
	public void clicked(int x, int y) {
		
		ArrayList<? extends GameObject> portals = EntityContainer.getPortals();
		ArrayList<? extends GameObject> tiles = TileMapBuilder.tileList;
		
		for (GameObject portal : portals) {
			boolean hit = portal.getShape().contains(x, y);
			if (hit) {
				ControllerEvent event = new ControllerEvent(this, (GameObject) portal);
				eventDispatch(CLICKED_PORTAL, event);
				return;
			}
		}
		
		for (GameObject tile : tiles) {
			boolean hit = tile.getShape().contains(x, y);
			if (hit) {	
				ControllerEvent event = new ControllerEvent(this, (GameObject) tile);
				eventDispatch(CLICKED_TILE, event);
				return;
			}
		}
		
		ControllerEvent event = new ControllerEvent(this, null);
		eventDispatch(CLICKED_VOID, event);
		
		
	}
	
	private void eventDispatch(int eventID, ControllerEvent event) {
		
		for (ControllerListener listener : listeners) {
			switch(eventID) {
				case CLICKED_PORTAL:
					listener.controllerClickedPortal(event);
					break;
				case CLICKED_TILE:
					listener.controllerClickedTile(event);
					break;
				default:
					listener.controllerClickedVoid(event);
			}
		}
	}
	
	public void addListener(ControllerListener listener) {
		listeners.add(listener);
	}

}
