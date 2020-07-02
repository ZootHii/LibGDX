package com.zoothii.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Background extends Object {

    public Background(Texture texture, float x, float y, float width, float height) {
        super(texture, x, y, width, height);
    }

    public static void backgroundAnimation(Background background, SpriteBatch batch){
        if(background.getY() > Gdx.graphics.getHeight()){
            background.setY(-Gdx.graphics.getHeight());
        }

        background.setY(background.getY()+1.35f);
        batch.draw(background.getTexture(), background.getX(), background.getY(), background.getWidth(), background.getHeight());
    }
}
