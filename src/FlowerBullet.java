import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class FlowerBullet extends Projectile {
	
	public static final int FLOWER_BULLET_DAMAGE = 1;
	
	private Image img;
	private int skippedFrames;
	
	/**
	 * Creates new flower bullet of coordinates x,y and width/height of width,height at angle angle
	 * @param x x coordinate of the bullet
	 * @param y y coordinate of the bullet
	 * @param width width of the bullet
	 * @param height height of the bullet
	 * @param angle angle of the bullet
	 */
	public FlowerBullet(int x, int y, int width, int height, double angle) {
		super(x, y, width, height);
		img = (new ImageIcon("assets/bullet-2.png")).getImage();
		super.setVelX(Math.cos(angle) * Projectile.BULLET_SPEED);
		super.setVelY(Math.sin(angle) * Projectile.BULLET_SPEED);
		skippedFrames = 0;
		
		super.setDamage(FLOWER_BULLET_DAMAGE);
	}
	
	/**
	 * Skips frames and acts
	 */
	public void act(){
		if(skippedFrames == 10){
			super.act();
			skippedFrames = 0;
		}
		skippedFrames++;
	}
	
	/**
	 * Draws bullet at given coordinates
	 */
	public void draw(Graphics2D g2){
		super.draw(g2);
		g2.drawImage(img, super.getX(), super.getY(), super.getHeight(), super.getWidth(), null);
	}

}
