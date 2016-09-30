package com.zep.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Timer;
import com.zep.buttons.Button;
import com.zep.images.ImageLoader;
import com.zep.inputhandler.MenuStateInput;
import com.zep.util.Constant;
import com.zep.util.SkinLoader;
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

		isSlcNewGame = false; // default seçimler

		this.sm = sm;

		buttonX = Gdx.graphics.getWidth() / 4;
		buttonY = Gdx.graphics.getHeight() / 6;

		fontButton = new BitmapFont(true);
		fontButton.setColor(Color.WHITE);

		buttonNewGamePsv = new Button(buttonX * 2 - 24, buttonY * 3, ImageLoader.btnNewGame, fontButton, Util.Bundle.getText("button.newGame"));
		buttonLanguagePsv = new Button(buttonX * 1 - 24, buttonY * 4, ImageLoader.btnLanguage, fontButton, Util.Bundle.getText("button.language"));
		buttonVolumeOnPsv = new Button(buttonX * 3 - 24, buttonY * 4, ImageLoader.btnVolumeOn, fontButton, Util.Bundle.getText("button.volume"));

		buttonNewGameSlc = new Button(buttonX * 2 - 24, buttonY * 3, ImageLoader.btnNewGameSlc, fontButton, Util.Bundle.getText("button.newGame"));
		buttonLanguageSlc = new Button(buttonX * 1 - 24, buttonY * 4, ImageLoader.btnLanguageSlc, fontButton, Util.Bundle.getText("button.language"));
		buttonVolumeOnSlc = new Button(buttonX * 3 - 24, buttonY * 4, ImageLoader.btnVolumeOnSlc, fontButton, Util.Bundle.getText("button.volume"));

		buttonNewGame = buttonNewGamePsv;
		buttonLanguage = buttonLanguagePsv;
		buttonVolumeOn = buttonVolumeOnPsv;

		setSlcLanguage(!(Boolean) "en_EN".equals(Util.Prefs.getValue(Constant.PREF_LANG, "en_EN")));
		setSlcVolume(!(Boolean) Util.Prefs.getValue(Constant.PREF_VOLUME, true));

		Gdx.input.setInputProcessor(new MenuStateInput(this));

		/**/
//		final com.badlogic.gdx.scenes.scene2d.ui.Button button = new com.badlogic.gdx.scenes.scene2d.ui.Button(SkinLoader.skin, "music");
		final SelectBox<String> button = new SelectBox<String>(SkinLoader.skin);
		button.setItems(Util.Bundle.getText("title.lang.english"), Util.Bundle.getText("title.lang.turkish"));
		if ("tr_TR".equals(Util.Prefs.getValue(Constant.PREF_LANG, "en_EN"))) {
			button.setSelectedIndex(1);
		}

//		final TextButton button = new TextButton("Click Me",SkinLoader.skin,"default");
		button.setWidth(200);
//	      button.setHeight(50);

//	      final Dialog dialog = new Dialog("Click Message",SkinLoader.skin);
//
		button.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
//	            dialog.show(SkinLoader.stage);
				System.out.println("Selected: " + button.getSelectedIndex() + " - " + button.getSelected());
				if (button.getSelectedIndex() == 0) { // ilk secenek English
					Util.Prefs.putValue(Constant.PREF_LANG, "en_EN");
				} else {
					Util.Prefs.putValue(Constant.PREF_LANG, "tr_TR");
				}
				
				Util.load();
				getSm().pushState(new MenuState(getSm()));
			}
		});

		SkinLoader.stage.addActor(button);

		Gdx.input.setInputProcessor(SkinLoader.stage);
		/**/
	}

	@Override
	public void render(SpriteBatch sb) {

//		//butonlar cizdirilcek.
		sb.setProjectionMatrix(camera.combined);
		sb.begin();

		sb.draw(ImageLoader.txtrRegBkg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		fontButton.draw(sb, Util.Bundle.getText("title.highScore") + Util.Prefs.getValue(Constant.PREF_HIGH_SCORE, 0), buttonX * 4 / 3, buttonY * 5);

		sb.end();
		SkinLoader.stage.act(Gdx.graphics.getDeltaTime());
		SkinLoader.stage.draw();

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

	public boolean isSlcLanguage() {
		return isSlcLanguage;
	}

	public void setSlcLanguage(boolean isSlcLanguage) {
		this.isSlcLanguage = isSlcLanguage;
		buttonLanguage = isSlcLanguage ? buttonLanguageSlc : buttonLanguagePsv;
	}

	public void switchSlcLanguage() {
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
