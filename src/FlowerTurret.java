import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class FlowerTurret extends Actor implements Turret {
	
	private Image img;
	private double angle;
	
	public FlowerTurret(int x, int y, int width, int height){
		super(x, y, width, height);
		img = (new ImageIcon("assets/turret-flower/turret-flower.png")).getImage();
	}
	
	@Override
	public Projectile shoot() {
		System.out.println(angle);
		return new FlowerBullet(super.getX(), super.getY(), super.getWidth()/2, super.getHeight()/2, angle*Math.PI/180);
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
		angle += 15;
	}
}
