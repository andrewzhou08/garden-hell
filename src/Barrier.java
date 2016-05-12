import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Barrier extends Actor {
	
	Image img;
	
	// x, y, width, height is in number of grid cells, not pixels
	public Barrier(int x, int y, int width, int height) {
		super(x * Main.CELL_WIDTH, y * Main.CELL_HEIGHT, width * Main.CELL_WIDTH, height * Main.CELL_HEIGHT);
		img = (new ImageIcon("assets/barrier.png")).getImage();
	}
	
	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, 150, 150, super.getWidth(), super.getHeight(), null);
	}
}
