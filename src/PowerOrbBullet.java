import java.awt.Graphics2D;

public class PowerOrbBullet extends Projectile {
	
	private Animation moveAnimation;
	private int skippedFrames;
	
	/**
	 * Creates new PowerOrbBullet at coordinates x,y
	 * @param x x coordinate of bullet
	 * @param y y coordinate of bullet
	 */
	public PowerOrbBullet(int x, int y) {
		super("assets/bullet-3/bullet-3.png", x, y, 40, 40);
		moveAnimation = new Animation("assets/bullet-3/bullet-3(%d).png", 1, 4, 3); 
		setVelX(getVelX() + Math.random()/2-.25);
		setVelY(getVelY() + Math.random()/2-.25);
		skippedFrames = 0;
	}
	
	/**
	 * Makes velocity random direction, updates animation
	 */
	@Override
	public void act() {
		if(skippedFrames == 8){
			super.act();
			setVelX(getVelX() + Math.random()/2-.25);
			setVelY(getVelY() + Math.random()/2-.25);
			moveAnimation.update();
			skippedFrames = 0;
		}
		skippedFrames++;
	}
	
	/**
	 * Draws bullet at given location
	 */
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(moveAnimation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
	}

}