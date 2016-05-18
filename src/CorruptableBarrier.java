import java.awt.Image;

public class CorruptableBarrier extends Barrier {
		
	private boolean isCorrupted;
	private Animation corruptAnimation;
	
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
