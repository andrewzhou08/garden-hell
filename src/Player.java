import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class Player extends Actor {
	
	public static final int DIR_RIGHT = 0;
	public static final int DIR_UP = 90;
	public static final int DIR_LEFT = 180;
	public static final int DIR_DOWN = 270;
	public static final int DIR_UP_RIGHT = 45;
	public static final int DIR_UP_LEFT = 135;
	public static final int DIR_DOWN_RIGHT = 315;
	public static final int DIR_DOWN_LEFT = 225;

	private double angle;
	private int speed;
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
		speed = 3;
	}

	@Override
	public void act() {
		updateAngle();
		if (!up && !down && !left && !right && standAnimation != null) {
			standAnimation.update();
		}
		else if (moveAnimation != null) {
			moveBy((int) (Math.cos(Math.toRadians(angle)) * speed + 0.5), 
					(int) -(Math.sin(Math.toRadians(angle)) * speed + 0.5));
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
	
	public void updateAngle() {
		if (up && left) {
			angle = DIR_UP_LEFT;
		}
		else if (up && right) {
			angle = DIR_UP_RIGHT;
		}
		else if (down && left) {
			angle = DIR_DOWN_LEFT;
		}
		else if (down && right) {
			angle = DIR_DOWN_RIGHT;
		}
		else if (up) {
			angle = DIR_UP;
		}
		else if (down) {
			angle = DIR_DOWN;
		}
		else if (left) {
			angle = DIR_LEFT;
		}
		else if (right) {
			angle = DIR_RIGHT;
		}		
	}
	
	/**
	 * 
	 * @return direction the player is facing in degrees
	 */
	public double getAngle() {
		return angle;
	}
	
}