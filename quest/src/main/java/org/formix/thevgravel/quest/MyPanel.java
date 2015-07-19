package org.formix.thevgravel.quest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MyPanel extends JPanel {
	
	private static final long serialVersionUID = 4463844263560893625L;
	
	private Bonhomme bonhomme;

	public MyPanel() {
		this.bonhomme = new Bonhomme(this);
		this.bonhomme.setX(50);
		this.bonhomme.setY(25);
		setBackground(Color.darkGray);
		setBorder(BorderFactory.createLineBorder(Color.black));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						if (!bonhomme.isRunning()) {
							setBackground(Color.red);
							bonhomme.startRunning(4);
						} else {
							setBackground(Color.darkGray);
							bonhomme.stopRunning();
						}
					}
				});
			}
		});
	}


	public Dimension getPreferredSize() {
		return new Dimension(600, 200);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		bonhomme.draw(g);
	}

}
