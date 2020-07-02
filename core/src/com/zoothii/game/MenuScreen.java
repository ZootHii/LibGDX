package com.zoothii.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import sun.font.TrueTypeFont;


class MenuScreen implements Screen {
    public static final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

    private Texture backgroundTexture, coreTexture, titleTexture;
    private SecureCore parent;
    ImageButton buttonImage;
    TextField textField;
    Stage stage;
    private OrthographicCamera orthographicCamera;
    private Viewport viewport;


    public MenuScreen(SecureCore parent) {
        this.parent = parent;
        stage = new Stage();
        orthographicCamera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), orthographicCamera);
        Gdx.input.setInputProcessor(stage);
        backgroundTexture = new Texture("background.png");
        coreTexture = new Texture("core.png");
        titleTexture = new Texture("title2.png");

    }
    //TAP anywhere on the screen and move your finger to move core

    @Override
    public void render(float delta) {

        float SCREEN_WIDTH = Gdx.graphics.getWidth();
        float SCREEN_HEIGHT = Gdx.graphics.getHeight();
        float CORE_WIDTH_HEIGHT = Gdx.graphics.getHeight()/13f;
        parent.batch.begin();
        parent.batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        parent.batch.draw(coreTexture,SCREEN_WIDTH/2.185f, CORE_WIDTH_HEIGHT*11.7f, CORE_WIDTH_HEIGHT*0.65f, CORE_WIDTH_HEIGHT*0.65f);
        parent.batch.draw(titleTexture,SCREEN_WIDTH/2f-Gdx.graphics.getHeight()/3.2f*3f/2, CORE_WIDTH_HEIGHT*8.4f, Gdx.graphics.getHeight()/3.2f*3f, Gdx.graphics.getHeight()/3.8f);
        //batch.draw(rankTexture,SCREEN_WIDTH/2-210, CORE_WIDTH_HEIGHT*8f, 500, 200);


        parent.batch.end();
        playButton();
        rankButton();

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();


        style.font = new BitmapFont();
        style.font.getData().setScale(5f);
        style.fontColor = Color.WHITE;

        textField = new TextField("", style);
        textField.setText("test");
        textField.setPosition(0,0);
        System.out.println(style.font.getCache().getFont());
        stage.addActor(textField);


        stage.act(Gdx.graphics.getRawDeltaTime());
        stage.draw();

    }

    public void playButton(){

        float SCREEN_WIDTH = Gdx.graphics.getWidth();
        float SCREEN_HEIGHT = Gdx.graphics.getHeight();
        float CORE_WIDTH_HEIGHT = Gdx.graphics.getHeight()/13f;

        Texture texture = new Texture("playButton111.png");
        final Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        drawable.setMinWidth(SCREEN_HEIGHT/3.82f);
        drawable.setMinHeight(SCREEN_HEIGHT/9.31f);
        //System.out.println(drawable.getMinHeight() + "" + drawable.getMinWidth());
        buttonImage = new ImageButton(drawable);
        buttonImage.setPosition(SCREEN_WIDTH/2-SCREEN_HEIGHT/3.82f/2f, CORE_WIDTH_HEIGHT*7f);
        buttonImage.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(SecureCore.GAME_SCREEN);
                GameScreen.command = "start";
            }
        });
        stage.addActor(buttonImage);
    }

    public void rankButton(){
        float SCREEN_WIDTH = Gdx.graphics.getWidth();
        float SCREEN_HEIGHT = Gdx.graphics.getHeight();
        float CORE_WIDTH_HEIGHT = Gdx.graphics.getHeight()/13f;

        Texture texture = new Texture("rankButton111.png");
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        drawable.setMinWidth(SCREEN_HEIGHT/3.82f);
        drawable.setMinHeight(SCREEN_HEIGHT/9.31f);
        //System.out.println(drawable.getMinHeight() + "" + drawable.getMinWidth());
        buttonImage = new ImageButton(drawable);
        buttonImage.setPosition(SCREEN_WIDTH/2-SCREEN_HEIGHT/3.82f/2f, CORE_WIDTH_HEIGHT*5.5f);
        buttonImage.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(SecureCore.GAME_SCREEN);
                GameScreen.command = "start";
            }
        });
        stage.addActor(buttonImage);
    }

    @Override public void show() { }

    @Override public void resize(int width, int height) {
        viewport.update(width,height);
        //stage.getViewport().update(width, height, true);
    }

    @Override public void pause() { }

    @Override public void resume() { }

    @Override public void hide() { }

    @Override public void dispose() {
        parent.batch.dispose();
        stage.dispose();
        coreTexture.dispose();
        backgroundTexture.dispose();
        titleTexture.dispose();
        parent.dispose();
        Gdx.app.exit();
    }
}
