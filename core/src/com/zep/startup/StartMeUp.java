package com.zep.startup;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zep.images.ImageLoader;
import com.zep.screen.PlayScreen;

public class StartMeUp extends Game {
	
	public static final String TITLE = "StartUp";
	public static final int WIDTH = 90*4;
	public static final int HEIGHT = 160*4;
	
	private PlayScreen ps;
	private SpriteBatch sb;
	
	@Override
	public void create () {
		System.out.println("Calisti");
		ImageLoader.load(5);
		sb = new SpriteBatch();
		ps = new PlayScreen(sb);
		
		setScreen(ps);
	}

	@Override
	public void dispose() {
		System.out.println("Kapandi");
	}
}
