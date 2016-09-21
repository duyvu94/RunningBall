package com.duyvu.rb;

import com.badlogic.gdx.Game;
import com.duyvu.RBSupporters.*;
import com.duyvu.Screens.*;

public class RunningBall extends Game {
	
	@Override
	public void create () {
		AssetLoader.load();
		GameScreen gameScreen = new GameScreen(this);
		MainScreen mainScreen = new MainScreen(this);
		SplashScreen splashScreen = new SplashScreen(this);
		
		splashScreen.setMainScreen(mainScreen);
		mainScreen.setGameScreen(gameScreen);
		gameScreen.setMainScreen(mainScreen);
		
		setScreen(splashScreen);
	}

	@Override
	public void dispose () {
		super.dispose();
		//AssetLoader.d
	}
}
