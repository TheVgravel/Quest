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

public class MyPanel extends JPanel {
	
	private static final long serialVersionUID = 4463844263560893625L;
	
	private Scene scene;
	private Image imageToRender;
	
	private Bonhomme bonhomme;

	public MyPanel() {
		this.imageToRender = null;
		
		this.scene = new Scene(640, 200) {
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
		
		Bloc bloc1 = new Bloc();
		bloc1.setZoom(200);
		bloc1.setX(200);
		bloc1.setY(60);
		this.scene.addItem(bloc1);
		
		
		Bloc bloc2 = new Bloc();
		bloc2.setZoom(400);
		bloc2.setX(300);
		bloc2.setY(40);
		bloc2.setZ(125);
		this.scene.addItem(bloc2);
		
		this.addKeyListener(l);
		this.removeKeyListener(l);
		this.getKeyListeners()
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!scene.isAnimated()) {
					scene.startAnimation();
				} else {
					scene.fireEvent("accelerate", this, null);
					if (bonhomme.getFPS() == 4) {
						scene.stopAnimation();
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
