package com.mygdx.carcassonne.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MeepleActor extends Actor {
    private final Texture texture = new Texture(Gdx.files.internal("meeple.png"));

    public MeepleActor() {
        setBounds(GuiParams.WIDTH - GuiParams.TILE_SIZE - GuiParams.MEEPLE_SIZE,
                GuiParams.HEIGHT - GuiParams.MEEPLE_SIZE,
                GuiParams.MEEPLE_SIZE,
                GuiParams.MEEPLE_SIZE);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
                getHeight(), getScaleX(), getScaleY(), getRotation(), 0, 0,
                texture.getWidth(), texture.getHeight(), false, false);
    }
}
