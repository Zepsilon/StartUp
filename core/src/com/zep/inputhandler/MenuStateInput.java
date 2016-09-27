package com.zep.inputhandler;

import com.badlogic.gdx.InputProcessor;
import com.zep.sounds.MusicLoader;
import com.zep.states.MenuState;
import com.zep.states.PlayState;

public class MenuStateInput implements InputProcessor {
	private MenuState menuState;

	public MenuStateInput(MenuState menuState) {

		this.menuState = menuState;

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		if (menuState.getButtonNewGame().getButtonRect().contains(screenX, screenY)) {
			menuState.getSm().pushState(new PlayState(menuState.getSm()));
		}
		if (menuState.getButtonSettings().getButtonRect().contains(screenX, screenY)) {
			System.out.println("Settings selected");

		}
		if (menuState.getButtonVolumeOn().getButtonRect().contains(screenX, screenY)) {
			MusicLoader.musicPlay(1, true);
		}
		if (menuState.getButtonVolumeOff().getButtonRect().contains(screenX, screenY)) {
			MusicLoader.musicStop();
		}

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
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
