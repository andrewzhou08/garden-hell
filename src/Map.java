import java.awt.Graphics2D;
import java.util.ArrayList;

public class Map {

	private ArrayList<Barrier> barriers;

	public Map(){
		barriers = new ArrayList<Barrier>();
		generateMapBarriers();
	}

	public void generateMapBarriers(){
		
		CorruptableBarrier[] sides = new CorruptableBarrier[14];
		
		//Two I shaped on left/right sides
		sides[0] = new CorruptableBarrier(3, 3, 1, 11);
		sides[1] = new CorruptableBarrier(28, 3, 1, 11);
		sides[2] = new CorruptableBarrier(4, 3, 1, 1);
		sides[3] = new CorruptableBarrier(4, 13, 1, 1);
		sides[4] = new CorruptableBarrier(27, 3, 1, 1);
		sides[5] = new CorruptableBarrier(27, 13, 1, 1);
		sides[6] = new CorruptableBarrier(8, 1, 6, 1);
		sides[7] = new CorruptableBarrier(18, 1, 6, 1);
		sides[8] = new CorruptableBarrier(18, 0, 1, 1);
		sides[9] = new CorruptableBarrier(13, 0, 1, 1);
		sides[10] = new CorruptableBarrier(8, 15, 6, 1);
		sides[11] = new CorruptableBarrier(18, 15, 6, 1);
		sides[12] = new CorruptableBarrier(18, 16, 1, 2);
		sides[13] = new CorruptableBarrier(13, 16, 1, 2);
		
		for(CorruptableBarrier b : sides){
			b.setTurret(1);
			barriers.add(b);
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
		barriers.add(flower1);
		barriers.add(new BreakableBarrier(10, 9, 1, 1));
		barriers.add(new BreakableBarrier(10, 10, 1, 1));
		barriers.add(new BreakableBarrier(11, 5, 1, 1));
		barriers.add(new BreakableBarrier(11, 11, 1, 1));
		//Right side
		barriers.add(new BreakableBarrier(21, 6, 1, 1));
		barriers.add(new BreakableBarrier(21, 7, 1, 1));
		CorruptableBarrier flower2 = new CorruptableBarrier(21, 8, 1, 1);
		flower2.setTurret(2);
		barriers.add(flower2);
		barriers.add(new BreakableBarrier(21, 9, 1, 1));
		barriers.add(new BreakableBarrier(21, 10, 1, 1));
		barriers.add(new BreakableBarrier(20, 5, 1, 1));
		barriers.add(new BreakableBarrier(20, 11, 1, 1));

		//Middle square
		CorruptableBarrier mid = new CorruptableBarrier(15, 8, 2, 1);
		mid.setTurret(3);
		barriers.add(mid);
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

	public void draw(Graphics2D g){
		for(Barrier b : barriers){
			b.draw(g);
		}
	}

	public ArrayList<Barrier> getBarriers(){
		return barriers;
	}

	public void addBarrier(Barrier barrier){
		barriers.add(barrier);
	}

	public Barrier getBarrier(int index){
		return barriers.get(index);
	}
}
