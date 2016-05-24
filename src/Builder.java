import java.util.ArrayList;

public class Builder extends Player {
	
	public static final int HP = 5200;
	private int skippedFrames;
	private Player ultimateTarget;
	
	/**
	 * Creates new Builder character of coordinates x,y
	 * @param x x coordinate of builder
	 * @param y y coordinate of builder
	 * @param angle angle of builder
	 */
	public Builder(int x, int y, double angle) {
		super(x, y, angle, HP);
		setMoveAnimation(new Animation("assets/player-builder/player-builder-moving(%d).png", 1, 4, 6));
		setStandAnimation(new Animation("assets/player-builder/player-builder-standing(%d).png", 1, 2, 16));
		setSpeed(3);
		skippedFrames = 0;
	}
	
	/**
	 * Returns shot projectile
	 * @return Projectile shot
	 */
	public Projectile shoot() {
		if (skippedFrames == 3) {
			skippedFrames = 0;
			playShootingSound();
			return new BuilderBullet(
					(int) (getX() + 20 + Player.DISTANCE_TO_BARREL * Math.cos(getAngle() * Math.PI / 180)),
					(int) (getY() + 20 + Player.DISTANCE_TO_BARREL * -Math.sin(getAngle() * Math.PI / 180)),
					getAngle() * Math.PI / 180);
			
		}
		skippedFrames++;
		return null;
	}
	
	/**
	 * initiates the builder's special attack
	 */
	public BreakableBarrier initiateSpecial() {
		double cosX = Math.cos(getAngle() * Math.PI / 180);
		double cosY = -Math.sin(getAngle() * Math.PI /180);
		
		if (cosX >= 0)
			cosX = (int) (cosX + 0.5);
		else if (cosX <= 0)
			cosX = (int) (cosX - 0.5);
		if (cosY >= 0)
			cosY = (int) (cosY + 0.5);
		else if (cosY <= 0)
			cosY = (int) (cosY - 0.5);
		
		int x = (int) (getX() + Main.CELL_WIDTH * cosX);
		int y = (int) (getY() + Main.CELL_HEIGHT * cosY);
		
		return new BreakableBarrier(x, y, 40, 40, 150, true);
	}
	
	/**
	 * The Builder ultimate attack creates barriers surrounding the opponent.
	 */
	public void initiateUltimate(ArrayList<Actor> actors, ArrayList<Barrier> barriers, ArrayList<Projectile> bullets) {
		super.initiateUltimate(actors, barriers, bullets);
		ArrayList<BreakableBarrier> ultBarriers = new ArrayList<BreakableBarrier>();
//		ultBarriers.add(new BreakableBarrier(ultimateTarget.getX(), ultimateTarget.getY(), 40, 40, true));
		ultBarriers.add(new BreakableBarrier(ultimateTarget.getX()-40, ultimateTarget.getY(), 40, 40, true));
		ultBarriers.add(new BreakableBarrier(ultimateTarget.getX()-40, ultimateTarget.getY()-40, 40, 40, true));
		ultBarriers.add(new BreakableBarrier(ultimateTarget.getX(), ultimateTarget.getY()-40, 40, 40, true));
		ultBarriers.add(new BreakableBarrier(ultimateTarget.getX()+40, ultimateTarget.getY()-40, 40, 40, true));
		ultBarriers.add(new BreakableBarrier(ultimateTarget.getX()+40, ultimateTarget.getY(), 40, 40, true));
		ultBarriers.add(new BreakableBarrier(ultimateTarget.getX()+40, ultimateTarget.getY()+40, 40, 40, true));
		ultBarriers.add(new BreakableBarrier(ultimateTarget.getX(), ultimateTarget.getY()+40, 40, 40, true));
		ultBarriers.add(new BreakableBarrier(ultimateTarget.getX()-40, ultimateTarget.getY()+40, 40, 40, true));
		barriers.addAll(ultBarriers);
		actors.addAll(ultBarriers);
	}
	
	/**
	 * Since the Builder's ultimate is to create barriers surrounding the opponent, this method
	 * is to set which player the ultimate attack is targeting
	 * @param ultimateTarget player to target
	 */
	public void setUltimateTarget(Player ultimateTarget) {
		this.ultimateTarget = ultimateTarget;
	}
}
