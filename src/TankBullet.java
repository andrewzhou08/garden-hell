import java.awt.Color;
import java.awt.Graphics;

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
	
	public void draw(Graphics g){
		super.draw(g);
		
		g.setColor(Color.BLACK);
		g.fillOval(super.getX(), super.getY(), super.getHeight(), super.getWidth());
	}

}
