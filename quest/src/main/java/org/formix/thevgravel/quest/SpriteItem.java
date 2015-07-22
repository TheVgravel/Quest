package org.formix.thevgravel.quest;

import java.awt.Image;
import java.awt.Rectangle;

import org.formix.thevgravel.quest.engine.ImageItem;

public class SpriteItem extends ImageItem {

	private Rectangle position;
	private int imageCount;
	private int fps;
	
	
	public SpriteItem() {
	}

	public SpriteItem(Image image) {
		super(image);
		// TODO Auto-generated constructor stub
	}

	public SpriteItem(Image image, double zoomFactor) {
		super(image, zoomFactor);
		// TODO Auto-generated constructor stub
	}
	
	public Rectangle getPosition() {
		return position;
	}

	public void setPosition(Rectangle position) {
		this.position = position;
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

	public void update() {
		// TODO Auto-generated method stub

	}

	public void initialize() {
		// TODO Auto-generated method stub

	}

}
