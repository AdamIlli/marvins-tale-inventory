package Game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.util.pathfinding.Path;

public class Player extends GameObject {
		
	private int tileX;
	private int tileY;
	private List<Movement> movement = new ArrayList<Movement>();
	
	private int targetX;
	private int targetY;
	private int targetXDistance;
	private int targetYDistance;
	
	private Tile queuedTile;
	private Movement curMove;
	
	private Image mar;
	
	double depth;
	
	public Player(Shape shape) throws SlickException {
		super(shape);
		
		tileX = 28;
		tileY = 28;
		
		this.x = tileX * TileMapBuilder.tileSizeInPixels;
		this.y = tileY * TileMapBuilder.tileSizeInPixels;
		
		this.shape.setX(this.x);
		this.shape.setY(this.y);
		
		mar = new Image("resources/sprites/marvin-right.png");
		
		// TODO Auto-generated constructor stub
	}
	
	public void render(GameContainer gc, Graphics g) {
		depth = (this.y / 600);
		double scale = 0.2 * depth;
		
		int xx = (int) (this.x - ((mar.getWidth() *scale) / 2));
		int yy = (int) (this.y - (mar.getHeight() * scale) + TileMapBuilder.tileSizeInPixels);
		mar.draw(xx, yy, (float) scale);
	}
	
	public void update() {
		if (this.curMove != null) {
			moveMarvin();
		}
		
		this.shape.setX(this.x);
		this.shape.setY(this.y);
		updateTileReference();
	}
	
	private void updateTileReference() {
		tileX = (int) (this.x / TileMapBuilder.tileSizeInPixels);
		tileY = (int) (this.y / TileMapBuilder.tileSizeInPixels);
	}
	
	private void moveMarvin() {
		Movement currentMovement = this.curMove;
		double speed = 1 + (1 * depth);
		
		if (targetXDistance > 0 || targetXDistance < -0) {
			int velX = (int) (speed * (currentMovement.getX() / currentMovement.getMagnitude() / TileMapBuilder.tileSizeInPixels));
			this.x += velX;
			targetXDistance -= velX;
		}
		if (targetYDistance > 0 || targetYDistance < -0) {
			int velY = (int) (speed * (currentMovement.getY() / currentMovement.getMagnitude() / TileMapBuilder.tileSizeInPixels));
			this.y += velY;
			targetYDistance -= velY;
		}
		if (targetXDistance == 0 && targetYDistance == 0) {
			// Move finished!
			System.out.println("Move finished!");
			
			// Remove move reference
			this.movement.remove(0);
			this.curMove = null;
			// Try to initialize new move
			initializeMove();
		}
	}
	
	public void move(Tile tile) {
		updateTileReference();
		queuedTile = null;
		
		System.out.println("Move player! To tile " + tile.x + " " + tile.y);
		
		PathFinder pathFinder = new PathFinder();
		pathFinder.findPath(tileX, tileY, tile.x, tile.y, TileMapBuilder.tileMap, 100);
		Path path = pathFinder.getValidPath();
		
		if (path == null) {
			System.out.println("Couldn't create path.");
			return;
		}
		
		
		if (this.curMove == null) {
			// No active move, set new moving instructions
			this.movement = pathToMoverInstructions(path);
			if (movement.size() > 0) {
				initializeMove();
			}
		}
		else {
			// There is an active move, save the tile for when the move is finished
			System.out.println("Queue move");
			queuedTile = tile;
		}
	}
	
	private void initializeMove() {
		System.out.println("Initialize new move!");
		if (queuedTile != null) {
			System.out.println("Set queued movement from: X:"+tileX + " Y:" + tileY);
			curMove = null;
			
			move(queuedTile);
		}
		if (movement.isEmpty()) {
			System.out.println("No more moves. :(");
			this.curMove = null;
			return;
		}
		Movement m = this.movement.get(0);
		this.targetX = m.getX();
		this.targetY = m.getY();
		
		this.targetXDistance = m.getX();
		this.targetYDistance = m.getY();
		
		this.curMove = m;
	}
	
	public List<Movement> pathToMoverInstructions (Path path) {
		List<Movement> moverInstructions = new ArrayList<Movement>();
		List<Movement> moves = new ArrayList<Movement>();
		
		
		for (int i = 1; i < path.getLength(); i++) {
			Movement move = new Movement();
			move.setX((path.getStep(i).getX() - path.getStep(i-1).getX()) * TileMapBuilder.tileSizeInPixels);
			move.setY((path.getStep(i).getY() - path.getStep(i-1).getY()) * TileMapBuilder.tileSizeInPixels);
			
			moves.add(move);
		}
		
		for (Movement m : moves) {
			System.out.println("R: " + m.getRotation() + " X:" + m.getX() + " Y:" + m.getY());
		}
		Movement currentMove = new Movement();
		int magnitudeCount = 1;
		for (int i = 1; i < moves.size(); i++) {
			Movement nextMove = moves.get(i);
			currentMove = moves.get(i-1);
			
			if (currentMove.getRotation() == nextMove.getRotation()) {
				magnitudeCount += 1;
			}
			else {
				currentMove.setMagnitude(magnitudeCount);
				Movement newMove = currentMove;
				moverInstructions.add(newMove);
				magnitudeCount = 1; // Reset
			}
		}
		if (magnitudeCount == 1) {
			currentMove = moves.get(moves.size() -1);
		}
		currentMove.setMagnitude(magnitudeCount);
		Movement newMove = currentMove;
		moverInstructions.add(newMove);
		System.out.println("Parsed moves");
		for (Movement m : moverInstructions) {
			System.out.println("R: " + m.getRotation() + " X:" + m.getX() + " Y:" + m.getY() + " Magnitude:" + m.getMagnitude());
		}
		
		return moverInstructions;
	}
}
