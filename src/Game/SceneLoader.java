package Game;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.util.xml.*;




public class SceneLoader {
	
	/**
	 * Loads and initiates a map from a map resource file.
	 * 
	 * @param filename Path to resource 
	 * @return Scene
	 */
	public Scene loadMap(String filename) throws SlickException {
		System.out.println("Load map: " + filename);
		// Init parser
		XMLParser parser = new XMLParser();
		XMLElement rootElement = parser.parse(filename);
		
		// Load Name and background resource url
		String mapName = rootElement.getChildrenByName("Name").get(0).getContent();
		String mapBgUrl = rootElement.getChildrenByName("Background").get(0).getContent();
		
		// Go through portals
		ArrayList<Portal> portalList = new ArrayList<Portal>();
		XMLElementList portals = rootElement.getChildrenByName("portals").get(0).getChildren();
		for (int i = 0; i < portals.size(); i++) {
			XMLElement portalElement = portals.get(i);
			XMLElementList portalNodes = portalElement.getChildrenByName("nodes").get(0).getChildren();
			String portalDest = portalElement.getChildrenByName("Destination").get(0).getContent();
			portalList.add(new Portal((Shape) new Polygon(parseShapeNodes(portalNodes)), portalDest));
		}
		
		// Go through grounds
		ArrayList<Ground> groundList = new ArrayList<Ground>();
		XMLElementList grounds = rootElement.getChildrenByName("grounds").get(0).getChildren();
		for (int i = 0; i < grounds.size(); i++) {
			XMLElement groundElement = grounds.get(i);
			XMLElementList groundNodes = groundElement.getChildrenByName("nodes").get(0).getChildren();
			groundList.add(new Ground((Shape) new Polygon(parseShapeNodes(groundNodes))));
		}
		
		// Create a new scene and return in
		return new Scene(mapName, mapBgUrl, portalList, groundList);
	}
	
	/**
	 * Parse nodes with structure like example below. 
	 * Return as a float list with following structure:
	 * {(Point1) x,y, (Point2) x, y, ...}
	 * 
	 * Is used to create a polygon which is transformed to a shape, which creates
	 * the form and hitbox of an Collidable.
	 * 
	 * <nodes>
	 * 	<node>
	 * 		<x>[float]</x>
	 * 		<y>[float]</y>
	 * 	</node>
	 * </nodes>
	 * @param shapeNodes
	 * @return float[]
	 */
	private float[] parseShapeNodes(XMLElementList shapeNodes) {
		float[] shapeCoords = new float[shapeNodes.size() * 2];
		int coordsListIndex = 0;
		
		// Go trough the nodes making up the shape of the portal
		for (int iy = 0; iy < shapeNodes.size(); iy++) {				
			shapeCoords[coordsListIndex] = Float.parseFloat(shapeNodes.get(iy).getChildrenByName("x").get(0).getContent());
			shapeCoords[coordsListIndex + 1] = Float.parseFloat(shapeNodes.get(iy).getChildrenByName("y").get(0).getContent());
			coordsListIndex += 2;
		}
		return shapeCoords;
	}

}
