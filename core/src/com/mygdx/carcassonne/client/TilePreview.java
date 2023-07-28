package com.mygdx.carcassonne.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class TilePreview extends Group {
    private Controller controller;
    private Texture texture = new Texture(Gdx.files.internal("blocker.png"));
    private Image image = new Image(texture);

    public TilePreview(Controller controller) {
        setBounds(0, 0, GuiParams.TILE_SIZE, GuiParams.TILE_SIZE);
        this.controller = controller;
        addActor(image);
    }

    void spawnNextTile() { // tą wywołamy po zakończeniu tury poprzedniego gracza
        TileActor tileActor = new TileActor(controller.createNextTile(), controller);
        addActor(tileActor);
    }
}
