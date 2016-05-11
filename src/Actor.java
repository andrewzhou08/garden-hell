import java.awt.Graphics;

public abstract class Actor implements Drawable {
	
	private String sprite;
	private HitBox h;
	private int width, height;
	private int x, y;
	
	public Actor(int x, int y, int width, int height) {
		h = new HitBox(x, y, width, height);
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public Actor(String sprite, int x, int y, int width, int height) {
		this(x, y, width, height);
		this.sprite = sprite;
	}
	
	public HitBox getHitBox() {
		return h;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void move(int newX, int newY) {
		h.move(newX, newY);
		x = newX;
		y = newY;
	}
	
	public void moveBy(int movedX, int movedY) {
		h.move(x + movedX, x + movedY);
		x = x + movedX;
		y = y + movedY;
	}

	public abstract void act(); 

	@Override
	public abstract void draw(Graphics g);
	

}
