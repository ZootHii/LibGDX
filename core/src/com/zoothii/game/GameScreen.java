package com.zoothii.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Stage;

class GameScreen implements Screen {
    Stage stage;
    private SecureCore parent;
    SpriteBatch batch;
    Sprite core;
    Circle coreCircle;
    Enemy enemy1 ,enemy2, enemy3, enemy4;

    Background background1, background2;
    //Background backgroundTest;
    public static String command = "";
    public static int score = 0;
    ShapeRenderer shapeRenderer;

    public GameScreen(SecureCore p)
    {
        super();
        parent = p;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        float SCREEN_WIDTH = Gdx.graphics.getWidth();
        float SCREEN_HEIGHT = Gdx.graphics.getHeight();
        float CORE_WIDTH_HEIGHT = Gdx.graphics.getHeight()/13f;

        batch = new SpriteBatch();
        //backgroundTest = new Background(new Texture("logoBack2.png"), 0,0 , SCREEN_WIDTH, SCREEN_HEIGHT);
        background1 = new Background(new Texture("background.png"), 0,0 , SCREEN_WIDTH, SCREEN_HEIGHT);
        background2 = new Background(new Texture("background2.png"), 0, -SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT);

        core = new Sprite(new Texture("core.png"));
        core.setPosition(SCREEN_WIDTH/2.24f, CORE_WIDTH_HEIGHT*11.7f);
        core.setSize(CORE_WIDTH_HEIGHT*0.65f, CORE_WIDTH_HEIGHT*0.65f);
        coreCircle = new Circle();
        shapeRenderer = new ShapeRenderer();
        createEnemy();
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        // START
        batch.begin();
        //batch.draw(backgroundTest.getTexture(), backgroundTest.getX(), backgroundTest.getY(), backgroundTest.getWidth(), backgroundTest.getHeight());
        Background.backgroundAnimation(background1, batch);
        Background.backgroundAnimation(background2, batch);
        batch.draw(core, core.getX(), core.getY(), core.getWidth(), core.getHeight());
        System.out.println("Score: "+ score);
        collisionDetection(); // command = "over";
        if(command.equals("start")){
            Enemy.setEnemyAnimation(enemy1, enemy2, enemy3, enemy4, batch);
            Enemy.setEnemyAnimation(enemy2, enemy1, enemy3, enemy4, batch);
            Enemy.setEnemyAnimation(enemy3, enemy1, enemy2, enemy4, batch);
            Enemy.setEnemyAnimation(enemy4, enemy1, enemy2, enemy3, batch);

            Enemy.set4EnemyVelocity(enemy1, enemy2, enemy3, enemy4);
            if(Gdx.input.isTouched()){
                if (core.getX() <= 0) {
                    core.setX(Gdx.graphics.getHeight()/192f);
                    core.setY(core.getY());
                }else if (core.getX()+Gdx.graphics.getHeight()/11.3f >= Gdx.graphics.getWidth()+Gdx.graphics.getHeight()/21f){
                    core.setX(Gdx.graphics.getWidth()-Gdx.graphics.getHeight()/21f);
                    core.setY(core.getY());
                }else if (core.getY() <= 0){
                    core.setY(0);
                    core.setX(core.getX());
                }else if (core.getY()+Gdx.graphics.getHeight()/11.3f >= Gdx.graphics.getHeight()+Gdx.graphics.getHeight()/21f){
                    core.setY(Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/21f);
                    core.setX(core.getX());
                }
                core.setX(core.getX()+Gdx.input.getDeltaX());
                core.setY(core.getY()-Gdx.input.getDeltaY());
            }
        }else if(command.equals("over")){
            gameOver();
            if (Gdx.input.justTouched()){
                playAgain();
            }
        }
        batch.end();
        // END
        coreCircle.set(core.getX()+core.getWidth()/2, core.getY()+core.getWidth()/2, core.getWidth()/2);
        Enemy.set4EnemyCircle(enemy1, enemy2, enemy3, enemy4);
        //setShapeRenderer();
        stage.draw();
    }

    @Override
    public void dispose () {
        Gdx.app.exit();
    }

    public void collisionDetection(){
        if (Intersector.overlaps(coreCircle, enemy1.getEnemyCircle()) || Intersector.overlaps(coreCircle, enemy2.getEnemyCircle())
                || Intersector.overlaps(coreCircle, enemy3.getEnemyCircle()) || Intersector.overlaps(coreCircle, enemy4.getEnemyCircle())){
            command = "over";
        }
    }

    public void gameOver(){
        Enemy.stopEnemy(enemy1, batch);
        Enemy.stopEnemy(enemy2, batch);
        Enemy.stopEnemy(enemy3, batch);
        Enemy.stopEnemy(enemy4, batch);
    }

    public void playAgain(){

        float CORE_WIDTH_HEIGHT = Gdx.graphics.getHeight()/13f;

        core.setPosition(Gdx.graphics.getWidth()/2.24f, CORE_WIDTH_HEIGHT*11.7f);
        core.setSize(CORE_WIDTH_HEIGHT*0.65f, CORE_WIDTH_HEIGHT*0.65f);
        enemy1.setPosition((float) randX(),(float) -randY());
        enemy1.setVelocity(4);
        enemy1.setAcceleration(0.068);
        enemy2.setPosition((float) randX(),(float) -randY());
        enemy2.setVelocity(4);
        enemy2.setAcceleration(0.046);
        enemy3.setPosition((float) randX(),(float) -randY());
        enemy3.setVelocity(4);
        enemy3.setAcceleration(0.022);
        enemy4.setPosition((float) randX(),(float) -randY());
        enemy4.setVelocity(4);
        enemy4.setAcceleration(0.012);
        command = "start";
    }

    public void createEnemy(){
        enemy1 = new Enemy(new Texture("enemy.png"), (float) randX(),(float) -randY(),Gdx.graphics.getHeight()/8.5f, Gdx.graphics.getHeight()/8.5f ,4, 0.068, new Circle());
        enemy2 = new Enemy(new Texture("enemy1.png"), (float) randX(),(float) -randY()-Gdx.graphics.getHeight()/9f,Gdx.graphics.getHeight() /7.5f, Gdx.graphics.getHeight() /7.5f ,4, 0.046, new Circle());
        enemy3 = new Enemy(new Texture("enemy2.png"), (float) randX(),(float) -randY()-Gdx.graphics.getHeight()/7f,Gdx.graphics.getHeight() /6.5f, Gdx.graphics.getHeight() /6.5f ,4, 0.022,new Circle());
        enemy4 = new Enemy(new Texture("enemy3.png"), (float) randX(),(float) -randY()-Gdx.graphics.getHeight()/5f,Gdx.graphics.getHeight() /6.5f, Gdx.graphics.getHeight() /6.5f ,4, 0.012,new Circle());
    }

    public double randX(){
        int max = (int) (Gdx.graphics.getWidth()-Gdx.graphics.getHeight()/8f);
        int min = 0;
        return Math.random() * (max - min + 1) + min;
    }
    public double randY(){
        int max = (int) (Gdx.graphics.getHeight() + Gdx.graphics.getHeight()/13f + 300f - Gdx.graphics.getHeight()*0.78);
        int min = (int) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight()*0.78);
        return Math.random() * (max - min + 1) + min;
    }

    /*public void setShapeRenderer(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(coreCircle.x, coreCircle.y, coreCircle.radius);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(enemy1.getEnemyCircle().x, enemy1.getEnemyCircle().y, enemy1.getEnemyCircle().radius);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(enemy2.getEnemyCircle().x, enemy2.getEnemyCircle().y, enemy2.getEnemyCircle().radius);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(enemy3.getEnemyCircle().x, enemy3.getEnemyCircle().y, enemy3.getEnemyCircle().radius);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(enemy4.getEnemyCircle().x, enemy4.getEnemyCircle().y, enemy4.getEnemyCircle().radius);
        shapeRenderer.end();

    }*/

    @Override public void show() { }

    @Override public void resize(int width, int height) { }

    @Override public void pause() { }

    @Override public void resume() { }

    @Override public void hide() { }
}
