package com.zep.inputhandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.zep.object.Direction;
import com.zep.ui.Tahta;

/**
 * Created by secelik on 05.08.2016.
 */
public class BoardInput implements InputProcessor {

//	private Board board;
	private Tahta	tahta;
	int				x0, y0;
	boolean			touched;

//	public BoardInput(Board board) {
//		this.board = board;
//
//	}

	public BoardInput(Tahta tahta) {
		this.tahta = tahta;
	}

	public boolean keyDown(int keycode) {

		switch (keycode) {
			case Input.Keys.LEFT:
				tahta.moveLeft(true);
				break;
			case Input.Keys.RIGHT:
				tahta.moveRight(true);
				break;
			case Input.Keys.UP:
				tahta.moveUp(true);
				break;
			case Input.Keys.DOWN:
				tahta.moveDown(true);
				break;
			case Input.Keys.NUM_1:
				tahta.addRow();
				break;
			case Input.Keys.NUM_2:
				tahta.addColumn();
				break;
			case Input.Keys.PLUS:
				tahta.nextHistory();
				break;
			case Input.Keys.MINUS:
				tahta.prevHistory();
				break;
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		resetCoordinats(screenX, screenY);
		// TODO ilk tiklandiginda aktif karenin ustunde olma sarti aranmali?
		touched = true;

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		/*
		 * if(pointer==0) {
		 * playState.getGameWorld().getController().setClicked(false);
		 * playState.getGameWorld().getPlayer().setMoveWithDirection(false);
		 * playState.getGameWorld().getController().setEndCord(new
		 * Vector2(2*Gdx.graphics.getWidth()/10,
		 * Gdx.graphics.getHeight()-2*Gdx.graphics.getWidth()/10)
		 * 
		 * );
		 * 
		 * }
		 */
		touched = false;

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		// TODO ilk tiklandiginda aktif karenin ustunde olma sarti aranmali?
		
		if (touched) {
			// sag - sol ya da yukari - asagi yonune karar verir
			if (Math.abs(x0 - screenX) > Math.abs(y0 - screenY)) {
				if (x0 - screenX > tahta.kWidth) {
					tahta.drag(Direction.LEFT);
					resetCoordinats(screenX, screenY);
				} else if (Math.abs(x0 - screenX) > tahta.kWidth) {
					tahta.drag(Direction.RIGHT);
					resetCoordinats(screenX, screenY);
				}
			} else {
				if (y0 - screenY > tahta.kHeight) {
					tahta.drag(Direction.UP);
					resetCoordinats(screenX, screenY);
				} else if (Math.abs(y0 - screenY) > tahta.kHeight) {
					tahta.drag(Direction.DOWN);
					resetCoordinats(screenX, screenY);
				}
			}
		}
		return false;
	}

	private void resetCoordinats(int screenX, int screenY) {
		x0 = screenX;
		y0 = screenY;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
