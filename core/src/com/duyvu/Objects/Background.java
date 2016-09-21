package com.duyvu.Objects;

import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.duyvu.RBSupporters.AssetLoader;

public class Background {
	private final Integer SCOLLING_SPEED = -4;
	public Queue<Sprite> bgSet = new LinkedList<Sprite>();
	public Sprite bg1, bg2;
	public Vector2 position1, position2, velocity;
	
	public float gameHeight, gameWidth, screenWidth, screenHeight;
	
	public Background(){
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		gameHeight = 480;
		gameWidth = screenWidth / (screenHeight / gameHeight);
		
		position1 = new Vector2();
		position2 = new Vector2();
		velocity = new Vector2(0, SCOLLING_SPEED);
		reset();
	}
	
	public void reset(){
		bgSet.clear();
		bgSet.add(AssetLoader.background3);
		bgSet.add(AssetLoader.background4);
		bg1 = AssetLoader.background1;
		bg2 = AssetLoader.background2;
		position1.x = 0;
		position1.y = 0;
		position2.x = 0;
		position2.y = gameHeight*2;
		
	}
	
	public void draw(SpriteBatch batcher){
		batcher.draw(bg1, position1.x, position1.y, gameWidth, gameHeight*2);
		batcher.draw(bg2, position2.x, position2.y, gameWidth, gameHeight*2);
	}
	
	public void update(float delta){
		
		if ((position1.y>0 && position1.y<gameHeight) || (position2.y>0 && position2.y<gameHeight)){
			velocity.set(0, SCOLLING_SPEED*15);
		}
		else{
			velocity.set(0, SCOLLING_SPEED);
		}
		
		if (position1.y<-gameHeight*2){
			bgSet.add(bg1);
			bg1 = bgSet.poll();
			position1.y = position2.y+gameHeight*2;
		}
		
		if (position2.y<-gameHeight*2){
			bgSet.add(bg2);
			bg2 = bgSet.poll();
			position2.y = position1.y+gameHeight*2;
		}
		
		position1.add(velocity.cpy().scl(delta));
		position2.add(velocity.cpy().scl(delta));
	}
}
