import java.awt.Graphics;

public class Projectile extends Actor {
	
	public static final int BULLET_SPEED = 1;
	private double velX;
	private double velY;
	
	public Projectile(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void setVelX(double newVelX){
		velX = newVelX;
	}
	
	public void setVelY(double newVelY){
		velY = newVelY;
	}
	
	public double getVelX(){
		return velX;
	}
	
	public double getVelY(){
		return velY;
	}

	@Override
	public void act() {
		super.moveBy((int)velX, (int)velY);
	}

	@Override
	public void draw(Graphics g) {
		
	}
}
