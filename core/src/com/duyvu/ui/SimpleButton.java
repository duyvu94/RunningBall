package com.duyvu.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class SimpleButton {
	private float x, y, width, height;
	
	private TextureRegion buttonUp;
	private TextureRegion buttonDown;
	
	private Rectangle bounds;
	
	private boolean isPressed = false;
	
	public SimpleButton(float x, float y, float width, float height, TextureRegion buttonUp, TextureRegion buttonDown){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.buttonUp = buttonUp;
		this.buttonDown = buttonDown;
		
		bounds = new Rectangle(x, y, width, height);
	}
	
	public boolean isTouched(int screenX, int screenY){
		return bounds.contains(screenX, screenY);
	}

	public boolean isTouchedDown(int screenX, int screenY){
		
		if (bounds.contains(screenX, screenY)){
			isPressed = true;
			return true;
		}
		return false;
	}
	
	public boolean isTouchedUp(int screenX, int screenY){
		if (bounds.contains(screenX, screenY) && isPressed){
			isPressed = false;
			return true;
		}
		
		isPressed = false;
		return false;
	}

	public boolean isPressed() {
		return isPressed;
	}

	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public TextureRegion getButtonUp() {
		return buttonUp;
	}

	public void setButtonUp(TextureRegion buttonUp) {
		this.buttonUp = buttonUp;
	}

	public TextureRegion getButtonDown() {
		return buttonDown;
	}

	public void setButtonDown(TextureRegion buttonDown) {
		this.buttonDown = buttonDown;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
}
