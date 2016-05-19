public class LaserTurret extends Turret {

	public static final int HP = 50;
	
	public LaserTurret(int x, int y) {
		super("", x, y, 40, 40, HP);
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
