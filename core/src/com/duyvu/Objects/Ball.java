package com.duyvu.Objects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.duyvu.RBSupporters.AssetLoader;

public class Ball {
	private final int FALLINGSPEED = 90;
	private final int MOVINGSPEED = 500;
	
	private Vector2 position;
	private Vector2 velocity;
	private int width, height;
	
	private boolean moveLeft = false;
	private boolean moveRight = false;
	
	private boolean isDead = false;
	private boolean isFalling = true;
	private int heart = 3;
	private Circle boundingCircle;
	
	public Ball(int width, int height){
		this.width = width;
		this.height = height;
		boundingCircle = new Circle();
		position = new Vector2();
		velocity = new Vector2();
		reset();
		boundingCircle.set(position.x, position.y+14, 1);
	}
	
	public Circle getBoundingCircle() {
		return boundingCircle;
	}

	public void setBoundingCircle(Circle boundingCircle) {
		this.boundingCircle = boundingCircle;
	}

	public void update(float delta){
		if (isMoveLeft() && position.x<=30) stop();
		if (isMoveRight() && position.x>= width-30) stop();
		position.add(velocity.cpy().scl(delta));
		boundingCircle.set(position.x, position.y+29, 2);
		if (position.y <= 30 || position.y>=height-30) dies();
	}
	
	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}

	public void stop(){
		velocity.x = 0;
		setMoveLeft(false);
		setMoveRight(false);
	}
	
	public void moveLeft(){
		stop();
		if (position.x<= 30)
			return;
		setMoveLeft(true);
		velocity.x = -MOVINGSPEED;
	}
	
	public void moveRight(){
		stop();
		if (position.x>= width- 30) return;
		setMoveRight(true);
		velocity.x = MOVINGSPEED;
	}
	
	public float getX(){
		return position.x;
	}
	
	public float getY(){
		return position.y;
	}
	
	public void setX(float X){
		position.x = X;
		boundingCircle.set(position.x, position.y+14, 1);
	}
	
	public void setY(float Y){
		position.y = Y;
		boundingCircle.set(position.x, position.y+14, 1);
	}
	
	public boolean isMoveLeft() {
		return moveLeft;
	}

	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}

	public boolean isMoveRight() {
		return moveRight;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}

	public boolean isDead(){
		return isDead;
	}
	
	public void dies(){
		heart--;
		isDead = true;
		AssetLoader.pop.play(AssetLoader.soundVolume);
		if (heart==0) AssetLoader.dead.play(AssetLoader.soundVolume);
		
		velocity.x = 0;
		velocity.y = 0;
	}
	
	public boolean isFalling() {
		return isFalling;
	}
	
	public void rasing(int speed){
		setFalling(false);
		velocity.y = speed;
	}

	public void setFalling(boolean isFalling) {
		if (isFalling) velocity.y = FALLINGSPEED;
		this.isFalling = isFalling;
	}

	public void reset(){
		isDead = false;
		isFalling = true;
		heart = 3;
		position.x = width*3/32;
		position.y = 40;
		velocity.x = 0;
		velocity.y = FALLINGSPEED;
	}
}
