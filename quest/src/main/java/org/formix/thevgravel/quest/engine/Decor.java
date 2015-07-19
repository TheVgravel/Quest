package org.formix.thevgravel.quest.engine;

import java.awt.Graphics;
import java.awt.Image;

/**
 * A Decor is something passive that does nothing.
 * 
 * @author jpgravel
 *
 */
public class Decor extends AbstractSceneItem {

	private Image image;
	private double zoom;

	public Decor() {
		this(null);
	}
	
	public Decor(Image image) {
		super();
		this.setZ(50);
		this.image = image;
		this.zoom = 100;
	}
	
	public Image getImage() {
		return this.image;
	}
	
	protected void setImage(Image image) {
		this.image = image;
	}
	
	public double getZoom() {
		return this.zoom;
	}
	
	public void setZoom(double zoom) {
		this.zoom = zoom;
	}
	
	public void registerEvents() {
	}

	public void update() {
		// a Decor shouldn't have anything to do...
	}

	public void draw(Graphics g) {
		g.drawImage(
				this.image, 
				this.getX(), 
				this.getY(), 
				this.getX() + (int) (this.image.getWidth(null) * this.zoom / 100), 
				this.getY() + (int) (this.image.getHeight(null) * this.zoom / 100), 
				0,
				0, 
				this.image.getWidth(null), 
				this.image.getHeight(null), 
				null);
	}

}
