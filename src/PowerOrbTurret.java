public class PowerOrbTurret extends Turret {

	public static final int HP = 50;
	
	private int delay;

	/**
	 * Creates new PowerOrbTurret of coordinates x, y and size width, height
	 * @param x the x coordinate of the turret
	 * @param y the y coordinate of the turret
	 */
	public PowerOrbTurret(int x, int y) {
		super("assets/turret-powerorb/turret-powerorb.png", x, y, 40, 40, HP);
		delay = 0;
	}

	/**
	 * Gives projectile shot by turret
	 * @return projectile shot by turret
	 */
	@Override
	public Projectile shoot() {
		if (delay % 30 == 0)
			return new PowerOrbBullet(super.getX(), super.getY());
		return null;
	}

	/**
	 * Increases delay in the power orb turret; shoots every second
	 */
	@Override
	public void act() {
		delay++;
	}
}
