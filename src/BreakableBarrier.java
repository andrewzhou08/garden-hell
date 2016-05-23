import java.awt.Color;

import java.awt.Graphics2D;

public class BreakableBarrier extends Barrier {
	
	private Animation breakAnimation;
	private int currentHealth;
	private int maxHealth;
	private EasySound breakingSound;
	private int tempTime;
	
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
		tempTime = -1;
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
		tempTime = -1;
	}
	
	/**
	 * Creates new breakable barrier of coordinates x, y and width and height of width,height
	 * @param x x coordinate of barrier
	 * @param y y coordinate of barrier
	 * @param width width of barrier
	 * @param height height of barrier
	 * @param useRealCoords signifies usage of real coordinates
	 * @param tempTime time the barrier will be alive
	 */
	public BreakableBarrier(int x, int y, int width, int height, int tempTime, boolean useRealCoords) {
		super("assets/barrier-breakable.png", x, y, width, height, useRealCoords);
		breakAnimation = new Animation("assets/barrier-breakable/barrier-breakable-breaking(%d).png", 1, 10, 1); 
		currentHealth = 500;
		maxHealth = 500;
		breakingSound = new EasySound("assets/Barrier.wav");
		this.tempTime = tempTime;
	}
	
	/**
	 * If the barrier has no more health, does break animation
	 */
	public void act() {
		if(tempTime == 0)
			currentHealth = 0;
		if (currentHealth <= 0) {
			breakAnimation.update();
		}
		tempTime--;
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

	/**
	 * 
	 * @return if the animation has completed
	 */
	public boolean animationComplete() {
		return breakAnimation.getCurrentFrameID() == breakAnimation.length() - 1 ? true : false;
	}
	
	/**
	 * Plays sound of barrier breaking
	 */
	public void playSound(){
		breakingSound.play();
	}

	/**
	 * Sets temp time of barrier
	 * @param time time it takes to destroy
	 */
	public void setTempTime(int time){
		tempTime = time;
	}
	
	/**
	 * 
	 * @return time it takes to destroy barrier
	 */
	public int getTempTime(){
		return tempTime;
	}
}
