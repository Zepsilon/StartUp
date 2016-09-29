package com.zep.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zep.buttons.Button;
import com.zep.images.ImageLoader;
import com.zep.inputhandler.MenuStateInput;
import com.zep.util.Constant;
import com.zep.util.Util;

public class MenuState extends State {

	private StateManager		sm;
	private Button				buttonNewGame, buttonLanguage, buttonVolumeOn, buttonVolumeOff;
	private Button				buttonNewGameSlc, buttonLanguageSlc, buttonVolumeOnSlc;
	private Button				buttonNewGamePsv, buttonLanguagePsv, buttonVolumeOnPsv;
	private boolean				isSlcNewGame, isSlcLanguage, isSlcVolume;

	private static BitmapFont	fontButton;

	private float				buttonX;
	private float				buttonY;

	public MenuState(StateManager sm) {
		super(sm);

		isSlcNewGame = isSlcLanguage = false; // default seçimler

		this.sm = sm;

		buttonX = Gdx.graphics.getWidth() / 4;
		buttonY = Gdx.graphics.getHeight() / 6;

		fontButton = new BitmapFont(true);
		fontButton.setColor(Color.WHITE);

		buttonNewGamePsv = new Button(buttonX * 2 - 24, buttonY * 3, ImageLoader.btnNewGame, fontButton, Util.Bundle.getText("title.newGame"));
		buttonLanguagePsv = new Button(buttonX * 1 - 24, buttonY * 4, ImageLoader.btnLanguage, fontButton, Util.Bundle.getText("title.language"));
		buttonVolumeOnPsv = new Button(buttonX * 3 - 24, buttonY * 4, ImageLoader.btnVolumeOn, fontButton, Util.Bundle.getText("title.volume"));

		buttonNewGameSlc = new Button(buttonX * 2 - 24, buttonY * 3, ImageLoader.btnNewGameSlc, fontButton, Util.Bundle.getText("title.newGame"));
		buttonLanguageSlc = new Button(buttonX * 1 - 24, buttonY * 4, ImageLoader.btnLanguageSlc, fontButton, Util.Bundle.getText("title.language"));
		buttonVolumeOnSlc = new Button(buttonX * 3 - 24, buttonY * 4, ImageLoader.btnVolumeOnSlc, fontButton, Util.Bundle.getText("title.volume"));

		buttonNewGame = buttonNewGamePsv;
		buttonLanguage = buttonLanguagePsv;
		buttonVolumeOn = buttonVolumeOnPsv;
		
		setSlcVolume(!(Boolean) Util.Prefs.getValue(Constant.PREF_VOLUME, true));

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
		buttonLanguage.render(sb);
		buttonVolumeOn.render(sb);
	}

	@Override
	public void update(float delta) {

		buttonNewGame.update(delta);
		buttonLanguage.update(delta);
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

	public Button getButtonLanguage() {
		return buttonLanguage;
	}

	public void setButtonSettings(Button buttonSettings) {
		this.buttonLanguage = buttonSettings;
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

	public boolean isSlclanguage() {
		return isSlcLanguage;
	}

	public void setSlcLanguage(boolean isSlclanguage) {
		this.isSlcLanguage = isSlclanguage;
		buttonLanguage = isSlclanguage ? buttonLanguageSlc : buttonLanguagePsv;
	}

	public void switchSlclanguage() {
		this.isSlcLanguage = !isSlcLanguage;
		setSlcLanguage(isSlcLanguage);
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
