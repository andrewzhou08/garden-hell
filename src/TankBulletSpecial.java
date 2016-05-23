import java.awt.Color;
import java.awt.Graphics2D;

public class TankBulletSpecial extends Projectile {
	
	public static final int TANK_BULLET_SPECIAL_DAMAGE = 1200;
	private int skippedFrames;
	
	/**
	 * Creates new TankBullet of coordinates x, y and angle angle
	 * @param x
	 * @param y
	 * @param angle
	 */
	public TankBulletSpecial(int x, int y, double angle) {
		super(x, y, 40, 40);
		
		double velX = Math.cos(angle) * super.BULLET_SPEED;
		double velY = -Math.sin(angle) * super.BULLET_SPEED;
		
		super.setDamage(TANK_BULLET_SPECIAL_DAMAGE);
		
		setVelX(velX);
		setVelY(velY);
		skippedFrames = 0;
	}
	/**
	 * Causes the bullet to move
	 */
	public void act() {
		if(skippedFrames == 4){
			super.act();
			skippedFrames = 0;
		}
		skippedFrames++;
	}
	
	/**
	 * Draws a TankBullet of size 6x6 and color black
	 */
	public void draw(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillOval(getX(), getY(), getHeight(), getWidth());
	}

}
