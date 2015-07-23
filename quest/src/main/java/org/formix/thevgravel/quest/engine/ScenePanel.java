package org.formix.thevgravel.quest.engine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ScenePanel extends JPanel {

	private static final long serialVersionUID = -7483104538242003978L;

	private Image renderedImage;
	private Scene scene;

	public ScenePanel() {
		this.renderedImage = null;

		this.scene = new Scene() {
			@Override
			public void updateDisplay(Image renderedImage) {
				updatePanelDisplay(renderedImage);
			}
		};

		this.convertKeyListener();
		this.convertMouseListener();

		this.setFocusable(true);
	}
	
	private void convertMouseListener() {
		this.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				for (MouseListener listener : scene.getMouseListeners()) {
					listener.mouseReleased(e);
				}
			}
			
			public void mousePressed(MouseEvent e) {
				for (MouseListener listener : scene.getMouseListeners()) {
					listener.mousePressed(e);
				}
			}
			
			public void mouseExited(MouseEvent e) {
				for (MouseListener listener : scene.getMouseListeners()) {
					listener.mouseExited(e);
				}
			}
			
			public void mouseEntered(MouseEvent e) {
				for (MouseListener listener : scene.getMouseListeners()) {
					listener.mouseEntered(e);
				}
			}
			
			public void mouseClicked(MouseEvent e) {
				for (MouseListener listener : scene.getMouseListeners()) {
					listener.mouseClicked(e);
				}
			}
		});
	}

	protected Scene getScene() {
		return this.scene;
	}

	private void convertKeyListener() {
		this.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				for (KeyListener listener : scene.getKeyListeners()) {
					listener.keyTyped(e);
				}
			}

			public void keyReleased(KeyEvent e) {
				for (KeyListener listener : scene.getKeyListeners()) {
					listener.keyReleased(e);
				}
			}

			public void keyPressed(KeyEvent e) {
				for (KeyListener listener : scene.getKeyListeners()) {
					listener.keyPressed(e);
				}
			}
		});
	}

	private void updatePanelDisplay(Image renderedImage) {
		this.renderedImage = renderedImage;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				repaint();
			}
		});
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image = this.renderedImage; 
		if (image == null) {
			image = this.scene.renderFrame();
		}
		g.drawImage(image, 0, 0, null);
	}
	
}
