package com.zep.buttons;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.zep.object.GameObject;

public class Button implements GameObject {

	private TextureRegion		buttonImage;
	private float				xKord, yKord, width, height;

	private float				maxWidth, maxHeight;

	private Rectangle			buttonRect;
	private BitmapFont	font;
	
	private String				text;

	private float				time		= 0;
	private int					animTime	= 4;

	public Button(float xKord, float yKord, TextureRegion buttonImage, BitmapFont	font, String text) {
		this.buttonImage = buttonImage;
		this.xKord = xKord;
		this.yKord = yKord;
		this.font = font;
		this.text = text;
		
		width = 48;
		height = 48;
		maxWidth = 48; // degistirilebilir
		maxHeight = 48; // degistirilebilir

		buttonRect = new Rectangle(xKord, yKord, width, height);

	}

	public void render(SpriteBatch sb) {
		sb.begin();

		sb.draw(buttonImage, xKord, yKord, width, height);
		font.draw(sb, text, xKord, yKord + 55, width, Align.center, false);

		sb.end();

	}

	public void update(float delta) {

		buttonRect.width = width;
		buttonRect.height = height;

		time = time + delta;
		if (time < animTime) {
			if (width < maxWidth) {
				width += 3;
			}

			if (height < maxHeight) {
				height++;
			}

		}

	}

	public Rectangle getButtonRect() {
		return buttonRect;
	}

	public void dispose() {
		font.dispose();
	}

}
