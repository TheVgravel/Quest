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
	
	public Decor(Image image) {
		super();
		this.setZ(50);
		this.image = image;
	}
	
	public Image getImage() {
		return this.image;
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
				this.getX() + this.image.getWidth(null) - 1, 
				this.getY() + this.image.getHeight(null) - 1, 
				0,
				0, 
				this.image.getWidth(null) - 1, 
				this.image.getHeight(null) - 1, 
				null);
	}

}
