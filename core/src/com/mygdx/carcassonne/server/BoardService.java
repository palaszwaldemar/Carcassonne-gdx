package com.mygdx.carcassonne.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BoardService {
    private final List<Tile> tilesBoard = new ArrayList<>();
    private final Queue<Tile> tilesPile;
    private final TileFactory tileFactory;
    private final SpawnValidator spawnValidator;

    public BoardService() {
        tileFactory = new TileFactory();
        tilesPile = tileFactory.getListOfTile();
        spawnValidator = new SpawnValidator(tilesBoard);
    }

    public Tile nextTile() {
        return tilesPile.poll();
    }

    public Tile setupFirstTile() {
        Tile tile = nextTile();
        tile.setX(4);
        tile.setY(8);
        tilesBoard.add(tile);
        tile.setLocked(true);
        return tile;
    }
}
