package org.formix.thevgravel.quest;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.formix.thevgravel.quest.engine.Decor;

public class Bloc extends Decor {
	
	public Bloc() {
		try {
			Image image = ImageIO.read(this.getClass().getResourceAsStream("mur-test.png"));
			this.setImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
