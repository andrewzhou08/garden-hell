import java.awt.Graphics;

public class Player extends Actor {

	// x, y is in number of grid cells, not pixels
	public Player(int x, int y) {
		super(x * Main.CELL_WIDTH, y * Main.CELL_HEIGHT, Main.CELL_WIDTH,
				Main.CELL_HEIGHT);
	}

	@Override
	public void act() {
		moveBy(1, 1);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.fillOval(getX(), getY(), getWidth(), getHeight());
	}
	
	
}