import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Turret extends Actor implements Drawable {
	
	private int currentHealth;
	private int maxHealth;
	private Animation explodeAnimation;
	
	/**
	 * Creates a turret object
	 * @param sprite sprite of the turret
	 * @param x x coordinate of the turret
	 * @param y y coordinate of the turret
	 * @param width width of the turret
	 * @param height height of the turret
	 * @param hp hp of the turret
	 */
	public Turret(String sprite, int x, int y, int width, int height, int hp) {
		super(sprite, x-1, y-1, width+2, height+2);
		this.currentHealth = hp;
		maxHealth = hp;
	}
	
	public abstract Projectile shoot();
	
	/**
	 * draws the turret
	 * @param g2 Graphics2D object to draw the turret
	 */
	public void draw(Graphics2D g2) {
		if (currentHealth <= 0) {
			g2.drawImage(explodeAnimation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
		}
		else {
			g2.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
		}
		if (currentHealth < maxHealth) {
			g2.setColor(Color.red);
			g2.drawRect(getX(), getY() + getHeight(), getWidth(), 10);
			g2.fillRect(getX(), getY() + getHeight(), (int) ((double) currentHealth / maxHealth * getWidth()), 10);
		}
	}
	
	@Override
	public void act() {
		if (currentHealth <= 0) {
			explodeAnimation.update();
		}
	}
	
	/**
	 * Sets the max health of the turret
	 * @param maxHealth max health of the turret
	 */
	public void setMaxHealth(int maxHealth){
		this.maxHealth = maxHealth;
	}
	
	/**
	 * 
	 * @return The max health of the turret
	 */
	public int getMaxHealth(){
		return maxHealth;
	}
	
	/**
	 * Sets new current health of the turret
	 * @param newCurrentHealth new current health of the turret
	 */
	public void setCurrentHealth(int newCurrentHealth){
		currentHealth = newCurrentHealth;
	}
	
	/**
	 * 
	 * @return Current health of the turret
	 */
	public int getCurrentHealth(){
		return currentHealth;
	}
	
	/**
	 * Change current health by a number
	 * @param changeBy changed by current health
	 */
	public void changeCurrentHealth(int changeBy){
		currentHealth += changeBy;
	}
	
	/**
	 * Set explode animation for when Turret has no more health
	 * @param explodeAnimation Animation object
	 */
	public void setExplodeAnimation(Animation explodeAnimation) {
		this.explodeAnimation = explodeAnimation;
	}

	public boolean animationComplete() {
		return explodeAnimation.getCurrentFrameID() == explodeAnimation.length() - 1 ? true : false;
	}
}
