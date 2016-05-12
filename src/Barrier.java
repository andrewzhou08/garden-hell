import java.awt.Graphics2D;

public class Barrier extends Actor {
			
	
	
	/**
	 * Creates new barrier with coordinates x,y and width of width and height of height
	 * @param x x coordinate of barrier - number in grid cells, not pixels
	 * @param y y coordinate of barrier - number in grid cells, not pixels
	 * @param width width of barrier
	 * @param height height of barrier
	 */
	public Barrier(int x, int y, int width, int height) {	// x, y, width, height is in number of grid cells, not pixels
		super("assets/barrier.png", x * Main.CELL_WIDTH, y * Main.CELL_HEIGHT, width * Main.CELL_WIDTH, height * Main.CELL_HEIGHT);
	}
	
	@Override
	public void act() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Plays animation to make barrier corrupt
	 */
	public void playCorruptionAnimation(){
		//TODO @Heidi
	}

	/**
	 * Draws the barrier at specified coordinates
	 */
	@Override
	public void draw(Graphics2D g2) {
		for (int x = 0; x < getWidth(); x += Main.CELL_WIDTH) {
			for (int y = 0; y < getHeight(); y += Main.CELL_HEIGHT) {
				g2.drawImage(getSprite(), getX() + x, getY() + y,
						Main.CELL_WIDTH, Main.CELL_HEIGHT, null);
			}
		}
	}
}
