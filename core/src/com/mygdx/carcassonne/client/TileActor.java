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

    public TileActor(Tile tile) {
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


   /* class DragTileListener extends DragListener {
        private float deltaX;
        private float deltaY;

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { // TODO: 01.06.2023 powiększenie/pomniejszenie okna zmienia położenie chwytu kafelka
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
            float newX = getX() + x - deltaX;
            float newY = getY() + y - deltaY;
            int roundedX = Math.round(newX / GuiParams.TILE_SIZE) * GuiParams.TILE_SIZE; // CHECK : 16.05.2023 czy jestem w stanie wykorzystać tutaj swoją klasę Cords, lub metody setGridX, setGridY
            int roundedY = Math.round(newY / GuiParams.TILE_SIZE) * GuiParams.TILE_SIZE;

            setPosition(roundedX, roundedY);
        }
    }*/

    class DragTileListener extends DragListener {
        private Vector2 lastTouch = new Vector2();

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            lastTouch.set(x, y);
            lastTouch = event.getTarget().localToParentCoordinates(lastTouch);
            return super.touchDown(event, x, y, pointer, button);
        }

        @Override
        public void drag(InputEvent event, float x, float y, int pointer) {
            Actor tile = event.getTarget();

            Vector2 newTouch = new Vector2(x, y);
            newTouch = tile.localToParentCoordinates(newTouch);
            Vector2 delta = newTouch.cpy().sub(lastTouch);

            tile.moveBy(delta.x, delta.y);

            lastTouch = newTouch;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            super.touchUp(event, x, y, pointer, button);
            Actor tile = event.getTarget();

            Vector2 newTouch = new Vector2(x, y);
            newTouch = tile.localToParentCoordinates(newTouch);
            Vector2 delta = newTouch.cpy().sub(lastTouch);
            float newX = getX() + x - delta.x;
            float newY = getY() + y - delta.y;
            int roundedX = Math.round(newX / GuiParams.TILE_SIZE) * GuiParams.TILE_SIZE; // CHECK : 16.05.2023 czy jestem w stanie wykorzystać tutaj swoją klasę Cords, lub metody setGridX, setGridY
            int roundedY = Math.round(newY / GuiParams.TILE_SIZE) * GuiParams.TILE_SIZE;

            setPosition(roundedX, roundedY);
        }
    }

    class RightClick extends ClickListener {
        public RightClick() {
            super(Input.Buttons.RIGHT);
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            addAction(Actions.rotateBy(-90f, 1));
            super.clicked(event, x, y);
        }
    }
}
