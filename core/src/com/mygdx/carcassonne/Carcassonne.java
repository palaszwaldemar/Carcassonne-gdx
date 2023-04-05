package com.mygdx.carcassonne;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Carcassonne extends Game {
	public SpriteBatch batch; // CHECK : 05.04.2023 dlaczego te pola są publiczne?
	public BitmapFont font;

	@Override // CHECK : 05.04.2023 dlaczego tylko ta metda jest implementowana? Skąd ta metoda jest implementowana?
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new GameScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
