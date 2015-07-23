package org.formix.thevgravel.quest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.formix.thevgravel.quest.engine.Background;
import org.formix.thevgravel.quest.engine.FpsDisplay;
import org.formix.thevgravel.quest.engine.ScenePanel;

public class MyPanel extends ScenePanel {
	
	private static final long serialVersionUID = 4463844263560893625L;
	
	private Image imageToRender;
	
	private Bonhomme bonhomme;

	public MyPanel() {
		this.imageToRender = null;
		
		this.getScene().setSize(new Dimension(640, 200));
		
		this.getScene().addItem(new Background(Color.darkGray));

		this.getScene().addItem(new FpsDisplay());
		
		this.bonhomme = new Bonhomme();
		this.bonhomme.setZoomFactor(400);
		this.bonhomme.setX(50);
		this.bonhomme.setY(35);
		this.bonhomme.setZ(100);
		this.bonhomme.setFps(10);
		this.getScene().addItem(bonhomme);
		
		Bloc bloc1 = new Bloc();
		bloc1.setZoomFactor(400);
		bloc1.setX(200);
		bloc1.setY(60);
		bloc1.setZ(50);
		this.getScene().addItem(bloc1);
		
		
		Bloc bloc2 = new Bloc();
		bloc2.setZoomFactor(400);
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
					getScene().stopAnimation();
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
