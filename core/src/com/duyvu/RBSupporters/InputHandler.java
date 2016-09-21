package com.duyvu.RBSupporters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.duyvu.GameWorld.GameWorld;
import com.duyvu.GameWorld.GameWorld.GameState;
import com.duyvu.Objects.Ball;
import com.duyvu.Objects.PlatformHandler;

public class InputHandler implements InputProcessor {
	private float scaleFactorX;
	private float scaleFactorY;
	private int midPointX;
	private Ball ball;
	private GameWorld world;
	private PlatformHandler platformHandler;

	public InputHandler(GameWorld world, float scaleFactorX, float scaleFactorY) {
		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;
		this.world = world;
		midPointX = world.getMidPointX();
		ball = world.getBall();
		platformHandler = world.getPlatformHandler();
	}

	public int scaleX(int x) {
		return (int) (x / scaleFactorX);
	}

	public int scaleY(int y) {
		return (int) (y / scaleFactorY);
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
		
		if (world.getCurrentState()== GameState.PAUSE){
			world.getMenuButton().isTouchedDown(screenX, screenY);
			world.getContinueButton().isTouchedDown(screenX, screenY);
			return false;
		}
		else if (world.getCurrentState()== GameState.RUNNING){
			if (world.getPauseButton().isTouchedDown(screenX, screenY)) return false;
			if (pointer== 1){
				ball.stop();
				platformHandler.setBoosted(true);
			}
			else if (pointer==0){
				if (screenX<=midPointX) 
					ball.moveLeft();
				else
					ball.moveRight();
			}
			else 
				ball.stop();
		}
		else if (world.getCurrentState()== GameState.GAMEOVER)
			world.setReady();

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		platformHandler.setBoosted(false);
		
		if (world.getCurrentState()== GameState.PAUSE){
			if (world.getMenuButton().isTouchedUp(screenX, screenY)){
				AssetLoader.press.play(AssetLoader.soundVolume);
				world.backToMenu();
			}
			else if (world.getContinueButton().isTouchedUp(screenX, screenY)){
				AssetLoader.press.play(AssetLoader.soundVolume);
				world.setCurrentState(GameState.RUNNING);
			}
			return false;
		}
		else if (world.getCurrentState()== GameState.RUNNING){
			if (world.getPauseButton().isTouchedUp(screenX, screenY)){
				AssetLoader.press.play(AssetLoader.soundVolume);
				world.setCurrentState(GameState.PAUSE);
			}
			
			int avail = -1;
			if (Gdx.input.isTouched(1)) avail = 1;
			else if (Gdx.input.isTouched(0)) avail = 0;
			else ball.stop();
			
			if (avail!=-1){
				if (scaleX(Gdx.input.getX(avail))<=midPointX) ball.moveLeft();
				else ball.moveRight();
			}
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
