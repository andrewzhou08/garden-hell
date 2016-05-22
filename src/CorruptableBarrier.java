import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class CorruptableBarrier extends Barrier {
		
	private boolean isCorrupted;
	private Animation corruptAnimation;
	private boolean hasSpawnedTurrets;
	private int turretNumber;
	private double velX, velY;
	private int skippedFrames;
	private ArrayList<Turret> turrets;
	private Player p1, p2;
	
	/**
	 * Creates a corruptanle barrer
	 * @param x x coordinate of the barrier
	 * @param y y coordinate of the barrier
	 * @param width width of the barrier
	 * @param height height of the barrier
	 */
	public CorruptableBarrier(int x, int y, int width, int height) {
		super(x, y, width, height);
		turrets = new ArrayList<Turret>();
		corruptAnimation = new Animation("assets/barrier-corrupt/barrier-corrupt(%d).png", 1, 16, 3);
		velX = velY = 0;
	}

	/**
	 * Causes the corrupted barrier to act, updates animation
	 */
	public void act() {
		if (isCorrupted && corruptAnimation.getCurrentFrameID() < corruptAnimation.length() - 1) {
			corruptAnimation.update();
		}
		
		velX += Math.random()/8-0.0625;
		velY += Math.random()/8-0.0625;
		
		if(isCorrupted && skippedFrames == 6){
			skippedFrames = 0;
			if(super.getX() <= 120 && velX < 0){
				velX = 0;
			}
			else if(super.getX() >= 1120 && velX > 0){
				velX = 0;
			}
			else if(super.getY() <= 20 && velY < 0){
				velY = 0;
			}
			else if(super.getY() >= 640 && velY > 0){
				velY = 0;
			}
			
			HitBox h = super.getHitBox();
			HitBox p1HitBox = new HitBox(p1.getX()-10, p1.getY()-10, 60, 60);
			HitBox p2HitBox = new HitBox(p2.getX()-10, p2.getY()-10, 60, 60);
			
			if(p1HitBox.intersects(h)){
				velX = 0;
				velY = 0;
			}
			if(p2HitBox.intersects(h)){
				velX = 0;
				velY = 0;
			}
			
			super.moveBy((int)velX, (int)velY);
			
			for(Turret t : turrets){
				t.move(super.getX(), super.getY());
			}
		}
		skippedFrames++;
	}
	
	public void draw(Graphics2D g2){
		super.draw(g2);
		
	}
	
	public void setPlayerOne(Player p1){
		this.p1 = p1;
	}
	
	public void setPlayerTwo(Player p2){
		this.p2 = p2;
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
		this.turrets = turrets;
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
	
	public boolean getCorruption(){
		return isCorrupted;
	}
}
