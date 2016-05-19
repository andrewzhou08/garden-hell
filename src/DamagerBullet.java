import java.awt.Color;
import java.awt.Graphics2D;

public class DamagerBullet extends Projectile {
	
	public DamagerBullet(int x, int y, double angle){
		super(x, y, 6, 6);
		
		double velX = Math.cos(angle) * super.BULLET_SPEED;
		double velY = -Math.sin(angle) * super.BULLET_SPEED;
		
		setVelX(velX);
		setVelY(velY);
	}
	
	public void act() {
		super.act();
	}
	
	public void draw(Graphics2D g2){
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillOval(getX(), getY(), getHeight(), getWidth());
	}
}
