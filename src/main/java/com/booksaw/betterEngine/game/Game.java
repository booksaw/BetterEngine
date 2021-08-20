package com.booksaw.betterEngine.game;

import com.booksaw.betterEngine.game.settings.GameSettings;
import com.booksaw.betterEngine.project.ProjectSettings;

public class Game {

	private final GameSettings settings;
	private final ProjectSettings projectSettings;

	public Game(ProjectSettings projectSettings) {
		settings = new GameSettings();
		this.projectSettings = projectSettings;
	}

	public GameSettings getSettings() {
		return settings;
	}

	public ProjectSettings getProjectSettings() {
		return projectSettings;
	}

}
