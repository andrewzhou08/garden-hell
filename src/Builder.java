import java.awt.Color;
import java.awt.Graphics;

public class Builder extends Player {

	public Builder(int x, int y) {
		super(x, y);
		
	}
	
	
	
	public void draw(Graphics g){
		g.setColor(Color.red);
		g.fillOval(getX(), getY(), getWidth(), getHeight());
	}

}
