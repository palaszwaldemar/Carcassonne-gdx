package com.mygdx.carcassonne.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.carcassonne.server.Tile;

public class TileActor extends Actor {
    private final Tile tile;
    private final Texture image;
    private final TextureActor textureActor;
    private final Controller controller;

    public TileActor(Tile tile, Controller controller) {
        this.controller = controller;
        this.tile = tile;
        this.image = new Texture(Gdx.files.internal("tiles/PNG/Base_Game_C2_Tile_" + tile.getName() + ".png"));
        setBounds(0, 0, GuiParams.TILE_SIZE, GuiParams.TILE_SIZE);
        textureActor = new TextureActor(image, getX(), getY());
        addListener(new DragTileListener());
        addListener(new RightClick());
        setOrigin(Align.center);
    }

    public void setGridPosition(int x, int y) {
        textureActor.setX(Cords.xToPixels(x));
        textureActor.setY(Cords.yToPixels(y));
        setX(Cords.xToPixels(x));
        setY(Cords.yToPixels(y));
        tile.setX(x);
        tile.setY(y);
    }

    public void draw(Batch batch, float parentAlpha) {
        textureActor.draw(batch, parentAlpha);
    }

    // TODO: 06.07.2023 spróbować zmienić aby wyłączyć listener
    class DragTileListener extends DragListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            return super.touchDown(event, x, y, pointer, button);
        }

        @Override
        public void drag(InputEvent event, float x, float y, int pointer) {
            if (tile.isNotLocked()) {
                drag(x, y);
            }
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            if (tile.isNotLocked()) {
                super.touchUp(event, x, y, pointer, button);
                getStage().addActor(TileActor.this);
                int xCord = (int) event.getStageX() / 100;
                int yCord = (int) event.getStageY() / 100;
                setGridPosition(xCord, yCord);
                controller.placeTile(tile);
            }
        }

        private void drag(float x, float y) {
            moveBy(x - getWidth() / 2, y - getHeight() / 2);
            textureActor.moveBy(x - getWidth() / 2, y - getHeight() / 2);
        }
    }

    class RightClick extends ClickListener {
        public RightClick() {
            super(Input.Buttons.RIGHT);
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            if (tile.isNotLocked()) {
                float newRotation = textureActor.getRotation() - 90f;
                textureActor.setRotation(newRotation);
                // CHECK: 03.08.2023 nie ma animacji obrotu kafelka
                controller.rotateTile(tile);
                if (!Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    controller.placeTile(tile);
                }
            }
        }
    }
}
