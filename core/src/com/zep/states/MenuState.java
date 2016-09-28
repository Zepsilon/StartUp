package com.zep.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zep.buttons.Button;
import com.zep.images.ImageLoader;
import com.zep.inputhandler.MenuStateInput;
import com.zep.util.Util;

public class MenuState extends State {

	private StateManager		sm;
	private Button				buttonNewGame, buttonSettings, buttonVolumeOn, buttonVolumeOff;
	private Button				buttonNewGameSlc, buttonSettingsSlc, buttonVolumeOnSlc;
	private Button				buttonNewGamePsv, buttonSettingsPsv, buttonVolumeOnPsv;
	private boolean				isSlcNewGame, isSlcSettings, isSlcVolume;

	private static BitmapFont	fontButton;

	private float				buttonX;
	private float				buttonY;

	public MenuState(StateManager sm) {
		super(sm);

		isSlcNewGame = isSlcSettings = isSlcVolume = false; // default seçimler

		this.sm = sm;

		buttonX = Gdx.graphics.getWidth() / 4;
		buttonY = 2 * (Gdx.graphics.getHeight() / 3);

		fontButton = new BitmapFont(true);
		fontButton.setColor(Color.WHITE);

		buttonNewGamePsv = new Button(buttonX - 24, buttonY, ImageLoader.btnNewGame, fontButton, Util.Bundle.getText("title.newGame"));
		buttonSettingsPsv = new Button(buttonX * 2 - 24, buttonY, ImageLoader.btnSettings, fontButton, Util.Bundle.getText("title.settings"));
		buttonVolumeOnPsv = new Button(buttonX * 3 - 24, buttonY, ImageLoader.btnVolumeOn, fontButton, Util.Bundle.getText("title.volume"));

		buttonNewGameSlc = new Button(buttonX - 24, buttonY, ImageLoader.btnNewGameSlc, fontButton, Util.Bundle.getText("title.newGame"));
		buttonSettingsSlc = new Button(buttonX * 2 - 24, buttonY, ImageLoader.btnSettingsSlc, fontButton, Util.Bundle.getText("title.settings"));
		buttonVolumeOnSlc = new Button(buttonX * 3 - 24, buttonY, ImageLoader.btnVolumeOnSlc, fontButton, Util.Bundle.getText("title.volume"));

		buttonNewGame = buttonNewGamePsv;
		buttonSettings = buttonSettingsPsv;
		buttonVolumeOn = buttonVolumeOnPsv;

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
	}

	@Override
	public void update(float delta) {

		buttonNewGame.update(delta);
		buttonSettings.update(delta);
		buttonVolumeOn.update(delta);

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

	public static void dispose() {
		if (fontButton != null)
			fontButton.dispose();
	}

}
