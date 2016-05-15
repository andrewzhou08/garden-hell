import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PowerOrbTurret extends Actor implements Turret {
	
	private Image img;
	private int delay;
	
	public PowerOrbTurret(int x, int y, int width, int height){
		super(x,y,width,height);
		img = (new ImageIcon("assets/turret-powerorb/turret-powerorb.png")).getImage();
		delay = 0;
	}
	
	@Override
	public Projectile shoot() {
		if(delay % 30 == 0)
			return new PowerOrbBullet(super.getX(), super.getY());
		return null;
	}

	@Override
	public void updateCoordinates(int newX, int newY) {
		super.move(newX, newY);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
	}

	@Override
	public void act() {
		delay++;
	}
}
