import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class BreakableBarrier extends Barrier {
	
	private Image img;
	
	/**
	 * Creates new breakable barrier of coordinates x, y and width and height of width,height
	 * @param x x coordinate of barrier - number in grid cells, not pixels
	 * @param y y coordinate of barrier - number in grid cells, not pixels
	 * @param width width of barrier
	 * @param height height of barrier
	 */
	public BreakableBarrier(int x, int y, int width, int height){
		super(x, y, width, height);
		img = (new ImageIcon("assets/barrier-breakable.png")).getImage();
	}
	
	/**
	 * Draws barrier onto screen with specified characteristics
	 */
	public void draw(Graphics g){
		g.drawImage(img, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
	}
	
	/**
	 * Plays breaking animations
	 */
	public void playBreakAnimation(){
		
	}
}
