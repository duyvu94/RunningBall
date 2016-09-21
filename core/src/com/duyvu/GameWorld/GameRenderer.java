package com.duyvu.GameWorld;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.duyvu.GameWorld.GameWorld.GameState;
import com.duyvu.Objects.Background;
import com.duyvu.Objects.Ball;
import com.duyvu.Objects.Platform;
import com.duyvu.Objects.PlatformHandler;
import com.duyvu.RBSupporters.AssetLoader;
import com.duyvu.ui.SimpleButton;

public class GameRenderer {

	private GameWorld gameWorld;
	private OrthographicCamera cam;
	private SpriteBatch batcher;

	private Ball ball;
	private PlatformHandler platformHandler;
	private List<Platform> list;
	private int gameWidth;
	private float transparency = 0;
	private boolean loadingTransparency = true;
	private Sprite brick, spike, mainBall, deadBall, black;
	private Background background;

	public GameRenderer(GameWorld gameWorld, int gameWidth) {
		this.gameWorld = gameWorld;
		ball = gameWorld.getBall();
		platformHandler = gameWorld.getPlatformHandler();
		list = platformHandler.getList();
		this.gameWidth = gameWidth;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, gameWidth, 480);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);

		initAssets();
	}

	public void initAssets() {
		background = AssetLoader.background;
		spike = AssetLoader.spike;
		brick = AssetLoader.brick;
		mainBall = AssetLoader.mainBall;
		deadBall = AssetLoader.deadBall;
		black = AssetLoader.black;
	}

	public boolean isLoadingTransparency() {
		return loadingTransparency;
	}

	public void setLoadingTransparency(boolean loadingTransparency) {
		this.loadingTransparency = loadingTransparency;
	}

	public void drawPlatforms() {
		int platformWidth = gameWidth * 3 / 16;
		for (Platform p : list) {
			if (p.isTrap())
				batcher.draw(spike, (int) (p.getCenterX() - platformWidth / 2), (int) (p.getCenterY() - 9),
						platformWidth, 38);
			else
				batcher.draw(brick, (int) (p.getCenterX() - platformWidth / 2), (int) (p.getCenterY() - 9),
						platformWidth, 38);
		}
	}

	public void drawBall() {
		if (ball.isDead())
			batcher.draw(deadBall, (int) (ball.getX() - 30), (int) (ball.getY() - 30), 60, 60);
		else
			batcher.draw(mainBall, (int) (ball.getX() - 30), (int) (ball.getY() - 30), 60, 60);
	}

	public void setReady() {
		setLoadingTransparency(true);
		transparency = 0;
	}

	public void render(float delta, float runTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.begin();

		if (gameWorld.getCurrentState() == GameState.READY) {
			AssetLoader.textFont.getData().setScale(2, -2);
			AssetLoader.dead.stop();
			
			if (transparency==0 || (transparency<1 && transparency+delta>=1)|| (transparency<2 && transparency+delta>=2))
				AssetLoader.crank.play(AssetLoader.soundVolume);
			
			if (transparency<3 && transparency+delta>=3)
				AssetLoader.ringing.play(AssetLoader.soundVolume);

			if (transparency <= 1) {
				batcher.setColor(0, 0, 0, 1);
				batcher.draw(black, 0, 0, gameWidth, 480);
				batcher.setColor(0, 0, 0, transparency);
				AssetLoader.textFont.draw(batcher, "3", gameWidth / 2 - 30, 180);
				batcher.draw(black, 0, 0, gameWidth, 480);
			} else if (transparency > 1 && transparency <= 2) {
				batcher.setColor(0, 0, 0, 1);
				batcher.draw(black, 0, 0, gameWidth, 480);
				batcher.setColor(0, 0, 0, transparency - 1);
				AssetLoader.textFont.draw(batcher, "2", gameWidth / 2 - 30, 180);
				batcher.draw(black, 0, 0, gameWidth, 480);
			} else if (transparency > 2 && transparency <= 3) {
				batcher.setColor(0, 0, 0, 1);
				batcher.draw(black, 0, 0, gameWidth, 480);
				batcher.setColor(0, 0, 0, transparency - 2);
				AssetLoader.textFont.draw(batcher, "1", gameWidth / 2 - 20, 180);
				batcher.draw(black, 0, 0, gameWidth, 480);
			} else {
				batcher.setColor(0, 0, 0, 1);
				batcher.draw(black, 0, 0, gameWidth, 480);
				batcher.setColor(0, 0, 0, transparency - 3);
				AssetLoader.textFont.draw(batcher, "READY", gameWidth / 2 - 200, 180);
				batcher.draw(black, 0, 0, gameWidth, 480);
			}

			transparency += delta;
			if (transparency >= 4)
				setLoadingTransparency(false);
		} else if (gameWorld.getCurrentState() == GameState.RUNNING) {
			batcher.setColor(1, 1, 1, 1);
			background.draw(batcher);
			drawPlatforms();
			drawBall();
			for(int i = 0; i <3; i++){
				batcher.draw(AssetLoader.blackheart, 30+ 44*i, 20, 40, 40);
			}
			for(int i = 0; i <ball.getHeart(); i++){
				batcher.draw(AssetLoader.heart, 30+ 44*i, 20, 40, 40);
			}
			SimpleButton pause = gameWorld.getPauseButton();
			batcher.draw(pause.getButtonUp(), pause.getX(), pause.getY(), pause.getWidth(), pause.getHeight());
			AssetLoader.scoreFont.draw(batcher, "Score: " + gameWorld.getScore(), gameWidth-160, 24);
		}

		else if (gameWorld.getCurrentState() == GameState.PAUSE) {
			batcher.setColor(1, 1, 1, 1);
			background.draw(batcher);
			drawPlatforms();
			drawBall();
			batcher.setColor(1, 1, 1, 0.6f);
			batcher.draw(black, 0, 0, 2000, 1000);

			batcher.setColor(1, 1, 1, 1);
			SimpleButton continueButton = gameWorld.getContinueButton();
			SimpleButton menuButton = gameWorld.getMenuButton();
			if (!continueButton.isPressed()) {
				batcher.draw(AssetLoader.continueButtonUp, continueButton.getX(), continueButton.getY(),
						continueButton.getWidth(), continueButton.getHeight());
			} else
				batcher.draw(AssetLoader.continueButtonDown, continueButton.getX(), continueButton.getY(),
						continueButton.getWidth(), continueButton.getHeight());
			if (!menuButton.isPressed()) {
				batcher.draw(AssetLoader.menuButtonUp, menuButton.getX(), menuButton.getY(), menuButton.getWidth(),
						menuButton.getHeight());
			} else
				batcher.draw(AssetLoader.menuButtonDown, menuButton.getX(), menuButton.getY(), menuButton.getWidth(),
						menuButton.getHeight());

		} else if (gameWorld.getCurrentState() == GameState.GAMEOVER) {
			batcher.setColor(1, 1, 1, 1);
			background.draw(batcher);
			drawPlatforms();
			drawBall();
			batcher.setColor(1, 1, 1, 0.6f);
			batcher.draw(black, 0, 0, 2000, 1000);

			int hc = AssetLoader.getHighScore();
			AssetLoader.textFont.getData().setScale(1f, -1f);
			AssetLoader.textFont.draw(batcher, "HIGH SCORE: " + hc,
					gameWidth / 2 - 240 - (int) Math.log10(hc == 0 ? 1 : hc) * 10, 140);
			AssetLoader.ntFont.draw(batcher, "your score: " + gameWorld.getScore(),
					gameWidth / 2 - 100 - (int) Math.log10(hc == 0 ? 1 : hc) * 10, 240);
			AssetLoader.ntFont.draw(batcher, "Touch to play again!", gameWidth / 2 - 178, 400);

		}

		batcher.end();
	}

}
