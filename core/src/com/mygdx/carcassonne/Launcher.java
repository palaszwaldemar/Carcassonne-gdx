package com.mygdx.carcassonne;

public class Launcher {
    public static void main(String[] args) {
        Display display = new Display("Carcassonne");
        Gameplay gameplay = new Gameplay();
        display.addListener(gameplay);

        GameEngine gameEngine = new GameEngine(display, gameplay);
        gameEngine.start();
    }
}

// CHECK : 20.02.2023 nie wiem jak się zabrać za testy


