package com.mygdx.carcassonne.server;

public class Terrain {
    private final TerrainType type;
    private boolean[] sides;

    public Terrain(TerrainType type) {
        this.type = type;
    }

    public void setSide(char side) {
        switch (side) {
            case 'N' -> sides[0] = true;
            case 'E' -> sides[1] = true;
            case 'S' -> sides[2] = true;
            case 'W' -> sides[3] = true;
        }
    }

    public boolean getSide(int i){
        return sides[i];
    }
}
