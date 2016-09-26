package com.zep.inputhandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.zep.util.Constant;

/*
 * Score Mantığı: score = timer.delay - gecenZaman + (max kare sayisi - yok edilen kare sayisi) * multiplier multiplier degeri zorluk arttikca artacak
 */
public class ScoreHandler {

	private Preferences			pref;
	private int					score;
	private int					multiplier;
	private int					highScore;
	private PlayTimerHandler	timer;
	private static BitmapFont	font;
	private int					maxSquare;
	private Vector2				coordinate;	// score'un yazilacagi koordinatlar
	private int					moveCount;	// hamle sayisi

	public ScoreHandler(PlayTimerHandler timer, int multiplier, int maxSqSize) {
		pref = Gdx.app.getPreferences("ZePSquare");
		moveCount = 0;
		this.timer = timer;
		this.multiplier = multiplier;
		this.maxSquare = maxSqSize;

		score = 0;
		highScore = pref.getInteger(Constant.HIGH_SCORE, score);

		font = new BitmapFont(true);
		font.setColor(Color.WHITE);

		this.coordinate = new Vector2(6 * Gdx.graphics.getWidth() / 10, 3 * Gdx.graphics.getHeight() / 15 - 10);
	}

	public void render(SpriteBatch sb) {
		sb.begin();

		font.draw(sb, "Score: " + getScore(), coordinate.x, coordinate.y);
		font.draw(sb, "Time: " + (timer.delay() - timer.elapsedTime()), coordinate.x, coordinate.y + 20);
		font.draw(sb, "Move: " + (getMoveCount()), coordinate.x, coordinate.y + 40);

		sb.end();
	}

	public void update(float delta) {
	}

	public static void dispose() {
		font.dispose();
	}

	public int getScore(int destroyed) {
		score += (int) ((timer.delay() - timer.elapsedTime()) + (maxSquare - destroyed + 1) * multiplier);
		return getScore();
	}

	public int getScore() {
		if (highScore < score) {
			pref.putInteger(Constant.HIGH_SCORE, score);
		}

		return score;
	}

	public int getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}

	public int getHighScore() {
		highScore = pref.getInteger(Constant.HIGH_SCORE, 0);
		return highScore;
	}

	public int getMoveCount() {
		return moveCount;
	}

	public void addMoveCount() {
		this.moveCount++;
	}

}
