package com.mygdx.carcassonne.client;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

public class TextureActor extends Actor {
    private final Texture texture;

    public TextureActor(Texture texture, float x, float y) {
        this.texture = texture;
        setBounds(x, y, GuiParams.TILE_SIZE, GuiParams.TILE_SIZE);
        setOrigin(Align.center);
    }

    public void draw(Batch batch, float parentAlpha) {
        /*Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);*/
        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation(), 0, 0, texture.getWidth(), texture.getHeight(), false, false);
//        batch.setColor(Color.WHITE);
    }
}
