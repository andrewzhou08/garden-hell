import java.awt.Graphics2D;

public class FlowerTurret extends Turret {

	public static final int HP = 1000;
	
	private double angle;
	private static final int DISTANCE_TO_BARREL = 30;

	/**
	 * Creates new flower turret with x and y of x, y and size of width, height
	 * @param x the x coordinate of the turret
	 * @param y the y coordinate of the turret
	 */
	public FlowerTurret(int x, int y) {
		super("assets/turret-flower/turret-flower.png", x, y, 40, 40, HP);
	}

	/**
	 * Returns the shot projectile
	 * @return Projectile that is being shot
	 */
	@Override
	public Projectile shoot() {
		return new FlowerBullet((int)(getX() + 20 + DISTANCE_TO_BARREL*Math.cos(angle*Math.PI/180)),
				(int)(getY() + 20 + DISTANCE_TO_BARREL*-Math.sin(angle*Math.PI/180)), getWidth()/2, getHeight()/2,
				angle);
	}

	/**
	 * Updates angle if flower turret
	 */
	@Override
	public void act() {
		angle += 12;
		if (angle == 360)
			angle = 0;
	}

	/**
	 * Draws flower turret based on angle
	 */
	@Override
	public void draw(Graphics2D g2) {
			g2.rotate(-Math.toRadians(angle), getX() + getWidth() / 2, getY() + getHeight() / 2);
			super.draw(g2);
			g2.rotate(Math.toRadians(angle), getX() + getWidth() / 2, getY() + getHeight() / 2);
	}
}
