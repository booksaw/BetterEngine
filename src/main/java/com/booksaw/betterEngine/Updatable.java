package com.booksaw.betterEngine;

/**
 * Used for objects which can be updated
 * 
 * @author James
 *
 */
public interface Updatable {

	/**
	 * Called whenever the object is being updated
	 * 
	 * @param delta The number of updates that need to be processed (this will be 1
	 *              unless the game is lagging)
	 */
	public void update(int delta);

}
