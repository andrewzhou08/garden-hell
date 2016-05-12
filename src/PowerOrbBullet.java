import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PowerOrbBullet extends Projectile {
	
	private Image img;
	
	public PowerOrbBullet(int x, int y, int width, int height) {
		super(x, y, width, height);
		img = (new ImageIcon("assets/bullet-3/bullet-3.png")).getImage();
		super.setVelX(super.getVelX() + 2*Math.random()-1);
		super.setVelY(super.getVelY() + 2*Math.random()-1);
	}
	
	public void act(){
		super.act();
	}
	
	private void nextFrame(){
		
	}
	
	public void draw(Graphics g){
		super.draw(g);
		
		g.drawImage(img, super.getX(), super.getY(), super.getHeight(), super.getWidth(), null);
	}

}
