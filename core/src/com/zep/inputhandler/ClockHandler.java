package com.zep.inputhandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class ClockHandler {

	private Vector2				center;
	private Vector2				coordinate;
	private float				radius;
	private float				degrees;
	private Timer				timer;
	private Task				task;
	private PlayTimerHandler	playerTimer;
	private static final int	FIX_DEGREE	= 270;	// ust

	public ClockHandler(PlayTimerHandler playerTimer, Vector2 center, float radius) {
		this.center = center;
		this.radius = radius;
		this.playerTimer = playerTimer;

		this.degrees = 0;

		coordinate = new Vector2(center.x, center.y - radius);

		timer = new Timer();
		schedule();
	}

	private void schedule() {

		task = new Task() {
			@Override
			public void run() {
				updateDegrees();
			}
		};

		reSchedule();
	}

	private void reSchedule() {
		timer.clear();
		this.degrees = 0;

		if (task == null)
			schedule();

		timer.scheduleTask(task, 1, 1);
	}

	public void reSchedule(float delay) {
		playerTimer.scheduleTask(delay);
		reSchedule();
	}

	public void cancelSchedule() {
		timer.clear();
		degrees = 0;
		playerTimer.cancelSchedule();
	}

	private void updateDegrees() {
		degrees += 360 / playerTimer.delay(); // zamani duzelt
		degrees = degrees % 360;
//		System.out.println(degrees + FIX_DEGREE);
	}

	public void render(ShapeRenderer sr) {
		if (playerTimer.isRescheduled()){
			playerTimer.setRescheduled(false);
			reSchedule();
		}
		sr.begin(ShapeType.Line);

		sr.setColor(Color.RED);
		sr.circle(center.x, center.y, radius);

		sr.setColor(Color.YELLOW);
		sr.line(center, coordinate);

		sr.end();
	}

	public void update(float delta) {

		coordinate.x = center.x + radius * MathUtils.cosDeg(degrees + FIX_DEGREE);
		coordinate.y = center.y + radius * MathUtils.sinDeg(degrees + FIX_DEGREE);
	}

	public PlayTimerHandler getPlayerTimer() {
		return playerTimer;
	}

	public void setPlayerTimer(PlayTimerHandler playerTimer) {
		this.playerTimer = playerTimer;
	}

}
