package org.formix.thevgravel.quest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	
	private Bonhomme bonhomme;
	private int imageX = 50;
	private int imageY = 50;
	private int imageW = 20;
	private int imageH = 20;

	public MyPanel() {
		this.bonhomme = new Bonhomme();
		setBackground(Color.black);
		setBorder(BorderFactory.createLineBorder(Color.black));
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				moveImage(e.getX(), e.getY());
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				moveImage(e.getX(), e.getY());
			}
		});

	}

	private void moveImage(int x, int y) {

		// Current square state, stored as final variables
		// to avoid repeat invocations of the same methods.
		final int CURR_X = bonhomme.getX();
		final int CURR_Y = bonhomme.getY();
		final int CURR_W = bonhomme.getWidth();
		final int CURR_H = bonhomme.getHeight();
		final int OFFSET = 1;

		if ((CURR_X != x) || (CURR_Y != y)) {

			// The square is moving, repaint background
			// over the old square location.
			repaint(CURR_X, CURR_Y, CURR_W + OFFSET, CURR_H + OFFSET);

			// Update coordinates.
			bonhomme.setX(x);
			bonhomme.setY(y);

			// Repaint the square at the new location.
			repaint(bonhomme.getX(), bonhomme.getY(), bonhomme.getWidth() + OFFSET, bonhomme.getHeight() + OFFSET);
		}
		int OFFSET1 = 1;
		if ((imageX != x) || (imageY != y)) {
			repaint(imageX, imageY, imageW + OFFSET1, imageH + OFFSET1);
			imageX = x;
			imageY = y;
			repaint(imageX, imageY, imageW + OFFSET1, imageH + OFFSET1);
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(800, 200);
	}

	public void paintComponent(Graphics g) {
		// Let UI Delegate paint first, which
		// includes background filling since
		// this component is opaque.
		super.paintComponent(g);

		// Draw Text
		g.drawString("This is my custom Panel!", 10, 20);
		bonhomme.draw(g, 0);
	}
}

class RedSquare {

	private int xPos = 50;
	private int yPos = 50;
	private int width = 20;
	private int height = 20;

	public void setX(int xPos) {
		this.xPos = xPos;
	}

	public int getX() {
		return xPos;
	}

	public void setY(int yPos) {
		this.yPos = yPos;
	}

	public int getY() {
		return yPos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void paintSquare(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(xPos, yPos, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(xPos, yPos, width, height);
	}
}
