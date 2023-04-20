package com.mygdx.carcassonne;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {
    private Carcassonne game;
    private Stage stage;
    private OrthographicCamera camera;

    public GameScreen(Carcassonne game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GuiParams.WIDTH, GuiParams.HEIGHT);
        stage = new Stage(new ScreenViewport(camera), game.getBatch());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
// TODO: 07.04.2023 //https://github.com/dixu11/deckard-thief/blob/master/core/src/com/deckard/client/core/CombatScreen.java

