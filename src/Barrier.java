
public class Barrier implements Drawable {
	
	private HitBox h;
	private int width, height;
	private int x, y;
	
	public Barrier(int x, int y, int width, int height){
		HitBox h = new HitBox(x, y, width, height);
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public void becomeCorrupt(){
		
	}
	
	public HitBox getHitBox(){
		return h;
	}
	
	
	
	public void draw(){
		
	}
}
