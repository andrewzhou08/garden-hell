import java.awt.Graphics2D;

public class FlowerTurret extends Turret {

	private double angle;

	public FlowerTurret(int x, int y, int width, int height) {
		super("assets/turret-flower/turret-flower.png", x, y, width, height);
	}

	@Override
	public Projectile shoot() {
		System.out.println(angle);
		return new FlowerBullet(getX(), getY(), getWidth() / 2, getHeight() / 2, -angle * Math.PI / 180);
	}

	@Override
	public void act() {
		angle += 12;
		if (angle == 360)
			angle = 0;
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.rotate(-Math.toRadians(angle), getX() + getWidth() / 2, getY() + getHeight() / 2);
		super.draw(g2);
		g2.rotate(Math.toRadians(angle), getX() + getWidth() / 2, getY() + getHeight() / 2);
	}
}
