import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class Actor implements Drawable {
	
	private Image sprite;
	private HitBox h;
	private int width, height;
	private int x, y;
	
	/**
	 * Creates new actor with x and y coordinates of width width and height height
	 * @param x x coordinate of actor
	 * @param y y coordinate of actor
	 * @param width width of actor
	 * @param height height of actor
	 */
	public Actor(int x, int y, int width, int height) {
		h = new HitBox(x, y, width, height);
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Creates a new actor with x and y coordinates with width and height of width, height, as well as image sprite
	 * @param sprite filename of image
	 * @param x x coordinate of actor
	 * @param y y coordinate of actor
	 * @param width width of actor
	 * @param height height of actor
	 */
	public Actor(String sprite, int x, int y, int width, int height) {
		this(x, y, width, height);
		this.sprite = (new ImageIcon(sprite)).getImage();
	}
	
	/**
	 * returns image of the actor
	 * @return image of the actor
	 */
	public Image getSprite() {
		return sprite;
	}
	
	/**
	 * 
	 * @return hitbox of the actor
	 */
	public HitBox getHitBox() {
		return h;
	}
	
	/**
	 * Sets the hitbox
	 * @param h new hitbox
	 */
	public void setHitBox(HitBox h){
		this.h = h;
	}
	
	/**
	 * 
	 * @return x coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 
	 * @return y coordinate
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * 
	 * @return width of actor
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * 
	 * @return height of actor
	 */
	public int getHeight() {
		return height;
	}
	
	
	/**
	 * Moves actor to (newX, newY)
	 * @param newX new x coordinate
	 * @param newY new y coordinate
	 */
	public void move(int newX, int newY) {
		h.move(newX, newY);
		x = newX;
		y = newY;
	}
	
	/**
	 * Moves actor by movedX, movedY
	 * @param movedX how much the actor is moved by on the X axis
	 * @param movedY how much the actor is moved by on the Y axis
	 */
	public void moveBy(int movedX, int movedY) {
		h.move(x + movedX, y + movedY);
		x = x + movedX;
		y = y + movedY;
	}
	
	/**
	 * Looks through and finds the actor that is being collided with the actor
	 * @param actors ArrayList of actors to test for
	 * @param angle current actor's angle of movement
	 * @return Actor that is being collided with
	 */
	public  Actor willCollide(ArrayList<Actor> actors, double angle){
		Rectangle window = new Rectangle(0,0,Main.WINDOW_WIDTH-8,Main.WINDOW_HEIGHT-32);
		if(this instanceof Player){
			Player p =(Player)this;
			Rectangle newBoxX = null;
			Rectangle newBoxY = null;
			if(angle>0 && angle <180){
				newBoxY = new Rectangle(x,y-p.getSpeed(), width, (int) Math.round(p.getSpeed()+h.getRectangle().getHeight()));
				if(angle == 90){
					newBoxX = new Rectangle(1,1,1,1);
				}
			}
			if(angle>180 && angle <360){
				newBoxY = new Rectangle(x,y, width, (int) Math.round(p.getSpeed()+h.getRectangle().getHeight()));
				if(angle == 270){
					newBoxX = new Rectangle(1,1,1,1);
				}
			}if(angle>90 && angle <270){
				newBoxX = new Rectangle(x-p.getSpeed(),y, (int) Math.round(p.getSpeed()+h.getRectangle().getWidth()),height);
				if(angle == 180){
					newBoxY = new Rectangle(1,1,1,1);
				}
			}
			if(angle<90 || angle >271) {
				newBoxX = new Rectangle(x,y, (int) (p.getSpeed()+h.getRectangle().getWidth()),height);
				if(angle == 0){
					newBoxY = new Rectangle(1,1,1,1);
				}
			}
			
			for(int i = 0; i<actors.size();i++){
				Actor a  = actors.get(i);
				if(this != a){
					if(newBoxX.intersects(a.h.getRectangle())||newBoxY.intersects(a.h.getRectangle())){
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
	
	
	public abstract void act(); 

	@Override
	public abstract void draw(Graphics2D g2);
	

}
