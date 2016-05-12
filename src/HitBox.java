import java.awt.Rectangle;

public class HitBox {
	
	private Rectangle rect;
	private int width, height;
	private double x, y;
	
	public HitBox(double x, double y, int width, int height){
		rect = new Rectangle((int)x, (int)y, width, height);
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
	
	public void move(double newX, double newY){
		rect = new Rectangle((int)newX, (int)newY, width, height);
	}
	
	public void resize(int newWidth, int newHeight){
		rect = new Rectangle((int)x, (int)y, newWidth, newHeight);
	}
	
	
}
