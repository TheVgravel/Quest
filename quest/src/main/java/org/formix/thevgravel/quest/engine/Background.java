package org.formix.thevgravel.quest.engine;

import java.awt.Color;
import java.awt.Graphics;

public class Background extends AbstractSceneItem {

	private Color color;

	public Background() {
		this(Color.black);
	}

	public Background(Color color) {
		this.color = color;
		this.setZ(Double.MIN_VALUE);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void update() {
	}

	public void draw(Graphics g) {
		Color penColor = g.getColor();
		g.setColor(this.color);
		g.drawRect(0, 0, this.getScene().getSize().width - 1, this.getScene().getSize().height - 1);
		g.setColor(penColor);
	}

	public void registerEvents() {
	}

}
