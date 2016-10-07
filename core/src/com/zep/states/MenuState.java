package com.zep.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.zep.images.ImageLoader;
import com.zep.sounds.MusicLoader;
import com.zep.util.Constant;
import com.zep.util.SkinLoader;
import com.zep.util.Util;

public class MenuState extends State {

	private StateManager		sm;
	private Button				buttonNewGame, buttonMusic, buttonSound;
	private SelectBox<String>	buttonLanguage;
	private boolean				isSoundOn, isMusicOn;

	private static BitmapFont	fontButton;

	private float				buttonX;
	private float				buttonY;

	public MenuState(StateManager sm) {
		super(sm);

		this.sm = sm;

		buttonX = Gdx.graphics.getWidth() / 10;
		buttonY = Gdx.graphics.getHeight() / 15;
		
		isSoundOn = (Boolean) Util.Prefs.getValue(Constant.PREF_SOUND, true);
		isMusicOn = (Boolean) Util.Prefs.getValue(Constant.PREF_MUSIC, true);
		
		fontButton = new BitmapFont(true);
		fontButton.setColor(Color.WHITE);

		buttonNewGame = new TextButton(Util.Bundle.getText("button.newGame"), SkinLoader.skin);
		buttonNewGame.setWidth(150);
		buttonNewGame.setX(buttonX * 5 - buttonNewGame.getWidth() / 2);
		buttonNewGame.setY(buttonY * 9);
		setNewGameListener();

		buttonSound = new Button(SkinLoader.skin, "sound");
		buttonSound.setX(buttonX * 4 - buttonSound.getWidth() / 2);
		buttonSound.setY(buttonY * 5);
		buttonSound.setChecked(isSoundOn);
		setSoundListener();

		buttonMusic = new Button(SkinLoader.skin, "music");
		buttonMusic.setX(buttonX * 6 - buttonMusic.getWidth() / 2);
		buttonMusic.setY(buttonY * 5);
		buttonMusic.setChecked(isMusicOn);
		setMusicListener();

		/**/
		buttonLanguage = new SelectBox<String>(SkinLoader.skin);
		buttonLanguage.setItems(Util.Bundle.getText("title.lang.english"), Util.Bundle.getText("title.lang.turkish"));
		buttonLanguage.setWidth(150);
		buttonLanguage.setX(buttonX * 5 - buttonLanguage.getWidth() / 2);
		buttonLanguage.setY(buttonY * 7);
		if (Constant.LOCAL_TR.equals(Util.Prefs.getValue(Constant.PREF_LANG, Constant.LOCAL_EN))) {
			buttonLanguage.setSelectedIndex(1);
		}
		setLanguageListener();

		SkinLoader.stage.addActor(buttonLanguage);
		SkinLoader.stage.addActor(buttonNewGame);
		SkinLoader.stage.addActor(buttonMusic);
		SkinLoader.stage.addActor(buttonSound);

		Gdx.input.setInputProcessor(SkinLoader.stage);
		/**/
	}

	private void setSoundListener() {
		buttonSound.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

				isSoundOn = !isSoundOn;
				System.out.println("isSoundOn " + isSoundOn);
				
				if (isSoundOn) {
					MusicLoader.load();
				} else {
					MusicLoader.musicStop();
				}
				
				Util.Prefs.putValue(Constant.PREF_SOUND, isSoundOn);

				return false;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				System.out.println("touchUp " + pointer);
			}
		});
	}

	private void setMusicListener() {
		buttonMusic.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

				isMusicOn = !isMusicOn;
				System.out.println("isMusicOn " + isMusicOn);
				
				if (isMusicOn) {
					MusicLoader.load();
				} else {
					MusicLoader.musicStopFx();
				}
				
				Util.Prefs.putValue(Constant.PREF_MUSIC, isMusicOn);

				return false;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				System.out.println("touchUp " + pointer);
			}
		});
	}

	private void setNewGameListener() {
		buttonNewGame.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				getSm().pushState(new PlayState(getSm()));
				return false;
			}
		});
	}

	private void setLanguageListener() {
		buttonLanguage.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
//	            dialog.show(SkinLoader.stage);
				System.out.println("Selected: " + buttonLanguage.getSelectedIndex() + " - " + buttonLanguage.getSelected());
				if (buttonLanguage.getSelectedIndex() == 0) { // ilk secenek English
					Util.Prefs.putValue(Constant.PREF_LANG, Constant.LOCAL_EN);
				} else {
					Util.Prefs.putValue(Constant.PREF_LANG, Constant.LOCAL_TR);
				}

				SkinLoader.stage.clear();
				Util.load();
				getSm().pushState(new MenuState(getSm()));
			}
		});
	}

	@Override
	public void render(SpriteBatch sb) {

//		//butonlar cizdirilcek.
		sb.setProjectionMatrix(camera.combined);
		sb.begin();

//		sb.draw(ImageLoader.txtrRegBkg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		fontButton.draw(sb, Util.Bundle.getText("title.highScore") + Util.Prefs.getValue(Constant.PREF_HIGH_SCORE, 0), buttonX * 5, buttonY * 3);

		sb.end();
		SkinLoader.stage.act(Gdx.graphics.getDeltaTime());
		SkinLoader.stage.draw();

//		buttonMusic.render(sb);
//		buttonSound.render(sb);
//		buttonVolumeOn.render(sb);
	}

	@Override
	public void update(float delta) {

//		buttonNewGame.update(delta);
//		buttonLanguage.update(delta);
//		buttonVolumeOn.update(delta);

	}

	@Override
	public void handleInput() {

	}

	public StateManager getSm() {
		return sm;
	}

	public static void dispose() {
		if (fontButton != null)
			fontButton.dispose();
	}

}
