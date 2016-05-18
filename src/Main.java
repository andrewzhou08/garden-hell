import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
	
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 720;
	public static final int GRID_COLS = 32;
	public static final int GRID_ROWS = 18;
	public static final int CELL_WIDTH = WINDOW_WIDTH / GRID_COLS;
	public static final int CELL_HEIGHT= WINDOW_HEIGHT / GRID_ROWS;
	
	private JPanel cardPanel;
	private GamePanel game;
	private CharacterSelectionScreen screen;
	
	/**
	 * Initializes GUI window
	 */
	public Main() {
		setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
	    cardPanel = new JPanel();
	    CardLayout cl = new CardLayout();
	    cardPanel.setLayout(cl);
		
		game = new GamePanel();
		screen = new CharacterSelectionScreen(this);
		addMouseListener(screen);
		addKeyListener(game);
		add(game);
		
		cardPanel.add(screen, "1");
		cardPanel.add(game, "2");
		
		add(cardPanel);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	}
	
	public void changePanel(String name) {
		((CardLayout)cardPanel.getLayout()).show(cardPanel,name);
		requestFocus();
		game.startThread();
	}
	
	public boolean gameStarted(){
		return game.gameStarted();
	}
	
	public void setPlayer(int playerNumber, Player player){
		game.setPlayer(playerNumber, player);
	}
}
