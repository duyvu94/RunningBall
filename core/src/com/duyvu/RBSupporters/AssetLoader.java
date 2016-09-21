package com.duyvu.RBSupporters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.duyvu.Objects.Background;

public class AssetLoader {
	// public static Texture texture;
	public static Sprite background1, background2, background3, background4, brick, spike, mainBall, deadBall, logo, black, white, gamescreen,
			blackheart, heart;
	public static Sprite leftTouch, rightTouch, doubleTouch;
	public static Sprite startButtonUp, startButtonDown, helpButtonUp, helpButtonDown, pauseButton, continueButtonUp, continueButtonDown,
			menuButtonUp, menuButtonDown, settingButtonUp, settingButtonDown, soundOffButtonUp, soundOffButtonDown,
			soundOnButtonUp, soundOnButtonDown, musicOffButtonUp, musicOffButtonDown, musicOnButtonUp,
			musicOnButtonDown, backButtonUp, backButtonDown;
	public static float soundVolume;
	public static BitmapFont textFont, scoreFont, ntFont, calibriFont;
	public static Preferences prefs;
	public static Sound dead, coll, jumpOut, crank, ringing, pop, press;
	public static Music themes;
	public static TextureAtlas atlas;
	public static Background background;

	public static void setHighScore(int val) {
		prefs.putInteger("highScore", val);
		prefs.flush();
	}

	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}

	public static void load() {
		atlas = new TextureAtlas(Gdx.files.internal("RBPacker.pack"));
		
		background1 = new Sprite(atlas.findRegion("BG"));
		background2 = new Sprite(atlas.findRegion("BG1"));
		background3 = new Sprite(atlas.findRegion("BG2"));
		background4 = new Sprite(atlas.findRegion("BG3"));
		brick = new Sprite(atlas.findRegion("brick"));
		spike = new Sprite(atlas.findRegion("spikeboard"));
		mainBall = new Sprite(atlas.findRegion("ball"));
		deadBall = new Sprite(atlas.findRegion("deadball"));
		black = new Sprite(atlas.findRegion("black"));
		white = new Sprite(atlas.findRegion("white"));
		logo = new Sprite(atlas.findRegion("logo"));
		gamescreen = new Sprite(atlas.findRegion("gamescreen"));
		blackheart = new Sprite(atlas.findRegion("bheart"));
		heart = new Sprite(atlas.findRegion("heart"));
		
		rightTouch = new Sprite(atlas.findRegion("righttouch"));
		leftTouch = new Sprite(atlas.findRegion("lefttouch"));
		doubleTouch = new Sprite(atlas.findRegion("doubletouch"));

		startButtonUp = new Sprite(atlas.findRegion("startbuttonup"));
		startButtonDown = new Sprite(atlas.findRegion("startbuttondown"));
		helpButtonUp = new Sprite(atlas.findRegion("helpbuttonup"));
		helpButtonDown = new Sprite(atlas.findRegion("helpbuttondown"));
		settingButtonUp = new Sprite(atlas.findRegion("settingbuttonup"));
		settingButtonDown = new Sprite(atlas.findRegion("settingbuttondown"));
		pauseButton = new Sprite(atlas.findRegion("pausebutton"));
		continueButtonUp = new Sprite(atlas.findRegion("continueup"));
		continueButtonDown = new Sprite(atlas.findRegion("continuedown"));
		menuButtonUp = new Sprite(atlas.findRegion("menuup"));
		menuButtonDown = new Sprite(atlas.findRegion("menudown"));
		soundOffButtonUp = new Sprite(atlas.findRegion("soundoffup"));
		soundOffButtonDown = new Sprite(atlas.findRegion("soundoffdown"));
		soundOnButtonUp = new Sprite(atlas.findRegion("soundonup"));
		soundOnButtonDown = new Sprite(atlas.findRegion("soundondown"));
		musicOffButtonUp = new Sprite(atlas.findRegion("musicoffup"));
		musicOffButtonDown = new Sprite(atlas.findRegion("musicoffdown"));
		musicOnButtonUp = new Sprite(atlas.findRegion("musiconup"));
		musicOnButtonDown = new Sprite(atlas.findRegion("musicondown"));
		backButtonUp = new Sprite(atlas.findRegion("backup"));
		backButtonDown = new Sprite(atlas.findRegion("backdown"));
		
		background1.flip(false, true);
		background2.flip(false, true);
		background3.flip(false, true);
		background4.flip(false, true);
		brick.flip(false, true);
		spike.flip(false, true);
		mainBall.flip(false, true);
		deadBall.flip(false, true);
		black.flip(false, true);
		white.flip(false, true);
		logo.flip(false, true);
		gamescreen.flip(false, true);
		blackheart.flip(false, true);
		heart.flip(false, true);
		
		leftTouch.flip(false, true);
		rightTouch.flip(false, true);
		doubleTouch.flip(false, true);

		startButtonUp.flip(false, true);
		startButtonDown.flip(false, true);
		settingButtonUp.flip(false, true);
		settingButtonDown.flip(false, true);
		helpButtonUp.flip(false, true);
		helpButtonDown.flip(false, true);
		pauseButton.flip(false, true);
		continueButtonUp.flip(false, true);
		continueButtonDown.flip(false, true);
		menuButtonUp.flip(false, true);
		menuButtonDown.flip(false, true);
		soundOffButtonUp.flip(false, true);
		soundOffButtonDown.flip(false, true);
		soundOnButtonUp.flip(false, true);
		soundOnButtonDown.flip(false, true);
		musicOffButtonUp.flip(false, true);
		musicOffButtonDown.flip(false, true);
		musicOnButtonUp.flip(false, true);
		musicOnButtonDown.flip(false, true);
		backButtonUp.flip(false, true);
		backButtonDown.flip(false, true);
		
		background = new Background();

		calibriFont = new BitmapFont(Gdx.files.internal("calibri.fnt"));
		calibriFont.getData().setScale(1, -1);
		textFont = new BitmapFont(Gdx.files.internal("whitetext.fnt"));
		textFont.getData().setScale(2, -2);
		scoreFont = new BitmapFont(Gdx.files.internal("scorefont.fnt"));
		scoreFont.getData().setScale(1, -1);
		ntFont = new BitmapFont(Gdx.files.internal("infofont.fnt"));
		ntFont.getData().setScale(1, -1);

		themes = Gdx.audio.newMusic(Gdx.files.internal("theme.wav"));
		dead = Gdx.audio.newSound(Gdx.files.internal("die.wav"));
		coll = Gdx.audio.newSound(Gdx.files.internal("hit.wav"));
		jumpOut = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));
		ringing = Gdx.audio.newSound(Gdx.files.internal("ringing.wav"));
		pop = Gdx.audio.newSound(Gdx.files.internal("pop.wav"));
		crank = Gdx.audio.newSound(Gdx.files.internal("crank.wav"));
		press = Gdx.audio.newSound(Gdx.files.internal("press.wav"));

		prefs = Gdx.app.getPreferences("RunningBall");

		if (!prefs.contains("highScore"))
			prefs.putInteger("highScore", 0);
		if (!prefs.contains("music"))
			prefs.putInteger("music", 1);
		if (!prefs.contains("sound"))
			prefs.putInteger("sound", 1);
		prefs.flush();
		
		if (prefs.getInteger("music")==0){
			themes.setVolume(0);
		}
		else {
			themes.setVolume(1);
		}
		if (prefs.getInteger("sound")==0){
			soundVolume =0;
		}
		else{
			soundVolume =1;
		}
	}
}
