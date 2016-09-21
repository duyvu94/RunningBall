package com.duyvu.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.duyvu.RBSupporters.AssetLoader;

public class SplashScreen implements Screen{
	
	private OrthographicCamera cam;
	private SpriteBatch batcher;
	private float transparency = 0;
	private Game myGame;
	private MainScreen mainScreen;
	
	float screenWidth, screenHeight, gameHeight, gameWidth;
	
	public SplashScreen(Game myGame) {
		this.myGame = myGame;
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		gameHeight = 480;
		gameWidth = screenWidth / (screenHeight / gameHeight);
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, gameWidth, gameHeight);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
	}

	public MainScreen getMainScreen() {
		return mainScreen;
	}

	public void setMainScreen(MainScreen mainScreen) {
		this.mainScreen = mainScreen;
	}

	public Game getMyGame() {
		return myGame;
	}

	public void setMyGame(Game myGame) {
		this.myGame = myGame;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.begin();
		if (transparency<=1){
			batcher.setColor(1, 1, 1, 1);
			batcher.draw(AssetLoader.white, 0, 0, gameWidth, gameHeight);
			batcher.draw(AssetLoader.logo, 0, 0, gameWidth, gameHeight);
			batcher.setColor(1, 1, 1, 1- transparency);
			batcher.draw(AssetLoader.white, 0, 0, gameWidth, gameHeight);
			transparency+= delta;
		}
		else if (transparency<=2 && transparency>=1){
			batcher.setColor(1, 1, 1, 1);
			batcher.draw(AssetLoader.logo, 0, 0, gameWidth, gameHeight);
			transparency+= delta;
		}
		else if (transparency>= 2 && transparency<= 3){
			batcher.setColor(1, 1, 1, 1);
			batcher.draw(AssetLoader.white, 0, 0, gameWidth, gameHeight);
			batcher.draw(AssetLoader.logo, 0, 0, gameWidth, gameHeight);
			batcher.setColor(1, 1, 1, transparency-2);
			batcher.draw(AssetLoader.white, 0, 0, gameWidth, gameHeight);
			transparency+= delta;
		}
		else if (transparency>= 3){
			mainScreen.setInputPriority();
			myGame.setScreen(mainScreen);
			AssetLoader.themes.setLooping(true);
			AssetLoader.themes.play();
		}
		batcher.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
