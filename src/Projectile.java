import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Projectile extends Actor {
	
	public static final int BULLET_SPEED = 1;
	private double velX;
	private double velY;
	
	public Projectile(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public Projectile(String sprite, int x, int y, int width, int height) {
		super(sprite, x, y, width, height);
	}
	
	public void setVelX(double newVelX) {
		velX = newVelX;
	}
	
	public void setVelY(double newVelY) {
		velY = newVelY;
	}
	
	public double getVelX() {
		return velX;
	}
	
	public double getVelY() {
		return velY;
	}

	@Override
	public void act() {
		moveBy((int)(velX+0.5), (int)(velY+0.5));
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
	}
}
