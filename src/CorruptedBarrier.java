import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class CorruptedBarrier extends Barrier {
	
	private double velX, velY;
	private Turret t;
	private Image img;
	
	public CorruptedBarrier(int x, int y, int width, int height, Turret turret){
		super(x, y, width, height);
		velX = 0;
		velY = 0;
		t = turret;
		img = (new ImageIcon("assets/barrier-corrupt.png")).getImage();
	}
	
	public void move(){
		velX += 3*Math.random()-1;
		velY += 3*Math.random()-1;
		super.moveBy((int)velX, (int)velY);
	}
	
	public Turret getTurret(){
		return t;
	}
	
	public void draw(Graphics g){
		g.drawImage(img, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
	}
}
