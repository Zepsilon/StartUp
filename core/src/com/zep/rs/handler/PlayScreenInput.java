package com.zep.rs.handler;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.zep.rs.game.Board;

/**
 * Created by secelik on 05.08.2016.
 */
public class PlayScreenInput implements InputProcessor {

	private Board board;

	public PlayScreenInput(Board board) {
		this.board = board;

	}

	public boolean keyDown(int keycode) {

		switch (keycode) {
		case Input.Keys.LEFT:
			board.moveLeft();
			break;
		case Input.Keys.RIGHT:
			board.moveRight();
			break;
		case Input.Keys.UP:
			board.moveUp();
			break;
		case Input.Keys.DOWN:
			board.moveDown();
			break;
//		case Input.Keys.PLUS:
//			board.nextHistory();
//			break;
//		case Input.Keys.MINUS:
//			board.prevHistory();
//			break;
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		if (pointer == 0) {
			/*
			 * if(screenX< Gdx.graphics.getWidth()/2) {
			 * playState.getGameWorld().getController().setClicked(true);
			 * playState.getGameWorld().getPlayer().setMoveWithDirection(true);
			 * 
			 * }
			 */

		}

		System.out.println(pointer);

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
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

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		System.out.println("ScreenX: " + screenX + ", ScreenY: " + screenY + ", Pointer: " + pointer);
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}