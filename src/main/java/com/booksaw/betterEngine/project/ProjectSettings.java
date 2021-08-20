package com.booksaw.betterEngine.project;

import com.booksaw.betterEngine.storage.FileMapStorage;
import com.booksaw.betterEngine.storage.loadedElement.LoadedString;

/**
 * Used to store the settings for the project
 * 
 * @author James
 *
 */
public class ProjectSettings extends FileMapStorage<String> {

	public ProjectSettings() {
		super("projectSettings.txt", "projectSettings.txt", new LoadedString());
		load();
	}

	public String getName() {
		return getValue("name");
	}

}
