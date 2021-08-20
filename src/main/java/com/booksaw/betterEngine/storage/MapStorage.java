package com.booksaw.betterEngine.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.booksaw.betterEngine.logging.EngineLogger;
import com.booksaw.betterEngine.storage.loadedElement.LoadedElement;

/**
 * A class to store a map of a specified type, includes loading
 * 
 * @author James
 *
 * @param <V> The type for the value of the map
 */
public class MapStorage<V> {

	private Map<String, V> storedValues = new HashMap<>();

	/**
	 * Stores the methods to load and store the type V to / from a string
	 */
	private final LoadedElement<V> loadedElement;

	public MapStorage(LoadedElement<V> loadedElement) {
		this.loadedElement = loadedElement;
	}

	/**
	 * Used to load the information from the specified file into the map
	 * 
	 * @param file The stored map
	 */
	public void loadMap(File file) {

		EngineLogger.getLogger().info("MapStorage",
				"Loading file " + file.getName() + " into MapStorage Object (" + this + ")");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {

				if (line.length() == 0 || !line.contains(",")) {
					continue;
				}

				// limiting the split so the value can contain a ,
				String[] split = line.split(",", 2);
				storedValues.put(split[0], loadedElement.loadElement(split[1]));
			}

		} catch (Exception e) {
			EngineLogger.getLogger().severe("MapStorage", "Object failed to load");
			e.printStackTrace();
		}

	}

	/**
	 * Used to store the map to file
	 * 
	 * @param file The file to save it to
	 */
	public void saveMap(File file) {

		EngineLogger.getLogger().info("MapStorage",
				"Saving Map storage object (" + this + ") into file " + file.getName());

		try (PrintWriter pw = new PrintWriter(file)) {

			// using getStoredValues to get a clone to avoid concurrent
			for (Entry<String, V> temp : getStoredValues().entrySet()) {
				pw.println(temp.getKey() + "," + loadedElement.getString(temp.getValue()));
			}

		} catch (Exception e) {
			EngineLogger.getLogger().severe("MapStorage", "Object failed to save");
			e.printStackTrace();
		}

	}

	/**
	 * Get a clone of the map storing all information
	 * 
	 * @return The created map
	 */
	public Map<String, V> getStoredValues() {
		return new HashMap<>(storedValues);
	}

	/**
	 * Used to add a new value to the map
	 * 
	 * @param key   The key for the value (cannot contain the symbol `,`)
	 * @param value The value to store
	 */
	public void addValue(String key, V value) {
		if (key.contains(",")) {
			throw new IllegalArgumentException("Key cannot contain a `,`");
		}

		storedValues.put(key, value);

	}

	/**
	 * Used to get a value from a specified key
	 * 
	 * @param key The specified key
	 * @return The stored value
	 */
	public V getValue(String key) {
		return storedValues.get(key);
	}

	/**
	 * @return The interface which is used for string conversion
	 */
	public LoadedElement<V> getLoadedElement() {
		return loadedElement;
	}

}
