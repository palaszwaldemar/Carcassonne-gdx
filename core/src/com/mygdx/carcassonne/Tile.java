package com.mygdx.carcassonne;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import java.util.Arrays;

public class Tile extends Actor {

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
        setBounds(Cords.xToPixels(gridX), Cords.yToPixels(gridY), GuiParams.SIZE, GuiParams.SIZE);
        addListener(new DragTileListener());
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
        setX(Cords.xToPixels(gridX));
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
        setY(Cords.yToPixels(gridY));
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

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    @Override
    public String toString() {
        return "carcassone.Tile{" +
                "x=" + gridX +
                ", y=" + gridY +
                ", road=" + Arrays.toString(road) +
                '}';

    }

    class DragTileListener extends DragListener {
        private float deltaX;
        private float deltaY;

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            deltaX = x;
            deltaY = y;
            return super.touchDown(event, x, y, pointer, button);
        }

        @Override
        public void drag(InputEvent event, float x, float y, int pointer) {
            float newX = getX() + x - deltaX;
            float newY = getY() + y - deltaY;
            setPosition(newX, newY);
        }
    }
}
