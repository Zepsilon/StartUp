package com.zep.rs.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.zep.rs.RussianSquare;
import com.zep.rs.game.Board;

public class PlayScreen implements Screen {

	private RussianSquare		rs;
	private Board				board;
	
	private ShapeRenderer	sr;
	private OrthographicCamera	camera;

	
	public PlayScreen(RussianSquare rs) {
		this.rs = rs;
		board = new Board(this, 50 * 4, 50 * 4, 3, 3);
		
		sr = new ShapeRenderer();

		camera = new OrthographicCamera();
		camera.setToOrtho(true);
	}

	@Override
	public void show() {

		sr.setProjectionMatrix(camera.combined);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);


		sr.begin(ShapeType.Line);
		sr.setColor(Color.RED);
		sr.rect(50, 100, board.width, board.height);
		sr.end();
	
		board.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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
		rs.dispose();
	}

}
