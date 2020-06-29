package com.zoothii.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

public class SecureCore extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite core;
	Circle coreCircle;
	Enemy enemy1 ,enemy2, enemy3, enemy4;
	double enemy4Acc = 0.012; // XD
	
	double enemy1Acc = 0.068;
	double enemy2Acc = 0.046;
	double enemy3Acc = 0.022;
	Background background;
	String command = "";
	ShapeRenderer shapeRenderer;

	@Override
	public void create () {

		float SCREEN_WIDTH = Gdx.graphics.getWidth();
		float SCREEN_HEIGHT = Gdx.graphics.getHeight();
		float CORE_WIDTH_HEIGHT = Gdx.graphics.getHeight()/13f;

		batch = new SpriteBatch();

		background = new Background(new Texture("background.png"), 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

		core = new Sprite(new Texture("core.png"));
		core.setPosition(SCREEN_WIDTH/2.24f, CORE_WIDTH_HEIGHT*11.7f);
		core.setSize(CORE_WIDTH_HEIGHT*0.65f, CORE_WIDTH_HEIGHT*0.65f);
		coreCircle = new Circle();
		shapeRenderer = new ShapeRenderer();
		createEnemy();
	}

	@Override
	public void render () {

		// START
		batch.begin();
		batch.draw(background.getTexture(), background.getX(), background.getY(), background.getWidth(), background.getHeight());
		batch.draw(core, core.getX(), core.getY(), core.getWidth(), core.getHeight());

		if (Gdx.input.justTouched()) { command = "start"; }
		collisionDetection(); // command = "over";

		if(command.equals("start")){

			drawEnemy();
			setEnemyVelocity();
			if(Gdx.input.isTouched()){
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
		setEnemyCircle();
		//setShapeRenderer();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void collisionDetection(){
		if (Intersector.overlaps(coreCircle, enemy1.getEnemyCircle()) || Intersector.overlaps(coreCircle, enemy2.getEnemyCircle())
				|| Intersector.overlaps(coreCircle, enemy3.getEnemyCircle()) || Intersector.overlaps(coreCircle, enemy4.getEnemyCircle())){
			command = "over";
		}
	}

	public void gameOver(){
		enemy1.setY(enemy1.getY());
		enemy2.setY(enemy2.getY());
		enemy3.setY(enemy3.getY());
		enemy4.setY(enemy4.getY());

		batch.draw(enemy1.getTexture(), enemy1.getX(), enemy1.getY(), enemy1.getWidth(), enemy1.getHeight());
		batch.draw(enemy2.getTexture(), enemy2.getX(), enemy2.getY(), enemy2.getWidth(), enemy2.getHeight());
		batch.draw(enemy3.getTexture(), enemy3.getX(), enemy3.getY(), enemy3.getWidth(), enemy3.getHeight());
		batch.draw(enemy4.getTexture(), enemy4.getX(), enemy4.getY(), enemy4.getWidth(), enemy4.getHeight());
	}

	public void playAgain(){

		float CORE_WIDTH_HEIGHT = Gdx.graphics.getHeight()/13f;

		core.setPosition(Gdx.graphics.getWidth()/2.24f, CORE_WIDTH_HEIGHT*11.7f);
		core.setSize(CORE_WIDTH_HEIGHT*0.65f, CORE_WIDTH_HEIGHT*0.65f);
		enemy1.setPosition((float) randX(),(float) -randY());
		enemy1.setVelocity(4);
		enemy2.setPosition((float) randX(),(float) -randY());
		enemy2.setVelocity(4);
		enemy3.setPosition((float) randX(),(float) -randY());
		enemy3.setVelocity(4);
		enemy4.setPosition((float) randX(),(float) -randY());
		enemy4.setVelocity(4);
		enemy4Acc = 0.012;
		enemy1Acc = 0.068;
		enemy2Acc = 0.046;
		enemy3Acc = 0.022;
		command = "start";
	}

	public void drawEnemy(){

		if (enemy1.getY() > Gdx.graphics.getHeight()){ //IF ENEMY IS OUT OF THE SCREEN DRAW AGAIN
			enemy1.setY((float) - randY());
			enemy1.setVelocity(4);
			enemy1.setX((float) randX());
			enemy1Acc += 0.0083;
		}
		if (enemy2.getY() > Gdx.graphics.getHeight()){
			enemy2.setY((float) - randY());
			enemy2.setVelocity(4);
			enemy2.setX((float) randX());
			enemy2Acc += 0.0083;
		}
		if (enemy3.getY() > Gdx.graphics.getHeight()){
			enemy3.setY((float) - randY());
			enemy3.setVelocity(4);
			enemy3.setX((float) randX());
			enemy3Acc += 0.0083;
		}
		if (enemy4.getY() > Gdx.graphics.getHeight()){
			enemy4.setY((float) - randY());
			enemy4.setVelocity(4);
			enemy4.setX((float) randX());
			enemy4Acc += 0.0083;
		}
		if (!(enemy1.getY() < Gdx.graphics.getHeight() && enemy1.getY() > - Gdx.graphics.getHeight()/6f)){ // CHECK IF ENEMY IN SCREEN
			if (Intersector.overlaps(enemy1.getEnemyCircle(), enemy2.getEnemyCircle()) // IF ENEMY IN SCREEN CHECK ENEMY INTERSECTION
			|| Intersector.overlaps(enemy1.getEnemyCircle(), enemy3.getEnemyCircle())
			|| Intersector.overlaps(enemy1.getEnemyCircle(), enemy4.getEnemyCircle()))
			{ enemy1.setX((float) randX()); } // IF THERE IS AN ENEMY INTERSECTION PUT ENEMY ANYWHERE ELSE
		}else if (!(enemy2.getY() < Gdx.graphics.getHeight() && enemy2.getY() > - Gdx.graphics.getHeight()/6f)){
			if (Intersector.overlaps(enemy2.getEnemyCircle(), enemy1.getEnemyCircle())
			|| Intersector.overlaps(enemy2.getEnemyCircle(), enemy3.getEnemyCircle())
			|| Intersector.overlaps(enemy2.getEnemyCircle(), enemy4.getEnemyCircle()))
			{ enemy2.setX((float) randX()); }
		}else if (!(enemy3.getY() < Gdx.graphics.getHeight() && enemy3.getY() > - Gdx.graphics.getHeight()/6f)){
			if (Intersector.overlaps(enemy3.getEnemyCircle(), enemy1.getEnemyCircle())
			|| Intersector.overlaps(enemy3.getEnemyCircle(), enemy2.getEnemyCircle())
			|| Intersector.overlaps(enemy3.getEnemyCircle(), enemy4.getEnemyCircle()))
			{ enemy3.setX((float) randX()); }
		}else if (!(enemy4.getY() < Gdx.graphics.getHeight() && enemy4.getY() > - Gdx.graphics.getHeight()/6f)){
			if (Intersector.overlaps(enemy4.getEnemyCircle(), enemy1.getEnemyCircle())
			|| Intersector.overlaps(enemy4.getEnemyCircle(), enemy2.getEnemyCircle())
			|| Intersector.overlaps(enemy4.getEnemyCircle(), enemy3.getEnemyCircle()))
			{ enemy4.setX((float) randX()); }
		}

		batch.draw(enemy1.getTexture(), enemy1.getX(), enemy1.getY(), enemy1.getWidth(), enemy1.getHeight());
		batch.draw(enemy2.getTexture(), enemy2.getX(), enemy2.getY(), enemy2.getWidth(), enemy2.getHeight());
		batch.draw(enemy3.getTexture(), enemy3.getX(), enemy3.getY(), enemy3.getWidth(), enemy3.getHeight());
		batch.draw(enemy4.getTexture(), enemy4.getX(), enemy4.getY(), enemy4.getWidth(), enemy4.getHeight());
	}

	public void createEnemy(){
		enemy1 = new Enemy(new Texture("enemy.png"), (float) randX(),(float) -randY(),Gdx.graphics.getHeight() /10f, Gdx.graphics.getHeight() /10f ,4, new Circle());
		enemy2 = new Enemy(new Texture("enemy1.png"), (float) randX(),(float) -randY(),Gdx.graphics.getHeight() /9f, Gdx.graphics.getHeight() /10f ,4, new Circle());
		enemy3 = new Enemy(new Texture("enemy2.png"), (float) randX(),(float) -randY(),Gdx.graphics.getHeight() /8f, Gdx.graphics.getHeight() /8f ,4, new Circle());
		enemy4 = new Enemy(new Texture("enemy3.png"), (float) randX(),(float) -randY(),Gdx.graphics.getHeight() /8f, Gdx.graphics.getHeight() /8f ,4, new Circle());

	}

	public void setEnemyCircle(){
		enemy1.getEnemyCircle().set(enemy1.getX()+enemy1.getWidth()/1.93f, enemy1.getY()+enemy1.getWidth()/2, enemy1.getWidth()/2.37f);
		enemy2.getEnemyCircle().set(enemy2.getX()+enemy2.getWidth()/1.93f, enemy2.getY()+enemy2.getWidth()/2.4f, enemy2.getWidth()/2.2f);
		enemy3.getEnemyCircle().set(enemy3.getX()+enemy3.getWidth()/1.93f, enemy3.getY()+enemy3.getWidth()/2.25f, enemy3.getWidth()/2.2f);
		enemy4.getEnemyCircle().set(enemy4.getX()+enemy4.getWidth()/2, enemy4.getY()+enemy4.getWidth()/2, enemy4.getWidth()/2.2f);
	}

	public void setEnemyVelocity(){
		enemy1.setVelocity(enemy1.getVelocity()+enemy1Acc); // acc = 0,0226
		enemy1.setY(enemy1.getY()+(float) enemy1.getVelocity());
		enemy2.setVelocity(enemy2.getVelocity()+enemy2Acc); // acc = 0,0153
		enemy2.setY(enemy2.getY()+(float) enemy2.getVelocity());
		enemy3.setVelocity(enemy3.getVelocity()+enemy3Acc); // acc = 0,0073
		enemy3.setY(enemy3.getY()+(float) enemy3.getVelocity());
		enemy4.setVelocity(enemy4.getVelocity()+enemy4Acc); // acc = 0,0043
		enemy4.setY(enemy4.getY()+(float) enemy4.getVelocity());
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



	public void setShapeRenderer(){
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

	}



}
