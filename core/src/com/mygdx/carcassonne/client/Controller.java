package com.mygdx.carcassonne.client;

import com.mygdx.carcassonne.server.BoardService;
import com.mygdx.carcassonne.server.Tile;

public class Controller {
    /* w tym miejscu będziemy umieszać wszystkie komponenty graficzne, z którymi będzie potrzebna interakcja*/
    private TilePreview tilePreview;
    private final BoardService boardService = new BoardService();
    private EndButton endButton;
    private Tile correctPlacedTile;

    public void setTilePreview(TilePreview tilePreview) {
        this.tilePreview = tilePreview;
    }

    public Tile createNextTile() {
        return boardService.nextTile();
    }

    public void placeTile(Tile tile) {
        boolean correctPlacement = boardService.isValidPlacement(tile);
        if (correctPlacement) {
            endButton.setActive(true);
            correctPlacedTile = tile;
        } else {
            endButton.setActive(false);
        }
    }

    public void setEndButton(EndButton endButton) {
        this.endButton = endButton;
    }

    public void rotateTile(Tile tile) {
        tile.rotate();
    }

    public void endTurn() {
        if (correctPlacedTile == null) {
            throw new IllegalStateException("No tile placed!");
        }
        boardService.placeTile(correctPlacedTile);
        tilePreview.spawnNextTile();
        endButton.setActive(false);
    }
}
