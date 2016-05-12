import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class Player extends Actor {

	private double angle;
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	private boolean shoot;
	private Animation moveAnimation;
	private Animation standAnimation;
	
	// x, y is in number of grid cells, not pixels
	public Player(int x, int y, double angle) {
		super(x * Main.CELL_WIDTH, y * Main.CELL_HEIGHT, Main.CELL_WIDTH,
				Main.CELL_HEIGHT);
		this.angle = angle;
	}

	@Override
	public void act() {
		if (!up && !down && !left && !right && standAnimation != null) {
			standAnimation.update();
		}
		else if (moveAnimation != null){
			moveAnimation.update();
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(getAnimationFrame(), getX(), getY(), getWidth(), getHeight(), null);
	}
	
	public Image getAnimationFrame() {
		if (!up && !down && !left && !right && standAnimation != null) {
			return standAnimation.getCurrentFrame();
		}
		else if (moveAnimation != null) {
			return moveAnimation.getCurrentFrame();
		}
		return getSprite();
	}
	
	public void setMoveAnimation(Animation a) {
		moveAnimation = a;
	}
	
	public void setStandAnimation(Animation a) {
		standAnimation = a;
	}
	
	public void setUpPressed(boolean up) {
		this.up = up;
	}

	public void setDownPressed(boolean down) {
		this.down = down;
	}

	public void setLeftPressed(boolean left) {
		this.left = left;
	}

	public void setRightPressed(boolean right) {
		this.right = right;
	}

	public void setShootPressed(boolean shoot) {
		this.shoot = shoot;
	}
	
	public Projectile shoot(){
		return null;
	}
	
	public void setAngle(double angle){
		this.angle = angle;
	}
	
}