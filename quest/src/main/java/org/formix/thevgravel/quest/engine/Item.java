package org.formix.thevgravel.quest.engine;

import java.awt.Graphics;

public interface Item {

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
	 * Gets the X position component of this Item.
	 * 
	 * @return the X position component of this Item.
	 */
	int getX();

	/**
	 * Sets the X position component of this Item.
	 * 
	 * @param x
	 *            the X position component of this Item.
	 */
	void setX(int x);

	/**
	 * Gets the Y position component of this Item.
	 * 
	 * @return the Y position component of this Item.
	 */
	int getY();

	/**
	 * Sets the Y position component of this Item.
	 * 
	 * @param y
	 *            the Y position component of this Item.
	 */
	void setY(int y);

	/**
	 * Gets the Z position component of this Item.
	 * 
	 * @return the Z position component of this Item.
	 */
	int getZ();

	/**
	 * Sets the Z position of this Item.
	 * 
	 * @param z
	 *            the Z position component of this Item.
	 */
	void setZ(int z);

	/**
	 * Update the state of the current Item. Called just before being drawn.
	 * This method implementation must be synchronized.
	 * 
	 * @return true if the item was updated, false otherwise.
	 */
	boolean update();

	/**
	 * Draw this Item to the given graphics object. This method implementation
	 * must be synchronized.
	 * 
	 * @param g
	 */
	void draw(Graphics g);

	/**
	 * This method is called when its time to initialize the current Item
	 * events.
	 * 
	 * @param scene
	 *            The Scene owning this Item.
	 */
	void initialize();

}
