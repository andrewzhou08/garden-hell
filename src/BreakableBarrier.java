import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class BreakableBarrier extends Barrier {
	
	private Image img;
	private int currentHealth;
	private int maxHealth;
	
	/**
	 * Creates new breakable barrier of coordinates x, y and width and height of width,height
	 * @param x x coordinate of barrier - number in grid cells, not pixels
	 * @param y y coordinate of barrier - number in grid cells, not pixels
	 * @param width width of barrier
	 * @param height height of barrier
	 */
	public BreakableBarrier(int x, int y, int width, int height){
		super(x, y, width, height);
		img = (new ImageIcon("assets/barrier-breakable.png")).getImage();
		currentHealth = 50;
		maxHealth = 50;
	}
	
	public void act(){
		
	}
	
	/**
	 * Draws barrier onto screen with specified characteristics
	 */
	@Override
	public void draw(Graphics2D g){
		g.drawImage(img, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
		g.setColor(Color.red);
		g.drawRect(getX(), getY()+getHeight(), getWidth(), 10);
		g.fillRect(getX(), getY()+getHeight(), (int)((double)currentHealth/maxHealth *getWidth()), 10);
	}
	
	/**
	 * Plays breaking animations
	 */
	public void playBreakAnimation(){
		
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
}