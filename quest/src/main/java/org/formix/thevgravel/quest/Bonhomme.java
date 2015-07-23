package org.formix.thevgravel.quest;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.formix.thevgravel.quest.engine.Animation;

public class Bonhomme extends Animation {

	private Rectangle runRightRect;
	private Rectangle runLeftRect;

	private BonhommeState state;
	
	private int lastKeyCode;

	public Bonhomme() {
		try {
			Image image = ImageIO.read(this.getClass().getResourceAsStream("bonhomme-sprite.png"));
			this.setImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setSpriteCount(4);
		this.state = BonhommeState.STILL;

		this.runRightRect = new Rectangle(0, 0, 128, 32);
		this.runLeftRect = new Rectangle(0, 32, 128, 32);

		this.setAnimationPosition(this.runRightRect);
		this.state = BonhommeState.STILL;
		this.setSpriteIndex(3);
		
		this.lastKeyCode = -1;
	}

	public void initialize() {
		this.getScene().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				changeState(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				resetState(e);
			}
		});
	}

	protected void changeState(KeyEvent e) {
		if (this.lastKeyCode == e.getKeyCode()) {
			return;
		}
		switch (e.getKeyCode()) {

		case 37: // left
			state = BonhommeState.RUN_LEFT;
			setAnimationPosition(runLeftRect);
			startAnimation();
			break;

		case 38: // up
			break;

		case 39: // right
			state = BonhommeState.RUN_RIGHT;
			setAnimationPosition(runRightRect);
			startAnimation();
			break;

		case 40: // down
			break;

		default:
			break;

		}
	}

	protected void resetState(KeyEvent e) {
		this.lastKeyCode = -1;
		
		switch (e.getKeyCode()) {

		case 37: // left
			if (state == BonhommeState.RUN_LEFT) {
				state = BonhommeState.STILL;
				stopAnimation();
				setSpriteIndex(3);
			}
			break;

		case 38: // up
			break;

		case 39: // right
			if (state == BonhommeState.RUN_RIGHT) {
				state = BonhommeState.STILL;
				stopAnimation();
				setSpriteIndex(3);
			}
			break;

		case 40: // down
			break;

		default:
			break;

		}
	}

	public synchronized boolean update() {
		boolean updated = super.update();;

		switch (this.state) {
		case RUN_RIGHT:
			this.moveX(6);
			updated = true;
			break;

		case RUN_LEFT:
			this.moveX(-6);
			updated = true;
			break;

		default:
			break;
		}
		
		return updated;
	}

	private void moveX(int dx) {
		int x = this.getX() + dx;
		this.setX(x);
	}

}
