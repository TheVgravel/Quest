package org.formix.thevgravel.quest.engine;

public abstract class AbstractItem implements Item {

	private Scene scene;
	private int x;
	private int y;
	private int z;
	
	public AbstractItem() {
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

	public int getZ() {
		return this.z;
	}

	public void setZ(int z) {
		this.z = z;
	}

}
