import java.awt.Color;

import java.awt.Graphics2D;

public class BreakableBarrier extends Barrier {
	
	private Animation breakAnimation;
	private int currentHealth;
	private int maxHealth;
	private EasySound breakingSound;
	
	/**
	 * Creates new breakable barrier of coordinates x, y and width and height of width,height
	 * @param x x coordinate of barrier - number in grid cells, not pixels
	 * @param y y coordinate of barrier - number in grid cells, not pixels
	 * @param width width of barrier
	 * @param height height of barrier
	 */
	public BreakableBarrier(int x, int y, int width, int height) {
		super("assets/barrier-breakable.png", x, y, width, height);
		breakAnimation = new Animation("assets/barrier-breakable/barrier-breakable-breaking(%d).png", 1, 10, 1); 
		currentHealth = 500;
		maxHealth = 500;
		breakingSound = new EasySound("assets/Barrier.wav");
	}
	
	/**
	 * Creates new breakable barrier of coordinates x, y and width and height of width,height
	 * @param x x coordinate of barrier
	 * @param y y coordinate of barrier
	 * @param width width of barrier
	 * @param height height of barrier
	 * @param useRealCoords signifies usage of real coordinates
	 */
	public BreakableBarrier(int x, int y, int width, int height, boolean useRealCoords) {
		super("assets/barrier-breakable.png", x, y, width, height, useRealCoords);
		breakAnimation = new Animation("assets/barrier-breakable/barrier-breakable-breaking(%d).png", 1, 10, 1); 
		currentHealth = 500;
		maxHealth = 500;
		breakingSound = new EasySound("assets/Barrier.wav");
	}
	
	public void act() {
		if (currentHealth <= 0) {
			breakAnimation.update();
		}
	}
	
	/**
	 * Draws barrier onto screen with specified characteristics
	 */
	@Override
	
	public void draw(Graphics2D g2) {
		if (currentHealth <= 0) {
			g2.drawImage(breakAnimation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
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

	/**
	 * Sets the max health of the barrier
	 * @param maxHealth max health of the barrier
	 */
	public void setMaxHealth(int maxHealth){
		this.maxHealth = maxHealth;
	}
	
	/**
	 * 
	 * @return The max health of the barrier
	 */
	public int getMaxHealth(){
		return maxHealth;
	}
	
	/**
	 * Sets new current health of the barrier
	 * @param newCurrentHealth new current health of the barrier
	 */
	public void setCurrentHealth(int newCurrentHealth){
		currentHealth = newCurrentHealth;
	}
	
	/**
	 * 
	 * @return Current health of the barrier
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

	public boolean animationComplete() {
		return breakAnimation.getCurrentFrameID() == breakAnimation.length() - 1 ? true : false;
	}
	
	public void playSound(){
		breakingSound.play();
	}

}
