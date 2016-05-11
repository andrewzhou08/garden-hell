import java.util.ArrayList;

import javax.swing.JFrame;

public class Main extends JFrame {
	
	public static final int WINDOW_WIDTH = 1440;
	public static final int WINDOW_HEIGHT = 810;
	public static final int GRID_COLS = 32;
	public static final int GRID_ROWS = 18;
	public static final int CELL_WIDTH = WINDOW_WIDTH / GRID_COLS;
	public static final int CELL_HEIGHT= WINDOW_HEIGHT / GRID_ROWS;
		
	public Main() {
		setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		GamePanel game = new GamePanel();
		main.add(game);
		game.loop();
	}
	
}
