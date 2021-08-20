package com.booksaw.betterEngine.storage.loadedElement;

/**
 * Used to convert an element between a string and an object
 * 
 * @author James
 *
 * @param <V> The type of the element
 */
public interface LoadedElement<V> {

	public V loadElement(String data);

	public String getString(V value);

}
