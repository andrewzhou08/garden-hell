import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PowerOrbBullet extends Projectile {
	
	private Animation moveAnimation;
	
	public PowerOrbBullet(int x, int y) {
		super("assets/bullet-3/bullet-3.png", x, y, 40, 40);
		moveAnimation = new Animation("assets/bullet-3/bullet-3(%d).png", 1, 4, 3); 
		setVelX(getVelX() + 8.0*Math.random());
		setVelY(getVelY() + 8.0*Math.random());
	}
	
	@Override
	public void act() {
		super.act();
		System.out.println(getX());
		moveAnimation.update();
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(moveAnimation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
