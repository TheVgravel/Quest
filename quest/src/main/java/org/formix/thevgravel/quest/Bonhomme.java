package org.formix.thevgravel.quest;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Bonhomme {

	private static final int SPRITE_WIDTH = 32;
	private static final int SPRITE_HEIGHT = 32;

	private JPanel container;
	private Image rightRunSprite;
	private int x;
	private int y;
	private int spriteIndex;

	private boolean running;

	public Bonhomme(JPanel container) {
		this.running = false;
		this.container = container;
		this.x = 0;
		this.y = 0;
		this.spriteIndex = 0;
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

	public int getSpriteIndex() {
		return this.spriteIndex;
	}

	public void setSpriteIndex(int spriteIndex) {
		this.spriteIndex = spriteIndex;
	}

	public int getWidth() {
		return SPRITE_WIDTH;
	}

	public int getHeight() {
		return SPRITE_HEIGHT;
	}

	public JPanel getContainer() {
		return container;
	}

	public boolean isRunning() {
		return this.running;
	}

	public void draw(Graphics g) {
		int spriteX = this.spriteIndex * SPRITE_WIDTH;
		g.setColor(this.container.getBackground());
		g.fillRect(this.x, this.y, this.x + SPRITE_WIDTH * 4, this.y + SPRITE_HEIGHT * 4);
		g.drawImage(this.rightRunSprite, this.x, this.y, this.x + SPRITE_WIDTH * 4, this.y + SPRITE_HEIGHT * 4, spriteX,
				0, spriteX + SPRITE_WIDTH - 1, SPRITE_HEIGHT - 1, null);
	}

	public void startRunning(final int fps) {
		this.running = true;
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (running) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							try {
								Thread.sleep(1 / fps * 1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							spriteIndex = (spriteIndex + 1) % 4;
							container.repaint(x, y, x + SPRITE_WIDTH * 4, y + SPRITE_HEIGHT * 4);
						}
					});
				}
			}
		});
		t.run();
	}

	public void stopRunning() {
		this.running = false;
	}
}
