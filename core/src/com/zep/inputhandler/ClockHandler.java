package com.zep.inputhandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.zep.states.PlayState;

public class ClockHandler {

	private PlayState	playState;
	private Vector2		center;
	private Vector2		coordinate;
	private float		radius;
	private float		degrees;
	private Timer timer;
	private Task task;
	private static final int FIX_DEGREE = 270; // ust

	public ClockHandler(PlayState playState, Vector2 center, float radius) {
		this.playState = playState;
		this.center = center;
		this.radius = radius;

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

	public void reSchedule() {
		timer.clear();
		
		if (task == null)
			schedule();
		
		this.degrees = 0;
		timer.scheduleTask(task, 1, 1);
	}
	
	public void cancelSchedule() {
		timer.clear();
		degrees = 0;
	}

	private void updateDegrees() {
		degrees += 360 / playState.getTimer().delay(); // zamani duzelt
		degrees = degrees % 360;
//		System.out.println(degrees + FIX_DEGREE);
	}

	public void render(ShapeRenderer sr) {
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

}
