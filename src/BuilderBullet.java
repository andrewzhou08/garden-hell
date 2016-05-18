import java.awt.Color;
import java.awt.Graphics2D;

public class BuilderBullet extends Projectile {
	
	public BuilderBullet(int x, int y, double angle){
		super(x, y, 6, 6);
		
		double velX = Math.cos(angle) * super.BULLET_SPEED*6;
		double velY = -Math.sin(angle) * super.BULLET_SPEED*6;
		
		setVelX(velX);
		setVelY(velY);
	}
	
	public void act() {
		super.act();
	}
	
	public void draw(Graphics2D g2){
		g2.setColor(Color.RED);
		g2.fillOval(getX(), getY(), getHeight(), getWidth());
	}
}
