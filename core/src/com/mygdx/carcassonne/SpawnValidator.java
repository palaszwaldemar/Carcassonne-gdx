package com.mygdx.carcassonne;

import java.util.List;

public class SpawnValidator {

    private final List<Tile> tilesBoard;

    private final TilePreview tilePreview;

    public SpawnValidator(List<Tile> tilesBoard, TilePreview tilePreview) {
        this.tilesBoard = tilesBoard;
        this.tilePreview = tilePreview;
    }

    boolean canSpawnTile(int x, int y) {
        return isConnectedTile(x, y) &&
                placeIsEmpty(x, y) &&
                isMatchingTile(x, y);
    }

    private boolean isConnectedTile(int x, int y) {
        for (Tile tile : tilesBoard) {
            if (tile.equals(tilePreview.getTile())) {
                continue;
            }
            int otherXDifference = Math.abs(tile.getGridX() - x);
            int otherYDifference = Math.abs(tile.getGridY() - y);
            int sum = otherXDifference + otherYDifference;
            if (sum == 1) {
                return true;
            }
        }
        return false;
    }

    private boolean placeIsEmpty(int x, int y) {
        for (Tile tile : tilesBoard) {
            if (tile.getX() == x && tile.getY() == y) {
                return false;
            }
        }
        return true;
    }

    private boolean isMatchingTile(int x, int y) {
        return areRoadsCorrectlyConnected(x, y) &&
                areCitiesCorrectlyConnected(x, y);
    }

    private boolean areRoadsCorrectlyConnected(int x, int y) { //todo try refactor
        Tile northConnected;
        Tile eastConnected;
        Tile southConnected;
        Tile westConnected;
        for (Tile tile : tilesBoard) {
            if (tile.equals(tilePreview.getTile())) {
                continue;
            }
            if (tile.getX() == x && tile.getY() == y + 1) {
                northConnected = tile;
                if (northConnected.getRoad(0) != tilePreview.getTile().getRoad(2)) {
                    return false;
                }
            }
            if (tile.getX() == x && tile.getY() == y - 1) {
                southConnected = tile;
                if (southConnected.getRoad(2) != tilePreview.getTile().getRoad(0)) {
                    return false;
                }
            }
            if (tile.getX() == x - 1 && tile.getY() == y) {
                eastConnected = tile;
                if (eastConnected.getRoad(1) != tilePreview.getTile().getRoad(3)) {
                    return false;
                }
            }
            if (tile.getX() == x + 1 && tile.getY() == y) {
                westConnected = tile;
                if (westConnected.getRoad(3) != tilePreview.getTile().getRoad(1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean areCitiesCorrectlyConnected(int x, int y) {
        Tile northConnected;
        Tile eastConnected;
        Tile southConnected;
        Tile westConnected;
        for (Tile tile : tilesBoard) {
            if (tile.equals(tilePreview.getTile())) {
                continue;
            }
            if (tile.getX() == x && tile.getY() == y + 1) {
                northConnected = tile;
                if (northConnected.getCity(0) != tilePreview.getTile().getCity(2)) {
                    return false;
                }
            }
            if (tile.getX() == x && tile.getY() == y - 1) {
                southConnected = tile;
                if (southConnected.getCity(2) != tilePreview.getTile().getCity(0)) {
                    return false;
                }
            }
            if (tile.getX() == x - 1 && tile.getY() == y) {
                eastConnected = tile;
                if (eastConnected.getCity(1) != tilePreview.getTile().getCity(3)) {
                    return false;
                }
            }
            if (tile.getX() == x + 1 && tile.getY() == y) {
                westConnected = tile;
                if (westConnected.getCity(3) != tilePreview.getTile().getCity(1)) {
                    return false;
                }
            }
        }
        return true;
    }

}
