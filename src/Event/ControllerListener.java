package Event;

public interface ControllerListener {
	
	public void controllerClickedTile(ControllerEvent e);
	public void controllerClickedPortal(ControllerEvent e);
	public void controllerClickedVoid(ControllerEvent event);
	
}
