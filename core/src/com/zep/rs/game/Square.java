package com.zep.rs.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zep.rs.image.ButtonLoader;

public class Square {

	private SpriteBatch			sb;
	private OrthographicCamera	camera;
	private Board				board;
	private int				width, height;
	private int					x, y;
	private int					color;
	private boolean				active;

	public Square(Board board, int width, int height, boolean active) {
		this.board = board;
		this.width = width;
		this.height = height;
		this.active = active;

		sb = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(true);
	}

	public void setCoordinateAndColor(int x, int y, int color) {
//		(int) (i % row * (width / (row + 2))) + x, (int) (Math.floor(i / col) * height / (col + 2) + y)
		this.x = (int) (x * (board.width / (board.row + 2)) + board.x);
		this.y = (int) (y * (board.height / (board.col + 2)) + board.y);
		this.color = color;
	}

	public void render(float delta) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		sb.draw(ButtonLoader.txtrRegBtn[color], x, y, width, height);
		sb.end();
	}

	public void update(float delta) {
	}

	public void moveLeft() {
		x -= (board.width / (board.row + 2));
	}

	public void moveRight() {
		x += (board.width / (board.row + 2));
	}

	public void moveUp() {
		y -= (board.height / (board.col + 2));
	}

	public void moveDown() {
		y += (board.height / (board.col + 2));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Square [x=" + x + ", y=" + y + ", color=" + color + ", active=" + active + ", width=" + width + ", height=" + height + "]";
	}
}
