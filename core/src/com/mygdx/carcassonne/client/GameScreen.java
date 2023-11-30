package com.mygdx.carcassonne.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {
    private final Carcassonne game;
    private final Stage stage;
    private final OrthographicCamera camera;
    private final TilePreview tilePreview;
    private final BoardGroup boardGroup;
    private final EndButton endButton;
    private final MeepleActor meepleActor;

    public GameScreen(Carcassonne game) {
        this.game = game;
        Controller controller = new Controller();
        endButton = new EndButton(controller);
        boardGroup = new BoardGroup(controller);
        tilePreview = new TilePreview(controller);
        meepleActor = new MeepleActor();
        controller.setTilePreview(tilePreview);
        controller.setBoardGroup(boardGroup);
        //przygotowanie elementów graficznych
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GuiParams.WIDTH, GuiParams.HEIGHT);
        controller.setEndButton(endButton);
        stage = new Stage(new ScreenViewport(camera), game.getBatch());
        Gdx.input.setInputProcessor(stage);
        stage.addActor(endButton);
        stage.addActor(boardGroup);
        stage.addActor(tilePreview);
        stage.addActor(meepleActor);
        //elementy rozgrywki
        boardGroup.addFirstTile();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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

