package com.zoothii.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


class GameScreen implements Screen {
    Stage stage;
    private SecureCore parent;
    Sprite core;
    Circle coreCircle;
    Rectangle buttonRectangle;
    Enemy enemy1 ,enemy2, enemy3, enemy4;
    Background background1, background2;
    Texture menuTexture3, button;
    ImageButton buttonImage;
    public static String command = "";
    public static int score = 0;
    public static int highScore = 0;
    ShapeRenderer shapeRenderer;
    Table table;
    Hud hud;
    private OrthographicCamera orthographicCamera;
    private Viewport viewport;

    public GameScreen(SecureCore parent) {
        this.parent = parent;

        table = new Table();

        menuTexture3 = new Texture("title.png");
        orthographicCamera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), orthographicCamera);
        stage = new Stage();
        hud = new Hud(parent.batch);
        Gdx.input.setInputProcessor(stage);



        float SCREEN_WIDTH = Gdx.graphics.getWidth();
        float SCREEN_HEIGHT = Gdx.graphics.getHeight();
        float CORE_WIDTH_HEIGHT = Gdx.graphics.getHeight()/13f;


        background1 = new Background(new Texture("background.png"), 0,0 , SCREEN_WIDTH, SCREEN_HEIGHT);
        background2 = new Background(new Texture("background2.png"), 0, -SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT);
        core = new Sprite(new Texture("core.png"));
        core.setPosition(SCREEN_WIDTH/2.185f, CORE_WIDTH_HEIGHT*11.7f);
        core.setSize(CORE_WIDTH_HEIGHT*0.65f,CORE_WIDTH_HEIGHT*0.65f);
        coreCircle = new Circle();
        buttonRectangle = new Rectangle();
        shapeRenderer = new ShapeRenderer();
        createEnemy();
    }

    @Override
    public void render(float delta) {


        stage.act(delta);
        coreCircle.set(core.getX()+core.getWidth()/2, core.getY()+core.getWidth()/2, core.getWidth()/2);
        Enemy.set4EnemyCircle(enemy1, enemy2, enemy3, enemy4);
        /*setShapeRenderer();*/

        if(command.equals("over")){
            float SCREEN_WIDTH = Gdx.graphics.getWidth();
            float SCREEN_HEIGHT = Gdx.graphics.getHeight();
            float CORE_WIDTH_HEIGHT = Gdx.graphics.getHeight()/13f;

            Texture texture = new Texture("retryButton111.png");
            Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
            drawable.setMinWidth(SCREEN_HEIGHT/3.82f);
            drawable.setMinHeight(SCREEN_HEIGHT/9.31f);
            buttonImage = new ImageButton(drawable);
            buttonImage.setPosition(SCREEN_WIDTH/2-SCREEN_HEIGHT/3.82f/2f, CORE_WIDTH_HEIGHT*8f);
            buttonImage.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    parent.changeScreen(SecureCore.MENU_SCREEN);
                }
            });
            stage.addActor(buttonImage);
        }

        // START
        parent.batch.begin();
        Background.backgroundAnimation(background1, parent.batch);
        Background.backgroundAnimation(background2, parent.batch);
        collisionDetection(); // command = "over";
        if(command.equals("over")){
            gameOver();
        }
        if(command.equals("start")){
            Enemy.setEnemyAnimation(enemy1, enemy2, enemy3, enemy4, parent.batch);
            Enemy.setEnemyAnimation(enemy2, enemy1, enemy3, enemy4, parent.batch);
            Enemy.setEnemyAnimation(enemy3, enemy1, enemy2, enemy4, parent.batch);
            Enemy.setEnemyAnimation(enemy4, enemy1, enemy2, enemy3, parent.batch);

            Enemy.set4EnemyVelocity(enemy1, enemy2, enemy3, enemy4);

            if(Gdx.input.isTouched()){
                core.setX(core.getX()+Gdx.input.getDeltaX());
                core.setY(core.getY()-Gdx.input.getDeltaY());
            }

            if (core.getX() <= 0) {
                core.setX(0);
                core.setY(core.getY());
            }
            if (core.getX()+Gdx.graphics.getHeight()/13f*0.65f >= Gdx.graphics.getWidth()){
                core.setX(Gdx.graphics.getWidth()-Gdx.graphics.getHeight()/13f*0.65f);
                core.setY(core.getY());
            }
            if (core.getY() <= 0){
                core.setY(0);
                core.setX(core.getX());
            }
            if (core.getY()+Gdx.graphics.getHeight()/13f*0.65f >= Gdx.graphics.getHeight()){
                core.setY(Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/13f*0.65f);
                core.setX(core.getX());
            }
        }

        parent.batch.draw(core, core.getX(), core.getY(), core.getWidth(), core.getHeight());
        parent.batch.end();
        // END
        parent.batch.setProjectionMatrix(hud.stage.getCamera().combined); // TODO learn this shit
        hud.stage.draw();

        stage.draw();
    }


    @Override
    public void dispose () {
        Gdx.app.exit();
        menuTexture3.dispose();
        shapeRenderer.dispose();
        button.dispose();
        parent.dispose();
        parent.batch.dispose();
        stage.dispose();
    }

    public void collisionDetection(){
        if (Intersector.overlaps(coreCircle, enemy1.getEnemyCircle()) || Intersector.overlaps(coreCircle, enemy2.getEnemyCircle())
                || Intersector.overlaps(coreCircle, enemy3.getEnemyCircle()) || Intersector.overlaps(coreCircle, enemy4.getEnemyCircle())){
            command = "over";
        }
    }

    public void gameOver(){

        Enemy.stopEnemy(enemy1, parent.batch);
        Enemy.stopEnemy(enemy2, parent.batch);
        Enemy.stopEnemy(enemy3, parent.batch);
        Enemy.stopEnemy(enemy4, parent.batch);

        if(highScore < score){
            highScore = score;
        }
        score = 0;
    }

    public void createEnemy(){
        enemy1 = new Enemy(new Texture("enemy.png"), (float) randX(),(float) -randY(),Gdx.graphics.getHeight()/9f, Gdx.graphics.getHeight()/9f ,4, 0.068, new Circle());
        enemy2 = new Enemy(new Texture("enemy1.png"), (float) randX(),(float) -randY()-Gdx.graphics.getHeight()/9f,Gdx.graphics.getHeight() /8f, Gdx.graphics.getHeight() /8f ,4, 0.046, new Circle());
        enemy3 = new Enemy(new Texture("enemy2.png"), (float) randX(),(float) -randY()-Gdx.graphics.getHeight()/7f,Gdx.graphics.getHeight() /7f, Gdx.graphics.getHeight() /7f ,4, 0.022,new Circle());
        enemy4 = new Enemy(new Texture("enemy3.png"), (float) randX(),(float) -randY()-Gdx.graphics.getHeight()/5f,Gdx.graphics.getHeight() /7f, Gdx.graphics.getHeight() /7f ,4, 0.012,new Circle());
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



    @Override public void show() {
        /*if(flag){
            float SCREEN_WIDTH = Gdx.graphics.getWidth();
            float CORE_WIDTH_HEIGHT = Gdx.graphics.getHeight()/13f;
            table.setPosition(SCREEN_WIDTH/2-210, CORE_WIDTH_HEIGHT*8f);

            button = new Texture("rank2.png");
            buttonImage = new Image(button);
            //buttonImage.setPosition(SCREEN_WIDTH/2-210, CORE_WIDTH_HEIGHT*8f);
            buttonImage.setSize(500, 200);
            buttonImage.addListener(new InputListener(){

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    System.out.println("AAAAAAA");
                }
            });

            table.add();
            table.add(buttonImage).size(500, 200);
            table.add();
            table.row().pad(5,5,5,5);
            stage.addActor(table);
        }*/

    }

    @Override public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override public void pause() { }

    @Override public void resume() { }

    @Override public void hide() {

    }

     public void setShapeRenderer(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(coreCircle.x, coreCircle.y, coreCircle.radius);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(enemy1.getEnemyCircle().x, enemy1.getEnemyCircle().y, enemy1.getEnemyCircle().radius);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(enemy2.getEnemyCircle().x, enemy2.getEnemyCircle().y, enemy2.getEnemyCircle().radius);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(enemy3.getEnemyCircle().x, enemy3.getEnemyCircle().y, enemy3.getEnemyCircle().radius);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(enemy4.getEnemyCircle().x, enemy4.getEnemyCircle().y, enemy4.getEnemyCircle().radius);
        shapeRenderer.end();


    }

    /*public void playAgain(){
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
    }*/
}
