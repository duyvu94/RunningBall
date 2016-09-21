package com.duyvu.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.duyvu.GameWorld.GameRenderer;
import com.duyvu.GameWorld.GameWorld;
import com.duyvu.RBSupporters.InputHandler;

public class GameScreen implements Screen{
	
	private GameWorld world;
	private GameRenderer renderer;
	private MainScreen mainScreen;
	private InputHandler inputHandler;
	private float runTime;
	
	
	public GameScreen(Game myGame){
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameHeight = 480;
		float gameWidth = screenWidth / (screenHeight / gameHeight);
		world = new GameWorld(myGame, gameWidth, gameHeight);
		inputHandler = new InputHandler(world, screenWidth/gameWidth, screenHeight/gameHeight);
		renderer = new GameRenderer(world, (int) gameWidth);
		world.setRenderer(renderer);
	}
	
	public MainScreen getMainScreen() {
		return mainScreen;
	}

	public void setMainScreen(MainScreen mainScreen) {
		this.mainScreen = mainScreen;
		world.setMainScreen(mainScreen);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}	

	public void setInputPriority(){
		Gdx.input.setInputProcessor(inputHandler);
	}

	@Override
	public void render(float delta) {
		//runTime += delta;
		world.update(delta);
		renderer.render(delta, runTime);
		
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
