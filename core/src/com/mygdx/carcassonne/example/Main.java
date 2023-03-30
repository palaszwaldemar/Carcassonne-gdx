package com.mygdx.carcassonne.example;

public class Main {

    public static void main(String[] args) {
        MyGame myGame = new MyGame();
        Aplication aplication = new Aplication(myGame);
        aplication.startGame();
    }

}
