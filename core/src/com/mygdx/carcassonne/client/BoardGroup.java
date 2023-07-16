package com.mygdx.carcassonne.client;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.carcassonne.server.Tile;

public class BoardGroup extends Group {
    private final Controller controller;

    public BoardGroup(Controller controller) {
        this.controller = controller;
    }

    void addFirstTile() {// CHECK: 16.07.2023 nie trzeba zmenić nazwy metody?
        Tile tile = controller.createNextTile();
        TileActor tileActor = new TileActor(tile, controller);
        tileActor.setGridPosition(8, 4);
        addActor(tileActor);
        controller.placeTile(tile);
    }
}
