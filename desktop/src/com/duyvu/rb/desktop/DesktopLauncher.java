package com.duyvu.rb.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.duyvu.rb.RunningBall;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "RunningBall";
		config.useGL30 = false;
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new RunningBall(), config);
	}
}
