package com.mygdx.carcassonne.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.carcassonne.server.BoardService;

public class GameScreen implements Screen {
    private final Carcassonne game;
    private final Stage stage;
    private final OrthographicCamera camera;
    private final TilePreview tilePreview;
    private final EndButton endButton;

    public GameScreen(Carcassonne game) {
        this.game = game;
        Controller controller = new Controller();

        //przygotowanie elementów graficznych
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GuiParams.WIDTH, GuiParams.HEIGHT);

        endButton = new EndButton();

        stage = new Stage(new ScreenViewport(camera), game.getBatch());
        Gdx.input.setInputProcessor(stage);

        tilePreview = new TilePreview(controller);
        int margin = 10;
        tilePreview.setPosition(margin, GuiParams.HEIGHT - GuiParams.TILE_SIZE - margin);
        stage.addActor(tilePreview);
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


// sterowanie przyciskiem, kiedy aktywny kiedy nie
/*    private boolean handleEndButtonClick(int pixelX, int pixelY) {
        if (!endButton.isDisable() && endButton.isClicked(pixelX, pixelY)) {
            drawNextTile();
            endButton.disable();
            return true;
        }
        return false;
    }*/

}

