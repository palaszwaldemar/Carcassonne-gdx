package com.mygdx.carcassonne;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Carcassonne");
		config.setWindowedMode(GuiParameters.WIDTH, GuiParameters.HEIGHT);
		new Lwjgl3Application(new Carcassonne(), config); // CHECK : 05.04.2023 jak to działa? dlaczego ten obiekt nie jest do niczego przypisany?
	}
}
