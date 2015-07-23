package org.formix.thevgravel.quest.engine;

import java.awt.Color;
import java.awt.Graphics;

public class Background extends AbstractItem {

	private Color color;

	public Background() {
		this(Color.black);
	}

	public Background(Color color) {
		this.color = color;
		this.setZ(-99999999);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean update() {
		return false;
	}

	public void draw(Graphics g) {
		Color penColor = g.getColor();
		g.setColor(this.color);
		g.fillRect(0, 0, this.getScene().getSize().width, this.getScene().getSize().height);
		g.setColor(penColor);
	}

	public void initialize() {
	}

}
