import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

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
		h.move(x + movedX, y + movedY);
		x = x + movedX;
		y = y + movedY;
	}
	// 1 = up, 2 = down, 3 = left, 4 = right
	public boolean willCollide(ArrayList<Actor> actors, int direction){
		boolean out = false;
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
					out = true;
				if(!window.contains(h.getRectangle())){
					out = true;
				}
			}	
		}
		h.move(x, y);
		return out;
		
		
	}
	
	public abstract void act(); 

	@Override
	public abstract void draw(Graphics g);
	

}
