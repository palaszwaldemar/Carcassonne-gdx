package com.mygdx.carcassonne.server;

public class Tile {
    private int x = 0;
    private int y = 0;
    private boolean locked;
    private final Terrain road;
    private final Terrain city;

    public Tile(Terrain road, Terrain city) {
        this.road = road;
        this.city = city;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Terrain getRoad() {
        return road;
    }

    public Terrain getCity() {
        return city;
    }

    public Terrain getTerrain(TerrainType terrainType) {
        return switch (terrainType) {
            case CITY -> city;
            case ROAD -> road;
        };
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
