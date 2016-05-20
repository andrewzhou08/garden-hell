import java.awt.Rectangle;

public class HitBox {
	
	private Rectangle rect;
	private int width, height;
	private double x, y;
	
	/**
	 * Creates new hitbox of coordinates x,y and size width,height
	 * @param x x coordinate of the hitbox
	 * @param y y coordinate of the hitbox
	 * @param width width of the hitbox
	 * @param height height of the hitbox
	 */
	public HitBox(double x, double y, int width, int height){
		rect = new Rectangle((int)x, (int)y, width, height);
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Tests if h intersects the hitbox
	 * @param h tested hitbox
	 * @return true if they intersect, false otherwise
	 */
	public boolean intersects(HitBox h){
		return rect.intersects(h.getRectangle());
	}
	
	/**
	 * 
	 * @return Rectangle used in the hitbox
	 */
	public Rectangle getRectangle(){
		return rect;
	}
	
	/**
	 * Moves hitbox to needed location
	 * @param newX new x coordinate
	 * @param newY new y coordinate
	 */
	public void move(double newX, double newY){
		rect = new Rectangle((int)newX, (int)newY, width, height);
	}
	
	/**
	 * Resizes hitbox to needed size
	 * @param newWidth new width
	 * @param newHeight new height
	 */
	public void resize(int newWidth, int newHeight){
		rect = new Rectangle((int)x, (int)y, newWidth, newHeight);
	}
	
	public int getX(){
		return (int)x;
	}
	
	public int getY(){
		return (int)y;
	}
	
	
}
