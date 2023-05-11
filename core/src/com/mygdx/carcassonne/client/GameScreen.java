package com.mygdx.carcassonne.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.carcassonne.server.BoardService;
import com.mygdx.carcassonne.server.Tile;

import java.awt.event.MouseEvent;

public class GameScreen implements Screen {
    private final Carcassonne game;
    private final Stage stage;
    private final OrthographicCamera camera;
    private final TilePreview tilePreview;
    private final EndButton endButton;
    private final BoardService boardService = new BoardService();

    public GameScreen(Carcassonne game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GuiParams.WIDTH, GuiParams.HEIGHT);
        stage = new Stage(new ScreenViewport(camera), game.getBatch());
        tilePreview = new TilePreview(stage);
        endButton = new EndButton();

        //biore z fabryki kafelek startowy
        Tile tile = boardService.setupFirstTile();
        TileActor tileActor = new TileActor(new Texture(Gdx.files.internal("tiles/PNG/Base_Game_C2_Tile_A.png")), tile);
        stage.addActor(tileActor);
        drawNextTile();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

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

    public void mousePressed(MouseEvent e) {
        int pixelX = e.getX();
        int pixelY = e.getY();
        boolean clicked = handleEndButtonClick(pixelX, pixelY);
        if (clicked) return;
        handleBoardClick(pixelX, pixelY);
    }

    private boolean handleEndButtonClick(int pixelX, int pixelY) {
        if (!endButton.isDisable() && endButton.isClicked(pixelX, pixelY)) {
            drawNextTile();
            endButton.disable();
            return true;
        }
        return false;
    }

    private void handleBoardClick(int pixelX, int pixelY) {
        int x = Cords.xToCords(pixelX);
        int y = Cords.yToCords(pixelY);
        if (spawnValidator.canSpawnTile(x, y)) {
            spawnTile(x, y);
            endButton.enable();
        }
    }

    private void drawNextTile() {
        tilePreview.addTile(tilesPile.poll()); // TODO: 16.02.2023 wysypie się jak się skończą
    }


    private void spawnTile(int x, int y) {
        TileActor newTileActor = tilePreview.getTile();
        newTileActor.setGridX(x);
        newTileActor.setGridY(y);
        tilesBoard.add(newTileActor);
    }
}
// TODO: 07.04.2023 //https://github.com/dixu11/deckard-thief/blob/master/core/src/com/deckard/client/core/CombatScreen.java

//podgląd aktualnego
//postawienie aktualnego chowa podgląd
//również aktywuje przycisk
//również blokuje stawianie klocka
//przycisk zatwierdzenia - naciśnięcie pokazuje podgląd kolejnego
//przyleganie dróg - zezwala lub nie na położenie tileActor

