package com.zep.inputhandler;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.zep.ui.Board;
import com.zep.ui.Tahta;

/**
 * Created by secelik on 05.08.2016.
 */
public class BoardInput implements InputProcessor {

	private Board board;
	private Tahta tahta;

	public BoardInput(Board board) {
		this.board = board;

	}

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

//		if (pointer == 0) {
			tahta.clicked();
//			System.out.println("x:" + screenX + ", y:" + screenY);
//		}


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

//		System.out.println("ScreenX: " + screenX + ", ScreenY: " + screenY + ", Pointer: " + pointer);
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
