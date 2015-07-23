package org.formix.thevgravel.quest.engine;

import java.awt.Graphics;
import java.awt.Image;

/**
 * A Sprite is a dynamic Scene item backed by an image with or without
 * transparency.
 * 
 * @author jpgravel
 *
 */
public class Sprite extends AbstractItem {

	private Image image;
	private double zoomFactor;

	/**
	 * Creates a dummy sprite for subclassing.
	 */
	protected Sprite() {
		this(null, 100);
	}

	/**
	 * Creates a Sprite object.
	 * 
	 * @param image
	 *            The backing image of the sprite.
	 */
	public Sprite(Image image) {
		this(image, 100);
	}

	/**
	 * Initializes a ImageItem to with a given image and ZoomFactor.
	 * 
	 * @param image
	 *            The image to use for the sprite.
	 * 
	 * @param zoomFactor
	 *            The zoom factor applied to the sprite.
	 */
	public Sprite(Image image, double zoomFactor) {
		this.image = image;
		this.zoomFactor = zoomFactor;
		this.setZ(100);
	}

	/**
	 * Gets the image of the current sprite.
	 * 
	 * @return the image of the current sprite.
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Sets the image of the current sprite.
	 * 
	 * @param image
	 *            the image of the current sprite.
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Gets the zoom factor applied to the current sprite. The default value is
	 * 100 which imply a 1:1 ratio.
	 * 
	 * @return
	 */
	public double getZoomFactor() {
		return zoomFactor;
	}

	/**
	 * Sets the zoom factor of the current sprite, from 0 to infinite. 100 being
	 * 100% or a 1:1 ratio.
	 * 
	 * @param zoomFactor
	 *            the zoom factor.
	 */
	public void setZoomFactor(double zoomFactor) {
		this.zoomFactor = zoomFactor;
	}

	/**
	 * Returns the zoom factor multiplied by the Z_Index / 100.
	 * 
	 * @return the zoom factor multiplied by the Z_Index / 100.
	 */
	public double getEffectiveZoomFactor() {
		return this.zoomFactor * this.getZ() / 100.0;
	}

	public boolean update() {
		return false;
	}

	public void initialize() {
	}
	
	public synchronized void draw(Graphics g) {
		if (this.image == null) {
			return;
		}

		int imgWidth = this.image.getWidth(null);
		int imgHeight = this.getImage().getHeight(null);
		int displayedWidth = (int) (imgWidth * this.getEffectiveZoomFactor() / 100);
		int displayedHeight = (int) (imgHeight * this.getEffectiveZoomFactor() / 100);

		g.drawImage(
				this.image, 
				this.getX(), 
				this.getY(),
				this.getX() + displayedWidth,
				this.getY() + displayedHeight, 
				0, 
				0, 
				imgWidth, 
				imgHeight, 
				null);
	}
}
