package com.zep.startup.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zep.startup.KareTahta;

public class KareDesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = KareTahta.TITLE;
		config.width = KareTahta.WIDTH;
		config.height = KareTahta.HEIGHT;
		
		new LwjglApplication(new KareTahta(), config);
	}
}
