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
	public double elapsedTime; //in seconds
	
	private boolean gameStarted;
	private boolean[] keyPressed;
	private boolean isRunning;
	
	private Image background;
	private Image p1WinsImage, p2WinsImage;
	private Image heart;
	
	private ArrayList<Actor> actors;
	private ArrayList<Projectile> bullets;
	private ArrayList<Barrier> barriers;
	private Map map;
	private Player p1, p2;
	private boolean playerOneDead;
	private boolean playerTwoDead;
	private int corruptionDelay = 1;
	private boolean p1Wins;
	private boolean p2Wins;
	
	/**
	 * Creates new GamePanel. Initializes all needed variables
	 */
	public GamePanel() {
		elapsedTime = 0;
		p1 = new Tank(1, 8, 0);
		p2 = new Tank(30, 8, 0);
		gameStarted = false;
		addKeyListener(this);
		keyPressed = new boolean[10];
		background = (new ImageIcon("assets/background.png")).getImage();
		p1WinsImage = (new ImageIcon("assets/p1-wins.png")).getImage();
		p2WinsImage = (new ImageIcon("assets/p2-wins.png")).getImage();
		heart = (new ImageIcon("assets/heart.png")).getImage();
		actors = new ArrayList<Actor>();
		bullets = new ArrayList<Projectile>();
		p1 = new Builder(5, 5, 0);
		p2 = new Tank(10, 5, 0);
		actors.add(p1);
		actors.add(p2);
		map = new Map();
		playerOneDead = playerTwoDead = false;
		p1Wins = p2Wins = false;
		
		barriers = map.getBarriers();
		for(Barrier b : barriers)
			actors.add(b);
	}
	/**
	 * Starts the card layout
	 */
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
			
			if(playerOneDead){
				p1.move(1*Main.CELL_WIDTH, 8*Main.CELL_WIDTH);
				p1.setAngle(0);
				p1.setCurrentHealth(p1.getMaxHealth());
				p1.setLives(p1.getLives()-1);
				playerOneDead = playerTwoDead = false;
			}
			else if(playerTwoDead){
				p2.move(30*Main.CELL_WIDTH, 8*Main.CELL_WIDTH);
				p2.setAngle(180);
				p2.setCurrentHealth(p2.getMaxHealth());
				p2.setLives(p2.getLives()-1);
				playerOneDead = playerTwoDead = false;
			}
			
			if(p1.getLives() <= 0){
				p2Wins = true;
				isRunning = false;
			}
			else if(p2.getLives() <= 0){
				p1Wins = true;
				isRunning = false;
			}
			
			sleepTime = 1000 / FPS - timeDiff;
			if (sleepTime <= 0) {
				sleepTime = 5;
			}
			try {
				Thread.sleep(sleepTime);
			}
			catch (InterruptedException e) { }
			elapsedTime = 1.0/30;
		}
		reset();
	}
	/**
	 * Resets the current players to their original state
	 */
	public void reset(){
		repaint();
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
		if(keyPressed[0] ||keyPressed[1] ||keyPressed[2] ||keyPressed[3] ){
			double[] angle = p1.getAngleArray();
			
			if(p1.willCollide(actors, angle[0])==null){
				p1.moveX();
			}
			if(p1.willCollide(actors, angle[1])==null){
				p1.moveY();
			}
		}
		if(keyPressed[4] ||keyPressed[5] ||keyPressed[6] ||keyPressed[7]  ){
			double[] angle2 = p2.getAngleArray();
			if(p2.willCollide(actors, angle2[0])==null){
				p2.moveX();
			}
			if(p2.willCollide(actors, angle2[1])==null){
				p2.moveY();
			}
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
		for (int i = 0; i < actors.size(); i++) {
			Actor currentActor = actors.get(i);
			if (!(currentActor instanceof Player))
				currentActor.act();
			if (currentActor instanceof BreakableBarrier) {
				if (((BreakableBarrier) currentActor).getCurrentHealth() <= 0) {
					if (((BreakableBarrier) currentActor).animationComplete()) {
						actors.remove(currentActor);
						i--;
					}
				}
			} else if (currentActor instanceof Turret) {
				if (((Turret) currentActor).getCurrentHealth() <= 0) {
					if (((Turret) currentActor).animationComplete()) {
						actors.remove(currentActor);
						i--;
					}
				}
			}
		}
		if(p1.getCurrentHealth()<0){
			playerOneDead = true;
		}
		if(p2.getCurrentHealth()<0){
			playerTwoDead = true;
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
		
		//Draws lives
		for(int i = 0; i < p1.getLives(); i++){
			g.drawImage(heart, 5 + 30*i, 5, 30, 30, null);
		}
		for(int i = 0; i < p2.getLives(); i++){
			g.drawImage(heart, 975 + 30*i, 5, 30, 30, null);
		}
		
		if(p1Wins){
			g.drawImage(p1WinsImage, 380, 200, 540, 300, null);
		}	
		else if(p2Wins){
			g.drawImage(p2WinsImage, 380, 200, 540, 300, null);
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
	
	/**
	 * Determines whether the game started or not
	 * @return true if the game started, false otherwise
	 */
	public boolean gameStarted(){
		return gameStarted;
	}

}
