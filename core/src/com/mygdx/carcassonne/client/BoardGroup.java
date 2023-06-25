package com.mygdx.carcassonne.client;

import com.badlogic.gdx.scenes.scene2d.Group;

public class BoardGroup extends Group {
    private final Controller controller;

    public BoardGroup(Controller controller) {
        this.controller = controller;
    }

    void addFirstTile() {
        TileActor tileActor = new TileActor(controller.createFirstTile());
        tileActor.setPosition(800 + GuiParams.MARGIN, 500 - GuiParams.MARGIN);
        addActor(tileActor);
    }
}
