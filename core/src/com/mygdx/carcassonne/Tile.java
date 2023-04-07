package com.mygdx.carcassonne;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Arrays;

public class Tile extends Actor {
    private static final int SIZE = 100;

    //gui
    private int gridX;
    private int gridY;
    private final Texture image;

    //logic
    private final boolean[] road = new boolean[4];
    private final boolean[] city = new boolean[4];

    public Tile(int gridX, int gridY, Texture image) {
        this.gridX = gridX;
        this.gridY = gridY;
        this.image = image;
    }

    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(image, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(),
                getRotation(), 0, 0, image.getWidth(), image.getHeight(),
                false, false);
        batch.setColor(Color.WHITE);
    }

    // TODO: 06.04.2023 pamiętać o przerobieniu xToPixels


    public void setGridX(int gridX) {
        this.gridX = gridX;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
    }

    public void setRoad(char side) {
        switch (side) {
            case 'N' -> road[0] = true;
            case 'E' -> road[1] = true;
            case 'S' -> road[2] = true;
            case 'W' -> road[3] = true;
        }
    }

    public void setCity(char side) {
        switch (side) {
            case 'N' -> city[0] = true;
            case 'E' -> city[1] = true;
            case 'S' -> city[2] = true;
            case 'W' -> city[3] = true;
        }
    }

    public boolean getRoad(int i) {
        return road[i];
    }

    public boolean getCity(int i) {
        return city[i];
    }

    @Override
    public String toString() {
        return "carcassone.Tile{" +
                "x=" + gridX +
                ", y=" + gridY +
                ", road=" + Arrays.toString(road) +
                '}';

    }
}
