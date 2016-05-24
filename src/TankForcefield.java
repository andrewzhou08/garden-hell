import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class TankForcefield extends Actor {

	private Tank t;
	
	/**
	 * Creates new tank forcefield
	 * @param t tank that the forcefield is on
	 */
	public TankForcefield(Tank t) {
		super("assets/forcefield.png", t.getX() - 40, t.getY() - 40, 120, 120);
		this.t = t;
	}

	/**
	 * Moves forcefield to the tank location
	 */
	@Override
	public void act() {
		move(t.getX() - 40, t.getY() - 40);
	}

	/**
	 * Draws the forcefield
	 */
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
	}
	
}
