package org.formix.thevgravel.quest;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.formix.thevgravel.quest.engine.Actor;

public class Bonhomme extends Actor {

	private static final int SPRITE_WIDTH = 32;
	private static final int SPRITE_HEIGHT = 32;
	
	private Image rightRunSprite;
	private int spriteIndex;
	private long lastUpdate;
	private int fps;
	
	
	public Bonhomme() {
		this(4);
	}
	
	public Bonhomme(int fps) {
		this.spriteIndex = 0;
		this.lastUpdate = 0;
		this.fps = fps;
		try {
			this.rightRunSprite = ImageIO.read(this.getClass().getResourceAsStream("courir-droite-sprite.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public long getFPS() {
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
		}
	}

	public void draw(Graphics g) {
		int spriteX = (this.spriteIndex * SPRITE_WIDTH) % this.rightRunSprite.getWidth(null);
		g.drawImage(this.rightRunSprite, this.getX(), this.getY(), this.getX() + SPRITE_WIDTH * 4, this.getY() + SPRITE_HEIGHT * 4, spriteX,
				0, spriteX + SPRITE_WIDTH - 1, SPRITE_HEIGHT - 1, null);
	}

	public void registerEvents() {
		// TODO Auto-generated method stub

	}

}
