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
	public double elapsedTime; //in seconds

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
	private boolean playerOneDead;
	private boolean playerTwoDead;
	private int corruptionDelay = 1;
	private boolean p1Wins;
	private boolean p2Wins;
	private int p1Special, p2Special;
	private int dam1Special, dam2Special;
	private int p1Ult, p2Ult;
	private int p1TankUlt, p2TankUlt;
	private TankForcefield f, f2;

	/**
	 * Creates new GamePanel. Initializes all needed variables
	 */
	public GamePanel() {
		elapsedTime = 0;
		p1 = new Tank(1, 8, 0);
		p2 = new Tank(30, 8, 0);
		gameStarted = false;
		addKeyListener(this);
		addMouseListener(this);
		keyPressed = new boolean[14];
		background = (new ImageIcon("assets/background.png")).getImage();
		p1WinsImage = (new ImageIcon("assets/p1-wins.png")).getImage();
		p2WinsImage = (new ImageIcon("assets/p2-wins.png")).getImage();
		heart = (new ImageIcon("assets/heart.png")).getImage();
		actors = new ArrayList<Actor>();
		bullets = new ArrayList<Projectile>();
		p1 = new Builder(5, 5, 0);
		p2 = new Tank(10, 5, 0);
		map = new Map();
		actors.add(p1);
		actors.add(p2);
		playerOneDead = playerTwoDead = false;
		p1Wins = p2Wins = false;
		barriers = new ArrayList<Barrier>();
		p1Special = p2Special = 150;
		dam1Special = dam2Special = 30;
		p1Ult = p2Ult = 0;
		backgroundSound = new EasySound("assets/Background.wav");
		timer = new Timer(106000, this);
		
	}
	/**
	 * Starts the card layout
	 */
	public void startThread(){
		map.generateMapBarriers();
		barriers = map.getBarriers();
		for(Barrier b : barriers)
			actors.add(b);
		new Thread(this).start();
		gameStarted = true;
		backgroundSound.play();
		timer.start();
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
			
			if(p1Special > 0)
				p1Special--;
			if(p2Special > 0)
				p2Special--;
			if(p1TankUlt > 0){
				p1TankUlt--;
				actors.add(f);
			}
			else{
				actors.remove(f);
			}
			if(p2TankUlt > 0){
				p2TankUlt--;
				actors.add(f2);
			}
			else{
				actors.remove(f2);
			}
			p1Ult++;
			p2Ult++;

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
		repaint();
	}
	/**
	 * Resets the current players to their original state
	 */
	public void reset(){
		bullets = new ArrayList<Projectile>();
		barriers = new ArrayList<Barrier>();
		actors = new ArrayList<Actor>();
		
		actors.add(p1);
		actors.add(p2);

		p1.move(1*Main.CELL_WIDTH, 8*Main.CELL_WIDTH);
		p1.setAngle(0);
		p1.setCurrentHealth(p1.getMaxHealth());
		p1.setLives(Player.MAX_LIVES);

		p2.move(30*Main.CELL_WIDTH, 8*Main.CELL_WIDTH);
		p2.setAngle(180);
		p2.setCurrentHealth(p2.getMaxHealth());
		p2.setLives(Player.MAX_LIVES);

		startThread();
		
		map = new Map();
		map.generateMapBarriers();
		barriers = map.getBarriers();

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
		if(keyPressed[10]){
			if(p1 instanceof Tank && p1Special <= 0){
				TankBulletSpecial bs = (TankBulletSpecial)(((Tank) p1).initiateSpecial());
				bullets.add(bs);
				p1Special = 300;
			} else if(p1 instanceof Damager && p1Special <= 0){
				dam1Special--;
				if(dam1Special > 0 && dam1Special % 3 == 0){
					Projectile[] p = ((Damager) p1).initializeSpecial();
					if(p != null){
						for(Projectile proj : p){
							bullets.add(proj);
						}
					}
				}
				else if(dam1Special <= 0){
					p1Special = 150;
					dam1Special = 30;
				}
			} else if(p1 instanceof Builder && p1Special <= 60){
				BreakableBarrier builtBarrier = ((Builder) p1).initiateSpecial();
				barriers.add(builtBarrier);
				actors.add(builtBarrier);
				p1Special = 150;
			}
		}
		if(keyPressed[11]){
			if(p2 instanceof Tank && p2Special <= 0){
				TankBulletSpecial bs = (TankBulletSpecial)(((Tank) p2).initiateSpecial());
				bullets.add(bs);
				p2Special = 300;
			} else if(p2 instanceof Damager && p2Special <= 0){
				dam2Special--;
				if(dam2Special > 0 && dam2Special % 3 == 0){
					Projectile[] p = ((Damager) p2).initializeSpecial();
					if(p != null){
						for(Projectile proj : p){
							bullets.add(proj);
						}
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
		if(keyPressed[12]){
			if(p1 instanceof Tank && p1Ult >= 1800){
				p1Ult = 0;
				p1TankUlt = 150;
				f = ((Tank) p1).initiateUltimate();
				actors.add(f);
			}
			else if(p1 instanceof Damager && p1Ult >= 1800){
				p1Ult = 0;
				Projectile p = ((Damager)p1).initiateUltimate();
				bullets.add(p);
			}
			else if(p1 instanceof Builder && p1Ult >= 1800){
				p1Ult = 0;
				ArrayList<BreakableBarrier> ultBarriers = new ArrayList<BreakableBarrier>();
				ultBarriers.add(new BreakableBarrier(p2.getX(), p2.getY(), 40, 40, 300, true));
				ultBarriers.add(new BreakableBarrier(p2.getX()-40, p2.getY(), 40, 40, 300, true));
				ultBarriers.add(new BreakableBarrier(p2.getX()-40, p2.getY()-40, 40, 40, 300, true));
				ultBarriers.add(new BreakableBarrier(p2.getX(), p2.getY()-40, 40, 40, 300, true));
				ultBarriers.add(new BreakableBarrier(p2.getX()+40, p2.getY()-40, 40, 40, 300, true));
				ultBarriers.add(new BreakableBarrier(p2.getX()+40, p2.getY(), 40, 40, 300, true));
				ultBarriers.add(new BreakableBarrier(p2.getX()+40, p2.getY()+40, 40, 40, 300, true));
				ultBarriers.add(new BreakableBarrier(p2.getX(), p2.getY()+40, 40, 40, 300, true));
				ultBarriers.add(new BreakableBarrier(p2.getX()-40, p2.getY()+40, 40, 40, 300, true));
				
				for(BreakableBarrier b : ultBarriers){
					barriers.add(b);
					actors.add(b);
				}
			}
		}
		if(keyPressed[13]){
			if(p2 instanceof Tank && p2Ult >= 1800){
				p2Ult = 0;
				p2TankUlt = 150;
				f2 = ((Tank) p2).initiateUltimate();
				actors.add(f2);
			}
			else if(p2 instanceof Damager && p2Ult >= 1800){
				p2Ult = 0;
				Projectile p = ((Damager)p2).initiateUltimate();
				bullets.add(p);
			}
			else if(p2 instanceof Builder && p2Ult >= 1800){
				p2Ult = 0;
				ArrayList<BreakableBarrier> ultBarriers = new ArrayList<BreakableBarrier>();
				ultBarriers.add(new BreakableBarrier(p1.getX(), p1.getY(), 40, 40, 300, true));
				ultBarriers.add(new BreakableBarrier(p1.getX()-40, p1.getY(), 40, 40, 300, true));
				ultBarriers.add(new BreakableBarrier(p1.getX()-40, p1.getY()-40, 40, 40, 300, true));
				ultBarriers.add(new BreakableBarrier(p1.getX(), p1.getY()-40, 40, 40, 300, true));
				ultBarriers.add(new BreakableBarrier(p1.getX()+40, p1.getY()-40, 40, 40, 300, true));
				ultBarriers.add(new BreakableBarrier(p1.getX()+40, p1.getY(), 40, 40, 300, true));
				ultBarriers.add(new BreakableBarrier(p1.getX()+40, p1.getY()+40, 40, 40, 300, true));
				ultBarriers.add(new BreakableBarrier(p1.getX(), p1.getY()+40, 40, 40, 300, true));
				ultBarriers.add(new BreakableBarrier(p1.getX()-40, p1.getY()+40, 40, 40, 300, true));
				
				for(BreakableBarrier b : ultBarriers){
					barriers.add(b);
					actors.add(b);
				}
			}
		}
		
		for (int i = 0; i < actors.size(); i++) {
			Actor currentActor = actors.get(i);
			if (!(currentActor instanceof Player))
				currentActor.act();
			if (currentActor instanceof BreakableBarrier) {
				if (((BreakableBarrier) currentActor).getCurrentHealth() <= 0) {
					((BreakableBarrier) currentActor).playSound();
					if (((BreakableBarrier) currentActor).animationComplete()) {
						actors.remove(currentActor);
						i--;
					}
				}
			} else if (currentActor instanceof Turret) {
				if (((Turret) currentActor).getCurrentHealth() <= 0) {
					((Turret) currentActor).playExplosionSound();
					if (((Turret) currentActor).animationComplete()) {
						actors.remove(currentActor);
						i--;
					}
				}
			}
		}
		if(p1.getCurrentHealth()<0){
			p1.playDeathSound();
			playerOneDead = true;
		}
		if(p2.getCurrentHealth()<0){
			p2.playDeathSound();
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
		int p1SecondsUlt = (1800-p1Ult) / 30;
		if(p1SecondsUlt < 0)
			p1SecondsUlt = 0;
		
		int p2SecondsUlt = (1800-p2Ult) / 30;
		if(p2SecondsUlt < 0)
			p2SecondsUlt = 0;
		
		g.setColor(Color.BLACK);
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
		if (key == KeyEvent.VK_SHIFT) {
			keyPressed[8] = true;
		}
		if (key == KeyEvent.VK_SPACE) {
			keyPressed[9] = true;
		}
		if(key == KeyEvent.VK_F) {
			keyPressed[10] = true;
		}
		if(key == KeyEvent.VK_ALT) {
			keyPressed[11] = true;
		}
		if(key == KeyEvent.VK_C) {
			keyPressed[12] = true;
		}
		if(key == KeyEvent.VK_COMMA) {
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
		if(key == KeyEvent.VK_SPACE){
			keyPressed[9] = false;
		}
		if(key == KeyEvent.VK_F) {
			keyPressed[10] = false;
		}
		if(key == KeyEvent.VK_ALT) {
			keyPressed[11] = false;
		}
		if(key == KeyEvent.VK_C) {
			keyPressed[12] = false;
		}
		if(key == KeyEvent.VK_COMMA) {
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
	public void setPlayer(int playerNumber, Player p){
		if(playerNumber == 1){
			actors.remove(p1);
			p1 = p;
			actors.add(p);
			map.setP1(p);
		}
		else if(playerNumber == 2){
			actors.remove(p2);
			p2 = p;
			actors.add(p);
			map.setP2(p);
		}
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
		if(p1Wins || p2Wins){
			//reset();
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
