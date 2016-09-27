package com.zep.buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.zep.object.GameObject;

public class Button implements GameObject {

	private TextureRegion	buttonImage;
	private float			xKord, yKord, width, height;

	private float			maxWidth, maxHeight;

	private Rectangle		buttonRect;

	private float			time		= 0;
	private int				animTime	= 4;

	public Button(float xKord, float yKord, TextureRegion buttonImage) {
		this.buttonImage = buttonImage;
		this.xKord = xKord;
		this.yKord = yKord;
		width = 48;
		height = 48;
		maxWidth = 48; // degistirilebilir
		maxHeight = 48; // degistirilebilir

		buttonRect = new Rectangle(xKord, yKord, width, height);
	}

	public void render(SpriteBatch sb) {
		sb.begin();

		sb.draw(buttonImage, xKord, yKord, width, height);

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

}
