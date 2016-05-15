import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class StandardTurret extends Turret {

	private double angle;
	private int delay;

	public StandardTurret(int x, int y, int width, int height) {
		super("assets/turret-standard/turret-standard.png", x, y, width, height);
		angle = 0;
		delay = 0;
	}

	@Override
	public Projectile shoot() {
		if (delay % 15 == 0) {
			delay = 0;
			angle = Math.random() * 360;
			return new StandardBullet(getX(), getY(), getWidth() / 2, getHeight() / 2, angle * Math.PI / 180);
		}
		return null;
	}

	@Override
	public void act() {
		delay++;
	}
}
