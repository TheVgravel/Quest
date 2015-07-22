package org.formix.thevgravel.quest.engine;

import java.awt.Image;
import java.awt.Rectangle;

public class Sprite extends Picture {

	private int imageCount;
	private int fps;
	private Rectangle spritePosition;
	private int spriteIndex;

	public Sprite(Image image, int imageCount) {
		super(image);
		this.imageCount = imageCount;
		this.fps = imageCount;
		this.setSpritePosition(null);
		this.spriteIndex = 0;
	}

	public int getSpriteIndex() {
		return this.spriteIndex;
	}

	public void setSpriteIndex(int spriteIndex) {
		if (spriteIndex < 0 || spriteIndex >= this.imageCount) {
			throw new IllegalArgumentException("The sprite index must be between 0 and imageCount - 1.");
		}
		this.spriteIndex = spriteIndex;
	}

	/**
	 * Gets a single image width within the sprite. ImageWidth is
	 * position.getWidth() / imageCount.
	 * 
	 * @return
	 */
	public int getImageWidth() {
		return this.spritePosition.getWidth() / this.imageCount;
	}

	/**
	 * Gets the sprite position within the specified image. By default, the
	 * sprite position is the rectangle defined by the whole image: 0, 0,
	 * image.width, image.height
	 * 
	 * @return the sprite position within the specified image.
	 */
	public Rectangle getSpritePosition() {
		return spritePosition;
	}

	/**
	 * Sets the sprite position within the specified image. Setting the sprite
	 * position to null will revert back to the default sprite position of 0, 0,
	 * image.width, image.height.
	 * 
	 * @param spritePosition
	 *            the sprite position within the specified image.
	 */
	public void setSpritePosition(Rectangle spritePosition) {
		if (spritePosition != null) {
			this.spritePosition = spritePosition;
		} else {
			this.spritePosition = new Rectangle(0, 0, this.getImage().getWidth(null), this.getImage().getHeight(null));
		}
	}

	public int getImageCount() {
		return imageCount;
	}

	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}

	public synchronized void update() {

		g.drawImage(this.getImage(), this.getX(), this.getY(), this.getX() + SPRITE_WIDTH * 4,
				this.getY() + SPRITE_HEIGHT * 4, spriteX, 0, spriteX + SPRITE_WIDTH - 1, SPRITE_HEIGHT - 1, null);
	}

	public void initialize() {
	}

}
