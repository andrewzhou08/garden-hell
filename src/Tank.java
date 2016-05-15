import java.awt.Color;
import java.awt.Graphics;

public class Tank extends Player {

	private double angle; //Degrees
	
	/**
	 * Creates new tank character of coordinates x,y
	 * @param x x coordinate of tank
	 * @param y y coordinate of tank
	 */
	public Tank(int x, int y, double angle) {
		super(x, y, angle);
		setMoveAnimation(new Animation("assets/player-tank/player-tank-moving(%d).png", 1, 4, 4));
		setStandAnimation(new Animation("assets/player-tank/player-tank-standing(%d).png", 1, 2, 8));
	}
	
	public Projectile shoot(){
		return new TankBullet(super.getX()+(super.getWidth()/2-3),super.getY()+(super.getHeight()/2-3),6,6, angle*Math.PI/180);
	}
	
	public void act(){
		super.act();
	}
	
	public void setAngle(double angle){
		super.setAngle(angle);
		System.out.println(angle);
	}

}
