package com.mygdx.carcassonne.client;

import com.mygdx.carcassonne.server.BoardService;
import com.mygdx.carcassonne.server.Tile;

public class Controller {
    /* w tym miejscu będziemy umieszać wszystkie komponenty graficzne, z którymi będzie potrzebna interakcja*/
    private TilePreview tilePreview;
    private BoardService boardService = new BoardService();

    public Tile createNextTile() {
        return boardService.nextTile();
    }
}
