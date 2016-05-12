import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Barrier extends Actor {
	
	private Image img;
	
	// x, y, width, height is in number of grid cells, not pixels
	public Barrier(int x, int y, int width, int height) {
		super(x * Main.CELL_WIDTH, y * Main.CELL_HEIGHT, width * Main.CELL_WIDTH, height * Main.CELL_HEIGHT);
		img = (new ImageIcon("assets/barrier.png")).getImage();
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
		g.drawImage(img, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
	}
}
