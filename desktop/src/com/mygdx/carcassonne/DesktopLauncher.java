package com.mygdx.carcassonne;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.carcassonne.Carcassonne;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Carcassonne");
		config.setWindowedMode(GuiParameters.WIDTH, GuiParameters.HEIGHT);
		new Lwjgl3Application(new Carcassonne(), config); // CHECK : 05.04.2023 jak to działa? dlaczego ten obiekt nie jest do niczego przypisany?
	}
}
