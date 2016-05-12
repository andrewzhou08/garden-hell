import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Actor implements Drawable {
	
	private Image sprite;
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
		this.sprite = (new ImageIcon(sprite)).getImage();
	}
	
	public Image getSprite() {
		return sprite;
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
