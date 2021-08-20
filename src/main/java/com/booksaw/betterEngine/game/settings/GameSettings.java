package com.booksaw.betterEngine.game.settings;

import com.booksaw.betterEngine.storage.FileMapStorage;
import com.booksaw.betterEngine.storage.loadedElement.LoadedString;

public class GameSettings extends FileMapStorage<String> {

	public GameSettings() {
		super("settings.txt", "settings.txt", new LoadedString());
		load();
	}

	private int getInt(String key, int defaultValue) {

		try {
			return Integer.parseInt(getValue(key));
		} catch (NumberFormatException e) {
			return defaultValue;
		}

	}

	public int getWindowWidth() {
		return getInt("window.width", 1024);
	}

	public int getWindowHeight() {
		return getInt("window.height", 600);
	}

}
