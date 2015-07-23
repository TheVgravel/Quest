package org.formix.thevgravel.quest;

import java.io.IOException;

import javax.imageio.ImageIO;

import org.formix.thevgravel.quest.engine.Sprite;

public class Bloc extends Sprite {

	public Bloc() {
		try {
			this.setImage(ImageIO.read(this.getClass().getResourceAsStream("mur-test.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
