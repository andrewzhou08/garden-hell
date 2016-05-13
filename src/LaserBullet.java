import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LaserBullet extends Projectile {
	
	private Image img;
	
	/**
	 * Creates new laser bullet of coordinates x,y and width/height of width,height at angle angle
	 * @param x x coordinate of the bullet
	 * @param y y coordinate of the bullet
	 * @param width width of the bullet
	 * @param height height of the bullet
	 * @param angle angle of the bullet
	 */
	public LaserBullet(int x, int y, int width, int height, double angle) {
		super(x, y, width, height);
		img = (new ImageIcon("assets/bullet-4.png")).getImage();
		super.setVelX(Math.cos(angle) * Projectile.BULLET_SPEED * 10);
		super.setVelY(Math.sin(angle) * Projectile.BULLET_SPEED * 10);
	}
	
	public void act(){
		super.act();
	}
	
	/**
	 * Draws bullet at given location
	 */
	public void draw(Graphics g){
		super.draw(g);
		
		g.drawImage(img, super.getX(), super.getY(), super.getHeight(), super.getWidth(), null);
	}

}
