import java.awt.Color;
import java.awt.Graphics;

public class Tank extends Player {

	
	
	/**
	 * Creates new tank character of coordinates x,y
	 * @param x x coordinate of tank
	 * @param y y coordinate of tank
	 */
	public Tank(int x, int y) {
		super(x, y);
		setMoveAnimation(new Animation("assets/player-tank/player-tank-moving(%d).png", 1, 4, 4));
		setStandAnimation(new Animation("assets/player-tank/player-tank-standing(%d).png", 1, 2, 8));
	}

}
