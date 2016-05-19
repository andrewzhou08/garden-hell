public class LaserTurret extends Turret {

	public static final int HP = 50;
	
	public LaserTurret(int x, int y, int width, int height) {
		super("", x, y, width, height, HP);
	}

	@Override
	public Projectile shoot() {
		return null;
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

}
