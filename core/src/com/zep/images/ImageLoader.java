package com.zep.images;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ImageLoader {
	private static Texture txtrBorder;
	public static TextureRegion	txrgBorder[];
	
	private static Texture txtrGroundAndPattern;
	public static TextureRegion	txrgGround, txrgPattern[];

	private static Texture txtrButtonsSlc, txtrButtons;
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
		
		txtrBtn = new Texture("buttons/Square.png");
		txtrRegBtn = new TextureRegion[size];

		x = 0;
		y = 0;
		width = 140;
		height = width;
		gap = 0;

		for (int i = 0; i < 5; i++) {
			txtrRegBtn[i] = new TextureRegion(txtrBtn, x, y, width, height); // tek tek parcalar aliniyor
//            txtrRegBtn[i].flip(false, true);
			x += width + gap;
		}
		

		txtrBkg = new Texture("backround/backroundLightYear.png");
		txtrRegBkg = new TextureRegion(txtrBkg);
//		txtrRegBkg.flip(false, true);

		txtrGroundAndPattern = new Texture("backround/groundAndPattern4.png");
		txrgGround = new TextureRegion(txtrGroundAndPattern, 0, 100, 5, 5);
//		txrgGround.flip(false, true);
		
		txrgPattern = new TextureRegion[]{new TextureRegion(txtrGroundAndPattern, 50, 0, 50, 50), new TextureRegion(txtrGroundAndPattern, 50, 0, 50, 50)};
//		txrgPattern[0].flip(false, true);
//		txrgPattern[1].flip(false, true);

		txtrBorder = new Texture("backround/borders.png");
		TextureRegion[][] borderBtnReg = new TextureRegion(txtrBorder).split(40, 40);
		txrgBorder = new TextureRegion[]{borderBtnReg[0][0], borderBtnReg[0][1], borderBtnReg[0][2], borderBtnReg[0][3], borderBtnReg[0][4], borderBtnReg[0][5], borderBtnReg[0][6], borderBtnReg[0][7]};

//		for (int i = 0; i < borderBtnReg.length; i++) {
//			txrgBorder[i] = borderBtnReg[0][i];
//		}
	}

	public static void dispose() {
		txtrBtn.dispose();
		txtrBkg.dispose();
		txtrButtons.dispose();
		txtrButtonsSlc.dispose();
	}

}
