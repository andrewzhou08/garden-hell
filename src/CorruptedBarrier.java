
public class CorruptedBarrier extends Barrier {
	
	private double velX, velY;
	
	public CorruptedBarrier(int x, int y, int width, int height){
		super(x, y, width, height);
		velX = 0;
		velY = 0;
	}
	
	public void move(){
		velX += 3*Math.random()-1;
		velY += 3*Math.random()-1;
		
		super.moveBy(velX, velY);
	}
}
