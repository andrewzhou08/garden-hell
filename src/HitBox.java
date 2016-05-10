import java.awt.Rectangle;

public class HitBox {
	
	private Rectangle rect;
	private int width, height;
	private int x, y;
	
	public HitBox(int x, int y, int width, int height){
		rect = new Rectangle(x, y, width, height);
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public boolean intersects(HitBox h){
		return rect.intersects(h.getRectangle());
	}
	
	public Rectangle getRectangle(){
		return rect;
	}
	
	public void move(int newX, int newY){
		rect = new Rectangle(newX, newY, width, height);
	}
	
	public void resize(int newWidth, int newHeight){
		rect = new Rectangle(x, y, newWidth, newHeight);
	}
	
	
}
