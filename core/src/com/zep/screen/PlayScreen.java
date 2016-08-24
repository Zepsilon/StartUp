package com.zep.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zep.ui.Board;
import com.zep.ui.Tahta;

public class PlayScreen implements Screen {

	private Board		board;
	private Tahta	tahta;
	private SpriteBatch	sb;

	public PlayScreen(SpriteBatch sb) {
		this.sb = sb;

//		board = new Board(50 * 4, 50 * 4, 6, 6); // width, height, x, y
		tahta = new Tahta(50 * 4, 50 * 4, 6, 6);
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		update(delta);

//		board.render(sb, delta);
		tahta.render(sb, delta);
	}

	public void update(float delta) {
//		board.update(sb, delta);
		tahta.update(sb, delta);
	}

	@Override
	public void resize(int width, int height) {
//		System.out.println("Resizing");
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		System.out.println("Kapandi");
	}

}