import java.util.ArrayList;

public class Damager extends Player {

	public static final int HP = 4500;
	private int skippedFrames;
	
	/**
	 * Creates new damager character of coordinates x,y
	 * @param x x coordinate of damager
	 * @param y y coordinate of damager
	 * @param angle angle of damager
	 */
	public Damager(int x, int y, double angle) {
		super(x, y, angle, HP);
		setMoveAnimation(new Animation("assets/player-damager/player-damager-moving(%d).png", 1, 4, 6));
		setStandAnimation(new Animation("assets/player-damager/player-damager-standing(%d).png", 1, 2, 16));
		setSpeed(5);
		skippedFrames = 0;
	}
	/**
	 * Returns shot projectile
	 * @return Projectile shot
	 */
	public Projectile shoot(){
		if (skippedFrames == 3) {
			skippedFrames = 0;
			playShootingSound();
			return new DamagerBullet(
					(int) (getX() + 20 + Player.DISTANCE_TO_BARREL * Math.cos(super.getAngle() * Math.PI / 180)),
					(int) (getY() + 20 + Player.DISTANCE_TO_BARREL * -Math.sin(super.getAngle() * Math.PI / 180)),
					getAngle() * Math.PI / 180);
		}
		skippedFrames++;
		return null;
	}
	
	/**
	 * Returns bullets on all eight sides
	 * @return bullets for each of the eight sides
	 */
	
	public ArrayList<Projectile> initiateSpecial() {
		ArrayList<Projectile> special = new ArrayList<Projectile>(8);
		for (int i = 0; i < 8; i++) {
			special.add(new DamagerBullet(
					(int) (getX() + 20 + Player.DISTANCE_TO_BARREL * Math.cos(i * 45 * Math.PI / 180)),
					(int) (getY() + 20 + Player.DISTANCE_TO_BARREL * -Math.sin(i * 45 * Math.PI / 180)),
					i * 45 * Math.PI / 180));
		}
		return special;
	}
	
	public void initiateUltimate(ArrayList<Actor> actors, ArrayList<Barrier> barriers, ArrayList<Projectile> bullets) {
		super.initiateUltimate(actors, barriers, bullets);
		bullets.add(new DamagerFreezeBullet(
				(int) (getX() + (Player.DISTANCE_TO_BARREL + 20) * Math.cos(super.getAngle() * Math.PI / 180)),
				(int) (getY() + (Player.DISTANCE_TO_BARREL + 20) * -Math.sin(super.getAngle() * Math.PI / 180)),
				getAngle() * Math.PI / 180));
	}
	
}
