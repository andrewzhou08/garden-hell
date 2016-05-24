import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class StartScreen extends JPanel implements MouseListener {

	private Main main;
	
	/**
	 * creates the character selection 
	 * @param main object for main to switch panels
	 */
	public StartScreen(Main main) {
		this.main = main;
		addMouseListener(this);
	}

	/**
	 * Handles the mouse clicks for selection the players
	 */
	public void mouseClicked(MouseEvent e) {
		if((new Rectangle(505, 505, 299, 78)).contains(getMousePosition()))
			main.changePanel("2");
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	/**
	 * draws the images on the screen
	 * @param g Graphics object to draw the images
	 */
	public void paintComponent(Graphics g){
		g.drawImage((new ImageIcon("assets/main-screen.png")).getImage(), 0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT, null);
	}
}
