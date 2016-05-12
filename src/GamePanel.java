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
	
	
	
	public GamePanel() {
		background = (new ImageIcon("assets/background.png")).getImage();
		actors = new ArrayList<Actor>();
		actors.add(new Barrier(3, 3, 20, 1));
		Player p1 = new Player(5, 5);
		actors.add(p1);
		Player p2 = new Player(10, 5);
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
		for (Actor a : actors) {
			a.act();
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
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
