import java.awt.Color;
import java.awt.Graphics2D;

public class TankBullet extends Projectile {
	
	public static final int TANK_BULLET_DAMAGE = 120;
	
	/**
	 * Creates new TankBullet of coordinates x, y and angle angle
	 * @param x
	 * @param y
	 * @param angle
	 */
	public TankBullet(int x, int y, double angle) {
		super(x, y, 6, 6);
		
		double velX = Math.cos(angle) * super.BULLET_SPEED;
		double velY = -Math.sin(angle) * super.BULLET_SPEED;
		
		super.setDamage(TANK_BULLET_DAMAGE);
		
		setVelX(velX);
		setVelY(velY);
	}
	/**
	 * Causes the bullet to move
	 */
	public void act() {
		super.act();
	}
	
	/**
	 * Draws a TankBullet of size 6x6 and color black
	 */
	public void draw(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillOval(getX(), getY(), getHeight(), getWidth());
	}

}
