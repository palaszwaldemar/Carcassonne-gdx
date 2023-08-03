package com.mygdx.carcassonne.client;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class EndButton extends Actor {
    private final ShapeRenderer renderer;
    private boolean active;
    private final Controller controller;

    public EndButton(Controller controller) {
        this.controller = controller;
        renderer = new ShapeRenderer();
        setBounds(GuiParams.WIDTH - GuiParams.TILE_SIZE, GuiParams.HEIGHT - GuiParams.TILE_SIZE, GuiParams.TILE_SIZE, GuiParams.TILE_SIZE);
        addListener(new Click());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.end();  // koniec batch'a, bo ShapeRenderer zaczyna nową serię rysowania

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        if (active) {
            renderer.setColor(Color.GREEN);

        } else {
            renderer.setColor(Color.RED);
        }
        renderer.rect(getX(), getY(), getWidth(), getHeight());
        renderer.end();

        batch.begin();  // zaczynamy batch na nowo, żeby inne aktory mogły się rysować

    }

    public void setActive(boolean active) {
        this.active = active;

    }

    class Click extends ClickListener {
        public Click() {
            super(Input.Buttons.LEFT);
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            // CHECK: 03.08.2023 dodać aktora do grupy Board?
            controller.endTurn();
        }
    }
}
