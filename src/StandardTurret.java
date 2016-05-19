import java.awt.Graphics2D;

public class StandardTurret extends Turret {

	public static final int HP = 100;
	
	private double angle;
	private int delay;

	/**
	 * Creates new StandardTurret of coordinates x, y and size width, height
	 * @param x the x coordinate of the turret
	 * @param y the y coordinate of the turret
	 */
	public StandardTurret(int x, int y) {
		super("assets/turret-standard/turret-standard.png", x, y, 40, 40, HP);
		setExplodeAnimation(new Animation("assets/turret-standard/turret-standard-explode(%d).png", 1, 13, 1));
		angle = 0;
		delay = 0;
	}

	/**
	 * Returns shot projectile
	 * @return Projectile shot by the StandardTurret
	 */
	@Override
	public Projectile shoot() {
		if (delay % 15 == 0) {
			delay = 0;
			angle = Math.random() * 360;
			return new StandardBullet(getX(), getY(), getWidth() / 2, getHeight() / 2, -angle * Math.PI / 180);
		}
		return null;
	}

	/**
	 * Adds to delay; shoots every half second
	 */
	@Override
	public void act() {
		super.act();
		
		delay++;
	}

	/**
	 * Draws turret with correct angles
	 */
	@Override
	public void draw(Graphics2D g2) {
		g2.rotate(-Math.toRadians(angle), getX() + getWidth() / 2, getY() + getHeight() / 2);
		super.draw(g2);
		g2.rotate(Math.toRadians(angle), getX() + getWidth() / 2, getY() + getHeight() / 2);
	}	
	
}
