public class Tank extends Player {

	private int skippedFrames;
	
	/**
	 * Creates new tank character of coordinates x,y
	 * @param x x coordinate of tank
	 * @param y y coordinate of tank
	 */
	public Tank(int x, int y, double angle) {
		super(x, y, angle);
		setMoveAnimation(new Animation("assets/player-tank/player-tank-moving(%d).png", 1, 4, 6));
		setStandAnimation(new Animation("assets/player-tank/player-tank-standing(%d).png", 1, 2, 16));
		super.setSpeed(2);
		super.setMaxHealth(200);
		skippedFrames = 0;
	}

	/**
	 * Returns shot projectile
	 * @return Projectile shot
	 */
	public Projectile shoot() {
		if(skippedFrames == 3){
			skippedFrames = 0;
			return new TankBullet(getX() + (getWidth() / 2 - 3), getY() + (getHeight() / 2 - 3),
					getAngle() * Math.PI / 180);
		}
		skippedFrames++;
		return null;
	}
}
