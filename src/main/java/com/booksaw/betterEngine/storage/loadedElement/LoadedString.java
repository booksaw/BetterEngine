package com.booksaw.betterEngine.storage.loadedElement;

/**
 * Default loadedElement type that can be used within programs. used when
 * loading / storing a String
 * 
 * @author James
 *
 */
public class LoadedString implements LoadedElement<String> {

	@Override
	public String loadElement(String data) {
		return data;
	}

	@Override
	public String getString(String value) {
		return value;
	}

}
