

public class Builder extends Player {
	
	public static final int HP = 700;
	private int skippedFrames;
	
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
		super.setSpeed(3);
		skippedFrames = 0;
	}
	
	/**
	 * Returns shot projectile
	 * @return Projectile shot
	 */
	public Projectile shoot(){
		if(skippedFrames == 3){
			skippedFrames = 0;
			return new BuilderBullet((int)(getX() + 20 + Player.DISTANCE_TO_BARREL*Math.cos(super.getAngle()*Math.PI/180)),
					(int)(getY() + 20 + Player.DISTANCE_TO_BARREL*-Math.sin(super.getAngle()*Math.PI/180)),
					getAngle() * Math.PI / 180);
		}
		skippedFrames++;
		return null;
	}
	

}
