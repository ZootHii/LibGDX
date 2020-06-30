package com.zoothii.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


class MenuScreen implements Screen {

    private SpriteBatch batch;
    private Texture menuTexture1, menuTexture2, texture;
    private SecureCore parent;

    public MenuScreen(SecureCore p) {
        super();
        parent = p;
        batch = new SpriteBatch();
        menuTexture1 = new Texture("background.png");
        menuTexture2 = new Texture("core.png");
        texture = new Texture("enemy.png");

    }
    //TAP anywhere on the screen and move your finger to move core

    @Override
    public void render(float delta) {

        float SCREEN_WIDTH = Gdx.graphics.getWidth();
        float SCREEN_HEIGHT = Gdx.graphics.getHeight();
        float CORE_WIDTH_HEIGHT = Gdx.graphics.getHeight()/13f;
        batch.begin();
        batch.draw(menuTexture1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(menuTexture2,SCREEN_WIDTH/2.24f, CORE_WIDTH_HEIGHT*11.7f, CORE_WIDTH_HEIGHT*0.65f, CORE_WIDTH_HEIGHT*0.65f);
        batch.draw(texture, 50,50, 523f,523f);
        batch.end();
        if(Gdx.input.justTouched()){
            parent.changeScreen(SecureCore.GAME_SCREEN);
            GameScreen.command = "start";
        }



    }

    @Override public void show() { }

    @Override public void resize(int width, int height) { }

    @Override public void pause() { }

    @Override public void resume() { }

    @Override public void hide() { }

    @Override public void dispose() { }


}
