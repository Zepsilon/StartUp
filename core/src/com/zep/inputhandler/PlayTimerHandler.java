package com.zep.inputhandler;

import java.util.Date;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.zep.states.PlayState;

public class PlayTimerHandler {

	private PlayState	playState;
	private long		startTime;	// gecen sureyi olcmek icin
	private Timer		timer;
	private Task		task;
	private float		delay;

	public PlayTimerHandler(PlayState playState, float delay) {

		this.playState = playState;
		this.delay = delay;

		timer = new Timer();

		initTimerTask();

		scheduleTask(delay);
	}

	private void initTimerTask() {
		task = new Task() {

			@Override
			public void run() {
				System.out.println("Time is Up!");
				// TODO satir ya da sutun'dan kucuk olan eklenecek.
				// satir ve sutun max degere ulasmissa oyun biter
				if (playState.getTahta().getKareRowLen() > playState.getTahta().getKareColLen()) {
					playState.getController().addRow();
				} else {
					playState.getController().addColumn();
				}
				scheduleTask(delay+1);
			}
		};
	}

	public void scheduleTask(float delay) {
		// satir ya da sütun yok edildiginde cagrilir
		if (task == null) {
			initTimerTask();
		}
		
		System.out.println("Task scheduled: " + delay);
		this.delay = delay;
		timer.clear(); // önce timer iptal edilir, ardindan asagidaki satir ile tekrar set edilir.
		startTime = new Date().getTime();
		timer.scheduleTask(task, delay);
	}
	
	public void cancelSchedule() {
		timer.clear();
	}

	/** timer schedule edildikten sonra bu ana kadar gecen süreyi verir */
	public long elapsedTime() {
		return (new Date().getTime() - startTime) / 1000;
	}

	public float delay() {
		return delay;
	}
}
