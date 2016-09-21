package com.duyvu.GameWorld;

import com.badlogic.gdx.Game;
import com.duyvu.Objects.Background;
import com.duyvu.Objects.Ball;
import com.duyvu.Objects.PlatformHandler;
import com.duyvu.RBSupporters.AssetLoader;
import com.duyvu.Screens.MainScreen;
import com.duyvu.ui.SimpleButton;

public class GameWorld {
	
	private Game myGame;
	private MainScreen mainScreen;
	private Ball ball;
	private PlatformHandler platformHandler;
	private Background background;
	private int score = 0;
	private GameRenderer renderer;
	private SimpleButton pauseButton, menuButton, continueButton;
	private int midPointX;
	
	private GameState currentState;
	
	public enum GameState {
		READY, RUNNING, PAUSE, GAMEOVER
	}
	
	public GameWorld(Game myGame, float width, float height){
		ball = new Ball((int)width, (int) height);
		platformHandler = new PlatformHandler(this, width);
		midPointX = (int) width/2;
		currentState = GameState.READY;
		score = 0;
		this.myGame = myGame;
		
		background = AssetLoader.background;
		
		pauseButton = new SimpleButton(width/2-30, 10, 60, 60, AssetLoader.pauseButton, AssetLoader.pauseButton);
		menuButton = new SimpleButton(width/2-90, 260, 180, 32, AssetLoader.menuButtonUp, AssetLoader.menuButtonDown);
		continueButton = new SimpleButton(width/2-90, 160, 180, 32, AssetLoader.continueButtonUp, AssetLoader.continueButtonDown);
	}
	
	public void backToMenu(){
		setReady();
		myGame.setScreen(mainScreen);
		mainScreen.setInputPriority();
	}
	
	public MainScreen getMainScreen() {
		return mainScreen;
	}

	public void setMainScreen(MainScreen mainScreen) {
		this.mainScreen = mainScreen;
	}

	public SimpleButton getMenuButton() {
		return menuButton;
	}

	public void setMenuButton(SimpleButton menuButton) {
		this.menuButton = menuButton;
	}

	public SimpleButton getContinueButton() {
		return continueButton;
	}

	public void setContinueButton(SimpleButton continueButton) {
		this.continueButton = continueButton;
	}

	public SimpleButton getPauseButton() {
		return pauseButton;
	}

	public void setPauseButton(SimpleButton pauseButton) {
		this.pauseButton = pauseButton;
	}
	
	public void addScore(int s){
		score+= s;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setRenderer(GameRenderer gameRenderer){
		renderer = gameRenderer;
	}
	
	public int getMidPointX() {
		return midPointX;
	}
	
	public GameState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(GameState currentState) {
		this.currentState = currentState;
	}

	public void setMidPointY(int midPointX) {
		this.midPointX = midPointX;
	}
	
	public void setReady(){
		currentState = GameState.READY;
		ball.reset();
		platformHandler.reset();
		renderer.setReady();
		background.reset();
		score = 0;
	}
	
	public void update(float delta){
		
		if (currentState== GameState.READY){
			if (!renderer.isLoadingTransparency())
				currentState= GameState.RUNNING;
		}
		else if (currentState== GameState.RUNNING){
			platformHandler.update(delta);
			ball.update(delta);
			background.update(delta);
			if (ball.isDead() && ball.getHeart()>0) {
				int h = ball.getHeart();
				ball.reset();
				platformHandler.reset();
				ball.setHeart(h);
				
			}
			else if (ball.isDead() && ball.getHeart()==0){
				currentState = GameState.GAMEOVER;
			}
		}
		else if (currentState== GameState.PAUSE){
			
		}
		else if (currentState== GameState.GAMEOVER){
			if (AssetLoader.getHighScore()< score)
				AssetLoader.setHighScore(score);
		}
		
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public PlatformHandler getPlatformHandler() {
		return platformHandler;
	}

	public void setPlatformHandler(PlatformHandler platformHandler) {
		this.platformHandler = platformHandler;
	}
	
}
