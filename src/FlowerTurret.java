public class FlowerTurret extends Turret {

	private double angle;

	public FlowerTurret(int x, int y, int width, int height) {
		super("assets/turret-flower/turret-flower.png", x, y, width, height);
	}

	@Override
	public Projectile shoot() {
		System.out.println(angle);
		return new FlowerBullet(getX(), getY(), getWidth() / 2, getHeight() / 2, angle * Math.PI / 180);
	}

	@Override
	public void act() {
		angle += 15;
	}
}
