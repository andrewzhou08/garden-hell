import java.awt.Color;
import java.awt.Graphics;

public class Builder extends Player {

	private int skippedFrames;
	
	public Builder(int x, int y, double angle) {
		super(x, y, angle);
		setMoveAnimation(new Animation("assets/player-builder/player-builder-moving(%d).png", 1, 4, 6));
		setStandAnimation(new Animation("assets/player-builder/player-builder-standing(%d).png", 1, 2, 16));
		super.setSpeed(3);
		super.setMaxHealth(120);
		skippedFrames = 0;
	}
	
	public Projectile shoot(){
		if(skippedFrames == 3){
			skippedFrames = 0;
			return new BuilderBullet(getX() + (getWidth() / 2 - 3), getY() + (getHeight() / 2 - 3),
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
