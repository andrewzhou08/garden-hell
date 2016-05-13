import java.awt.Graphics;

public interface Turret{
	Projectile shoot();
	
	void updateCoordinates(int newX, int newY);
}
