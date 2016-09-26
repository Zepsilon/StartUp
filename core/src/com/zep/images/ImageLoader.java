package com.zep.images;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ImageLoader {

	private static Texture txtrButtons;
	public static TextureRegion	buttonNewGame;
	public static TextureRegion	buttonSettings;
	public static TextureRegion	buttonVolumeOn;
	public static TextureRegion	buttonVolumeOff;
	private static Texture		txtrBkg;
	public static TextureRegion	txtrRegBkg;
	private static Texture		txtrBtn;
	public static TextureRegion	txtrRegBtn[];

	private static int			x, y;
	private static int			width, height, gap;

	public static void load(int size) {
		txtrButtons = new Texture("buttons/menuButtons.png");
		buttonNewGame = new TextureRegion(txtrButtons, 0, 0, 192, 192);
		buttonSettings = new TextureRegion(txtrButtons, 192, 0, 192, 192);
		buttonVolumeOn = new TextureRegion(txtrButtons, 384, 0, 192, 192);
		buttonVolumeOff = new TextureRegion(txtrButtons, 576, 0, 192, 192);
		
		txtrBkg = new Texture("backround/backroundNeverAlone.png");
		txtrRegBkg = new TextureRegion(txtrBkg);
		txtrRegBkg.flip(false, true);

		txtrBtn = new Texture("buttons/Buttons_WHT.png");
		txtrRegBtn = new TextureRegion[size];

		x = 20;
		y = 20;
		width = 62;
		height = width;
		gap = 7;

		for (int i = 0; i < 5; i++) {
			txtrRegBtn[i] = new TextureRegion(txtrBtn, x, y, width, height); // tek tek parcalar aliniyor
//            txtrRegBtn[i].flip(false, true);
			x += width + gap;
		}

	}

	public static void dispose() {
		txtrBtn.dispose();
		txtrBkg.dispose();
	}

}
