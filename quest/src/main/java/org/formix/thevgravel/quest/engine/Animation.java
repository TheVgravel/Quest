package org.formix.thevgravel.quest.engine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * An animation is a sprite composed of many images.
 * 
 * @author jpgravel
 *
 */
public class Animation extends Sprite {

	private int spriteCount;
	private int fps;
	private int spritePeriod; // the time between sprite changes: 1000 / fps
	private Rectangle spriteLane;
	private int spriteWidth;
	private int spriteIndex;
	private boolean animated;
	private long lastUpdate;

	/**
	 * Creates a dummy animation for subclassing.
	 */
	protected Animation() {
		this(null, 1, 1, null);
	}

	/**
	 * Creates an animation from the given parameters taking the whole image for
	 * animation.
	 * 
	 * @param image
	 *            The image containing all part of the curent animation.
	 * 
	 * @param spriteCount
	 *            The number of sprites in the current animation.
	 * 
	 * @param fps
	 *            The desired frame per seconds for the current animation.
	 */
	public Animation(Image image, int spriteCount, int fps) {
		this(image, spriteCount, fps, null);
	}

	/**
	 * Creates an animation from the given parameters.
	 * 
	 * @param image
	 *            The image containing all part of the curent animation.
	 * 
	 * @param spriteCount
	 *            The number of sprites in the current animation.
	 * 
	 * @param fps
	 *            The desired frame per seconds for the current animation.
	 * 
	 * @param spriteLane
	 *            The position of all images of the current animation within the
	 *            given sprite sheet. The animation sprites are expected to be
	 *            all of the same width and height, contiguous and horizontal
	 *            within the given image.
	 */
	public Animation(Image image, int spriteCount, int fps, Rectangle spriteLane) {
		super(image);
		this.spriteCount = spriteCount;
		this.setFps(fps);
		this.setSpriteLane(spriteLane);
		this.spriteIndex = 0;
		this.animated = false;
		this.lastUpdate = 0;
		this.spriteWidth = 0;

	}

	/**
	 * Gets the index of the currently displayed sprite from the internal sprite
	 * sheet.
	 * 
	 * @return the index of the currently displayed sprite from the internal
	 *         sprite sheet.
	 */
	public int getSpriteIndex() {
		return this.spriteIndex;
	}

	/**
	 * Sets the index of the currently displayed sprite from the internal
	 * 
	 * @param spriteIndex
	 *            the index of the currently displayed sprite from the internal
	 *            sprite sheet.
	 */
	public void setSpriteIndex(int spriteIndex) {
		if (spriteIndex < 0 || spriteIndex >= this.spriteCount) {
			throw new IllegalArgumentException("The sprite index must be between 0 and imageCount - 1.");
		}
		this.spriteIndex = spriteIndex;
	}

	/**
	 * Gets the animation position within the specified image. By default, the
	 * sprite position is the rectangle defined by the whole image: 0, 0,
	 * image.width, image.height
	 * 
	 * @return the sprite position within the specified image.
	 */
	public Rectangle getSpriteLane() {
		return this.spriteLane;
	}

	/**
	 * Sets the animation position within the specified image. Setting the
	 * animation position to null will revert back to the default position of 0,
	 * 0, image.width, image.height (the whole image).
	 * 
	 * @param animationPosition
	 *            the sprite position within the specified image.
	 */
	public void setSpriteLane(Rectangle animationPosition) {
		if (animationPosition != null) {
			this.spriteLane = animationPosition;
		} else if (this.getImage() != null) {
			this.spriteLane = new Rectangle(0, 0, this.getImage().getWidth(null),
					this.getImage().getHeight(null));
		}
		if ((this.spriteLane != null) && (this.spriteCount > 0)) {
			this.spriteWidth = this.getImage().getWidth(null) / this.spriteCount;
		}
	}

	/**
	 * Gets the number of sprites of the current animation.
	 * 
	 * @return the number of sprites of the current animation.
	 */
	public int getSpriteCount() {
		return spriteCount;
	}

	/**
	 * Sets the number of images of the current animation.
	 * 
	 * @param spriteCount
	 *            the number of images of the current animation.
	 */
	public void setSpriteCount(int spriteCount) {
		this.spriteCount = spriteCount;
	}

	/**
	 * Gets the frame per seconds for the curren animation.
	 * 
	 * @return the frame per seconds for the curren animation.
	 */
	public int getFps() {
		return fps;
	}

	/**
	 * Sets the frame per seconds for the current animation.
	 * 
	 * @param fps
	 *            the frame per seconds for the current animation.
	 */
	public void setFps(int fps) {
		if (fps <= 0) {
			throw new IllegalArgumentException("The frame per seconds must be greater than 0.");
		}
		this.fps = fps;
		this.spritePeriod = 1000 / fps;
	}

	@Override
	public void setImage(Image image) {
		super.setImage(image);
		this.setSpriteLane(null);
	}

	/**
	 * Tells if the current sprite is animated.
	 * 
	 * @return if the current sprite is animated.
	 */
	public boolean isAnimated() {
		return this.animated;
	}

	/**
	 * Starts the animation.
	 */
	public void startAnimation() {
		this.animated = true;
		if (this.lastUpdate == 0) {
			this.lastUpdate = System.currentTimeMillis();
		}
	}

	/**
	 * Stops the animation and reset the spriteIndex back to 0.
	 */
	public void stopAnimation() {
		this.animated = false;
		this.setSpriteIndex(0);
		this.lastUpdate = 0;
	}

	@Override
	public synchronized boolean update() {
		if (!this.animated) {
			return false;
		}
		long now = System.currentTimeMillis();
		long period = now - this.lastUpdate;
		if (period >= this.spritePeriod) {
			this.spriteIndex = (this.spriteIndex + 1) % this.spriteCount;
			this.lastUpdate = now;
			return true;
		}
		return false;
	}

	@Override
	public synchronized void draw(Graphics g) {
		if (this.getImage() == null) {
			return;
		}

		int imgWidth = this.spriteWidth;
		int imgHeight = this.getSpriteLane().height;
		int displayedWidth = (int) (imgWidth * this.getEffectiveZoomFactor() / 100);
		int displayedHeight = (int) (imgHeight * this.getEffectiveZoomFactor() / 100);

		g.drawImage(this.getImage(), this.getX(), this.getY(), this.getX() + displayedWidth,
				this.getY() + displayedHeight, this.getSpriteLane().x + imgWidth * this.spriteIndex,
				this.getSpriteLane().y, this.getSpriteLane().x + imgWidth * (this.spriteIndex + 1),
				this.getSpriteLane().y + this.getSpriteLane().height, null);
	}

}
