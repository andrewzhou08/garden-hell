import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class FlowerBullet extends Projectile {
	
	private Image img;
	
	public FlowerBullet(int x, int y, int width, int height, double angle) {
		super(x, y, width, height);
		img = (new ImageIcon("assets/bullet-2.png")).getImage();
		super.setVelX(Math.cos(angle) * Projectile.BULLET_SPEED);
		super.setVelY(Math.sin(angle) * Projectile.BULLET_SPEED);
	}
	
	public void act(){
		super.act();
	}
	
	public void draw(Graphics g){
		super.draw(g);
		
		g.drawImage(img, super.getX(), super.getY(), super.getHeight(), super.getWidth(), null);
	}

}
