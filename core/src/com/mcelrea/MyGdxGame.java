package com.mcelrea;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {

	private final AssetManager assetManager = new AssetManager();

	@Override
	public void create() {
		setScreen(new loadingScreen(this));
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}
}
