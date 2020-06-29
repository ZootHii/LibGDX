package com.zoothii.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

class Enemy extends Object {

    private double velocity;
    private Circle enemyCircle;

    public Enemy(Texture texture, float x, float y, float width, float height, double velocity, Circle enemyCircle) {
        super(texture, x, y, width, height);
        this.velocity = velocity;
        this.enemyCircle = enemyCircle;
    }

    public void setPosition(float x, float y){
        super.setX(x);
        super.setY(y);

    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public Circle getEnemyCircle() {
        return enemyCircle;
    }

    public void setEnemyCircle(Circle enemyCircle) {
        this.enemyCircle = enemyCircle;
    }
}
