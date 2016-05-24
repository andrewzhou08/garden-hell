import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Map {

	public static final Point2D P1_STARTING_LOCATION = new Point2D.Double(1 * Main.CELL_WIDTH, 8 * Main.CELL_WIDTH); 
	public static final Point2D P2_STARTING_LOCATION = new Point2D.Double(30 * Main.CELL_WIDTH, 8 * Main.CELL_WIDTH);
	public static final int P1_STARTING_DIRECTION = Player.DIR_RIGHT;
	public static final int P2_STARTING_DIRECTION = Player.DIR_LEFT;
	
	private Player p1, p2;
	private ArrayList<Barrier> barriers;
	private ArrayList<CorruptableBarrier> corruptBarriers;
	
	/**
	 * Creates a new map with references to the two players
	 * @param p1 reference to Player object for Player 1
	 * @param p2 reference to Player object for Player 2
	 */
	public Map(Player p1, Player p2) {
		barriers = new ArrayList<Barrier>();
		corruptBarriers = new ArrayList<CorruptableBarrier>();
		this.p1 = p1;
		this.p2 = p2;
		generateMapBarriers();
	}

	private void generateMapBarriers() {
		//Two I shaped on left/right sides
		for(int i = 3; i < 14; i++){
			corruptBarriers.add(new CorruptableBarrier(3, i, 1, 1));
			corruptBarriers.add(new CorruptableBarrier(28, i, 1, 1));
		}
		corruptBarriers.add(new CorruptableBarrier(4, 3, 1, 1));
		corruptBarriers.add(new CorruptableBarrier(4, 13, 1, 1));
		corruptBarriers.add(new CorruptableBarrier(27, 3, 1, 1));
		corruptBarriers.add(new CorruptableBarrier(27, 13, 1, 1));
		for(int i = 8; i < 14; i++){
			corruptBarriers.add(new CorruptableBarrier(i, 1, 1, 1));
			corruptBarriers.add(new CorruptableBarrier(i, 15, 1, 1));
		}
		for(int i = 18; i < 24; i++){
			corruptBarriers.add(new CorruptableBarrier(i, 1, 1, 1));
			corruptBarriers.add(new CorruptableBarrier(i, 15, 1, 1));
		}
		corruptBarriers.add(new CorruptableBarrier(18, 0, 1, 1));
		corruptBarriers.add(new CorruptableBarrier(13, 0, 1, 1));
		for(int i = 16; i < 18; i++){
			corruptBarriers.add(new CorruptableBarrier(18, i, 1, 1));
			corruptBarriers.add(new CorruptableBarrier(13, i, 1, 1));
		}
		
		for(CorruptableBarrier b : corruptBarriers){
			b.setTurret(1);
			barriers.add(b);
			b.setPlayerOne(p1);
			b.setPlayerTwo(p2);
		}
		
		//Four 2x1s
		//Left side
		barriers.add(new BreakableBarrier(6, 6, 1, 1));
		barriers.add(new BreakableBarrier(6, 10, 1, 1));
		barriers.add(new BreakableBarrier(7, 6, 1, 1));
		barriers.add(new BreakableBarrier(7, 10, 1, 1));
		//Right side
		barriers.add(new BreakableBarrier(25, 6, 1, 1));
		barriers.add(new BreakableBarrier(25, 10, 1, 1));
		barriers.add(new BreakableBarrier(24, 6, 1, 1));
		barriers.add(new BreakableBarrier(24, 10, 1, 1));

		//Middle breakable circle
		//Left side
		barriers.add(new BreakableBarrier(10, 6, 1, 1));
		barriers.add(new BreakableBarrier(10, 7, 1, 1));
		CorruptableBarrier flower1 = new CorruptableBarrier(10, 8, 1, 1);
		flower1.setTurret(2);
		flower1.setPlayerOne(p1);
		flower1.setPlayerTwo(p2);
		barriers.add(flower1);
		corruptBarriers.add(flower1);
		barriers.add(new BreakableBarrier(10, 9, 1, 1));
		barriers.add(new BreakableBarrier(10, 10, 1, 1));
		barriers.add(new BreakableBarrier(11, 5, 1, 1));
		barriers.add(new BreakableBarrier(11, 11, 1, 1));
		//Right side
		barriers.add(new BreakableBarrier(21, 6, 1, 1));
		barriers.add(new BreakableBarrier(21, 7, 1, 1));
		CorruptableBarrier flower2 = new CorruptableBarrier(21, 8, 1, 1);
		flower2.setTurret(2);
		flower2.setPlayerOne(p1);
		flower2.setPlayerTwo(p2);
		barriers.add(flower2);
		corruptBarriers.add(flower2);
		barriers.add(new BreakableBarrier(21, 9, 1, 1));
		barriers.add(new BreakableBarrier(21, 10, 1, 1));
		barriers.add(new BreakableBarrier(20, 5, 1, 1));
		barriers.add(new BreakableBarrier(20, 11, 1, 1));

		//Middle square
		CorruptableBarrier mid = new CorruptableBarrier(15, 8, 1, 1);
		CorruptableBarrier mid2 = new CorruptableBarrier(16, 8, 1, 1);
		mid.setTurret(3);
		mid2.setTurret(3);
		mid.setPlayerOne(p1);
		mid.setPlayerTwo(p2);
		mid2.setPlayerOne(p1);
		mid2.setPlayerTwo(p2);
		barriers.add(mid);
		barriers.add(mid2);
		corruptBarriers.add(mid);
		corruptBarriers.add(mid2);
		barriers.add(new BreakableBarrier(15, 7, 1, 1));
		barriers.add(new BreakableBarrier(16, 7, 1, 1));
		barriers.add(new BreakableBarrier(15, 9, 1, 1));
		barriers.add(new BreakableBarrier(16, 9, 1, 1));

		//2x1 on top and bottom
		//Top
		barriers.add(new BreakableBarrier(12, 2, 1, 1));
		barriers.add(new BreakableBarrier(11, 2, 1, 1));
		barriers.add(new BreakableBarrier(19, 2, 1, 1));
		barriers.add(new BreakableBarrier(20, 2, 1, 1));
		//Bottom
		barriers.add(new BreakableBarrier(12, 14, 1, 1));
		barriers.add(new BreakableBarrier(11, 14, 1, 1));
		barriers.add(new BreakableBarrier(19, 14, 1, 1));
		barriers.add(new BreakableBarrier(20, 14, 1, 1));
	}
	
	/**
	 * draws the map on the screen
	 * @param g drawer that draws the map
	 */
	public void draw(Graphics2D g2) {
		barriers.stream().forEach((Barrier b) -> b.draw(g2));
	}
	
	/**
	 * returns the barriers in the map
	 * @return barriers of the map
	 */
	public ArrayList<Barrier> getBarriers() {
		return barriers;
	}
	
	/**
	 * adds the barrier into the map
	 * @param barrier barrier that is added in the map
	 */
	public void addBarrier(Barrier barrier) {
		barriers.add(barrier);
	}
	
	/**
	 * Gets the barrier at specified index
	 * @param index index of barrier 
	 * @return the barrier at specified index
	 */
	public Barrier getBarrier(int index) {
		return barriers.get(index);
	}
}
