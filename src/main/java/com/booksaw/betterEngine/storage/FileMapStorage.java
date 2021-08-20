package com.booksaw.betterEngine.storage;

import java.io.File;
import java.io.IOException;

import com.booksaw.betterEngine.logging.EngineLogger;
import com.booksaw.betterEngine.storage.loadedElement.LoadedElement;
import com.booksaw.betterEngine.utils.FileUtils;

/**
 * Used to manage map storage which has a file associated with it
 * 
 * @author James
 *
 */
public class FileMapStorage<V> extends MapStorage<V> {

	/**
	 * The file saving the settings
	 */
	private final File savedFile;

	/**
	 * Used to create a new settings class from the specified fileName
	 * 
	 * @param fileName      The name of the file
	 * @param resourceName  The name of the resource within the jar which has the
	 *                      default values
	 * @param loadedElement The element to load that type of object
	 */
	public FileMapStorage(String fileName, String resourceName, LoadedElement<V> loadedElement) {
		this(new File(fileName), resourceName, loadedElement);
	}

	/**
	 * 
	 * @param savedFile     The file where the values are saved
	 * @param resourceName  The name of the resource within the jar which has the
	 *                      default values
	 * @param loadedElement The element to load that type of object
	 */
	public FileMapStorage(File savedFile, String resourceName, LoadedElement<V> loadedElement) {
		super(loadedElement);

		this.savedFile = savedFile;
		if (!savedFile.exists()) {
			try {
				if (!savedFile.createNewFile()) {
					// if creating the new file failed
					throw new IllegalArgumentException("savedFile.createNewFile() does not work as expected");
				}
				FileUtils.exportResource(resourceName, savedFile);
			} catch (IOException e) {
				EngineLogger.getLogger().severe("FileMapStorage", "Could not create settings.txt");
				e.printStackTrace();
			}

		}
	}

	/**
	 * Used to load settings
	 */
	public void load() {
		loadMap(savedFile);
	}

	/**
	 * Used to save settings to file
	 */
	public void save() {
		saveMap(savedFile);
	}

	/**
	 * Get the file settings are saved to
	 */
	public File getSavedFile() {
		return savedFile;
	}

}
