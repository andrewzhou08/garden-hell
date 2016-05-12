import javax.swing.JFrame;

public class Main extends JFrame {
	
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 720;
	public static final int GRID_COLS = 32;
	public static final int GRID_ROWS = 18;
	public static final int CELL_WIDTH = WINDOW_WIDTH / GRID_COLS;
	public static final int CELL_HEIGHT= WINDOW_HEIGHT / GRID_ROWS;
		
	private GamePanel game;
	
	public Main() {
		setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		game = new GamePanel();
		this.addKeyListener(game);
		add(game);
		setVisible(true);
		game.loop();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	}
	
}
