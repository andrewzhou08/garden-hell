import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class BreakableBarrier extends Barrier {
	
	private Image img;
	
	public BreakableBarrier(int x, int y, int width, int height){
		super(x, y, width, height);
		img = (new ImageIcon("assets/barrier-breakable.png")).getImage();
	}
	
	public void draw(Graphics g){
		g.drawImage(img, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
	}
	
	public void playBreakAnimation(){
		
	}
}
