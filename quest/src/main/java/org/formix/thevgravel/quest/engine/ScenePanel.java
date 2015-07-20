package org.formix.thevgravel.quest.engine;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.formix.thevgravel.quest.engine.events.KeyboardData;
import org.formix.thevgravel.quest.engine.events.KeyboardDataType;
import org.formix.thevgravel.quest.engine.events.SceneEvent;

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

		this.setFocusable(true);
	}

	private void convertKeyListener() {
		this.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				KeyboardData data = new KeyboardData(e, KeyboardDataType.PRESSED);
				SceneEvent<KeyboardData> event = new SceneEvent<KeyboardData>(scene, "keyboard", data);
				scene.fireEvent(event);
			}

			public void keyReleased(KeyEvent e) {
				KeyboardData data = new KeyboardData(e, KeyboardDataType.UP);
				SceneEvent<KeyboardData> event = new SceneEvent<KeyboardData>(scene, "keyboard", data);
				scene.fireEvent(event);
			}

			public void keyPressed(KeyEvent e) {
				KeyboardData data = new KeyboardData(e, KeyboardDataType.DOWN);
				SceneEvent<KeyboardData> event = new SceneEvent<KeyboardData>(scene, "keyboard", data);
				scene.fireEvent(event);
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
}
