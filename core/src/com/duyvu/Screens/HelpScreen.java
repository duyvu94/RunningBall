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

public class HelpScreen implements Screen, InputProcessor {

	private SimpleButton backButton;
	private float screenWidth, screenHeight, gameHeight, gameWidth;
	private Game myGame;
	private OrthographicCamera cam;
	private SpriteBatch batcher;
	private float scaleFactorX;
	private float scaleFactorY;
	private MainScreen mainScreen;
	private BitmapFont font;

	public HelpScreen(Game myGame) {
		this.myGame = myGame;
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		gameHeight = 480;
		gameWidth = screenWidth / (screenHeight / gameHeight);
		
		font = AssetLoader.calibriFont;

		scaleFactorX = screenWidth / gameWidth;
		scaleFactorY = screenHeight / gameHeight;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, gameWidth, 480);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);

		backButton = new SimpleButton(gameWidth/2-90, 400, 180, 40, AssetLoader.backButtonUp, AssetLoader.backButtonDown);
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
	
		backButton.isTouchedDown(screenX, screenY);
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		
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

		batcher.draw(AssetLoader.background4, 0, -480, gameWidth, 480*2);
		
		batcher.draw(AssetLoader.leftTouch, gameWidth/12, gameHeight/14, gameWidth/3, gameHeight/4);
		batcher.draw(AssetLoader.rightTouch, gameWidth/14 + gameWidth/2, gameHeight/14, gameWidth/3, gameHeight/4);
		batcher.draw(AssetLoader.doubleTouch, gameWidth/12, gameHeight/2- gameHeight/14, gameWidth/3, gameHeight/4);
		
		font.getData().setScale(0.8f, -0.8f);
		font.draw(batcher, "Touch the left side to move left", gameWidth/14, gameHeight/2-gameHeight/6);
		font.draw(batcher, "Touch the right side to move right", gameWidth/2 + gameWidth/20, gameHeight/2-gameHeight/6);
		font.draw(batcher, "Touch both sides to accelerate", gameWidth/14, gameHeight/4*3);
		
		font.draw(batcher, "Try to survive as long as possible.", gameWidth/2 + gameWidth/20, gameHeight/2+ gameHeight/10);
		font.draw(batcher, "Hope you enjoy my game!", gameWidth/2 + gameWidth/10, gameHeight/2+ gameHeight/6);
		
		
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
