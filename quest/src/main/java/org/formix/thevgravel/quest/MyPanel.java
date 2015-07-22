package org.formix.thevgravel.quest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import org.formix.thevgravel.quest.engine.Actor;
import org.formix.thevgravel.quest.engine.Background;
import org.formix.thevgravel.quest.engine.FpsDisplay;
import org.formix.thevgravel.quest.engine.Scene;
import org.formix.thevgravel.quest.engine.ScenePanel;

public class MyPanel extends ScenePanel {
	
	private static final long serialVersionUID = 4463844263560893625L;
	
	private Image imageToRender;
	
	private Bonhomme bonhomme;

	public MyPanel() {
		this.imageToRender = null;
		
		this.getScene().addItem(new Background(Color.darkGray));

		this.getScene().addItem(new FpsDisplay());
		
		this.bonhomme = new Bonhomme();
		this.bonhomme.setX(50);
		this.bonhomme.setY(25);
		this.getScene().addItem(bonhomme);
		
		Bloc bloc1 = new Bloc();
		bloc1.setZoom(200);
		bloc1.setX(200);
		bloc1.setY(60);
		this.getScene().addItem(bloc1);
		
		
		Bloc bloc2 = new Bloc();
		bloc2.setZoom(400);
		bloc2.setX(300);
		bloc2.setY(40);
		bloc2.setZ(125);
		this.getScene().addItem(bloc2);
		
		
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!getScene().isAnimated()) {
					getScene().startAnimation();
				} else {
					getScene().fireEvent(null, "accelerate", ' ');
					if (bonhomme.getFPS() == 4) {
						getScene().stopAnimation();
					}
				} 
			}
		});
		
	}

	protected void updateJPanelDisplay(Image renderedImage) {
		this.imageToRender = renderedImage;
		this.repaint();
	}


	public Dimension getPreferredSize() {
		return this.getScene().getSize();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image = this.imageToRender; 
		if (image == null) {
			image = this.getScene().renderFrame();
		}
		g.drawImage(image, 0, 0, null);
	}

}
