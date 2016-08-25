package com.zep.rs;

import com.badlogic.gdx.Game;
import com.zep.rs.image.ButtonLoader;
import com.zep.rs.screen.PlayScreen;

public class RussianSquare extends Game {
	public static final String TITLE = "RussianSquare Game";
	public static final int WIDTH = 90*4;
	public static final int HEIGHT = 160*4;
	
	@Override
	public void create () {
		System.out.println("Starting");

        ButtonLoader.load();
        
        setScreen(new PlayScreen(this));
	}

	
	@Override
	public void dispose () {
		System.out.println("End");
	}
}
