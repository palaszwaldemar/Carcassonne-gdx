package com.mygdx.carcassonne.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.carcassonne.server.BoardService;

public class TilePreview extends Group {
    private BoardService boardService;
    private Texture texture = new Texture(Gdx.files.internal("shadow.png"));
    private Image image = new Image(texture);


    public TilePreview(BoardService boardService) {
        this.boardService = boardService;
        addActor(image);
        spawnFirstTile();
    }

    private void spawnFirstTile() {
        TileActor tileActor = new TileActor(boardService.setupFirstTile());
        addActor(tileActor);
        //odpalenie automatu który to ustawi?
    }

    void spawnNextTile() { // tą wywołamy po zakończeniu tury poprzedniego gracza
        TileActor tileActor = new TileActor(boardService.nextTile());
        addActor(tileActor);
    }
}


// CHECK: 11.05.2023 czy tilePrewiec nie powinien mieć swojego odpowiednika w backend? Gdzie np. podamy w jakim miejscu ma się pojawić na planszy?
