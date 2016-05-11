
public class CorruptedBarrier extends Barrier {
	
	private double velX, velY;
	private Turret t;
	
	public CorruptedBarrier(int x, int y, int width, int height, Turret turret){
		super(x, y, width, height);
		velX = 0;
		velY = 0;
		t = turret;
	}
	
	public void move(){
		velX += 3*Math.random()-1;
		velY += 3*Math.random()-1;
		
		super.moveBy(velX, velY);
	}
	
	public Turret getTurret(){
		return t;
	}
}
