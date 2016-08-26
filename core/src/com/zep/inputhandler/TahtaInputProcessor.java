package com.zep.inputhandler;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.zep.action.Action;
import com.zep.object.Direction;

/**
 * Created by secelik on 05.08.2016.
 */
public class TahtaInputProcessor implements InputProcessor {

//	private Board board;
	private Action	action;
	int				x0, y0;
	boolean			touched;

//	public BoardInput(Board board) {
//		this.board = board;
//
//	}

	public TahtaInputProcessor(Action action) {
		this.action = action;
	}

	public boolean keyDown(int keycode) {

		switch (keycode) {
			case Input.Keys.LEFT:
				action.moveLeft(true);
				break;
			case Input.Keys.RIGHT:
				action.moveRight(true);
				break;
			case Input.Keys.UP:
				action.moveUp(true);
				break;
			case Input.Keys.DOWN:
				action.moveDown(true);
				break;
			case Input.Keys.NUM_1:
				action.addRow();
				break;
			case Input.Keys.NUM_2:
				action.addColumn();
				break;
			case Input.Keys.PLUS:
				action.nextHistory();
				break;
			case Input.Keys.MINUS:
				action.prevHistory();
				break;
		}

		return false;
	}

//	public void moveRight(boolean checkSame) {
//		for (int i = 0; i < tahta.getKare().length; i++) {
//			for (int j = 0; j < tahta.getKare()[i].length; j++) {
//				if (tahta.getKare()[i][j] != null && tahta.getKare()[i][j].isActive()) {
//					tahta.getKare()[i][j].moveRight(i, j, tahta.getKare()[i][j].color, false);
//					if (checkSame)
//						checkSame(Direction.RIGHT);
//					print();
//					return;
//				}
//			}
//		}
//
//	}

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
		// TODO ilk tiklandiginda aktif karenin ustunde ya da aynı satir/susutunda olma sarti aranmali?
		touched = true;

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touched = false;

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		if (touched) {
			// sag - sol ya da yukari - asagi yonune karar verir
			if (Math.abs(x0 - screenX) > Math.abs(y0 - screenY)) {
				if (x0 - screenX > action.width()) {
					action.drag(Direction.LEFT);
					resetCoordinats(screenX, screenY);
				} else if (Math.abs(x0 - screenX) > action.width()) {
					action.drag(Direction.RIGHT);
					resetCoordinats(screenX, screenY);
				}
			} else {
				if (y0 - screenY > action.height()) {
					action.drag(Direction.UP);
					resetCoordinats(screenX, screenY);
				} else if (Math.abs(y0 - screenY) > action.height()) {
					action.drag(Direction.DOWN);
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
