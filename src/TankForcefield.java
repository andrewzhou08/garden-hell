import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class TankForcefield extends Actor {

	private Image img;
	private Tank t;
	
	public TankForcefield(Tank t) {
		super(t.getX()-40, t.getY()-40, 120, 120);
		img = (new ImageIcon("assets/forcefield.png")).getImage();
		this.t = t;
	}

	@Override
	public void act() {
		super.move(t.getX()-40, t.getY()-40);
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(img, getX(), getY(), getWidth(), getHeight(), null);
	}
	
}
