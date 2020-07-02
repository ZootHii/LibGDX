package com.zoothii.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


class Hud {
    public Stage stage;
    public Viewport viewport;

    Label scoreLabel;
    Label highScoreLabel;
    Label scoreLabelTop;
    Label highScoreLabelTop;
    Label.LabelStyle scoreLabelStyle;
    Label.LabelStyle highScoreLabelStyle;
    Label.LabelStyle scoreLabelTopStyle;
    Label.LabelStyle highScoreLabelTopStyle;

    public Hud(SpriteBatch batch){
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());

        stage = new Stage();
        Table table = new Table();
        table.top().left().padTop(Gdx.graphics.getWidth()/100f).padLeft(Gdx.graphics.getWidth()/90f);
        table.setFillParent(true);

        scoreLabelStyle = new Label.LabelStyle(new BitmapFont(), Color.BLUE);
        highScoreLabelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        scoreLabelTopStyle = new Label.LabelStyle(new BitmapFont(), Color.BLUE);
        highScoreLabelTopStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);


        scoreLabelStyle.font.getData().setScale(5f);
        highScoreLabelStyle.font.getData().setScale(5f);
        scoreLabelTopStyle.font.getData().setScale(5f);
        highScoreLabelTopStyle.font.getData().setScale(5f);

        scoreLabel = new Label(""+GameScreen.score+"", scoreLabelStyle);
        highScoreLabel = new Label(""+GameScreen.highScore+"", highScoreLabelStyle);
        scoreLabelTop = new Label("Score", scoreLabelTopStyle);
        highScoreLabelTop = new Label("HIGH SCORE", highScoreLabelTopStyle);

        //scoreLabel.setVisible(false);

       /* table.add(highScoreLabelTop).expandX().padTop(10);*/
        //table.add(scoreLabelTop).expandX().padTop(10);
        //table.row();
        /*table.add(highScoreLabel).expandX();*/
        table.add(scoreLabel);

        stage.addActor(table);
    }
}
