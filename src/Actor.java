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
	private int hp;
	
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
		hp = 100;
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
	 * returns hitbox
	 * @return hitbox of the actor
	 */
	public HitBox getHitBox() {
		return h;
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
	 * 
	 * @return health points of actor
	 */
	public int getHP(){
		return hp;
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
	 * @param direction direction of collision. 1 = up, 2 = down, 3 = left, 4 = right
	 * @return Actor that is being collided with
	 */
	public Actor willCollide(ArrayList<Actor> actors, int direction){	// 1 = up, 2 = down, 3 = left, 4 = right
		Actor out = null;
		Rectangle window = new Rectangle(0,0,Main.WINDOW_WIDTH-8,Main.WINDOW_HEIGHT-29);
		if(direction == 1)
			h.move(x, y-1);
		else if(direction == 2){
			h.move(x, y+1);
		}else if(direction == 3)
			h.move(x-1, y);
		else
			h.move(x+1, y);
		for(int i = 0; i<actors.size();i++){
			Actor a  = actors.get(i);
			if(this != a){
				if(h.intersects(a.h))
					out = a;
				if(!window.contains(h.getRectangle())){
					out = this;
				}
			}	
		}
		h.move(x, y);
		return out;
		
		
	}
	/**
	 * 
	 * @param amount amount of hp lost
	 * @return true if this character is dead
	 */
	public boolean loseHP(int amount){
		hp-=amount;
		if(hp<0)
			return true;
		return false;
	}
	
	public abstract void act(); 

	@Override
	public abstract void draw(Graphics2D g2);
	

}
