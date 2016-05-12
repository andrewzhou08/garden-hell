import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class StandardTurret extends Actor implements Turret {
	
	private Image img;
	private double angle;
	private int delay;
	
	public StandardTurret(int x, int y, int width, int height){
		super(x, y, width, height);
		img = (new ImageIcon("assets/turret-standard/turret-standard.png")).getImage();
		angle = 0;
		delay = 0;
	}
	
	@Override
	public Projectile shoot() {
		if(delay % 15 == 0){
			delay = 0;
			angle = Math.random()*360;
			return new StandardBullet(super.getX(), super.getY(), super.getWidth()/2,super.getHeight()/2,angle*Math.PI/180);
		}
		return null;
	}

	@Override
	public void updateCoordinates(int newX, int newY) {
		super.move(newX, newY);
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(img, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
	}

	@Override
	public void act() {
		delay++;
	}
}
