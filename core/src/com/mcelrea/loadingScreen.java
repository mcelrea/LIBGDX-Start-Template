package com.mcelrea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class loadingScreen implements Screen {

    private static final float WORLD_WIDTH = 640;
    private static final float WORLD_HEIGHT = 480;
    private static final float PROGRESS_BAR_WIDTH = 100;
    private static final float PROGRESS_BAR_HEIGHT = 25;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private float progress = 0;

    private MyGdxGame game;

    public loadingScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.position.set(WORLD_WIDTH/2, WORLD_HEIGHT/2,0);
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
    }

    @Override
    public void render(float delta) {
        update();
        clearScreen();
        draw();
    }

    private void draw() {
        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        shapeRenderer.begin();
        shapeRenderer.rect((WORLD_WIDTH - PROGRESS_BAR_WIDTH)/2,
                WORLD_HEIGHT/2 - PROGRESS_BAR_HEIGHT/2,
                progress * PROGRESS_BAR_WIDTH,
                PROGRESS_BAR_HEIGHT);
        shapeRenderer.end();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void update() {
        //if the asset manager is done loading everything
        if(game.getAssetManager().update()) {
            game.setScreen(new GameplayScreen(game));
        }
        else {//the asset manager is still loading images, sounds, maps, etc.
            progress = game.getAssetManager().getProgress();
        }
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
