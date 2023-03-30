package com.mygdx.carcassonne.example;

public class MyGame implements MyGameInterface{

/*
    public void start() {

    }

    public void renderMyGame() {

    }

    public void clean() {

    }*/


    @Override
    public void create() {
        System.out.println("Przygotowuje scenerie gry i obiekty");
    }

    @Override
    public void render() {
        System.out.println("renderuję obiekty");
    }

    @Override
    public void dispose() {
        System.out.println("czyszczę na zakończenie");
    }




}
