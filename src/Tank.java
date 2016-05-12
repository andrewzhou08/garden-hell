import java.awt.Color;
import java.awt.Graphics;

public class Tank extends Player {

	public Tank(int x, int y) {
		super(x, y);
	}

	public void act(){
		moveBy(1,1);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.blue);
		g.fillOval(getX(), getY(), getWidth(), getHeight());
	}
}
