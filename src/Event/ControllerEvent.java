package Event;

import Game.Controller;
import Game.GameObject;

public class ControllerEvent {
	
	private Controller source;
	private GameObject target;
	
	public ControllerEvent(Controller source, GameObject target) {		
		this.source = source;
		this.target = target;
	}
	
	public Controller getSource() {
		return source;
	}
	
	public GameObject getTarget() {
		return target;
	}
	
}
