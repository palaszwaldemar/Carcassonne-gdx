package com.mygdx.carcassonne.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//fasada całego backendu
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

    public boolean isValidPlacement(Tile tile) {
        return spawnValidator.canSpawnTile(tile);
    }

    public void placeTile(Tile tile) {
        if (!spawnValidator.canSpawnTile(tile)) {
            throw new IllegalStateException("not valid place");
        }
        tilesBoard.add(tile);
        tile.setLocked(true);
    }
}
