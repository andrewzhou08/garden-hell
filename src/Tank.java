import java.awt.Color;
import java.awt.Graphics;

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
	
	public Projectile shoot(){
		return new TankBullet(super.getX()+(super.getWidth()/2-3),super.getY()+(super.getHeight()/2-3),6,6, getAngle()*Math.PI/180);
	}
	
	public void act(){
		super.act();
	}

}
