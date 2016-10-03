package com.zep.buttons;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.zep.util.SkinLoader;

public class SkinButton extends Button implements ISkinButton {

	private BitmapFont font;
	private String	styleName;
	private String	text;

	public SkinButton(String styleName, float x, float y) {
		super(SkinLoader.skin, styleName);

		this.styleName = styleName;
		setX(x);
		setY(y);

		SkinLoader.stage.addActor(this);
	}

	public SkinButton(String styleName, float x, float y, String text, BitmapFont font) {
		super(SkinLoader.skin, styleName);

		this.styleName = styleName;
		this.text = text;
		this.font = font;
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
