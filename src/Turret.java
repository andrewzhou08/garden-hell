import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Turret extends Actor implements Drawable {
	
	private int currentHealth;
	private int maxHealth;

	public Turret(String sprite, int x, int y, int width, int height, int hp) {
		super(sprite, x, y, width, height);
		this.currentHealth = hp;
		maxHealth = hp;
	}
	
	public abstract Projectile shoot();
	
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
		g2.setColor(Color.red);
		g2.drawRect(getX(), getY()+getHeight(), getWidth(), 10);
		g2.fillRect(getX(), getY()+getHeight(), (int)((double)currentHealth/maxHealth *getWidth()), 10);
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
}
