package com.booksaw.betterEngine.logging;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Logger class for BetterEngine
 * 
 * @author James
 *
 */
public class EngineLogger {

	private static EngineLogger logger;

	public static EngineLogger getLogger() {
		if (logger == null) {
			logger = new EngineLogger();
			logger.addConsoleOutput();

			try {
				logger.addFileOutput();
			} catch (SecurityException | IOException e) {
				logger.severe("EngineLogger", "Something went wrong with setting up console log output");
				e.printStackTrace();
			}

		}

		return logger;
	}

	/**
	 * The java logger being used
	 */
	private final Logger LOGGER;

	/**
	 * The default format to use for handlers
	 */
	private final Formatter defaultFormatter;

	/**
	 * Used to create a new logger, all message levels will be logged
	 */
	public EngineLogger() {
		this(Level.ALL, new InlineLoggerFormatter());
	}

	/**
	 * Used to create a new logger where only some mesasge levels will be used
	 * 
	 * @param minLogLevel - The minimum level a log must be for it to be logged to
	 *                    console
	 */
	public EngineLogger(Level minLogLevel, Formatter defaultFormatter) {

		LOGGER = Logger.getLogger(EngineLogger.class.getName());
		LOGGER.setUseParentHandlers(false);

		LOGGER.setLevel(minLogLevel);
		this.defaultFormatter = defaultFormatter;
	}

	/**
	 * Add a handler to the logger so the information is logged to it
	 * 
	 * @param handler
	 */
	public void addHandler(Handler handler) {
		LOGGER.addHandler(handler);
	}

	/**
	 * Add the console to the locations the logger will log to
	 */
	public void addConsoleOutput() {
		ConsoleHandler handler = new ConsoleHandler();
		setDefaultFormatter(handler);
		addHandler(handler);
	}

	/**
	 * Add a file to the locations the logger will log to, default log file is
	 * log.txt
	 * 
	 * @throws SecurityException if a security manager exists and if the caller does
	 *                           not have LoggingPermission("control")).
	 * @throws IOException       If there are IO problems opening the file
	 */
	public void addFileOutput() throws SecurityException, IOException {
		addFileOutput("log.txt");
	}

	/**
	 * Add a file to the locations the logger will log to
	 * 
	 * @param fileName The name of the file to log to
	 * @throws SecurityException if a security manager exists and if the caller does
	 *                           not have LoggingPermission("control")).
	 * @throws IOException       If there are IO problems opening the file
	 */
	public void addFileOutput(String fileName) throws SecurityException, IOException {
		FileHandler handler = new FileHandler(fileName);

		setDefaultFormatter(handler);

		addHandler(handler);
	}

	public void setDefaultFormatter(Handler handler) {
		handler.setFormatter(defaultFormatter);
	}

	/**
	 * Used to log a message with a location
	 * 
	 * @param level    The severity of the message
	 * @param location The location of the call
	 * @param message  The message
	 */
	public void log(Level level, String location, String message) {
		LOGGER.logp(level, location, "", message);
	}

	/**
	 * Log a message
	 * 
	 * @param level   The severity of the message
	 * @param message The message
	 */
	public void log(Level level, String message) {
		log(level, "BetterEngine", message);
	}

	/**
	 * Log information
	 * 
	 * @param message the message
	 */
	public void info(String message) {
		log(Level.INFO, message);
	}

	/**
	 * Logs information with a location
	 * 
	 * @param location The location of the call
	 * @param message  the message
	 */
	public void info(String location, String message) {
		log(Level.INFO, location, message);
	}

	/**
	 * Log a warning
	 * 
	 * @param message the message
	 */
	public void warning(String message) {
		log(Level.WARNING, message);
	}

	/**
	 * Logs a warning message with a location
	 * 
	 * @param location The location of the call
	 * @param message  the message
	 */

	public void warning(String location, String message) {
		log(Level.WARNING, location, message);
	}

	/**
	 * log a severe message
	 * 
	 * @param message the message
	 */
	public void severe(String message) {
		log(Level.SEVERE, message);
	}

	/**
	 * Logs a severe message with a location
	 * 
	 * @param location The location of the call
	 * @param message  the message
	 */
	public void severe(String location, String message) {
		log(Level.SEVERE, location, message);
	}

}
