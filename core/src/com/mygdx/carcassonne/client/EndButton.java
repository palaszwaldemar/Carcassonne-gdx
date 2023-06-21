package com.mygdx.carcassonne.client;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class EndButton extends Actor {
    private ShapeRenderer renderer;

    public EndButton() {
        renderer = new ShapeRenderer();
        setBounds(GuiParams.WIDTH - GuiParams.TILE_SIZE - 10, GuiParams.HEIGHT - GuiParams.TILE_SIZE - 10, GuiParams.TILE_SIZE, GuiParams.TILE_SIZE);
        addListener(new Click());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.end();  // koniec batch'a, bo ShapeRenderer zaczyna nową serię rysowania

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.RED);
        renderer.rect(getX(), getY(), getWidth(), getHeight());
        renderer.end();

        batch.begin();  // zaczynamy batch na nowo, żeby inne aktory mogły się rysować

    }

    class Click extends ClickListener {
        public Click() {
            super(Input.Buttons.LEFT);
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            System.out.println("---");

            super.clicked(event, x, y);
        }
    }
}
