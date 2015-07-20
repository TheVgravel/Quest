package org.formix.thevgravel.quest.engine;

import java.awt.Graphics;

public interface SceneItem {

	/**
	 * Gets the Scene on which this object will be rendered.
	 * 
	 * @return the Scene on which this object will be rendered.
	 */
	Scene getScene();

	/**
	 * Sets the Scene for this item.
	 * 
	 * @param scene
	 *            the parent scene of the current item.
	 */
	void setScene(Scene scene);

	/**
	 * Gets the X position component of this SceneItem.
	 * 
	 * @return the X position component of this SceneItem.
	 */
	int getX();

	/**
	 * Sets the X position component of this SceneItem.
	 * 
	 * @param x
	 *            the X position component of this SceneItem.
	 */
	void setX(int x);

	/**
	 * Gets the Y position component of this SceneItem.
	 * 
	 * @return the Y position component of this SceneItem.
	 */
	int getY();

	/**
	 * Sets the Y position component of this SceneItem.
	 * 
	 * @param y
	 *            the Y position component of this SceneItem.
	 */
	void setY(int y);

	/**
	 * Gets the Z position component of this SceneItem.
	 * 
	 * @return the Z position component of this SceneItem.
	 */
	double getZ();

	/**
	 * Sets the Z position of this SceneItem.
	 * 
	 * @param z
	 *            the Z position component of this SceneItem.
	 */
	void setZ(double z);
	
	/**
	 * Update the state of the current Item. Called just before being drawn.
	 */
	void update();

	/**
	 * Draw this SceneItem to the given graphics object.
	 * 
	 * @param g
	 */
	void draw(Graphics g);

	/**
	 * This method is called when its time to register the current SceneItem
	 * events.
	 * 
	 * @param scene
	 *            The Scene owning this SceneItem.
	 */
	void registerEvents();
}
