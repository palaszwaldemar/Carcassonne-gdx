package com.mygdx.carcassonne.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class MeepleActor extends Actor {
    private final Texture texture = new Texture(Gdx.files.internal("meeple.png"));

    public MeepleActor() {
        setBounds(GuiParams.WIDTH - GuiParams.TILE_SIZE - GuiParams.MEEPLE_SIZE,
                GuiParams.HEIGHT - GuiParams.MEEPLE_SIZE,
                GuiParams.MEEPLE_SIZE,
                GuiParams.MEEPLE_SIZE);
        addListener(new DragAndDropMeeple());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
                getHeight(), getScaleX(), getScaleY(), getRotation(), 0, 0,
                texture.getWidth(), texture.getHeight(), false, false);
    }

    private class DragAndDropMeeple extends DragListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            return super.touchDown(event, x, y, pointer, button);
        }

        @Override
        public void drag(InputEvent event, float x, float y, int pointer) {
            moveBy(x - getWidth() / 2, y - getHeight() / 2);
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            super.touchUp(event, x, y, pointer, button);
            setX(event.getStageX() - 10);
            setY(event.getStageY() - 10);
        }
    }
}
