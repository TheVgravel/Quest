package org.formix.thevgravel.quest.engine;


public abstract class AbstractSceneItem implements SceneItem {

	private Scene scene;
	private int x;
	private int y;
	private double z;
	
	public AbstractSceneItem() {
		this.scene = null;
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public Scene getScene() {
		return this.scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getZ() {
		return this.z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public void notify(Event e) {
		// do nothing.
	}
	
	public void fireEvent(SceneItem source, String eventName, Object data) {
		if (this.getScene() == null) {
			return;
		}
		this.getScene().fireEvent(source, eventName, data);
	}
	
	public void registerEvent(String eventName) {
		if (this.getScene() == null) {
			return;
		}
		this.getScene().registerEvent(eventName, this);
	}
}
