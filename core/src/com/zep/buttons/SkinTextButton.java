package com.zep.buttons;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.zep.util.SkinLoader;

public class SkinTextButton extends TextButton implements ISkinButton {

	public SkinTextButton(String styleName, float x, float y, String text) {
		super(text, SkinLoader.skin, styleName);

		setX(x);
		setY(y);

		SkinLoader.stage.addActor(this);
	}

	@Override
	public void setSize(float width, float height) {

		setWidth(width);
		setHeight(height);
	}

	@Override
	public void setCoordinate(float x, float y) {
		setX(x);
		setY(y);
	}

}
