package com.zep.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.zep.action.Action;
import com.zep.inputhandler.TahtaInputProcessor;
import com.zep.ui.Tahta;

/**
 * Created by secelik on 05.08.2016.
 */
public class PlayState extends State {

	// private GameWorld gm;

	private ShapeRenderer		sr;		// basit cizim yapmak icin
	private StateManager		sm;
	private OrthographicCamera	cam;

	private Tahta				tahta;
	private Action				action;

	public PlayState(StateManager sm) {

		super(sm);
		cam = camera;
		this.sm = sm;

		sr = new ShapeRenderer();

		tahta = new Tahta(this, 50 * 4, 50 * 4, 6, 6);

		action = new Action(tahta, 25, 25);

		Gdx.input.setInputProcessor(new TahtaInputProcessor(action)); // input processor atandi (tus hareketleri, dokunma, surukleme vs.)

	}

	public void render(SpriteBatch sb) {
		sr.begin(ShapeType.Line);
		sr.setProjectionMatrix(camera.combined);
//		sr.setColor(Color.RED);
//		sr.line(x - 10, y - 10, x + width + 10, y - 10);
//		sr.line(x - 10, y - 10, x - 10, y + height + 10);
		sr.setColor(Color.YELLOW);
		sr.rect(tahta.x(), tahta.y(), tahta.width(), tahta.height());
		sr.end();

		sb.setProjectionMatrix(camera.combined);

		tahta.render(sb);

	}

	public void moveCamtoLeft() {
		camera.position.x -= 10;
	}

	public void update(float delta) {
		tahta.update(delta);
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

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

}
