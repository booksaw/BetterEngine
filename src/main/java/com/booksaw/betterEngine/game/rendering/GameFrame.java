package com.booksaw.betterEngine.game.rendering;

import javax.swing.JFrame;

import com.booksaw.betterEngine.game.Game;
import com.booksaw.betterEngine.game.settings.GameSettings;
import com.booksaw.betterEngine.logging.EngineLogger;
import com.booksaw.betterEngine.project.ProjectSettings;

/**
 * Used to contain the JFrame which is displaying the game rendering
 * 
 * @author James
 *
 */
public class GameFrame extends JFrame {

	private static final long serialVersionUID = 3357034428536348253L;

	private final Game game;

	public GameFrame(Game game) {
		this.game = game;

	}

	public void loadGameSettings() {
		GameSettings settings = game.getSettings();

		ProjectSettings projectSettings = game.getProjectSettings();

		setTitle(projectSettings.getName());

		EngineLogger.getLogger().info("GameFrame", "Loading game frame (" + projectSettings.getName() + ")");

		setSize(settings.getWindowWidth(), settings.getWindowHeight());

	}

}
