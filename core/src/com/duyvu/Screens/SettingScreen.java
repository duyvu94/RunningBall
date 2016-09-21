package com.duyvu.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.duyvu.RBSupporters.AssetLoader;
import com.duyvu.ui.SimpleButton;

public class SettingScreen implements Screen, InputProcessor {

	private SimpleButton musicOnButton, musicOffButton, soundOnButton, soundOffButton, backButton;
	private float screenWidth, screenHeight, gameHeight, gameWidth;
	private Game myGame;
	private OrthographicCamera cam;
	private SpriteBatch batcher;
	private float scaleFactorX;
	private float scaleFactorY;
	private MainScreen mainScreen;

	public SettingScreen(Game myGame) {
		this.myGame = myGame;
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		gameHeight = 480;
		gameWidth = screenWidth / (screenHeight / gameHeight);

		scaleFactorX = screenWidth / gameWidth;
		scaleFactorY = screenHeight / gameHeight;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, gameWidth, 480);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);

		musicOnButton = new SimpleButton(gameWidth/2-90, 140, 180, 40, AssetLoader.musicOnButtonUp, AssetLoader.musicOnButtonDown);
		musicOffButton = new SimpleButton(gameWidth/2-90, 140, 180, 40, AssetLoader.musicOffButtonUp, AssetLoader.musicOffButtonDown);
		soundOnButton = new SimpleButton(gameWidth/2-90, 220, 180, 40, AssetLoader.soundOnButtonUp, AssetLoader.soundOnButtonDown);
		soundOffButton = new SimpleButton(gameWidth/2-90, 220, 180, 40, AssetLoader.soundOffButtonUp,
				AssetLoader.soundOffButtonDown);
		backButton = new SimpleButton(gameWidth/2-90, 300, 180, 40, AssetLoader.backButtonUp, AssetLoader.backButtonDown);
	}

	public void setInputPriority() {
		Gdx.input.setInputProcessor(this);
	}

	public MainScreen getMainScreen() {
		return mainScreen;
	}

	public void setMainScreen(MainScreen mainScreen) {
		this.mainScreen = mainScreen;
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
	
	public int scaleX(int x) {
		return (int) (x / scaleFactorX);
	}

	public int scaleY(int y) {
		return (int) (y / scaleFactorY);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		
		if (AssetLoader.prefs.getInteger("music") == 1) {
			musicOnButton.isTouchedDown(screenX, screenY);
		}
		else {
			musicOffButton.isTouchedDown(screenX, screenY);
		}
		
		if (AssetLoader.prefs.getInteger("sound") == 1) {
			soundOnButton.isTouchedDown(screenX, screenY);
		}
		else {
			soundOffButton.isTouchedDown(screenX, screenY);
		}
	
		backButton.isTouchedDown(screenX, screenY);
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		
		if (AssetLoader.prefs.getInteger("music") == 1) {
			if(musicOnButton.isTouchedUp(screenX, screenY)){
				AssetLoader.press.play(AssetLoader.soundVolume);
				AssetLoader.prefs.putInteger("music", 0);
				AssetLoader.prefs.flush();
				AssetLoader.themes.setVolume(0);
			}
		}
		else {
			if(musicOffButton.isTouchedUp(screenX, screenY)){
				AssetLoader.press.play(AssetLoader.soundVolume);
				AssetLoader.prefs.putInteger("music", 1);
				AssetLoader.prefs.flush();
				AssetLoader.themes.setVolume(1);	
			}
		}
		
		if (AssetLoader.prefs.getInteger("sound") == 1) {
			if(soundOnButton.isTouchedUp(screenX, screenY)){
				AssetLoader.press.play(AssetLoader.soundVolume);
				AssetLoader.prefs.putInteger("sound", 0);
				AssetLoader.prefs.flush();
				AssetLoader.soundVolume = 0;
			}
		}
		else {
			if (soundOffButton.isTouchedUp(screenX, screenY)){
				AssetLoader.press.play(AssetLoader.soundVolume);
				AssetLoader.prefs.putInteger("sound", 1);
				AssetLoader.prefs.flush();
				AssetLoader.soundVolume = 1;
			}
		}
		
		if (backButton.isTouchedUp(screenX, screenY)){
			AssetLoader.press.play(AssetLoader.soundVolume);
			myGame.setScreen(mainScreen);
			mainScreen.setInputPriority();
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

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.begin();

		batcher.draw(AssetLoader.background3, 0, -480, gameWidth, 480*2);

		if (AssetLoader.prefs.getInteger("music") == 1) {
			if (musicOnButton.isPressed()) {
				batcher.draw(musicOnButton.getButtonDown(), musicOnButton.getX(), musicOnButton.getY(),
						musicOnButton.getWidth(), musicOnButton.getHeight());
			} else {
				batcher.draw(musicOnButton.getButtonUp(), musicOnButton.getX(), musicOnButton.getY(),
						musicOnButton.getWidth(), musicOnButton.getHeight());
			}
		} else {
			if (musicOffButton.isPressed()) {
				batcher.draw(musicOffButton.getButtonDown(), musicOffButton.getX(), musicOffButton.getY(),
						musicOffButton.getWidth(), musicOffButton.getHeight());
			} else {
				batcher.draw(musicOffButton.getButtonUp(), musicOffButton.getX(), musicOffButton.getY(),
						musicOffButton.getWidth(), musicOffButton.getHeight());
			}
		}

		if (AssetLoader.prefs.getInteger("sound") == 1) {
			if (soundOnButton.isPressed()) {
				batcher.draw(soundOnButton.getButtonDown(), soundOnButton.getX(), soundOnButton.getY(),
						soundOnButton.getWidth(), soundOnButton.getHeight());
			} else {
				batcher.draw(soundOnButton.getButtonUp(), soundOnButton.getX(), soundOnButton.getY(),
						soundOnButton.getWidth(), soundOnButton.getHeight());
			}
		} else {
			if (soundOffButton.isPressed()) {
				batcher.draw(soundOffButton.getButtonDown(), soundOffButton.getX(), soundOffButton.getY(),
						soundOffButton.getWidth(), soundOffButton.getHeight());
			} else {
				batcher.draw(soundOffButton.getButtonUp(), soundOffButton.getX(), soundOffButton.getY(),
						soundOffButton.getWidth(), soundOffButton.getHeight());
			}
		}
		
		if (backButton.isPressed()) {
			batcher.draw(backButton.getButtonDown(), backButton.getX(), backButton.getY(),
					backButton.getWidth(), backButton.getHeight());
		} else {
			batcher.draw(backButton.getButtonUp(), backButton.getX(), backButton.getY(),
					backButton.getWidth(), backButton.getHeight());
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
