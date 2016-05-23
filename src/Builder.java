

public class Builder extends Player {
	
	public static final int HP = 5200;
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
			playShootingSound();
			return new BuilderBullet((int)(getX() + 20 + Player.DISTANCE_TO_BARREL*Math.cos(super.getAngle()*Math.PI/180)),
					(int)(getY() + 20 + Player.DISTANCE_TO_BARREL*-Math.sin(super.getAngle()*Math.PI/180)),
					getAngle() * Math.PI / 180);
			
		}
		skippedFrames++;
		return null;
	}
	
	/**
	 * initiates the builder's special attack
	 * @return a breakable barrier in front of the builder
	 */
	public BreakableBarrier initiateSpecial(){
		double angle = super.getAngle();
		
		double cosX = Math.cos(angle*Math.PI/180);
		double cosY = -Math.sin(angle*Math.PI/180);
		
		if(cosX >= 0)
			cosX = (int)(cosX+0.5);
		else if(cosX <= 0)
			cosX = (int)(cosX-0.5);
		if(cosY >= 0)
			cosY = (int)(cosY+0.5);
		else if(cosY <= 0)
			cosY = (int)(cosY-0.5);
		
		int x = (int)(getX() + Main.CELL_WIDTH*cosX);
		int y = (int)(getY() + Main.CELL_HEIGHT*cosY);
		
		return new BreakableBarrier(x, y, 40, 40, 150, true);
	}
}
