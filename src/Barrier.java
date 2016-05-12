import java.awt.Graphics;

public class Barrier extends Actor {
	
	// x, y, width, height is in number of grid cells, not pixels
	public Barrier(int x, int y, int width, int height) {
		super(x * Main.CELL_WIDTH, y * Main.CELL_HEIGHT, width * Main.CELL_WIDTH, 
				height * Main.CELL_HEIGHT);
	}
	
	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
}
