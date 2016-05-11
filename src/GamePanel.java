import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private boolean isRunning;
	
	private ArrayList<Actor> actors;
	
	public GamePanel() {
		isRunning = true;
		actors = new ArrayList<Actor>();
		actors.add(new Barrier(3, 3, 20, 1));
		Player p1 = new Player(5, 5);
		actors.add(p1);
		Player p2 = new Player(10, 5);
		actors.add(p2);
	}
	
	public void loop() {
		while (isRunning) {
			update();
			draw();
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
		g.drawImage((new ImageIcon("assets/background.png")).getImage(), 0, 0, 
				Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT, null);
		for (Actor a : actors) {
			a.draw(g);
		}
	}

}
