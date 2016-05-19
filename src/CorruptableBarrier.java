import java.awt.Image;
import java.util.ArrayList;

public class CorruptableBarrier extends Barrier {
		
	private boolean isCorrupted;
	private Animation corruptAnimation;
	private boolean hasSpawnedTurrets;
	
	public CorruptableBarrier(int x, int y, int width, int height) {
		super(x, y, width, height);
		corruptAnimation = new Animation("assets/barrier-corrupt/barrier-corrupt(%d).png", 1, 16, 3);
	}

	@Override
	public void act() {
		if (isCorrupted && corruptAnimation.getCurrentFrameID() < corruptAnimation.length() - 1) {
			corruptAnimation.update();
		}
	}
	
	public void setCorrupt(boolean corrupt) {
		isCorrupted = corrupt;
	}
	
	public ArrayList<Turret> spawnTurrets() {
		ArrayList<Turret> turrets = new ArrayList<Turret>();
		if (hasSpawnedTurrets) {
			return turrets;
		}
		hasSpawnedTurrets = true;
		int randomTurret = (int) (Math.random() * 4);
		for (int x = getX(); x < getX() + getWidth(); x += Main.CELL_WIDTH) {
			for (int y = getY(); y < getY() + getHeight(); y += Main.CELL_HEIGHT) {
				if (randomTurret == 0)
					turrets.add(new StandardTurret(x, y));
				else if (randomTurret == 1)
					turrets.add(new FlowerTurret(x, y));
				else if (randomTurret == 2)
					turrets.add(new LaserTurret(x, y));
				else
					turrets.add(new PowerOrbTurret(x, y));
			}
		}
		return turrets;
	}
	
	@Override
	public Image getSprite() {
		if (isCorrupted) {
			return corruptAnimation.getCurrentFrame();
		}
		else {
			return super.getSprite();
		}
	}
}
