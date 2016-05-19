import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener, Runnable {

	public static final int FPS = 30;
	
	private boolean gameStarted;
	private boolean[] keyPressed;
	private boolean isRunning;
	private Image background;
	private Image p1Win, p2Win;
	private ArrayList<Actor> actors;
	private ArrayList<Projectile> bullets;
	private ArrayList<Barrier> barriers;
	private Map map;
	private Player p1, p2;
	private boolean playerOneWins;
	private boolean playerTwoWins;
	private int corruptionDelay = 1;
	
	/**
	 * Creates new GamePanel. Initializes all needed variables
	 */
	public GamePanel() {
		p1 = new Tank(1, 8, 0);
		p2 = new Tank(30, 8, 0);
		gameStarted = false;
		addKeyListener(this);
		keyPressed = new boolean[10];
		background = (new ImageIcon("assets/background.png")).getImage();
		p1Win = (new ImageIcon("assets/p1-wins.png")).getImage();
		p2Win = (new ImageIcon("assets/p2-wins.png")).getImage();
		actors = new ArrayList<Actor>();
		bullets = new ArrayList<Projectile>();
		p1 = new Builder(5, 5, 0);
		p2 = new Tank(10, 5, 0);
		actors.add(p1);
		actors.add(p2);
		map = new Map();
		playerOneWins = playerTwoWins = false;
		
		barriers = map.getBarriers();
		for(Barrier b : barriers)
			actors.add(b);
	}
	
	public void startThread(){
		  new Thread(this).start();
		  gameStarted = true;
	}
	
	/**
	 * Updates and repaints all graphics, then waits to run at 30FPS
	 */
	public void run() {
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
		reset();
	}
	
	public void reset(){
		p1.move(1*Main.CELL_WIDTH, 8*Main.CELL_WIDTH);
		p2.move(30*Main.CELL_WIDTH, 8*Main.CELL_WIDTH);
		p1.setAngle(0);
		p2.setAngle(180);
		p1.setCurrentHealth(p1.getMaxHealth());
		p2.setCurrentHealth(p2.getMaxHealth());
		bullets = new ArrayList<Projectile>();
		repaint();
		try{
			Thread.sleep(1000);
		}
			catch(InterruptedException e){ }
		isRunning = true;
		playerOneWins = playerTwoWins = false;
		run();
	}
	
	/**
	 * All actors act and collision updated
	 */
	public synchronized void update() {
		corruptionDelay--;
		if (corruptionDelay == 0) {
			for (Barrier b : barriers) {
				if (b instanceof CorruptableBarrier) {
					((CorruptableBarrier)b).setCorrupt(true);
					System.out.println(b);
				}
			}
		}
		for (int i = 0; i < actors.size(); i++) {
			Actor a = actors.get(i);
			if (a instanceof Turret) {
				Projectile p = ((Turret) a).shoot();
				if (p != null) {
					bullets.add(p);
					p.act();
				}
			}
			if (a instanceof CorruptableBarrier) {
				actors.addAll(((CorruptableBarrier)a).spawnTurrets());
			}
			for (int j = 0; j < bullets.size(); j++) {
				Projectile p = bullets.get(j);
				p.act();
				if (p.willCollide(actors, 0) != null) {
					bullets.remove(p);
					j--;
				}

			}
		}
		double angle = p1.getAngle();
		if(p1.willCollide(actors, angle)==null){
			p1.act();
		}
		
		double angle2 = p2.getAngle();
		if(p2.willCollide(actors, angle2)==null){
			p2.act();
		}

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
			if(!(currentActor instanceof Player))
				currentActor.act();
			if(currentActor instanceof BreakableBarrier){
				if(((BreakableBarrier) currentActor).getCurrentHealth()<0){
					actors.remove(currentActor);
					i--;
				}
			}else if(currentActor instanceof Turret){
				if(((Turret) currentActor).getCurrentHealth()<0){
					actors.remove(currentActor);
					i--;
				}
			}
		}
		if(p1.getCurrentHealth()<0){
			playerTwoWins = true;
			isRunning = false;
			
		}
		if(p2.getCurrentHealth()<0){
			playerOneWins = true;
			isRunning = false;
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
		for (Actor a : actors) {
			a.draw(g2);
		}
		for(Projectile p : bullets){
			p.draw(g2);
		}
		if(!isRunning){
			if(playerOneWins){
				g.drawImage(p1Win, 370, 200, 540, 300, null);
			}
			else if(playerTwoWins){
				g.drawImage(p2Win, 370, 200, 540, 300, null);
			}
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
	public void keyTyped(KeyEvent e) {}
	
	/**
	 * 
	 * @pre playerNumber is 1 or 2
	 * @param playerNumber
	 */
	public void setPlayer(int playerNumber, Player p){
		if(playerNumber == 1){
			actors.remove(p1);
			p1 = p;
			actors.add(p);
		}
		else if(playerNumber == 2){
			actors.remove(p2);
			p2 = p;
			actors.add(p);
		}
			
	}
	
	public boolean gameStarted(){
		return gameStarted;
	}

}
