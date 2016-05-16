public class Tank extends Player {

	/**
	 * Creates new tank character of coordinates x,y
	 * @param x x coordinate of tank
	 * @param y y coordinate of tank
	 */
	public Tank(int x, int y, double angle) {
		super(x, y, angle);
		setMoveAnimation(new Animation("assets/player-tank/player-tank-moving(%d).png", 1, 4, 6));
		setStandAnimation(new Animation("assets/player-tank/player-tank-standing(%d).png", 1, 2, 16));
	}

	public Projectile shoot() {
		return new TankBullet(getX() + (getWidth() / 2 - 3), getY() + (getHeight() / 2 - 3),
				getAngle() * Math.PI / 180);
	}

}
