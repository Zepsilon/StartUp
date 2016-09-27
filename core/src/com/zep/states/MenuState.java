package com.zep.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zep.buttons.Button;
import com.zep.images.ImageLoader;
import com.zep.inputhandler.MenuStateInput;

public class MenuState extends State {

	private StateManager	sm;
	private Button			buttonNewGame, buttonSettings, buttonVolumeOn, buttonVolumeOff;

	private float			buttonX;
	private float			buttonY;

	public MenuState(StateManager sm) {
		super(sm);

		this.sm = sm;
		buttonX = Gdx.graphics.getWidth() / 6;
		buttonY = Gdx.graphics.getHeight() / 2;

		Gdx.input.setInputProcessor(new MenuStateInput(this));
		buttonNewGame = new Button(buttonX, buttonY,ImageLoader.buttonNewGame);
		buttonSettings = new Button(buttonX*2,buttonY, ImageLoader.buttonSettings);
		buttonVolumeOn = new Button(buttonX*3, buttonY, ImageLoader.buttonVolumeOn);
		buttonVolumeOff = new Button(buttonX*4, buttonY, ImageLoader.buttonVolumeOff);

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

}
