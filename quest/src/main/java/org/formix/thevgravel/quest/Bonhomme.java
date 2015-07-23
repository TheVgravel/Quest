package org.formix.thevgravel.quest;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.formix.thevgravel.quest.engine.Animation;


public class Bonhomme extends Animation {

	private boolean mooving;
	
	
	public Bonhomme() {
		super(null, 4, 4, null);
		this.mooving = false;
		try {
			Image image = ImageIO.read(this.getClass().getResourceAsStream("courir-droite-sprite.png"));
			this.setImage(image);
			this.setAnimationPosition(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isMooving() {
		return this.mooving;
	}
	
	public void setMooving(boolean mooving) {
		this.mooving = mooving;
	}
	
	public boolean update() {
		if (super.update()) {
			this.moveRight();
			return true;
		}
		return false;
	}

	
	@Override
	public void initialize() {
		this.getScene().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				accelerate();
			}
		});
	}
	
	protected void accelerate() {
		if (!this.isAnimated()) {
			this.startAnimation();
			return;
		}
	}

	private void moveRight() {
		this.setX(this.getX() + 6);
		this.setX(this.getX() % 500);
	}
}
