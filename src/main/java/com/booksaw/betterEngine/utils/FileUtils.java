package com.booksaw.betterEngine.utils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.booksaw.betterEngine.logging.EngineLogger;

/**
 * Utilities used relating to files
 * 
 * @author James
 *
 */
public class FileUtils {

	/**
	 * Used to get a resource from within the jar file
	 * 
	 * @param resourceName The name of the resource to load
	 * @return The resource as an input stream
	 */
	public static InputStream getResourceAsStream(String resourceName) {
		return FileUtils.class.getClassLoader().getResourceAsStream(resourceName);
	}

	/**
	 * Used to export the contents of the input stream to the output file
	 * 
	 * @param stream     The input stream
	 * @param outputFile The output file to copy the results to
	 */
	public static void exportResource(InputStream stream, File outputFile) {
		try {
			Files.copy(stream, Paths.get(outputFile.toURI()), StandardCopyOption.REPLACE_EXISTING);

		} catch (Exception ex) {

			EngineLogger.getLogger().severe("Could not export resource to " + outputFile.getPath());
			ex.printStackTrace();

		}
	}

	/**
	 * Used to export the contents of the input resource to the output file
	 * 
	 * @param resourceName The name of the input resource
	 * @param outputFile   The output file to copy the results to
	 */
	public static void exportResource(String resourceName, File outputFile) {
		exportResource(getResourceAsStream(resourceName), outputFile);
	}

	private FileUtils() {
		// stopping an instance being created of this class
	}

}
