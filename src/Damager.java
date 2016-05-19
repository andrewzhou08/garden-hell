import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Damager extends Player {

	public static final int HP = 150;
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
		super.setSpeed(5);
		skippedFrames = 0;
	}
	/**
	 * Returns shot projectile
	 * @return Projectile shot
	 */
	public Projectile shoot(){
		if(skippedFrames == 3){
			skippedFrames = 0;
			return new DamagerBullet((int)(getX() + 20 + Player.DISTANCE_TO_BARREL*Math.cos(super.getAngle()*Math.PI/180)),
					(int)(getY() + 20 + Player.DISTANCE_TO_BARREL*-Math.sin(super.getAngle()*Math.PI/180)),
					getAngle() * Math.PI / 180);
		}
		skippedFrames++;
		return null;
	}
	
	
	

}
