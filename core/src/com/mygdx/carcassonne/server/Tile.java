package com.mygdx.carcassonne.server;

public class Tile {
    private int x = 0;
    private int y = 0;
    private boolean locked;
    private final Terrain road;
    private final Terrain city;
    private final String name;

    public Tile(char[] directionsRoadChar, char[] directionsCityChar, String name) {
        this.name = name;

        road = new Terrain(TerrainType.ROAD, directionsRoadChar);
        city = new Terrain(TerrainType.CITY, directionsCityChar);
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

    public String getName() {
        return name;
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

    public class Terrain {
        private final TerrainType type;
        private boolean[] sides;

        public Terrain(TerrainType type, char[] charSides) {
            this.type = type;
            sides = new boolean[4];

            for (char direction : charSides) {
                setSide(direction);
            }
        }

        private void setSide(char side) {
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
}
