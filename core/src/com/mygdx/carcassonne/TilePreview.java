package com.mygdx.carcassonne;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.awt.*;

public class TilePreview {
    private final Stage stage;
    private Tile tile;

    public TilePreview(Stage stage) {
        this.stage = stage;
    }

    public void addTile(Tile tile) {
        this.tile = tile;
        stage.addActor(tile);
        tile.setGridX(0);
        tile.setGridY(8);
    }

    void render(Graphics g) {
        int pixelX = 10;
        int pixelY = 10;
//        tile.render(g, pixelX, pixelY);
        g.drawString("ACTUAL TILE", pixelX + 15, pixelY + 115);
    }

    public Tile getTile() {
        return tile;
    }
}
