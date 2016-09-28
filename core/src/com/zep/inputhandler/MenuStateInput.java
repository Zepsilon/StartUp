package com.zep.inputhandler;

import com.badlogic.gdx.InputProcessor;
import com.zep.sounds.MusicLoader;
import com.zep.states.MenuState;
import com.zep.states.PlayState;
import com.zep.util.Constant;
import com.zep.util.Util;

public class MenuStateInput implements InputProcessor {
	private MenuState	menuState;
	private boolean		slcNewGame, slcSettings, isVolumeOn;
	private boolean		justSelected;

	public MenuStateInput(MenuState menuState) {

		slcNewGame = slcSettings = true;
		isVolumeOn = (Boolean) Util.Prefs.getValue(Constant.PREF_VOLUME, true);
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
			slcNewGame = !slcNewGame;
			menuState.getSm().pushState(new PlayState(menuState.getSm()));
			justSelected = true;
			System.out.println("NewGame selected " + slcNewGame);
		}
		if (menuState.getButtonSettings().getButtonRect().contains(screenX, screenY)) {
			slcSettings = !slcSettings;
			justSelected = true;
			System.out.println("Settings selected " + slcSettings);

		}
		if (menuState.getButtonVolumeOn().getButtonRect().contains(screenX, screenY)) {
			isVolumeOn = !isVolumeOn;
			justSelected = true;
			System.out.println("Volume selected " + isVolumeOn);
			if (isVolumeOn) {
				MusicLoader.load();
			} else {
				MusicLoader.musicStop();
				MusicLoader.dispose();
			}
			
			Util.Prefs.putValue(Constant.PREF_VOLUME, isVolumeOn);
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

		if (menuState.getButtonNewGame().getButtonRect().contains(screenX, screenY) && !justSelected) {
			menuState.setSlcNewGame(slcNewGame);
		} else {
			menuState.setSlcNewGame(!slcNewGame);
		}

		if (menuState.getButtonSettings().getButtonRect().contains(screenX, screenY) && !justSelected) {
			menuState.setSlcSettings(slcSettings);
		} else {
			menuState.setSlcSettings(!slcSettings);
		}

		if (menuState.getButtonVolumeOn().getButtonRect().contains(screenX, screenY) && !justSelected) {
			menuState.setSlcVolume(isVolumeOn);
		} else {
			menuState.setSlcVolume(!isVolumeOn);
		}

		if (!menuState.getButtonNewGame().getButtonRect().contains(screenX, screenY)
				&& !menuState.getButtonSettings().getButtonRect().contains(screenX, screenY)
				&& !menuState.getButtonVolumeOn().getButtonRect().contains(screenX, screenY)) {
			justSelected = false;
		}

		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
