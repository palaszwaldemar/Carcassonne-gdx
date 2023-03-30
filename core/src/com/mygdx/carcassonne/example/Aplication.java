package com.mygdx.carcassonne.example;

public class Aplication {

    private boolean running = true;
    private MyGameInterface game;
    private int maxRenderTimes = 100;
    private int renderCounter = 0;

    public Aplication(MyGameInterface game) {
        this.game = game;
    }

    public void startGame() {
        game.create();
        renderHisGame();
    }

    //miejsce dla tworcy gry na zrobienie rzeczy na start
    private void setupHisGame() {
        game.create();
    }

    //miejsce na odswiezanie gry
    private void renderHisGame() {
        while (running){
            //odswiezyc twoja gre
            game.render();
            renderCounter++;
            if (renderCounter == maxRenderTimes) { //symulacja ze ktos kliknie X okna po wyrenderowaniu 100 ramek
                onCloseWindowPressed();
            }
        }
        closeHisGame();
    }

    public void onCloseWindowPressed() {
        running = false;
    }

    //miejsce na zakonczenie
    private void closeHisGame() {
        game.dispose();
    }


}
