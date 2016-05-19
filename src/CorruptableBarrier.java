import java.awt.Image;
import java.util.ArrayList;

public class CorruptableBarrier extends Barrier {
		
	private boolean isCorrupted;
	private Animation corruptAnimation;
	private boolean hasSpawnedTurrets;
	private int turretNumber;
	/**
	 * Creates a corruptanle barrer
	 * @param x x coordinate of the barrier
	 * @param y y coordinate of the barrier
	 * @param width width of the barrier
	 * @param height height of the barrier
	 */
	public CorruptableBarrier(int x, int y, int width, int height) {
		super(x, y, width, height);
		corruptAnimation = new Animation("assets/barrier-corrupt/barrier-corrupt(%d).png", 1, 16, 3);
	}

	/**
	 * Causes the corrupted barrier to act, updates animation
	 */
	public void act() {
		if (isCorrupted && corruptAnimation.getCurrentFrameID() < corruptAnimation.length() - 1) {
			corruptAnimation.update();
		}
	}
	/**
	 * Sets whether the barrier is corrupted or not
	 * @param corrupt whether the barrier should be corrupt or not
	 */
	public void setCorrupt(boolean corrupt) {
		isCorrupted = corrupt;
	}
	/**
	 * Spawns the turrets that go on top of the barrier
	 * @return Array List of turrets that spawned
	 */
	public ArrayList<Turret> spawnTurrets() {
		ArrayList<Turret> turrets = new ArrayList<Turret>();
		if (hasSpawnedTurrets || corruptAnimation.getCurrentFrameID() < corruptAnimation.length() - 1) {
			return turrets;
		}
		hasSpawnedTurrets = true;
		int randomTurret = (int) (Math.random() * 3);
		for (int x = getX(); x < getX() + getWidth(); x += Main.CELL_WIDTH) {
			for (int y = getY(); y < getY() + getHeight(); y += Main.CELL_HEIGHT) {
				if(turretNumber == 1){
					turrets.add(new StandardTurret(x, y));
				}
				else if(turretNumber == 2){
					turrets.add(new FlowerTurret(x, y));
				}
				else{
					turrets.add(new PowerOrbTurret(x, y));
				}
			}
		}
		return turrets;
	}
	
	public void setTurret(int turretNumber){
		this.turretNumber = turretNumber;
	}
	
	/**
	 * returns the sprite of the turret
	 * @return Image of the sprite
	 */
	public Image getSprite() {
		if (isCorrupted) {
			return corruptAnimation.getCurrentFrame();
		}
		else {
			return super.getSprite();
		}
	}
}
