package com.mygdx.carcassonne.client;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.carcassonne.client.TileActor;

import java.awt.*;

public class TilePreview {
    private final Stage stage;
    private TileActor tileActor;

    public TilePreview(Stage stage) {
        this.stage = stage;
    }

    public void addTile(TileActor tileActor) {
        this.tileActor = tileActor;
        stage.addActor(tileActor);
        tileActor.setGridX(0);
        tileActor.setGridY(8);
    }

    void render(Graphics g) {
        int pixelX = 10;
        int pixelY = 10;
//        tileActor.render(g, pixelX, pixelY);
        g.drawString("ACTUAL TILE", pixelX + 15, pixelY + 115);
    }

    public TileActor getTile() {
        return tileActor;
    }
}
