package com.mygdx.carcassonne.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.carcassonne.server.Tile;

public class TileActor extends Actor {
    private final Tile tile;
    private final Texture image;
    private final Controller controller;

    public TileActor(Tile tile, Controller controller) {
        this.controller = controller;
        this.tile = tile;
        this.image = new Texture(Gdx.files.internal("tiles/PNG/Base_Game_C2_Tile_" + tile.getName() + ".png"));
        setBounds(0, 0, GuiParams.TILE_SIZE, GuiParams.TILE_SIZE);
        addListener(new DragTileListener());
        addListener(new RightClick());
        setOrigin(Align.center);
    }

    public void setGridX() {
        setX(Cords.xToPixels(tile.getX()));
    }

    public void setGridY() {
        setY(Cords.yToPixels(tile.getY()));
    }

    public void setGridPosition(int x, int y) {
        setX(Cords.xToPixels(x));
        setY(Cords.yToPixels(y));
        tile.setX(x);
        tile.setY(y);
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

    // TODO: 06.07.2023 spróbować zmienić aby wyłączyć listener
    class DragTileListener extends DragListener {
        private Vector2 lastTouch = new Vector2();
        private Vector2 delta;

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (tile.isNotLocked()) {
                lastTouch = convertToParentVector(x, y);
            }
            return super.touchDown(event, x, y, pointer, button);
        }

        @Override
        public void drag(InputEvent event, float x, float y, int pointer) {
            if (tile.isNotLocked()) {
                dragToPosition(x, y);
            }
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            if (tile.isNotLocked()) {
                super.touchUp(event, x, y, pointer, button);
                dragToPosition(x, y);
                float newX = getX() + x - delta.x;
                float newY = getY() + y - delta.y;
                int roundedX = (int) (Math.floor(newX / GuiParams.TILE_SIZE) * GuiParams.TILE_SIZE);
                int roundedY = (int) (Math.floor(newY / GuiParams.TILE_SIZE) * GuiParams.TILE_SIZE);
                setGridPosition(Cords.xToCords(roundedX), Cords.yToCords(roundedY));
                controller.placeTile(tile);
            }
        }

        private void dragToPosition(float x, float y) {
            Vector2 newTouch = convertToParentVector(x, y);
            delta = newTouch.cpy().sub(lastTouch);
            lastTouch = newTouch;
            TileActor.this.moveBy(delta.x, delta.y);
        }

        private Vector2 convertToParentVector(float x, float y) {
            Vector2 vector = new Vector2(x, y);
            vector = TileActor.this.localToParentCoordinates(vector);
            return vector;
        }

    }

    class RightClick extends ClickListener {
        public RightClick() {
            super(Input.Buttons.RIGHT);
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            if (tile.isNotLocked()) {
                addAction(Actions.rotateBy(-90f, 0.1F));
                controller.rotateTile(tile);
                controller.placeTile(tile);
                super.clicked(event, x, y);
            }
        }
    }
}
