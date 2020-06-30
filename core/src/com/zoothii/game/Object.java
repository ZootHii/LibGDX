package com.zoothii.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

abstract class Object {

    private Texture texture;
    private float x, y;
    private float width, height;

    public Object(Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public static double randX(){
        int max = (int) (Gdx.graphics.getWidth()-Gdx.graphics.getHeight()/8f);
        int min = 0;
        return Math.random() * (max - min + 1) + min;
    }
    public static double randY(){
        int max = (int) (Gdx.graphics.getHeight() + Gdx.graphics.getHeight()/13f + 300f - Gdx.graphics.getHeight()*0.78);
        int min = (int) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight()*0.78);
        return Math.random() * (max - min + 1) + min;
    }

}
