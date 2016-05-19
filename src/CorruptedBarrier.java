import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class CorruptedBarrier extends Barrier {
	
	private double velX, velY;
	private Turret t;
	private Image img;
	
	/**
	 * Creates new corrupted barrier of coordinates x,y and width and height of width,height with Turret turret on top
	 * @param x x coordinate of barrier - number in grid cells, not pixels
	 * @param y y coordinate of barrier - number in grid cells, not pixels
	 * @param width width of barrier
	 * @param height height of barrier
	 * @param turret turret mounted on top of the newly created corrupted barrier
	 */
	public CorruptedBarrier(int x, int y, int width, int height){
		super(x, y, width, height);
		velX = 0;
		velY = 0;
		img = (new ImageIcon("assets/barrier-corrupt.png")).getImage();
		
		int randomTurret = (int)(Math.random()*4);
		if(randomTurret == 0)
			t = new StandardTurret(x, y);
		else if(randomTurret == 1)
			t = new FlowerTurret(x, y);
		else if(randomTurret == 2)
			t = new LaserTurret(x, y);
		else
			t = new PowerOrbTurret(x, y);
	}
	
	/**
	 * Moves in random directions
	 */
	public void move(){
		velX += 3*Math.random()-1;
		velY += 3*Math.random()-1;
		super.moveBy((int)velX, (int)velY);
	}
	
	/**
	 * 
	 * @return turret on the corrupted barrier
	 */
	public Turret getTurret(){
		return t;
	}
	
	/**
	 * Draws barrier at specified location
	 */
	public void draw(Graphics g){
		g.drawImage(img, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
	}
}
