package com.zoothii.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

class SplashScreen implements Screen {

    Stage stage;
    private SpriteBatch batch;
    private Texture splashTextureLogo;
    private Texture splashTextureLogoBack;
    private SecureCore parent;

    private float timeToShowSplashScreen = 2f;

    public SplashScreen(SecureCore p) {
        super();
        parent = p;
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        splashTextureLogo = new Texture("logo.png");
        splashTextureLogoBack = new Texture("logoBack2.png");
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(splashTextureLogoBack, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(splashTextureLogo, Gdx.graphics.getWidth()/3.82f, Gdx.graphics.getHeight()/2.2f, Gdx.graphics.getWidth()/2.1f, Gdx.graphics.getWidth()/2.1f);
        batch.end();

        timeToShowSplashScreen -= delta;
        if(timeToShowSplashScreen <= 0){
            parent.changeScreen(SecureCore.MENU_SCREEN);
        }
    }

    @Override public void show() { }

    @Override public void resize(int width, int height) { }

    @Override public void pause() { }

    @Override public void resume() { }

    @Override public void hide() { }

    @Override public void dispose() { }
}
