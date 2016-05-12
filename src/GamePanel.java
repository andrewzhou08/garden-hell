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
		actors.add(new Barrier(3, 3, 20, 1));
		Player p1 = new Damager(5, 5);
		actors.add(new Barrier(3, 3, 1, 1));
//		Player p1 = new Player(5, 5);
		actors.add(p1);
		Player p2 = new Tank(10, 5);
		actors.add(p2);
		actors.add(new PowerOrbBullet(20, 20));
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
			actors.get(2).moveBy(0, -1);
		}
		if(keyPressed[1]){
			actors.get(2).moveBy(-1, 0);
		}if(keyPressed[2]){
			actors.get(2).moveBy(0, 1);
		}if(keyPressed[3]){
			actors.get(2).moveBy(1, 0);
		}if(keyPressed[4]){
			actors.get(3).moveBy(0, -1);
		}if(keyPressed[5]){
			actors.get(3).moveBy(0, 1);
		}if(keyPressed[6]){
			actors.get(3).moveBy(-1, 0);
		}if(keyPressed[7]){
			actors.get(3).moveBy(1, 0);
		}
		actors.get(4).act();
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
