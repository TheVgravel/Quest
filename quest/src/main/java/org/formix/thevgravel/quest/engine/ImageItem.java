package org.formix.thevgravel.quest.engine;

import java.awt.Graphics;
import java.awt.Image;

public abstract class ImageItem extends AbstractItem {

	private Image image;
	private double zoomFactor;

	
	public ImageItem() {
		this(null, 100);
	}
	
	public ImageItem(Image image) {
		this(image, 100);
	}
	
	/**
	 * Initializes a ImageItem to with a given image and ZoomFactor. 
	 */
	public ImageItem(Image image, double zoomFactor) {
		this.image = image; 
		this.zoomFactor = zoomFactor;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public double getZoomFactor() {
		return zoomFactor;
	}

	public void setZoomFactor(double zoomFactor) {
		this.zoomFactor = zoomFactor;
	}
	
	
	/**
	 * Returns the zomm factor multiplied by the Z_Index / 100.
	 *  
	 * @return the zomm factor multiplied by the Z_Index / 100.
	 */
	public double getEffectiveZoomFactor() {
		return this.zoomFactor * this.getZ() / 100;
	}

	public void draw(Graphics g) {
		if (this.image == null) {
			return;
		}
		
		int imgWidth = this.image.getWidth(null);
		int imgHeight = this.getImage().getHeight(null);
		
		g.drawImage(this.image, 
				this.getX(), 
				this.getY(), 
				this.getX() + (int)(imgWidth * this.getEffectiveZoomFactor()), 
				this.getY() + (int)(imgHeight * this.getEffectiveZoomFactor()), 
				0,
				0, 
				imgWidth, 
				imgHeight, 
				null);
	}
}
