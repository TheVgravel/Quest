package org.formix.thevgravel.quest;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.formix.thevgravel.quest.engine.Picture;

public class Bloc extends Picture {
	
	
	public Bloc() {
		try {
			this.setImage(ImageIO.read(this.getClass().getResourceAsStream("mur-test.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
	}

	public void draw(Graphics g) {
		
	}

	public void initialize() {
	}
	

}
