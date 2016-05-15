import java.awt.Graphics2D;

public abstract class Turret extends Actor implements Drawable {

	public Turret(String sprite, int x, int y, int width, int height) {
		super(sprite, x, y, width, height);
	}
	
	public abstract Projectile shoot();
	
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
	}
}
