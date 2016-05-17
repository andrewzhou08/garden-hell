import java.awt.Color;
import java.awt.Graphics;

public class Damager extends Player {

	private int skippedFrames;
	
	public Damager(int x, int y, double angle) {
		super(x, y, angle);
		setMoveAnimation(new Animation("assets/player-damager/player-damager-moving(%d).png", 1, 4, 6));
		setStandAnimation(new Animation("assets/player-damager/player-damager-standing(%d).png", 1, 2, 16));
		super.setSpeed(5);
		super.setMaxHealth(100);
		skippedFrames = 0;
	}
	
	public Projectile shoot(){
		if(skippedFrames == 3){
			skippedFrames = 0;
			return new DamagerBullet(getX() + (getWidth() / 2 - 3), getY() + (getHeight() / 2 - 3),
					getAngle() * Math.PI / 180);
		}
		skippedFrames++;
		return null;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.green);
		g.fillOval(getX(), getY(), getWidth(), getHeight());
	}

}
