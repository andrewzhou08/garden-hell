import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private boolean isRunning;
	
	private ArrayList<Actor> actors;
	
	public GamePanel() {
		isRunning = true;
		actors = new ArrayList<Actor>();
		actors.add(new Barrier(3, 3, 20, 1));
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
		for (Actor a : actors) {
			a.draw(g);
		}
	}

}
