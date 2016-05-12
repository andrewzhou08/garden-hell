import java.awt.Graphics2D;

public class LaserTurret extends Actor implements Turret {
	
	public LaserTurret(int x, int y, int width, int height){
		super(x,y,width,height);
	}
	
	@Override
	public Projectile shoot() {
		return null;
	}

	@Override
	public void updateCoordinates(int newX, int newY) {
		
	}

	@Override
	public void draw(Graphics2D g2) {
		
	}

	@Override
	public void act() {
		
	}
}
