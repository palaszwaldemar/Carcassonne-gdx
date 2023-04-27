package com.mygdx.carcassonne;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class GameScreen implements Screen {
    private Carcassonne game;
    private Stage stage;
    private OrthographicCamera camera;


    //dependencies
    private final TileFactory tileFactory;
    private final SpawnValidator spawnValidator;

    //game elements
    private final List<Tile> tilesBoard = new ArrayList<>();
    private final Queue<Tile> tilesPile;
    private final TilePreview tilePreview;

    //ui
    private final EndButton endButton;

    public GameScreen(Carcassonne game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GuiParams.WIDTH, GuiParams.HEIGHT);
        stage = new Stage(new ScreenViewport(camera), game.getBatch());
        tileFactory = new TileFactory();
        tilesPile = tileFactory.getListOfTile();
        tilePreview =  new TilePreview(stage);
        spawnValidator = new SpawnValidator(tilesBoard,tilePreview);
        endButton = new EndButton();

        //biore z fabryki kafelek startowy
        Tile tile = tilesPile.poll();
        stage.addActor(tile);
        tile.setGridX(8);
        tile.setGridY(4);
        tilesBoard.add(tile);
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
            spawnTile(x,y);
            endButton.enable();
        }
    }

    private void drawNextTile() {
        tilePreview.addTile(tilesPile.poll()); // TODO: 16.02.2023 wysypie się jak się skończą
    }


    private void spawnTile(int x, int y) {
        Tile newTile = tilePreview.getTile();
        newTile.setGridX(x);
        newTile.setGridY(y);
        tilesBoard.add(newTile);
    }
}
// TODO: 07.04.2023 //https://github.com/dixu11/deckard-thief/blob/master/core/src/com/deckard/client/core/CombatScreen.java

//podgląd aktualnego
//postawienie aktualnego chowa podgląd
//również aktywuje przycisk
//również blokuje stawianie klocka
//przycisk zatwierdzenia - naciśnięcie pokazuje podgląd kolejnego
//przyleganie dróg - zezwala lub nie na położenie tile

