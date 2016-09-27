package com.zep.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zep.buttons.Button;
import com.zep.images.ImageLoader;
import com.zep.inputhandler.MenuStateInput;

public class MenuState extends State {

	private StateManager	sm;
	private Button			buttonNewGame, buttonSettings, buttonVolumeOn, buttonVolumeOff;
	private Button			buttonNewGameSlc, buttonSettingsSlc, buttonVolumeOnSlc, buttonVolumeOffSlc;
	private Button			buttonNewGamePsv, buttonSettingsPsv, buttonVolumeOnPsv, buttonVolumeOffPsv;
	private boolean			isSlcNewGame, isSlcSettings, isSlcVolume;

	private float			buttonX;
	private float			buttonY;

	public MenuState(StateManager sm) {
		super(sm);

		isSlcNewGame = isSlcSettings = isSlcVolume = false;

		this.sm = sm;
		buttonX = Gdx.graphics.getWidth() / 6;
		buttonY = Gdx.graphics.getHeight() / 2;
		buttonNewGamePsv = new Button(buttonX, buttonY, ImageLoader.btnNewGame);
		buttonSettingsPsv = new Button(buttonX * 2, buttonY, ImageLoader.btnSettings);
		buttonVolumeOnPsv = new Button(buttonX * 3, buttonY, ImageLoader.btnVolumeOn);
		buttonVolumeOffPsv = new Button(buttonX * 4, buttonY, ImageLoader.btnVolumeOff);

		buttonNewGameSlc = new Button(buttonX, buttonY, ImageLoader.btnNewGameSlc);
		buttonSettingsSlc = new Button(buttonX * 2, buttonY, ImageLoader.btnSettingsSlc);
		buttonVolumeOnSlc = new Button(buttonX * 3, buttonY, ImageLoader.btnVolumeOnSlc);
		buttonVolumeOffSlc = new Button(buttonX * 4, buttonY, ImageLoader.btnVolumeOffSlc);

		buttonNewGame = buttonNewGamePsv;
		buttonSettings = buttonSettingsPsv;
		buttonVolumeOn = buttonVolumeOnPsv;
		buttonVolumeOff = buttonVolumeOffPsv;

		Gdx.input.setInputProcessor(new MenuStateInput(this));
	}

	@Override
	public void render(SpriteBatch sb) {

//		//butonlar cizdirilcek.
		sb.setProjectionMatrix(camera.combined);
		sb.begin();

		sb.draw(ImageLoader.txtrRegBkg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		sb.end();

		buttonNewGame.render(sb);
		buttonSettings.render(sb);
		buttonVolumeOn.render(sb);
		buttonVolumeOff.render(sb);
	}

	@Override
	public void update(float delta) {

		buttonNewGame.update(delta);
		buttonSettings.update(delta);
		buttonVolumeOn.update(delta);
		buttonVolumeOff.update(delta);

	}

	@Override
	public void handleInput() {

	}

	public StateManager getSm() {
		return sm;
	}

	public Button getButtonNewGame() {
		return buttonNewGame;
	}

	public void setButtonNewGame(Button buttonNewGame) {
		this.buttonNewGame = buttonNewGame;
	}

	public Button getButtonSettings() {
		return buttonSettings;
	}

	public void setButtonSettings(Button buttonSettings) {
		this.buttonSettings = buttonSettings;
	}

	public Button getButtonVolumeOn() {
		return buttonVolumeOn;
	}

	public void setButtonVolumeOn(Button buttonVolumeOn) {
		this.buttonVolumeOn = buttonVolumeOn;
	}

	public Button getButtonVolumeOff() {
		return buttonVolumeOff;
	}

	public void setButtonVolumeOff(Button buttonVolumeOff) {
		this.buttonVolumeOff = buttonVolumeOff;
	}

	public boolean isSlcNewGame() {
		return isSlcNewGame;
	}

	public void setSlcNewGame(boolean isSlcNewGame) {
		this.isSlcNewGame = isSlcNewGame;
		buttonNewGame = isSlcNewGame ? buttonNewGameSlc : buttonNewGamePsv;
	}

	public void switchSlcNewGame() {
		this.isSlcNewGame = !isSlcNewGame;
		setSlcNewGame(isSlcNewGame);
	}

	public boolean isSlcSettings() {
		return isSlcSettings;
	}

	public void setSlcSettings(boolean isSlcSettings) {
		this.isSlcSettings = isSlcSettings;
		buttonSettings = isSlcSettings ? buttonSettingsSlc : buttonSettingsPsv;
	}

	public void switchSlcSettings() {
		this.isSlcSettings = !isSlcSettings;
		setSlcSettings(isSlcSettings);
	}

	public boolean isSlcVolume() {
		return isSlcVolume;
	}

	public void setSlcVolume(boolean isSlcVolume) {
		this.isSlcVolume = isSlcVolume;
		buttonVolumeOn = isSlcVolume ? buttonVolumeOnSlc : buttonVolumeOnPsv;
	}

	public void switchSlcVolume() {
		this.isSlcVolume = !isSlcVolume;
		setSlcVolume(isSlcVolume);
	}

}
