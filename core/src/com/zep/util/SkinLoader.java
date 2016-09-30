package com.zep.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SkinLoader {

	public static Skin skin;
	public static Stage stage;

	static {
		setSkin(Constant.SKIN_DEFAULT);
		stage = new Stage(new ScreenViewport());
	}

	public static void setSkin(String name) {
		skin = new Skin(Gdx.files.internal("skin/" + name + "/" + name + ".json"));
	}

	public static void dispose() {
		skin.dispose();
		stage.dispose();
	}
}
