package com.zoothii.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

public class SecureCore extends Game {


	public static final int SPLASH_SCREEN = 0;
	public static final int GAME_SCREEN = 1;
	public static final int MENU_SCREEN = 2;
	public static final int GAME_OVER_SCREEN = 3;


	public SecureCore() { super(); }

	@Override
	public void create ()
	{
		changeScreen(SPLASH_SCREEN);
	}

	@Override
	public void dispose()
	{
		getScreen().dispose();
		Gdx.app.exit();
	}

	public void changeScreen(int screen)
	{
		if(screen == SPLASH_SCREEN){
			this.setScreen(new SplashScreen(this));
		}else if(screen == GAME_SCREEN){
			this.setScreen(new GameScreen(this));
		}else if(screen == MENU_SCREEN){
			this.setScreen(new MenuScreen(this));
		}/*else if(screen == GAME_OVER_SCREEN){
			this.setScreen(new GameOverScreen(this));
		}*/
	}
}
