import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Player extends Actor {
	
	public static final int DIR_RIGHT = 0;
	public static final int DIR_UP = 90;
	public static final int DIR_LEFT = 180;
	public static final int DIR_DOWN = 270;
	public static final int DIR_UP_RIGHT = 45;
	public static final int DIR_UP_LEFT = 135;
	public static final int DIR_DOWN_RIGHT = 315;
	public static final int DIR_DOWN_LEFT = 225;
	
	public static final int DISTANCE_TO_BARREL = 40;
	public static final int MAX_LIVES = 10;

	private double angle;
	private int speed;
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	private boolean shoot;
	private Animation moveAnimation;
	private Animation standAnimation;
	private int maxHealth;
	private int currentHealth;
	
	private int numLives;
	
	/**
	 * Creates a new player with x,y coordinates x and y and angle angle
	 * @param x the x coordinate of the player
	 * @param y the y coordinate of the player
	 * @param angle the angle of the player
	 */
	public Player(int x, int y, double angle, int hp) {	// x, y is in number of grid cells, not pixels
		super(x * Main.CELL_WIDTH, y * Main.CELL_HEIGHT, Main.CELL_WIDTH,
				Main.CELL_HEIGHT);
		this.angle = angle;
		maxHealth = hp;
		currentHealth = hp;
		numLives = MAX_LIVES;
	}

	/**
	 * Updates player's angle and animation
	 */
	@Override
	public void act() {

		
		if (!up && !down && !left && !right && standAnimation != null) {
			standAnimation.update();
		}
		else if (moveAnimation != null) {
			//moveBy((int)Math.round( (Math.cos(Math.toRadians(angle)) * speed )), 
			//		(int) Math.round((-(Math.sin(Math.toRadians(angle)) * speed ))));
			moveX();
			moveY();
			moveAnimation.update();
		}
	}
	/**
	 * Move the player in the x component of movement
	 */
	public void moveX(){
		moveBy((int)Math.round( (Math.cos(Math.toRadians(angle)) * speed )),0);
	}
	/**
	 * Move the player in the y component of movement
	 */
	public void moveY(){
		moveBy(0,(int) Math.round((-(Math.sin(Math.toRadians(angle)) * speed ))));
	}
	/**
	 * Looks through and finds the actor that is being collided with the actor
	 * @param actors ArrayList of actors to test for
	 * @param angle current actor's angle of movement
	 * @return Actor that is being collided with
	 */
	public Actor willCollide(ArrayList<Actor> actors, double angle){
		Rectangle window = new Rectangle(0,0,Main.WINDOW_WIDTH-8,Main.WINDOW_HEIGHT-32);

		Rectangle newBoxX = null;
		Rectangle newBoxY = null;
		if(angle>0 && angle <180){
			newBoxY = new Rectangle(super.getX(),getY()-this.getSpeed(), getWidth(), (int) Math.round(this.getSpeed()+super.getHitBox().getRectangle().getHeight()));
			if(angle == 90){
				newBoxX = new Rectangle(1,1,1,1);
			}
		}
		if(angle>180 && angle <360){
			newBoxY = new Rectangle(getX(),getY(), getWidth(), (int) Math.round(this.getSpeed()+super.getHitBox().getRectangle().getHeight()));
			if(angle == 270){
				newBoxX = new Rectangle(1,1,1,1);
			}
		}if(angle>90 && angle <270){
			newBoxX = new Rectangle(getX()-this.getSpeed(),getY(), (int) Math.round(this.getSpeed()+super.getHitBox().getRectangle().getWidth()),getHeight());
			if(angle == 180){
				newBoxY = new Rectangle(1,1,1,1);
			}
		}
		if(angle<90 || angle >271) {
			newBoxX = new Rectangle(getX(),getY(), (int) (this.getSpeed()+super.getHitBox().getRectangle().getWidth()),getHeight());
			if(angle == 0){
				newBoxY = new Rectangle(1,1,1,1);
			}
		}
		
		for(int i = 0; i<actors.size();i++){
			Actor a  = actors.get(i);
			if(this != a){
				if(!(a instanceof Turret)){
					if(newBoxX.intersects(a.getHitBox().getRectangle())||newBoxY.intersects(a.getHitBox().getRectangle())){
						return a;
					}
					else if(!window.contains(newBoxX)||!window.contains(newBoxY)){
						return this;
					}
				}
				
			}	
		}
	
		
		
		return null;
		
		
	
	}

	/**
	 * Draws the player, updates angles
	 * @param g2 The Graphics2D of the player
	 */
	@Override
	public void draw(Graphics2D g2) {
		g2.rotate(-Math.toRadians(angle), getX() + getWidth() / 2, getY() + getHeight() / 2);
		g2.drawImage(getAnimationFrame(), getX(), getY(), getWidth(), getHeight(), null);
		g2.rotate(Math.toRadians(angle), getX() + getWidth() / 2, getY() + getHeight() / 2);
		g2.setColor(Color.green);
		g2.drawRect(getX(), getY()+getHeight(), getWidth(), 10);
		g2.fillRect(getX(), getY()+getHeight(), (int)((double)currentHealth/maxHealth *getWidth()), 10);
	}
	
	/**
	 * 
	 * @return frame of the player's animation
	 */
	public Image getAnimationFrame() {
		if (!up && !down && !left && !right && standAnimation != null) {
			return standAnimation.getCurrentFrame();
		}
		else if (moveAnimation != null) {
			return moveAnimation.getCurrentFrame();
		}
		return getSprite();
	}
	
	/**
	 * Sets move animation to a
	 * @param a the animation of the player when moving
	 */
	public void setMoveAnimation(Animation a) {
		moveAnimation = a;
	}
	
	/**
	 * Sets stand animation to a
	 * @param a the animation of the player while standing
	 */
	public void setStandAnimation(Animation a) {
		standAnimation = a;
	}
		
	/**
	 * Sets up pressed to true or false
	 * @param up if button up is pressed
	 */
	public void setUpPressed(boolean up) {
		this.up = up;
	}

	/**
	 * Sets down pressed to true or false
	 * @param down if button down is pressed
	 */
	public void setDownPressed(boolean down) {
		this.down = down;
	}

	/**
	 * Sets left pressed to true or false
	 * @param left if button left is pressed
	 */
	public void setLeftPressed(boolean left) {
		this.left = left;
	}

	/**
	 * Sets right pressed to true or false
	 * @param right if button right is pressed
	 */
	public void setRightPressed(boolean right) {
		this.right = right;
	}

	/**
	 * Sets shoot pressed to true or false
	 * @param shoot if the shoot button is pressed
	 */
	public void setShootPressed(boolean shoot) {
		this.shoot = shoot;
	}
	
	/**
	 * 
	 * @return projectile of player
	 */
	public Projectile shoot(){
		return null;
	}
	
	/**
	 * Sets angle
	 * @param angle new angle of player
	 */
	public void setAngle(double angle){
		this.angle = angle;
	}
	
	/**
	 * Updates angle based on keys pressed
	 */
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
	 * updates the angle of the player and return the updated angle
	 * @return angle updated angle of the player
	 */
	public double getAngle(){
		updateAngle();
		return angle;
	}
	
	/**
	 * updates the angle of the player and return the updated angle in components
	 * @return direction the player is facing in degrees using components
	 */
	public double[] getAngleArray() {
		updateAngle();
		double[] angles = new double[2];
		if(angle==0){
			angles[0] = 0;
			angles[1] = 0;
		}else if(angle == 45){
			angles[0] = 0;
			angles[1] = 90;
		}else if(angle == 90){
			angles[0] = 90;
			angles[1] = 90;
		}else if(angle == 135){
			angles[0] = 180;
			angles[1] = 90;
		}else if(angle == 180){
			angles[0] = 180;
			angles[1] = 180;
		}else if(angle == 225){
			angles[0] = 180;
			angles[1] = 270;
		}else if(angle == 270){
			angles[0] = 270;
			angles[1] = 270;
		}else if(angle == 315){
			angles[0] = 0;
			angles[1] = 270;
		}
		return angles;
	}
	
	/**
	 * 
	 * @return speed of player
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Sets speed of player
	 * @param speed new speed of Player
	 */
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	/**
	 * Sets the max health of the player
	 * @param maxHealth max health of the player
	 */
	public void setMaxHealth(int maxHealth){
		this.maxHealth = maxHealth;
	}
	
	/**
	 * 
	 * @return The max health of the player
	 */
	public int getMaxHealth(){
		return maxHealth;
	}
	
	/**
	 * Sets new current health of the player
	 * @param newCurrentHealth new current health of the player
	 */
	public void setCurrentHealth(int newCurrentHealth){
		currentHealth = newCurrentHealth;
	}
	
	/**
	 * 
	 * @return Current health of the player
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
	 * Sets number of lives to new lives
	 * @param newLives new lives of the player
	 */
	public void setLives(int newLives){
		numLives = newLives;
	}
	
	/**
	 * 
	 * @return Number of lives player has
	 */
	public int getLives(){
		return numLives;
	}
}