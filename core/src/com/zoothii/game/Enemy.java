package com.zoothii.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

class Enemy extends Object {

    private double velocity, acceleration;
    private Circle enemyCircle;


    public Enemy(Texture texture, float x, float y, float width, float height, double velocity, double acceleration, Circle enemyCircle) {
        super(texture, x, y, width, height);
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.enemyCircle = enemyCircle;
    }

    public void setPosition(float x, float y){
        super.setX(x);
        super.setY(y);

    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
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

    public static void setEnemyAnimation(Enemy enemyA, Enemy enemyB, Enemy enemyC, Enemy enemyD, SpriteBatch batch){
        if (enemyA.getY() > Gdx.graphics.getHeight()){ //IF ENEMY IS OUT OF THE SCREEN DRAW AGAIN
            enemyA.setY((float) - randY());
            enemyA.setVelocity(4);
            enemyA.setX((float) randX());
            double enemyAcceleration = 0.0083;
            GameScreen.score++;
            enemyA.setAcceleration(enemyA.getAcceleration()+enemyAcceleration);
        }
        enemyIntersection(enemyA, enemyB, enemyC, enemyD);
        batch.draw(enemyA.getTexture(), enemyA.getX(), enemyA.getY(), enemyA.getWidth(), enemyA.getHeight());
    }

    private static void enemyIntersection(Enemy enemyA, Enemy enemyB, Enemy enemyC, Enemy enemyD){
        if (!(enemyA.getY() < Gdx.graphics.getHeight() && enemyA.getY() > - Gdx.graphics.getHeight()/6f)) { // CHECK IF ENEMY IN SCREEN
            if (Intersector.overlaps(enemyA.getEnemyCircle(), enemyB.getEnemyCircle()) // IF ENEMY IN SCREEN CHECK ENEMY INTERSECTION
                    || Intersector.overlaps(enemyA.getEnemyCircle(), enemyC.getEnemyCircle())
                    || Intersector.overlaps(enemyA.getEnemyCircle(), enemyD.getEnemyCircle())) {
                enemyA.setX((float) randX());
            } // IF THERE IS AN ENEMY INTERSECTION PUT ENEMY X ANYWHERE ELSE
        }
    }

    public static void set4EnemyVelocity(Enemy enemyA, Enemy enemyB, Enemy enemyC, Enemy enemyD){
        enemyA.setVelocity(enemyA.getVelocity()+enemyA.getAcceleration());
        enemyA.setY(enemyA.getY()+(float) enemyA.getVelocity());
        enemyB.setVelocity(enemyB.getVelocity()+enemyB.getAcceleration());
        enemyB.setY(enemyB.getY()+(float) enemyB.getVelocity());
        enemyC.setVelocity(enemyC.getVelocity()+enemyC.getAcceleration());
        enemyC.setY(enemyC.getY()+(float) enemyC.getVelocity());
        enemyD.setVelocity(enemyD.getVelocity()+enemyD.getAcceleration());
        enemyD.setY(enemyD.getY()+(float) enemyD.getVelocity());
    }

    public static void set4EnemyCircle(Enemy enemyA, Enemy enemyB, Enemy enemyC, Enemy enemyD){
        enemyA.getEnemyCircle().set(enemyA.getX()+enemyA.getWidth()/1.93f, enemyA.getY()+enemyA.getWidth()/2, enemyA.getWidth()/2.37f);
        enemyB.getEnemyCircle().set(enemyB.getX()+enemyB.getWidth()/1.93f, enemyB.getY()+enemyB.getWidth()/2.4f, enemyB.getWidth()/2.2f);
        enemyC.getEnemyCircle().set(enemyC.getX()+enemyC.getWidth()/1.93f, enemyC.getY()+enemyC.getWidth()/2.25f, enemyC.getWidth()/2.2f);
        enemyD.getEnemyCircle().set(enemyD.getX()+enemyD.getWidth()/2, enemyD.getY()+enemyD.getWidth()/2, enemyD.getWidth()/2.2f);
    }

    public static void stopEnemy(Enemy enemy, SpriteBatch batch){
        enemy.setY(enemy.getY());
        enemy.setVelocity(0);
        enemy.setAcceleration(0);
        batch.draw(enemy.getTexture(), enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
    }
}
