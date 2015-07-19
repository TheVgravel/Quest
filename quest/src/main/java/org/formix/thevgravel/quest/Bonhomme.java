package org.formix.thevgravel.quest;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.security.PublicKey;

import javax.imageio.ImageIO;

public class Bonhomme {

	private static final int SPRITE_WIDTH = 32;
	private static final int SPRITE_HEIGHT = 32;

	private Image rightRunSprite;
	private int x;
	private int y;

	public Bonhomme() {
		this.x = 0;
		this.y = 0;
		try {
			this.rightRunSprite = ImageIO.read(this.getClass().getResourceAsStream("courir-droite-sprite.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getWidth(){
		return SPRITE_WIDTH;
	}
	
	public int getHeight(){
		return SPRITE_HEIGHT;
	}

	public void draw(Graphics g, int spriteIndex) {
		g.drawImage(this.rightRunSprite, this.x, this.y, SPRITE_WIDTH * 16, SPRITE_HEIGHT * 4, null);
//		int spriteX = spriteIndex * SPRITE_WIDTH;
//		g.drawImage(this.rightRunSprite, 
//					this.x, this.y, SPRITE_WIDTH - 1, SPRITE_HEIGHT - 1, 
//					spriteX, 0, spriteX, SPRITE_HEIGHT - 1, null);
	}

}
