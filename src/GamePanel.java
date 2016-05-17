import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener {

	public static final int FPS = 30;
	
	private boolean[] keyPressed;
	private boolean isRunning;
	private Image background;
	private ArrayList<Actor> actors;
	private ArrayList<Projectile> bullets;
	private Player p1, p2;
	
	/**
	 * Creates new GamePanel. Initializes all needed variables
	 */
	public GamePanel() {
		addKeyListener(this);
		keyPressed = new boolean[10];
		background = (new ImageIcon("assets/background.png")).getImage();
		actors = new ArrayList<Actor>();
		bullets = new ArrayList<Projectile>();
		actors.add(new Barrier(3, 2, 20, 2));

		p1 = new Builder(5, 5, 0);

		p2 = new Tank(10, 5, 0);
		actors.add(new Barrier(3, 3, 20, 1));
		actors.add(new Barrier(3, 3, 1, 1));
		actors.add(new PowerOrbTurret(400, 400, 40, 40));
		actors.add(new StandardTurret(500, 400, 40, 40));
		actors.add(new FlowerTurret(300, 400, 40, 40));
	}
	
	/**
	 * Updates and repaints all graphics, then waits to run at 30FPS
	 */
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
	
	/**
	 * All actors act and collision updated
	 */
	public synchronized void update() {
		for (Actor a : actors) {
			if(a instanceof Turret){
				Projectile p = ((Turret)a).shoot();
				if(p != null)
					bullets.add(p);
			}
			for(Projectile p : bullets)
				p.act();
		}
		double angle = p1.getAngle();
		p1.updateAngle();
		if(p1.willCollide(actors, angle)==null){
			p1.act();
		}
		//		if(keyPressed[0]){
//			Actor t = p1.willCollide(actors, 1);
//			if(t==null){
//				p1.act();
//			}
//		}
//		if(keyPressed[1]){
//			if(p1.willCollide(actors, 3)==null)
//				p1.act();
//			}
//		if(keyPressed[2]){
//			if(p1.willCollide(actors, 2)==null)
//				p1.act();
//		}
//		if(keyPressed[3]){
//			if(p1.willCollide(actors, 4)==null)
//				p1.act();
//		}
//		if(keyPressed[4]){
//			if(p2.willCollide(actors, 1)==null)
//				p2.act();
//		}
//		if(keyPressed[5]){
//			if(p2.willCollide(actors, 2)==null)
//				p2.act();
//		}
//		if(keyPressed[6]){
//			if(p2.willCollide(actors, 3)==null)
//				p2.act();
//		}
//		if(keyPressed[7]){
//			if(p2.willCollide(actors, 4)==null)
//				p2.act();
//		}
		if(keyPressed[8]){
			Projectile tankBullet = p1.shoot();
			if(tankBullet != null)
				bullets.add(tankBullet);
		}
		if(keyPressed[9]){
			Projectile tankBullet = p2.shoot();
			if(tankBullet != null)
				bullets.add(tankBullet);
		}
		for(int i = 0;i<actors.size();i++){
			Actor currentActor = actors.get(i);
			currentActor.act();
		}
		if(actors.get(0).getHP()<0){
			System.out.println("dead");
		}
		if(actors.get(1).getHP()<0){
			System.out.println("dead");
		}
	}
	
	/**
	 * Draws all actors and background
	 * @param g Graphics used for drawing
	 */
	public synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(background, 0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT, null);
		p1.draw(g2);
		p2.draw(g2);
		for (Actor a : actors) {
			a.draw(g2);
		}
		for(Projectile p : bullets){
			p.draw(g2);
		}
	}

	/**
	 * Handles movement with key inputs
	 * @param e KeyEvent used to detect key inputs
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) {
			keyPressed[0] = true;
			p1.setUpPressed(true);
		}
		if (key == KeyEvent.VK_A) {
			keyPressed[1] = true;
			p1.setLeftPressed(true);
		}
		if (key == KeyEvent.VK_S) {
			keyPressed[2] = true;
			p1.setDownPressed(true);
		}
		if (key == KeyEvent.VK_D) {
			keyPressed[3] = true;
			p1.setRightPressed(true);
		}
		if (key == KeyEvent.VK_UP) {
			keyPressed[4] = true;
			p2.setUpPressed(true);
		}
		if (key == KeyEvent.VK_DOWN) {
			keyPressed[5] = true;
			p2.setDownPressed(true);
		}
		if (key == KeyEvent.VK_LEFT) {
			keyPressed[6] = true;
			p2.setLeftPressed(true);
		}
		if (key == KeyEvent.VK_RIGHT) {
			keyPressed[7] = true;
			p2.setRightPressed(true);
		}
		if (key == KeyEvent.VK_SPACE) {
			keyPressed[8] = true;
		}
		if (key == KeyEvent.VK_SHIFT) {
			keyPressed[9] = true;
		}
	}

	/**
	 * Handles player movement when key released
	 * @param e KeyEvent used to detect key inputs
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) {
			keyPressed[0] = false;
			p1.setUpPressed(false);
		}
		if (key == KeyEvent.VK_A) {
			keyPressed[1] = false;
			p1.setLeftPressed(false);
		}
		if (key == KeyEvent.VK_S) {
			keyPressed[2] = false;
			p1.setDownPressed(false);
		}
		if (key == KeyEvent.VK_D) {
			keyPressed[3] = false;
			p1.setRightPressed(false);
		}
		if (key == KeyEvent.VK_UP) {
			keyPressed[4] = false;
			p2.setUpPressed(false);
		}
		if (key == KeyEvent.VK_DOWN) {
			keyPressed[5] = false;
			p2.setDownPressed(false);
		}
		if (key == KeyEvent.VK_LEFT) {
			keyPressed[6] = false;
			p2.setLeftPressed(false);
		}
		if (key == KeyEvent.VK_RIGHT) {
			keyPressed[7] = false;
			p2.setRightPressed(false);
		}
		if(key == KeyEvent.VK_SPACE) {
			keyPressed[8] = false;
		}
		if(key == KeyEvent.VK_SHIFT){
			keyPressed[9] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
