package com.mygdx.carcassonne.client;

import com.mygdx.carcassonne.server.BoardService;
import com.mygdx.carcassonne.server.Tile;

public class Controller {
    /* w tym miejscu będziemy umieszać wszystkie komponenty graficzne, z którymi będzie potrzebna interakcja*/
    private TilePreview tilePreview;
    private final BoardService boardService = new BoardService();
    private EndButton endButton;
    private TileActor correctPlacedTileActor;
    private BoardGroup boardGroup;

    public Tile createNextTile() {
        return boardService.nextTile();
    }

    public void placeTile(TileActor tileActor) {
        boolean correctPlacement = boardService.isValidPlacement(tileActor.getTile());
        if (correctPlacement) {
            endButton.setActive(true);
            correctPlacedTileActor = tileActor;
        } else {
            endButton.setActive(false);
        }
    }

    public void rotateTile(Tile tile) {
        tile.rotate();
    }

    public void endTurn() {
        if (correctPlacedTileActor == null) {
            throw new IllegalStateException("No tile placed!");
        }
        boardService.placeTile(correctPlacedTileActor.getTile());
        tilePreview.spawnNextTile();
        endButton.setActive(false);
        boardGroup.attach(correctPlacedTileActor);
    }

    public void setBoardGroup(BoardGroup boardGroup) {
        this.boardGroup = boardGroup;
    }

    public void setTilePreview(TilePreview tilePreview) {
        this.tilePreview = tilePreview;
    }

    public void setEndButton(EndButton endButton) {
        this.endButton = endButton;
    }
}
