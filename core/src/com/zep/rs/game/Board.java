package com.zep.rs.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.zep.rs.handler.PlayScreenInput;
import com.zep.rs.screen.PlayScreen;

public class Board {

	private PlayScreen	ps;
	public float		width;
	public float		height;
	public int			x, y;
	public int			row, col;
	private Square[]	square;

	public Board(PlayScreen ps, float width, float height, int row, int col) {
		super();
		x = (int) (50+width / (row + 2));
		y = (int) (100+height / (col + 2));
		this.ps = ps;
		this.width = width;
		this.height = height;
		this.row = row;
		this.col = col;

		square = new Square[row * col+1];

		for (int i = 0; i < square.length; i++) {
			square[i] = new Square(this, (int) width / (row + 2), (int) height / (col + 2), false);
			square[i].setCoordinateAndColor(i % row, (int) Math.floor(i / col), MathUtils.random(4));
			System.out.println(square[i]);
		}
		
		square[square.length-1].setActive(true);
//		System.out.println(square[square.length-1]);
		Gdx.input.setInputProcessor(new PlayScreenInput(this)); // input processor atandi (tus hareketleri, dokunma, surukleme vs.)
	}

	public void render(float delta) {
		for (int i = 0; i < square.length; i++) {
			square[i].render(delta);
		}
	}

	public void update(float delta) {
	}

	public void moveLeft() {
		for (int i = 0; i < square.length; i++) {
			if (square[i].isActive()) {
				square[i].moveLeft();
			}
		}
	}

	public void moveRight() {
		for (int i = 0; i < square.length; i++) {
			if (square[i].isActive()) {
				square[i].moveRight();
			}
		}
	}

	public void moveUp() {
		for (int i = 0; i < square.length; i++) {
			if (square[i].isActive()) {
				square[i].moveUp();
			}
		}
	}

	public void moveDown() {
		for (int i = 0; i < square.length; i++) {
			if (square[i].isActive()) {
				square[i].moveDown();
			}
		}
	}
	
}
