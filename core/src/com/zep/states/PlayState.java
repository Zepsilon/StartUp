package com.zep.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.zep.controller.Controller;
import com.zep.inputhandler.ClockHandler;
import com.zep.inputhandler.PlayTimerHandler;
import com.zep.inputhandler.ScoreHandler;
import com.zep.inputhandler.TahtaInputProcessor;
import com.zep.object.Direction;
import com.zep.sounds.MusicLoader;
import com.zep.ui.Tahta;
import com.zep.util.Constant;

/**
 * Created by secelik on 05.08.2016.
 */
public class PlayState extends State {

	private GameWorld			gm;

	public static boolean		end;
	private ShapeRenderer		sr;			// basit cizim yapmak icin
	private StateManager		sm;
	private OrthographicCamera	cam;

	private Tahta				tahta;
	private Controller			controller;
	private ScoreHandler		score;
	private PlayTimerHandler	timer;
	private ClockHandler		clock;

	public PlayState(StateManager sm) {

		super(sm);
		gm = new GameWorld();
		cam = camera;
		this.sm = sm;

		int kareWidth = (Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / (Constant.POW / 2)) / (Constant.SQUARE_SIZE + 2);

		int unitWidth = Gdx.graphics.getWidth() / Constant.POW;
		int unitHeight = Gdx.graphics.getHeight() / Constant.POH;

		sr = new ShapeRenderer();

		timer = new PlayTimerHandler(this, 2 * Constant.SQUARE_SIZE);
		clock = new ClockHandler(this, new Vector2(3 * unitWidth, 3 * unitHeight), 50);
		score = new ScoreHandler(timer, Constant.MULTIPLIER, Constant.SQUARE_SIZE);

		tahta = new Tahta(this, kareWidth, Constant.SQUARE_SIZE, Constant.SQUARE_SIZE);

		controller = new Controller(tahta, kareWidth, kareWidth);

		Gdx.input.setInputProcessor(new TahtaInputProcessor(controller)); // input processor atandi (tus hareketleri, dokunma, surukleme vs.)
		tahta.checkSame(Direction.NOTHING);

		MusicLoader.musicPlay(1, true);

		System.out.println(tahta.getKare()[0][0]);
	}

	public void reScheduleTask() {
		// satir ya da sütun yok edildiginde cagrilir
		clock.reSchedule();
		timer.scheduleTask(tahta.getKareRowLen() + tahta.getKareColLen() - 2);
	}

	public void cancelSchedule() {
		// Oyun bittiginde cagrilir
		getClock().cancelSchedule();
		getTimer().cancelSchedule();
	}

	public void render(SpriteBatch sb) {
		sr.setProjectionMatrix(camera.combined);
		sb.setProjectionMatrix(camera.combined);

//		gm.render(sr);

		clock.render(sr);

		tahta.render(sb);

		score.render(sb);

	}

	public void moveCamtoLeft() {
		camera.position.x -= 10;
	}

	public void update(float delta) {

		gm.update(delta);

		tahta.update(delta);

		clock.update(delta);

		score.update(delta);
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub

	}

	public StateManager getSm() {
		return sm;
	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public Tahta getTahta() {
		return tahta;
	}

	public void setTahta(Tahta tahta) {
		this.tahta = tahta;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public ScoreHandler getScore() {
		return score;
	}

	public PlayTimerHandler getTimer() {
		return timer;
	}

	public ClockHandler getClock() {
		return clock;
	}

}
