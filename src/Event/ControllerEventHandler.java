package Event;

import Game.Player;
import Game.Tile;

public class ControllerEventHandler implements ControllerListener {
	
	private Player player;
	
	public ControllerEventHandler(Player player) {
		this.player = player;
	}

	public void controllerClickedTile(ControllerEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getTarget() + ". Debug: Clicked Ground.");
		
		
		player.move((Tile) e.getTarget());
	}

	@Override
	public void controllerClickedPortal(ControllerEvent e) {
		// TODO Auto-generated method stub	
		System.out.println(getClass().getSimpleName() + ". Debug: Clicked Portal.");
	}

	@Override
	public void controllerClickedVoid(ControllerEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Debug: Clicked void.");
	}
}
