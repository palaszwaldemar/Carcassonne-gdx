package com.mygdx.carcassonne;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Gameplay implements MouseListener {
    //dependencies
    private final TileFactory tileFactory;
    private final SpawnValidator spawnValidator;

    //game elements
    private final List<Tile> tilesBoard = new ArrayList<>();
    private final Queue<Tile> tilesPile;
    private final TilePreview tilePreview;

    //ui
    private final EndButton endButton;


    public Gameplay() {
        tileFactory = new TileFactory();
        tilesPile = tileFactory.getListOfTile();
        tilePreview =  new TilePreview();
        spawnValidator = new SpawnValidator(tilesBoard,tilePreview);
        endButton = new EndButton();

        //biore z fabryki kafelek startowy
        Tile tile = tilesPile.poll();
        tile.setGridX(8);
        tile.setGridY(4);
        tilesBoard.add(tile);
        drawNextTile();
    }

    public void tick() {
    }

    //render
    public void render(Graphics g) {
        for (Tile tile : new ArrayList<>(tilesBoard)) {
            tile.render(g);
        }
        tilePreview.render(g);
        endButton.render(g);
    }

    //handling input
    @Override
    public void mousePressed(MouseEvent e) {
        int pixelX = e.getX();
        int pixelY = e.getY();
        boolean clicked = handleEndButtonClick(pixelX, pixelY);
        if (clicked) return;
        handleBoardClick(pixelX, pixelY);
    }

    private boolean handleEndButtonClick(int pixelX, int pixelY) {
        if (!endButton.isDisable() && endButton.isClicked(pixelX, pixelY)) {
            drawNextTile();
            endButton.disable();
            return true;
        }
        return false;
    }

    private void handleBoardClick(int pixelX, int pixelY) {
        int x = Cords.xToCords(pixelX);
        int y = Cords.yToCords(pixelY);
        if (spawnValidator.canSpawnTile(x, y)) {
            spawnTile(x,y);
            endButton.enable();
        }
    }

    private void drawNextTile() {
        tilePreview.setTile(tilesPile.poll()); // TODO: 16.02.2023 wysypie się jak się skończą
    }


    private void spawnTile(int x, int y) {
        Tile newTile = tilePreview.getTile();
        newTile.setGridX(x);
        newTile.setGridY(y);
        tilesBoard.add(newTile);
    }



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

//podgląd aktualnego
//postawienie aktualnego chowa podgląd
//również aktywuje przycisk
//również blokuje stawianie klocka
//przycisk zatwierdzenia - naciśnięcie pokazuje podgląd kolejnego
//przyleganie dróg - zezwala lub nie na położenie tile
