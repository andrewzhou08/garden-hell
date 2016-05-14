import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class StandardTurret extends Actor implements Turret {
	
	private Image img;
	private double angle;
	
	public StandardTurret(int x, int y, int width, int height){
		super(x, y, width, height);
		img = (new ImageIcon("assets/turret-standard/turret-standard.png")).getImage();
		angle = 0;
	}
	
	@Override
	public Projectile shoot() {
		if(angle % Math.PI/2 == 0){
			System.out.println(angle);
		}
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
		angle += Math.PI/30;
	}
}
