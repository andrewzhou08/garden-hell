import java.awt.Color;
import java.awt.Graphics;

public class Damager extends Player {

	public Damager(int x, int y) {
		super(x, y);
	}
	
	
	
	public void draw(Graphics g){
		g.setColor(Color.green);
		g.fillOval(getX(), getY(), getWidth(), getHeight());
	}

}
