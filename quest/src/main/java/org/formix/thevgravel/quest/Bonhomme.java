package org.formix.thevgravel.quest;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.formix.thevgravel.quest.engine.Actor;
import org.formix.thevgravel.quest.engine.Event;

public class Bonhomme extends Actor {

	private static final int SPRITE_WIDTH = 32;
	private static final int SPRITE_HEIGHT = 32;
	
	private Image rightRunSprite;
	private int spriteIndex;
	private long lastUpdate;
	private int fps;
	private boolean mooving;
	
	
	public Bonhomme() {
		this(4);
	}
	
	public Bonhomme(int fps) {
		this.mooving = false;
		this.spriteIndex = 0;
		this.lastUpdate = 0;
		this.fps = fps;
		try {
			this.rightRunSprite = ImageIO.read(this.getClass().getResourceAsStream("courir-droite-sprite.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getFPS() {
		return this.fps;
	}
	
	public void setFPS(int fps) {
		this.fps = fps;
	}

	public void update() {
		long maxRetentionTime = 1000 / this.fps;
		long now = System.currentTimeMillis();
		long retention = now - this.lastUpdate;
		if (retention >= maxRetentionTime) {
			this.lastUpdate = now;
			this.spriteIndex++;
			this.moveRight();
		}
	}

	public void draw(Graphics g) {
		int spriteX = (this.spriteIndex * SPRITE_WIDTH) % this.rightRunSprite.getWidth(null);
		g.drawImage(this.rightRunSprite, this.getX(), this.getY(), this.getX() + SPRITE_WIDTH * 4, this.getY() + SPRITE_HEIGHT * 4, spriteX,
				0, spriteX + SPRITE_WIDTH - 1, SPRITE_HEIGHT - 1, null);
	}

	public void registerEvents() {
		this.registerEvent("accelerate");
	}
	
	@Override
	public void notify(Event e) {
		if (e.getData().equals(' ')) {
			this.changeFrameRate();
			this.mooving = true;
		}
	}

	private void moveRight() {
		this.setX(this.getX() + 6);
		this.setX(this.getX() % 500);
	}

	private void changeFrameRate() {
		this.fps = (this.fps + 2)  % 20;
		if (this.fps == 0) {
			this.fps = 4;
		}
	}

}
