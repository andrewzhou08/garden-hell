import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, MouseListener, Runnable, ActionListener {

	public static final int FPS = 30;
	private static final int CORRUPTION_DELAY = FPS * 60;
	private static final int ULT_ATK_DELAY = FPS * 30;
	public double elapsedTime;

	private boolean gameStarted;
	private boolean[] keyPressed;
	private boolean isRunning;

	private Image background;
	private Image p1WinsImage, p2WinsImage;
	private Image heart;
	private EasySound backgroundSound;
	private Timer timer;

	private ArrayList<Actor> actors;
	private ArrayList<Projectile> bullets;
	private ArrayList<Barrier> barriers;
	private Map map;
	private Player p1, p2;
	private boolean p1Wins, p2Wins;
	private int p1Special, p2Special;
	private int dam1Special, dam2Special;
	private int p1TankUlt, p2TankUlt;
	private TankForcefield f, f2;

	/**
	 * Creates new GamePanel. Initializes all needed variables
	 */
	public GamePanel() {
		elapsedTime = 0;
		gameStarted = false;
		addKeyListener(this);
		addMouseListener(this);
		keyPressed = new boolean[14];
		background = (new ImageIcon("assets/background.png")).getImage();
		p1WinsImage = (new ImageIcon("assets/p1-wins.png")).getImage();
		p2WinsImage = (new ImageIcon("assets/p2-wins.png")).getImage();
		heart = (new ImageIcon("assets/heart.png")).getImage();
		actors = new ArrayList<Actor>();
		barriers = new ArrayList<Barrier>();
		bullets = new ArrayList<Projectile>();
		p1Wins = p2Wins = false;
		p1Special = p2Special = 150;
		dam1Special = dam2Special = 30;
		backgroundSound = new EasySound("assets/Background.wav");
	}
	/**
	 * Starts the card layout
	 */
	public void startThread(){
		barriers = map.getBarriers();
		actors.addAll(barriers);
		new Thread(this).start();
		gameStarted = true;
		backgroundSound.play();
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
		repaint();
	}
	
	/**
	 * Resets the current players to their original state
	 */
	public void reset() {
		p1Wins = p2Wins = false;
		bullets = new ArrayList<Projectile>();
		barriers = map.getBarriers();
		actors = new ArrayList<Actor>();
		setPlayers(p1, p2);
		p1.reset();
		p2.reset();
		startThread();
		repaint();
	}

	/**
	 * All actors act and collision updated
	 */
	public synchronized void update() {
		elapsedTime++;
		// corrupt barriers after two minutes
		if (elapsedTime % CORRUPTION_DELAY == 0) {
			for (Barrier b : barriers) {
				if (b instanceof CorruptableBarrier) {
					((CorruptableBarrier)b).toggleCorruption();
				}
			}
		}
		Actor a;
		for (int i = 0; i < actors.size(); i++) {
			a = actors.get(i);
			// Turrets should shoot
			if (a instanceof Turret) {
				Projectile p = ((Turret) a).shoot();
				if (p != null) {
					bullets.add(p);
					p.act();
				}
			}
			// Corrupted barriers should spawn turrets
			if (a instanceof CorruptableBarrier) {
				if (((CorruptableBarrier)a).isCorrupted()) {
					actors.addAll(((CorruptableBarrier)a).spawnTurrets());
				}
				else {
					actors.removeAll(((CorruptableBarrier)a).getSpawnedTurrets());
				}
			}
			// Remove bullets that hit something
			Projectile p;
			for (int j = 0; j < bullets.size(); j++) {
				p = bullets.get(j);
				p.act();
				if (p.willCollide(actors) != null) {
					bullets.remove(p);
					j--;
				}

			}
		}
		// sketch collision
		if (keyPressed[0] || keyPressed[1] || keyPressed[2] || keyPressed[3]) {
			double[] angle = p1.getAngleArray();

			if (p1.willCollide(actors, angle[0]) == null) {
				p1.moveX();
			}
			if (p1.willCollide(actors, angle[1]) == null) {
				p1.moveY();
			}
		}
		if (keyPressed[4] || keyPressed[5] || keyPressed[6] || keyPressed[7]) {
			double[] angle2 = p2.getAngleArray();
			if (p2.willCollide(actors, angle2[0]) == null) {
				p2.moveX();
			}
			if (p2.willCollide(actors, angle2[1]) == null) {
				p2.moveY();
			}
		}


		// players shoot bullets
		if (keyPressed[8]) {
			Projectile bullet = p1.shoot();
			if (bullet != null)
				bullets.add(bullet);
		}
		if (keyPressed[9]) {
			Projectile bullet = p2.shoot();
			if (bullet != null)
				bullets.add(bullet);
		}
		
		// p1 special
		if (keyPressed[10]) {
			if (p1 instanceof Tank && p1Special <= 0) {
				TankBulletSpecial bs = (TankBulletSpecial) (((Tank) p1).initiateSpecial());
				bullets.add(bs);
				p1Special = 300;
			} else if (p1 instanceof Damager && p1Special <= 0) {
				dam1Special--;
				if (dam1Special > 0 && dam1Special % 3 == 0) {
					ArrayList<Projectile> p = ((Damager) p1).initiateSpecial();
					if (p != null) {
						bullets.addAll(p);
					}
				} else if (dam1Special <= 0) {
					p1Special = 150;
					dam1Special = 30;
				}
			} else if (p1 instanceof Builder && p1Special <= 60) {
				BreakableBarrier builtBarrier = ((Builder) p1).initiateSpecial();
				barriers.add(builtBarrier);
				actors.add(builtBarrier);
				p1Special = 150;
			}
		}
		
		// p2 special
		if(keyPressed[11]){
			if(p2 instanceof Tank && p2Special <= 0){
				TankBulletSpecial bs = (TankBulletSpecial)(((Tank) p2).initiateSpecial());
				bullets.add(bs);
				p2Special = 300;
			} else if(p2 instanceof Damager && p2Special <= 0){
				dam2Special--;
				if(dam2Special > 0 && dam2Special % 3 == 0){
					ArrayList<Projectile> p = ((Damager) p2).initiateSpecial();
					if(p != null){
						bullets.addAll(p);
					}
				}
				else if(dam2Special <= 0){
					p2Special = 150;
					dam2Special = 30;
				}
			} else if(p2 instanceof Builder && p2Special <= 60){
				BreakableBarrier builtBarrier = ((Builder) p2).initiateSpecial();
				barriers.add(builtBarrier);
				actors.add(builtBarrier);
				p2Special = 150;
			}
		}
		

		// p1 ultimate attack
		if (keyPressed[12] && p1.getTimeSinceUltAtk() >= ULT_ATK_DELAY) {
			if (p1 instanceof Tank) {
				p1TankUlt = 150;
//				actors.add(new TankForcefield((Tank)p1));
			}

			else if (p1 instanceof Builder) {
				((Builder) p1).setUltimateTarget(p2);
			}
			p1.initiateUltimate(actors, barriers, bullets);
		}
		
		// p2 ultimate attack
		if(keyPressed[13] && p2.getTimeSinceUltAtk() >= ULT_ATK_DELAY){
			if (p2 instanceof Tank) {
				p2TankUlt = 150;
//				f2 = ((Tank) p2).createForcefield();
//				actors.add(f2);
			}
			else if (p2 instanceof Builder) {
				((Builder) p2).setUltimateTarget(p1);
			}
			p2.initiateUltimate(actors, barriers, bullets);
		}
		
		for (int i = 0; i < actors.size(); i++) {
			a = actors.get(i);
			if (!(a instanceof Player))
				a.act();
			if (a instanceof BreakableBarrier && ((BreakableBarrier) a).animationComplete()
					|| a instanceof Turret && ((Turret) a).animationComplete()) {
				actors.remove(a);
				i--;
			}
		}
		
		
		// timing
		if(p1Special > 0)
			p1Special--;
		if (p2Special > 0)
			p2Special--;
		if (p1TankUlt > 0) {
			p1TankUlt--;
			actors.add(f);
		} 
		else {
			actors.remove(f);
		}
		if (p2TankUlt > 0) {
			p2TankUlt--;
			actors.add(f2);
		} else {
			actors.remove(f2);
		}
		
		// check if game won
		if (p1.getLives() <= 0) {
			p2Wins = true;
			isRunning = false;
		} 
		else if (p2.getLives() <= 0) {
			p1Wins = true;
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
		
		// yay lambda expressions!
		actors.stream().forEach((Actor a) -> a.draw(g2));
		bullets.stream().forEach((Projectile p) -> p.draw(g2));

		//Draws lives
		for (int i = 0; i < p1.getLives(); i++) {
			g.drawImage(heart, 5 + 30 * i, 5, 30, 30, null);
		}
		for (int i = 0; i < p2.getLives(); i++) {
			g.drawImage(heart, 975 + 30 * i, 5, 30, 30, null);
		}

		// draws win images
		if (p1Wins) {
			g.drawImage(p1WinsImage, 380, 200, 540, 300, null);
		}	
		else if (p2Wins) {
			g.drawImage(p2WinsImage, 380, 200, 540, 300, null);
		}
		int p1SecondsUlt = (ULT_ATK_DELAY - p1.getTimeSinceUltAtk()) / FPS;
		if(p1SecondsUlt < 0)
			p1SecondsUlt = 0;
		
		int p2SecondsUlt = (ULT_ATK_DELAY - p2.getTimeSinceUltAtk()) / FPS;
		if(p2SecondsUlt < 0)
			p2SecondsUlt = 0;
		
		g.setColor(Color.WHITE);
		g.drawString("Seconds until ultimate is ready: " + p1SecondsUlt, 10, 50);
		g.drawString("Seconds until ultimate is ready: " + p2SecondsUlt, 1050, 50);
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
		if (key == KeyEvent.VK_SHIFT) { // p1 shoot
			keyPressed[8] = true;
		}
		if (key == KeyEvent.VK_COMMA) { // p2 shoot
			keyPressed[9] = true;
		}
		if(key == KeyEvent.VK_F) { // p1 special
			keyPressed[10] = true;
		}
		if(key == KeyEvent.VK_M) { // p2 special 
			keyPressed[11] = true;
		}
		if(key == KeyEvent.VK_C) { // p1 ultimate
			keyPressed[12] = true;
		}
		if(key == KeyEvent.VK_N) { // p2 ultimate
			keyPressed[13] = true;
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
		if(key == KeyEvent.VK_SHIFT) {
			keyPressed[8] = false;
		}
		if(key == KeyEvent.VK_COMMA){
			keyPressed[9] = false;
		}
		if(key == KeyEvent.VK_F) {
			keyPressed[10] = false;
		}
		if(key == KeyEvent.VK_M) {
			keyPressed[11] = false;
		}
		if(key == KeyEvent.VK_C) {
			keyPressed[12] = false;
		}
		if(key == KeyEvent.VK_N) {
			keyPressed[13] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) { }

	/**
	 * 
	 * @pre playerNumber is 1 or 2
	 * @param playerNumber
	 */
	public void setPlayers(Player p1, Player p2) {
		map = new Map(p1, p2);
		p1.setStartingPosition(Map.P1_STARTING_LOCATION, Map.P1_STARTING_DIRECTION);
		p2.setStartingPosition(Map.P2_STARTING_LOCATION, Map.P2_STARTING_DIRECTION);
		actors.add(this.p1 = p1);
		actors.add(this.p2 = p2);
	}

	/**
	 * Determines whether the game started or not
	 * @return true if the game started, false otherwise
	 */
	public boolean gameStarted(){
		return gameStarted;
	}

	/**
	 * Resets after 10 lives lost
	 */
	public void mouseClicked(MouseEvent e) {
		if (p1Wins || p2Wins) {
			reset();
		}
	}
	
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	
	/**
	 * Starts playing background sound once game starts
	 */
	public void actionPerformed(ActionEvent arg0) {
		backgroundSound.play();
		
	}

}
