import java.awt.Color;
import java.awt.Graphics2D;

public class TankBullet extends Projectile {
	
	public TankBullet(int x, int y, double angle) {
		super(x, y, 6, 6);
		
		double velX = Math.cos(angle) * super.BULLET_SPEED;
		double velY = -Math.sin(angle) * super.BULLET_SPEED;
		
		System.out.println(velX + " " + velY);
		
		setVelX(velX);
		setVelY(velY);
	}
	
	public void act() {
		super.act();
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillOval(getX(), getY(), getHeight(), getWidth());
	}

}
