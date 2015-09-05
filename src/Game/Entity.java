package Game;

import org.newdawn.slick.geom.Shape;

public class Entity extends GameObject {

	public Entity(Shape shape) {
		super(shape);

		EntityContainer.add(this);
	}

}
