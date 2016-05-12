import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

	public static final int FPS = 30;
	
	private boolean isRunning;
	private Image background;
	private ArrayList<Actor> actors;
	private boolean[] keyPressed;
	
	
	public GamePanel() {
		keyPressed = new boolean[8];
		background = (new ImageIcon("assets/background.png")).getImage();
		actors = new ArrayList<Actor>();
		Player p1 = new Damager(2, 5);
		actors.add(p1);
		Player p2 = new Tank(10, 5);
		actors.add(p2);
		actors.add(new Barrier(3, 3, 20, 1));
		actors.add(new Barrier(3, 3, 1, 1));
		actors.add(new Barrier(3, 2, 20, 2));
		actors.add(new PowerOrbBullet(20, 20));
		actors.add(new PowerOrbBullet(700,175));
}
	
	public void loop() {
		isRunning = true;
		long startTime, timeDiff, sleepTime;
		while (isRunning) {
			startTime = System.currentTimeMillis();
			update();
			repaint();
			timeDiff = System.currentTimeMillis() - startTime;
			sleepTime = 1000 / FPS - timeDiff;
			if (sleepTime <= 0) {
				sleepTime = 5;
			}
			try {
				Thread.sleep(sleepTime);
			}
			catch (InterruptedException e) { }
		}
	}
	
	public void update() {
		
		if(keyPressed[0]){
			if(actors.get(0).willCollide(actors, 1)==null)
				actors.get(0).moveBy(0, -1);
		}
		if(keyPressed[1]){
			if(actors.get(0).willCollide(actors, 3)==null)
				actors.get(0).moveBy(-1, 0);
		}if(keyPressed[2]){
			if(actors.get(0).willCollide(actors, 2)==null)
				actors.get(0).moveBy(0, 1);
		}if(keyPressed[3]){
			if(actors.get(0).willCollide(actors, 4)==null)
				actors.get(0).moveBy(1, 0);
		}if(keyPressed[4]){
			if(actors.get(1).willCollide(actors, 1)==null)
				actors.get(1).moveBy(0, -1);
		}if(keyPressed[5]){
			if(actors.get(1).willCollide(actors, 2)==null)
				actors.get(1).moveBy(0, 1);
		}if(keyPressed[6]){
			if(actors.get(1).willCollide(actors, 3)==null)
				actors.get(1).moveBy(-1, 0);
		}if(keyPressed[7]){
			if(actors.get(1).willCollide(actors, 4)==null)
				actors.get(1).moveBy(1, 0);
		}
		for(int i = 2;i<actors.size();i++){
			Actor currentActor = actors.get(i);
			Actor a = currentActor.willCollide(actors, 4);
			Actor b = currentActor.willCollide(actors, 2);
			if(a==null&&b==null)
				currentActor.act();
			else{
				if(currentActor instanceof Projectile){
					if(a!=null && a instanceof Player){
						a.loseHP(110);
						System.out.println("test");
					}else if(b!=null && b instanceof Player){
						b.loseHP(110);
						System.out.println("test");
					}
					actors.remove(currentActor);
				}
			}
		}
		if(actors.get(0).getHP()<0){
			System.out.println("dead");
		}
		if(actors.get(1).getHP()<0){
			System.out.println("dead");
		}
	}
	
	public void draw() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT, null);
		for (Actor a : actors) {
			a.draw(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key==KeyEvent.VK_W){
			keyPressed[0] = true;
		}
		if(key==KeyEvent.VK_A){
			keyPressed[1] = true;
		}
		if(key==KeyEvent.VK_S){
			keyPressed[2] = true;
		}
		if(key==KeyEvent.VK_D){
			keyPressed[3] = true;
		}if(key==KeyEvent.VK_UP){
			keyPressed[4] = true;
		}if(key==KeyEvent.VK_DOWN){
			keyPressed[5] = true;
		}if(key==KeyEvent.VK_LEFT){
			keyPressed[6] = true;
		}if(key==KeyEvent.VK_RIGHT){
			keyPressed[7] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key==KeyEvent.VK_W){
			keyPressed[0] = false;
		}
		if(key==KeyEvent.VK_A){
			keyPressed[1] = false;
		}
		if(key==KeyEvent.VK_S){
			keyPressed[2] = false;
		}
		if(key==KeyEvent.VK_D){
			keyPressed[3] = false;
		}if(key==KeyEvent.VK_UP){
			keyPressed[4] = false;
		}if(key==KeyEvent.VK_DOWN){
			keyPressed[5] = false;
		}if(key==KeyEvent.VK_LEFT){
			keyPressed[6] = false;
		}if(key==KeyEvent.VK_RIGHT){
			keyPressed[7] = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
