package com.zep.startup;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zep.images.ImageLoader;
import com.zep.states.PlayState;
import com.zep.states.StateManager;

public class KareTahta extends Game {
	
	public static final String TITLE = "Square";
	public static final int WIDTH = 90*4;
	public static final int HEIGHT = 160*4;

    private SpriteBatch sb;

    private static StateManager sm; // oyun icinde tum bolumler buradan yonetiliyor


    @Override
    public void create() {

        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);

		ImageLoader.load(5);

        sb = new SpriteBatch();
        sm = new StateManager();

        sm.pushState(new PlayState(sm));
        System.out.println("Yaratildi..");
    }

    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sm.render(sb);
        sm.update(Gdx.graphics.getDeltaTime()); //bir iki frame arasinda gecen sureyi verir

    }


    public void dispose() {
        sb.dispose();
        ImageLoader.dispose();
        System.out.println("Yok edildi...");

    }
}
