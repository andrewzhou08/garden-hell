import java.awt.Color;
import java.awt.Graphics2D;

public class DamagerBullet extends Projectile {
	
	public static final int DAMAGER_BULLET_DAMAGE = 160;
	private int skippedFrames;
	
	/**
	 * creates a bullet for damagers
	 * @param x x coordinate of the bullet
	 * @param y y coordinate of the bullet
	 * @param angle angle of the bullet
	 */
	public DamagerBullet(int x, int y, double angle){
		super(x, y, 6, 6);
		
		double velX = Math.cos(angle) * super.BULLET_SPEED;
		double velY = -Math.sin(angle) * super.BULLET_SPEED;
		
		super.setDamage(DAMAGER_BULLET_DAMAGE);
		
		setVelX(velX);
		setVelY(velY);
		
		skippedFrames = 0;
	}
	
	/**
	 * causes the bullet to act
	 */
	public void act() {
		if(skippedFrames == 3){
			super.act();
			skippedFrames = 0;
		}
		skippedFrames++;
	}
	
	/**
	 * draws the bullet
	 * @param g2 Graphics2D object to draw the bullet
	 */
	public void draw(Graphics2D g2){
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillOval(getX(), getY(), getHeight(), getWidth());
	}
}
