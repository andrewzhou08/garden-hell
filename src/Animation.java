import java.awt.Image;

import javax.swing.ImageIcon;

public class Animation {
	
	private Image[] frames;
	private int currentFrame;
	private int delayTime;
	private int elapsedTime;
	/**
	 * Creates a new animation with the following fields
	 * @param filenameFormat Filename format of the animation
	 * @param start The start frame number of the animation
	 * @param end The end frame number of the animation
	 * @param delayTime The time delayed between frames
	 */
	public Animation(String filenameFormat, int start, int end, int delayTime) {
		frames = new Image[end - start + 1];
		for (int i = 0; i < frames.length; i++) {
			frames[i] = (new ImageIcon(String.format(filenameFormat, start + i))).getImage();
		}
		currentFrame = 0;
		this.delayTime = delayTime;
		elapsedTime = 0;
	}
	
	/**
	 * 
	 * @return Current frame of the animation
	 */
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

	/**
	 * 
	 * @return The id of the current frame
	 */
	public int getCurrentFrameID() {
		return currentFrame;
	}

	/**
	 * 
	 * @return The length of the animation
	 */
	public int length() {
		return frames.length;
	}
}
