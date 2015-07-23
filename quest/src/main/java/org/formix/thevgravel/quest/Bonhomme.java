package org.formix.thevgravel.quest;

import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
			this.setImageCount(4);
			this.setFps(4);
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
		
		this.getScene().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("DOWN: " + e.getKeyCode());
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("UP: " + e.getKeyCode());
			}
		});
	}
	
	protected void accelerate() {
		if (!this.isAnimated()) {
			this.startAnimation();
		}
	}

	private void moveRight() {
		this.setX(this.getX() + 6);
		this.setX(this.getX() % 500);
	}
}
