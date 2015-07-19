package org.formix.thevgravel.quest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import org.formix.thevgravel.quest.engine.Actor;
import org.formix.thevgravel.quest.engine.Background;
import org.formix.thevgravel.quest.engine.FpsDisplay;
import org.formix.thevgravel.quest.engine.Scene;

public class MyPanel extends JPanel {
	
	private static final long serialVersionUID = 4463844263560893625L;
	
	private Scene scene;
	private Image imageToRender;
	
	private Bonhomme bonhomme;

	public MyPanel() {
		this.imageToRender = null;
		
		this.scene = new Scene(640, 480) {
			@Override
			public void updateDisplay(Image renderedImage) {
				updateJPanelDisplay(renderedImage);
			}
		};
		
		this.scene.addItem(new Background(Color.darkGray));

		this.scene.addItem(new FpsDisplay());
		
		this.bonhomme = new Bonhomme();
		this.bonhomme.setX(50);
		this.bonhomme.setY(25);
		this.scene.addItem(bonhomme);
		
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!scene.isAnimated()) {
					scene.startAnimation();
				} else {
					scene.stopAnimation();
				} 
			}
		});
	}

	protected void updateJPanelDisplay(Image renderedImage) {
		this.imageToRender = renderedImage;
		this.repaint();
	}


	public Dimension getPreferredSize() {
		return this.scene.getSize();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image = this.imageToRender; 
		if (image == null) {
			image = this.scene.renderFrame();
		}
		g.drawImage(image, 0, 0, null);
	}

}
