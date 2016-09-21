package com.duyvu.Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.duyvu.GameWorld.GameWorld;
import com.duyvu.RBSupporters.AssetLoader;

public class PlatformHandler {

	public final int RASINGSPEED = -30;

	private GameWorld gameWorld;
	private Ball ball;
	private List<Platform> list = new ArrayList<Platform>();
	private float width;
	private int level;
	private int prePoint= -1;
	private boolean isJumping= false;
	private boolean isBoosted;

	public List<Platform> getList() {
		return list;
	}

	public void setList(List<Platform> list) {
		this.list = list;
	}

	public boolean isBoosted() {
		return isBoosted;
	}

	public void setBoosted(boolean isBoosted) {
		this.isBoosted = isBoosted;
	}

	public PlatformHandler(GameWorld world, float width) {
		this.gameWorld = world;
		this.ball = world.getBall();
		this.width = width;
		Random r = new Random();
		int platformWidth = (int) width * 3 / 16;
		list.add(new Platform(platformWidth/2+5, 360, false, (int) platformWidth));
		for (int i = 1; i <= 9; i++) {
			list.add(new Platform(r.nextInt((int) width - platformWidth) + platformWidth / 2,
					list.get(i - 1).getCenterY() + 100, r.nextInt(100)<=20, (int) platformWidth));
		}
	}
	
	public void update(float delta) {
		int platformWidth = (int) width * 3 / 16;
		Random r = new Random();
		boolean collision = false;
		if (ball.isDead()) return;
		level = gameWorld.getScore();
			
		for (Platform p : list) {
			p.setBoost(isBoosted);
			p.setRasingSpeed(RASINGSPEED- level);
			p.update(delta);
			if (p.collides(ball)) {
				isJumping = true;
				if (p.isTrap()){
					ball.dies();
					return;
				}
				if (prePoint != list.indexOf(p)){
					if (prePoint==-1) prePoint = 0;
					gameWorld.addScore((list.indexOf(p)- prePoint+10) %10);
					prePoint = list.indexOf(p);
					AssetLoader.coll.play(AssetLoader.soundVolume);
				}
				ball.setY(p.getCenterY()-39);
				ball.rasing(0);
				
				
				collision = true;
			}
			int nex = (list.indexOf(p) + 9) % 10;
			if (p.getCenterY() < -100) {
				p.setCenterY(list.get(nex).getCenterY() + 100);
				p.setCenterX(r.nextInt((int) width - platformWidth) + platformWidth / 2);
				p.setTrap(r.nextInt(100)<=20);
			}
		}
		
		if (!collision && isJumping) {
			isJumping = false;
			AssetLoader.jumpOut.play(AssetLoader.soundVolume);
		}
		
		if (!collision){
			ball.setFalling(true);
		}
	}

	public void reset() {
		level = 0;
		prePoint = -1;
		isJumping = false;
		int platformWidth = (int) width * 3 / 16;
		Random r = new Random();
		list.get(0).reset(platformWidth/2+5, 360, false);
		for (int i = 1; i <= 9; i++) {
			list.get(i).reset(r.nextInt((int) width - platformWidth) + platformWidth / 2,
					list.get(i - 1).getCenterY() + 100, r.nextInt(100)<=20);
		}
	}

}
