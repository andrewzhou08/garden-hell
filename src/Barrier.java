
public class Barrier implements Drawable {
	
	private HitBox h;
	private int width, height;
	private double x, y;
	
	public Barrier(double x, double y, int width, int height){
		HitBox h = new HitBox(x, y, width, height);
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public HitBox getHitBox(){
		return h;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void move(double newX, double newY){
		h.move(newX, newY);
		x = newX;
		y = newY;
	}
	
	public void moveBy(double movedX, double movedY){
		h.move(x+movedX, x+movedY);
		x = x + movedX;
		y = y + movedY;
	}
	
	public void draw(){
		
	}
}
