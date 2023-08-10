package com.mygdx.carcassonne.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.carcassonne.server.Tile;

public class TileActor extends Group {
    private final Tile tile;
    private final TextureActor textureActor;
    private final Controller controller;

    public TileActor(Tile tile, Controller controller) {
        this.controller = controller;
        this.tile = tile;
        Texture image = new Texture(Gdx.files.internal("tiles/PNG/Base_Game_C2_Tile_" + tile.getName() + ".png"));
        textureActor = new TextureActor(image, getX(), getY());
        setBounds(0, 0, GuiParams.TILE_SIZE, GuiParams.TILE_SIZE);
        addListener(new DragTileListener());
        addListener(new RightClick());
        addActor(textureActor);
        setOrigin(Align.center);
    }

    public void setGridPosition(int x, int y) {
        setX(Cords.xToPixels(x));
        setY(Cords.yToPixels(y));
        tile.setX(x);
        tile.setY(y);
    }

    class DragTileListener extends DragListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            return super.touchDown(event, x, y, pointer, button);
        }

        @Override
        public void drag(InputEvent event, float x, float y, int pointer) {
            if (tile.isNotLocked()) {
                moveBy(x - getWidth() / 2, y - getHeight() / 2);
            }
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            super.touchUp(event, x, y, pointer, button);
            if (tile.isNotLocked()) {
                getStage().addActor(TileActor.this);
                int xCord = (int) event.getStageX() / 100;
                int yCord = (int) event.getStageY() / 100;
                setGridPosition(xCord, yCord);
                controller.placeTile(tile);
            }
        }
    }

    class RightClick extends ClickListener {
        public RightClick() {
            super(Input.Buttons.RIGHT);
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            if (tile.isNotLocked()) {
                textureActor.addAction(Actions.rotateBy(-90f, 0.1F));
                controller.rotateTile(tile);
                if (!Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    controller.placeTile(tile);
                }
            }
        }
    }
}

