package com.duyvu.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.duyvu.RBSupporters.AssetLoader;
import com.duyvu.ui.SimpleButton;

public class MainScreen implements Screen, InputProcessor {

	private OrthographicCamera cam;
	private SpriteBatch batcher;
	private float scaleFactorX;
	private float scaleFactorY;

	private Game myGame;
	private GameScreen gameScreen;
	private SettingScreen settingScreen;
	private HelpScreen helpScreen;
	private float screenWidth, screenHeight, gameHeight, gameWidth;
	private SimpleButton startButton, settingButton, helpButton;

	public MainScreen(Game myGame) {
		this.myGame = myGame;
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		gameHeight = 480;
		gameWidth = screenWidth / (screenHeight / gameHeight);
		
		scaleFactorX = screenWidth/gameWidth;
		scaleFactorY = screenHeight/gameHeight;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, gameWidth, gameHeight);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);

		startButton = new SimpleButton(gameWidth-260, 110, 180, 40, AssetLoader.startButtonUp, AssetLoader.startButtonDown);
		settingButton = new SimpleButton(gameWidth-260, 210, 180, 40, AssetLoader.settingButtonUp, AssetLoader.settingButtonDown);
		helpButton = new SimpleButton(gameWidth-260, 310, 180, 40, AssetLoader.helpButtonUp, AssetLoader.helpButtonDown);
		
		settingScreen = new SettingScreen(myGame);
		settingScreen.setMainScreen(this);
		
		helpScreen = new HelpScreen(myGame);
		helpScreen.setMainScreen(this);
	}
	
	public int scaleX(int x) {
		return (int) (x / scaleFactorX);
	}

	public int scaleY(int y) {
		return (int) (y / scaleFactorY);
	}
	
	public void setInputPriority(){
		Gdx.input.setInputProcessor(this);
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.begin();
		
		batcher.draw(AssetLoader.gamescreen, 0, 0, gameWidth, gameHeight);
		if (startButton.isPressed()) {
			batcher.draw(startButton.getButtonDown(), startButton.getX(), startButton.getY(), startButton.getWidth(),
					startButton.getHeight());
		} else {
			batcher.draw(startButton.getButtonUp(), startButton.getX(), startButton.getY(), startButton.getWidth(),
					startButton.getHeight());
		}
		
		if (settingButton.isPressed()) {
			batcher.draw(settingButton.getButtonDown(), settingButton.getX(), settingButton.getY(), settingButton.getWidth(),
					settingButton.getHeight());
		} else {
			batcher.draw(settingButton.getButtonUp(), settingButton.getX(), settingButton.getY(), settingButton.getWidth(),
					settingButton.getHeight());
		}
		
		if (helpButton.isPressed()) {
			batcher.draw(helpButton.getButtonDown(), helpButton.getX(), helpButton.getY(), helpButton.getWidth(),
					helpButton.getHeight());
		} else {
			batcher.draw(helpButton.getButtonUp(), helpButton.getX(), helpButton.getY(), helpButton.getWidth(),
					helpButton.getHeight());
		}
		
		BitmapFont font= new BitmapFont();
		font.getData().setScale(1f, -1f);
		font.draw(batcher, "Developed by Vu Phuong Duy", gameWidth/2-100, 440);
		
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

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		
		startButton.isTouchedDown(screenX, screenY);
		helpButton.isTouchedDown(screenX, screenY);
		settingButton.isTouchedDown(screenX, screenY);
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		
		if (startButton.isTouchedUp(screenX, screenY)){
			AssetLoader.press.play(AssetLoader.soundVolume);
			myGame.setScreen(gameScreen);
			gameScreen.setInputPriority();
		}
		
		if (settingButton.isTouchedUp(screenX, screenY)){
			AssetLoader.press.play(AssetLoader.soundVolume);
			myGame.setScreen(settingScreen);
			settingScreen.setInputPriority();
		}
		
		if (helpButton.isTouchedUp(screenX, screenY)){
			AssetLoader.press.play(AssetLoader.soundVolume);
			myGame.setScreen(helpScreen);
			helpScreen.setInputPriority();
		}
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
