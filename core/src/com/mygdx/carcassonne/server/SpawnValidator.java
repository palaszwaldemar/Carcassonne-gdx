package com.mygdx.carcassonne.server;

import java.util.List;

public class SpawnValidator {

    private final List<Tile> tilesBoard;

    public SpawnValidator(List<Tile> tilesBoard) {
        this.tilesBoard = tilesBoard;
    }

    public boolean canSpawnTile(Tile tile) {
        return isConnectedTile(tile) &&
                placeIsEmpty(tile) &&
                isMatchingTile(tile);
    }

    private boolean isConnectedTile(Tile tile) {
        for (Tile boardTile : tilesBoard) {
            if (boardTile.equals(tile)) {
                continue;
            }
            int otherXDifference = Math.abs(boardTile.getX() - tile.getX());
            int otherYDifference = Math.abs(boardTile.getY() - tile.getY());
            int sum = otherXDifference + otherYDifference;
            if (sum == 1) {
                return true;
            }
        }
        return false;
    }

    private boolean placeIsEmpty(Tile tile) {
        for (Tile boardTile : tilesBoard) {
            if (boardTile.getX() == tile.getX() && boardTile.getY() == tile.getY()) {
                return false;
            }
        }
        return true;
    }

    private boolean isMatchingTile(Tile tile) {
        return areCorrectlyConnected(tile, TerrainType.CITY) &&
                areCorrectlyConnected(tile, TerrainType.ROAD);
    }

    private boolean areCorrectlyConnected(Tile tile, TerrainType terrainType) {
        Tile.Terrain tileTerrain = tile.getTerrain(terrainType);
        for (Tile boardTile : tilesBoard) {
            if (boardTile.equals(tile)) {
                continue;
            }
            Tile.Terrain boardTileTerrain = boardTile.getTerrain(terrainType);
            if (boardTile.getX() == tile.getX() && boardTile.getY() == tile.getY() + 1) {
                if (boardTileTerrain.getSide(0) != tileTerrain.getSide(2)) {
                    return false;
                }
            }
            if (boardTile.getX() == tile.getX() && boardTile.getY() == tile.getY() - 1) {
                if (boardTileTerrain.getSide(2) != tileTerrain.getSide(0)) {
                    return false;
                }
            }
            if (boardTile.getX() == tile.getX() - 1 && boardTile.getY() == tile.getY()) {
                if (boardTileTerrain.getSide(1) != tileTerrain.getSide(3)) {
                    return false;
                }
            }
            if (boardTile.getX() == tile.getX() + 1 && boardTile.getY() == tile.getY()) {
                if (boardTileTerrain.getSide(3) != tileTerrain.getSide(1)) {
                    return false;
                }
            }
        }
        return true;
    }
}
