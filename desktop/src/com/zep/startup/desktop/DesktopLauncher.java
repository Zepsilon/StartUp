package com.zep.startup.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zep.startup.StartMeUp;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = StartMeUp.TITLE;
		config.width = StartMeUp.WIDTH;
		config.height = StartMeUp.HEIGHT;
		
		new LwjglApplication(new StartMeUp(), config);
	}
}
