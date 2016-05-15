public class PowerOrbTurret extends Turret {

	private int delay;

	public PowerOrbTurret(int x, int y, int width, int height) {
		super("assets/turret-powerorb/turret-powerorb.png", x, y, width, height);
		delay = 0;
	}

	@Override
	public Projectile shoot() {
		if (delay % 30 == 0)
			return new PowerOrbBullet(super.getX(), super.getY());
		return null;
	}

	@Override
	public void act() {
		delay++;
	}
}
