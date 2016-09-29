package com.zep.images;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ImageLoader {

	private static Texture txtrButtons;
	private static Texture txtrButtonsSlc;
	public static TextureRegion	btnNewGame, btnNewGameSlc;
	public static TextureRegion	btnLanguage, btnLanguageSlc;
	public static TextureRegion	btnVolumeOn, btnVolumeOnSlc;
	public static TextureRegion	btnVolumeOff, btnVolumeOffSlc;
	
	private static Texture		txtrBkg;
	public static TextureRegion	txtrRegBkg;
	
	private static Texture		txtrBtn;
	public static TextureRegion	txtrRegBtn[];

	private static int			x, y;
	private static int			width, height, gap;

	public static void load(int size) {
		txtrButtons = new Texture("buttons/menuButtons_Inner.png");
		TextureRegion[][] splitBtnReg = new TextureRegion(txtrButtons).split(192, 192);
		btnNewGame = splitBtnReg[0][0];
		btnLanguage = splitBtnReg[0][4];
		btnLanguage.flip(false, true);
		btnVolumeOn = splitBtnReg[0][2];
//		btnVolumeOff = new TextureRegion(txtrButtons, 576, 0, 192, 192);
//		btnVolumeOff.flip(false, true);

		txtrButtonsSlc = new Texture("buttons/menuButtonsFrame.png");
		TextureRegion[][] splitBtnSlcReg = new TextureRegion(txtrButtonsSlc).split(192, 192);
		btnNewGameSlc = splitBtnSlcReg[0][0];
		btnLanguageSlc = splitBtnSlcReg[0][4];
		btnLanguageSlc.flip(false, true);
		btnVolumeOnSlc = splitBtnSlcReg[0][2];
//		btnVolumeOffSlc = new TextureRegion(txtrButtonsSlc, 576, 0, 192, 192);
		
		txtrBkg = new Texture("backround/backroundLightYear.png");
		txtrRegBkg = new TextureRegion(txtrBkg);
//		txtrRegBkg.flip(false, true);

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
		txtrButtons.dispose();
		txtrButtonsSlc.dispose();
	}

}
