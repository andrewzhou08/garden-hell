import java.awt.Graphics2D;

public class Projectile extends Actor {
	
	public static final double BULLET_SPEED = 0.2;
	private double velX;
	private double velY;
	
	/**
	 * Creates new projectile at coordinates x,y and size width,height
	 * @param x x coordinate of projectile
	 * @param y y coordinate of projectile
	 * @param width width of projectile
	 * @param height height of projectile
	 */
	public Projectile(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	/**
	 * Creates new projectile at coordinates x,y and size width,height and image filename sprite
	 * @param sprite filename of the image
	 * @param x x coordinate of projectile
	 * @param y y coordinate of projectile
	 * @param width width of projectile
	 * @param height height of projectile
	 */
	public Projectile(String sprite, int x, int y, int width, int height) {
		super(sprite, x, y, width, height);
	}
	
	/**
	 * 
	 * @param newVelX new x velocity of projectile
	 */
	public void setVelX(double newVelX) {
		velX = newVelX;
	}
	
	/**
	 * 
	 * @param newVelY new y velocity of projectile
	 */
	public void setVelY(double newVelY) {
		velY = newVelY;
	}
	
	/**
	 * 
	 * @return x velocity
	 */
	public double getVelX() {
		return velX;
	}
	
	/**
	 * 
	 * @return y velocity
	 */
	public double getVelY() {
		return velY;
	}

	/**
	 * Moves projectile by velocity, updates projectile
	 */
	@Override
	public void act() {
		double tempX = velX, tempY = velY;
		
		if(tempX > 0)
			tempX += 0.5;
		else
			tempX -= 0.5;
		
		if(tempY > 0)
			tempY += 0.5;
		else
			tempY -= 0.5;
		
		moveBy((int)(tempX), (int)(tempY));
	}

	/**
	 * Draws projectile at given coordinates
	 */
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
	}
}
