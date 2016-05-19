import java.awt.Color;
import java.awt.Graphics2D;

public class BuilderBullet extends Projectile {
	/**
	 * Creates a bullet for builder
	 * @param x x coordinate of the bullet
	 * @param y y coordinate of the bullet
	 * @param angle angle of the bullet
	 */
	public BuilderBullet(int x, int y, double angle){
		super(x, y, 6, 6);
		
		double velX = Math.cos(angle) * super.BULLET_SPEED;
		double velY = -Math.sin(angle) * super.BULLET_SPEED;
		
		setVelX(velX);
		setVelY(velY);
	}
	/**
	 * cause the bullet to act
	 */
	public void act() {
		super.act();
	}
	/**
	 * draws the bullet
	 * @param g2 Graphics2D object to draw
	 */
	public void draw(Graphics2D g2){
		g2.setColor(Color.RED);
		g2.fillOval(getX(), getY(), getHeight(), getWidth());
	}
}
