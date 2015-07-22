package org.formix.thevgravel.quest.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class Scene {

	public static final int FRAME_PERIOD = 40; // 40 ms -> 25 FPS

	private static Comparator<Item> itemComparator = null;

	private Dimension size;
	private Image lastFrame;
	private SortedSet<Item> items;
	private List<Item> addedItems;
	private List<Item> removedItems;
	private boolean animated;
	private int frameCount;
	
	private List<KeyListener> keyListeners;
	private List<MouseListener> mouseListeners;

	public Scene() {
		this(640, 480);
	}

	/**
	 * Creates a scene of the given size.
	 * 
	 * @param width
	 *            the width of the scene.
	 * 
	 * @param height
	 *            the height of the scene.
	 */
	public Scene(int width, int height) {
		this.size = new Dimension(width, height);
		this.lastFrame = null;
		this.items = new TreeSet<Item>(createComparator());
		this.addedItems = new LinkedList<Item>();
		this.removedItems = new LinkedList<Item>();
		this.animated = false;
		this.frameCount = 0;
		
		this.keyListeners = new LinkedList<KeyListener>();
		this.mouseListeners = new LinkedList<MouseListener>();
	}

	private static Comparator<? super Item> createComparator() {
		if (itemComparator == null) {
			itemComparator = new Comparator<Item>() {
				public int compare(Item o1, Item o2) {
					return Double.compare(o1.getZ(), o2.getZ());
				}
			};
		}
		return itemComparator;
	}
	
	
	public void addKeyListener(KeyListener listener) {
		this.keyListeners.add(listener);
	}
	
	public void removeKeyListener(KeyListener listener) {
		this.keyListeners.remove(listener);
	}
	
	public KeyListener[] getKeyListeners() {
		return this.keyListeners.toArray(new KeyListener[this.keyListeners.size()]);
	}
	
	
	public void addMouseListener(MouseListener listener) {
		this.mouseListeners.add(listener);
	}
	
	public void removeMouseListener(MouseListener listener) {
		this.mouseListeners.remove(listener);
	}
	
	public MouseListener[] getMouseListeners() {
		return this.mouseListeners.toArray(new MouseListener[this.mouseListeners.size()]);
	}


	/**
	 * Add a item to the current scene. When added, the
	 * item.setScene(this) and item.registerEvents methods are called
	 * for the item parameter.
	 * 
	 * @param item
	 *            The item to add.
	 */
	public void addItem(Item item) {
		item.setScene(this);
		item.initialize();
		if (this.animated) {
			synchronized (this.addedItems) {
				this.addedItems.add(item);
			}
		} else {
			this.items.add(item);
		}
	}

	public void removeItem(Item item) {
		item.setScene(null);
		this.unregisterEvents(item);
		if (this.animated) {
			synchronized (this.removedItems) {
				this.removedItems.add(item);
			}
		} else {
			this.items.remove(item);
		}
	}

	/**
	 * Returns the size of the current scene.
	 * 
	 * @return a Dimension object containing the size of the current Scene.
	 */
	public Dimension getSize() {
		return this.size.getSize();
	}

	/**
	 * Return the last generated image of the current scene.
	 * 
	 * @return the last generated image of the current scene.
	 */
	public Image getLastFrame() {
		return this.lastFrame;
	}

	public int getFrameCount() {
		return this.frameCount;
	}

	public SortedSet<Item> getItems() {
		return Collections.unmodifiableSortedSet(this.items);
	}

	public boolean isAnimated() {
		return this.animated;
	}

	private void unregisterEvents(Item item) {

	}

	public void startAnimation() {
		if (this.animated) {
			return;
		}
		this.frameCount = 0;
		this.animated = true;
		Thread t = new Thread(new Runnable() {
			public void run() {
				executeAnimationLoop();
			}
		});
		t.start();
	}

	private void executeAnimationLoop() {
		while (this.animated) {

			long start = System.currentTimeMillis();
			this.updateState();
			this.lastFrame = renderFrame();
			long end = System.currentTimeMillis();

			// insure that the rate of frame creation do now exceed 25 FPS.
			long duration = end - start;
			try {
				if (duration < FRAME_PERIOD) {
					// Wait some time to keep the max rate to 25 fps.
					Thread.sleep(FRAME_PERIOD - duration);
				} else {
					// This is a gentleman thread that let himself be
					// rescheduled for other tasks to be performed.
					// If reached, fps will be lower thant 25 FPS.
					Thread.sleep(0);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void updateState() {
		synchronized (this.removedItems) {
			// cleanup items to be removed.
			this.items.removeAll(this.removedItems);
			this.removedItems.clear();
		}

		synchronized (this.addedItems) {
			// Add newly created items.
			this.items.addAll(this.addedItems);
			this.addedItems.clear();
		}
		
		// For each item...
		for (Item item : this.items) {
			item.update(); // update its state.
		}
	}

	public Image renderFrame() {

		// Create a new image
		Image newFrame = new BufferedImage(this.size.width, this.size.height, BufferedImage.TYPE_INT_RGB);
		Graphics g = newFrame.getGraphics();

		// For each item...
		for (Item item : this.items) {
			item.draw(g); // draw it on the new image.
		}

		// update the display and internal states.
		this.updateDisplay(newFrame);
		this.frameCount++;

		return newFrame;
	}

	public void stopAnimation() {
		this.animated = false;
	}

	public abstract void updateDisplay(Image renderedImage);
}
