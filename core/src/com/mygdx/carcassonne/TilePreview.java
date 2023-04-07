package com.mygdx.carcassonne;

import java.awt.*;

public class TilePreview {
    private Tile tile;


    public void setTile(Tile tile) {
        this.tile = tile;
    }

    void render(Graphics g) {
        int pixelX = 10;
        int pixelY = 10;
        tile.render(g, pixelX, pixelY);
        g.drawString("ACTUAL TILE", pixelX + 15, pixelY + 115);
    }

    public Tile getTile() {
        return tile;
    }
}
