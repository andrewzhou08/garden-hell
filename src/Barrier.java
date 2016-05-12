import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Barrier extends Actor {
			
	// x, y, width, height is in number of grid cells, not pixels
	public Barrier(int x, int y, int width, int height) {
		super("assets/barrier.png", x * Main.CELL_WIDTH, y * Main.CELL_HEIGHT, width * Main.CELL_WIDTH, height * Main.CELL_HEIGHT);
	}
	
	@Override
	public void act() {
		// TODO Auto-generated method stub
	}
	
	public void playCorruptionAnimation(){
		//TODO @Heidi
	}

	@Override
	public void draw(Graphics g) {
		for (int x = 0; x < getWidth(); x += Main.CELL_WIDTH) {
			for (int y = 0; y < getHeight(); y += Main.CELL_HEIGHT) {
				g.drawImage(getSprite(), getX() + x, getY() + y,
						Main.CELL_WIDTH, Main.CELL_HEIGHT, null);
			}
		}
	}
}
