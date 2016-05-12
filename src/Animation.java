import java.awt.Image;

import javax.swing.ImageIcon;

public class Animation {
	
	private Image[] frames;
	private int currentFrame;
	private int delayTime;
	private int elapsedTime;
	
	public Animation(String filenameFormat, int start, int end, int delayTime) {
		frames = new Image[end - start];
		for (int i = 0; i < frames.length; i++) {
			frames[i] = (new ImageIcon(String.format(filenameFormat, start + i))).getImage();
		}
		currentFrame = 0;
		this.delayTime = delayTime;
		elapsedTime = 0;
	}
	
	public Image getCurrentFrame() {
		return frames[currentFrame];
	}
	
	public void update() {
		elapsedTime++;
		if (elapsedTime == delayTime) {
			elapsedTime = 0;
			currentFrame++;
			if (currentFrame == frames.length) {
				currentFrame = 0;
			}
		}
	}

}
