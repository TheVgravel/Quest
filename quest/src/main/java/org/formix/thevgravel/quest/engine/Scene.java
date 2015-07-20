package org.formix.thevgravel.quest.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.formix.thevgravel.quest.engine.events.SceneEvent;
import org.formix.thevgravel.quest.engine.events.SceneEventListener;

public abstract class Scene {

	public static final int FRAME_PERIOD = 40; // 40 ms -> 25 FPS

	private static Comparator<SceneItem> sceneItemComparator = null;

	
	private Dimension size;
	private Image lastFrame;
	private SortedSet<SceneItem> items;
	private List<SceneItem> addedItems;
	private List<SceneItem> removedItems;
	private Map<String, List<SceneItem>> eventRegistrations;
	private Map<SceneItem, List<String>> registeredEvents;
	private boolean animated;
	private int frameCount;

	private Map<String, SceneEventListener<?>> sceneEventListeners;

	
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
		this.items = new TreeSet<SceneItem>(createComparator());
		this.addedItems = new LinkedList<SceneItem>();
		this.removedItems = new LinkedList<SceneItem>();
		this.animated = false;
		this.frameCount = 0;
		this.eventRegistrations = new HashMap<String, List<SceneItem>>();
		this.registeredEvents = new HashMap<SceneItem, List<String>>();
		this.sceneEventListeners = new HashMap<String, SceneEventListener<?>>();
	}

	private static Comparator<? super SceneItem> createComparator() {
		if (sceneItemComparator == null) {
			sceneItemComparator = new Comparator<SceneItem>() {
				public int compare(SceneItem o1, SceneItem o2) {
					return Double.compare(o1.getZ(), o2.getZ());
				}
			};
		}
		return sceneItemComparator;
	}

	/**
	 * Add a SceneItem to the current scene. When added, the
	 * SceneItem.setScene(this) and SceneItem.registerEvents methods are called
	 * for the item parameter.
	 * 
	 * @param item
	 *            The item to add.
	 */
	public void addItem(SceneItem item) {
		item.setScene(this);
		item.registerEvents();
		if (this.animated) {
			synchronized (this.addedItems) {
				this.addedItems.add(item);
			}
		} else {
			this.items.add(item);
		}
	}

	public void removeItem(SceneItem item) {
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

	private void unregisterEvents(SceneItem item) {
		if (!this.registeredEvents.containsKey(item)) {
			return;
		}
		List<String> eventNames = this.registeredEvents.get(item);
		for (String eventName : eventNames) {
			this.unregisterEvent(eventName, item);
		}
		this.registeredEvents.remove(item);
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

	public SortedSet<SceneItem> getItems() {
		return Collections.unmodifiableSortedSet(this.items);
	}

	public boolean isAnimated() {
		return this.animated;
	}

	public void registerEvent(String eventName, SceneItem listener) {
		if (!this.eventRegistrations.containsKey(eventName)) {
			this.eventRegistrations.put(eventName, new LinkedList<SceneItem>());
		}
		this.eventRegistrations.get(eventName).add(listener);

		if (!this.registeredEvents.containsKey(listener)) {
			this.registeredEvents.put(listener, new LinkedList<String>());
		}
		this.registeredEvents.get(listener).add(eventName);
	}

	public void unregisterEvent(String eventName, SceneItem listener) {
		if (!this.eventRegistrations.containsKey(eventName)) {
			return;
		}
		this.eventRegistrations.get(eventName).remove(listener);
	}

	public void fireEvent(SceneEvent<?> event) {
//		if (!this.eventRegistrations.containsKey(eventName)) {
//			return;
//		}
//		List<SceneItem> items = this.eventRegistrations.get(eventName);
//		SceneEvent event = new SceneEvent(eventName, source, data);
//		for (SceneItem item : items) {
//			item.notify(event);
//		}
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

	public Image renderFrame() {
		synchronized (this.removedItems) {
			// cleanup SceneItems to be removed.
			this.items.removeAll(this.removedItems);
			this.removedItems.clear();
		}

		synchronized (this.addedItems) {
			// Add newly created SceneItems.
			this.items.addAll(this.addedItems);
			this.addedItems.clear();
		}

		// Create a new image
		Image newFrame = new BufferedImage(this.size.width, this.size.height, BufferedImage.TYPE_INT_RGB);
		Graphics g = newFrame.getGraphics();

		// For each SceneItem...
		for (SceneItem sceneItem : this.items) {
			sceneItem.update(); // update its state.
			sceneItem.draw(g); // draw it on the new image.
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
