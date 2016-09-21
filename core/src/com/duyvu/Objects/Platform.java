package com.duyvu.Objects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Platform {
	public static int BOOSTUPSPEED = -160;
	
	private Vector2 position, velocity, boostingVector;
	private boolean boost = false;
	private boolean isTrap ;
	private Rectangle boundingRectangle;
	private int platformWidth;
	private int rasingSpeed;
	
	public Platform(float centerX, float centerY, boolean trap, int platformWidth){
		this.platformWidth = platformWidth;
		position = new Vector2(centerX, centerY);
		velocity = new Vector2(0, 0);
		boundingRectangle = new Rectangle(position.x-platformWidth/2, position.y-16, platformWidth, 4);
		boostingVector = new Vector2(0, BOOSTUPSPEED);
		isTrap = trap;
	}

	public int getRasingSpeed() {
		return rasingSpeed;
	}

	public void setRasingSpeed(int rasingSpeed) {
		this.rasingSpeed = rasingSpeed;
		velocity.y = rasingSpeed;
	}

	public boolean collides(Ball ball){
		return Intersector.overlaps(ball.getBoundingCircle(), boundingRectangle);
	}

	public Rectangle getBoundingRectangle() {
		return boundingRectangle;
	}

	public void setBoundingRectangle(Rectangle boundingRectangle) {
		this.boundingRectangle = boundingRectangle;
	}

	public void update(float delta){
		position.add(velocity.cpy().scl(delta));
		if (isBoosted())
			position.add(boostingVector.cpy().scl(delta));
		boundingRectangle.set(position.x-platformWidth/2, position.y-8, platformWidth, 2);
	}
	
	public void reset(float centerX, float centerY, boolean trap){
		position = new Vector2(centerX, centerY);
		velocity = new Vector2(0, 0);
		isTrap = trap;
		boundingRectangle = new Rectangle(position.x-platformWidth/2, position.y-16, platformWidth, 2);
	}
	
	public float getCenterX() {
		return position.x;
	}
	
	public void setCenterX(float centerX) {
		position.x = centerX;
	}
	
	public float getCenterY() {
		return position.y;
	}
	
	public void setCenterY(float centerY) {
		position.y = centerY;
	}
	
	public boolean isBoosted() {
		return boost;
	}
	
	public void setBoost(boolean boost) {
		this.boost = boost;
	}
	
	public boolean isTrap() {
		return isTrap;
	}
	
	public void setTrap(boolean isTrap) {
		this.isTrap = isTrap;
	}
	
}
