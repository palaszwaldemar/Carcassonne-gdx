package com.mygdx.carcassonne.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mygdx.carcassonne.server.Tile;

public class TileActor extends Actor {
    private final Tile tile;
    private final Texture image;

    public TileActor(Tile tile) {
        this.tile = tile;
        this.image = new Texture(Gdx.files.internal("tiles/PNG/Base_Game_C2_Tile_" + tile.getName() + ".png"));
        setBounds(0, 0, GuiParams.SIZE, GuiParams.SIZE);
        addListener(new DragTileListener());
    }

    public void setGridX() {
        setX(Cords.xToPixels(tile.getX()));
    }

    public void setGridY() {
        setY(Cords.yToPixels(tile.getY()));
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

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            super.touchUp(event, x, y, pointer, button);
        }
    }
}
