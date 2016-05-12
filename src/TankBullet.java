import java.awt.Color;
import java.awt.Graphics2D;

public class TankBullet extends Projectile {
	
	private double angle;
	
	public TankBullet(int x, int y, int width, int height, double angle) {
		super(x, y, width, height);
		
		double velX = Math.cos(angle) * super.BULLET_SPEED;
		double velY = Math.sin(angle) * super.BULLET_SPEED;
		
		System.out.println(velX + " " + velY);
		
		super.setVelX(velX);
		super.setVelY(velY);
	}
	
	public void act(){
		super.act();
	}
	
	public void draw(Graphics2D g2){
		super.draw(g2);
		
		g2.setColor(Color.BLACK);
		g2.fillOval(super.getX(), super.getY(), super.getHeight(), super.getWidth());
	}

}
