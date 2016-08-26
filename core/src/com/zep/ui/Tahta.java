package com.zep.ui;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.zep.action.Action;
import com.zep.images.ImageLoader;
import com.zep.inputhandler.TahtaInputProcessor;
import com.zep.object.Direction;

public class Tahta {

	private OrthographicCamera	camera;
	private ShapeRenderer		sr;				// basit cizim yapmak icin
	private Action				action;

	private Kare[][]			kare;

	private int					width, height;	// boyutlar
	private int					x, y;			// x - y koordinatlari
	public int					row, col;		// satir - sutun sayisi
	public static final int		CN	= 5;		// renk miktari

	public Vector2				speed;
	

	public Tahta(int width, int height, int row, int col) {
		super();
		sr = new ShapeRenderer();

		int kareWidth = 25;
		int kareHeight = 25;

		speed = new Vector2(0, 0);
		action = new Action(this, kareWidth, kareHeight);

		this.width = width;
		this.height = height;

		this.row = row;
		this.col = col;

		this.x = (Gdx.graphics.getWidth() - width) / 2;
		this.y = (Gdx.graphics.getHeight() - height) / 2;

		camera = new OrthographicCamera();
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		kare = new Kare[row][col];

		int color;
		boolean visible;
		for (int i = 0; i < kare.length; i++) {
			for (int j = 0; j < kare[i].length; j++) {
				visible = !(i == 0 || i == kare.length - 1 || j == 0 || j == kare[i].length - 1);
				color = (i == 0 || i == kare.length - 1 || j == 0 || j == kare[i].length - 1) ? 0 : (int) new Random().nextInt(CN);
				kare[i][j] = new Kare(this, kareWidth, kareHeight, color, visible);
			}
		}

		kare[0][0] = new Kare(this, kareWidth, kareHeight, (int) new Random().nextInt(CN), true);
		kare[0][0].setActive(true);

		Gdx.input.setInputProcessor(new TahtaInputProcessor(action)); // input processor atandi (tus hareketleri, dokunma, surukleme vs.)

		checkSame(Direction.NOTHING);
		print();
	}

	public void render(SpriteBatch sb, float delta) {
		sr.begin(ShapeType.Line);
		sr.setProjectionMatrix(camera.combined);
		sr.setColor(Color.RED);
//		sr.line(x - 10, y - 10, x + width + 10, y - 10);
//		sr.line(x - 10, y - 10, x - 10, y + height + 10);
		sr.setColor(Color.YELLOW);
		sr.rect(x, y, width, height);
		sr.end();

		sb.begin();
		sb.setProjectionMatrix(camera.combined);

		action.playAnimation();
		
		for (int i = 0; i < kare.length; i++) {
			for (int j = 0; j < kare[i].length; j++) {
				if (kare[i][j] != null && kare[i][j].visible) {
					sb.draw(ImageLoader.txtrRegBtn[kare[i][j].color], x + i * kare[i][j].width() + speed.x, y + j * kare[i][j].height() + speed.y,
							kare[i][j].width(), kare[i][j].height());
				}
			}
		}

		sb.end();
	}

//	public void update(SpriteBatch sb, float delta) {
//System.out.println("update");
//		sb.begin();
//		sb.setProjectionMatrix(camera.combined);
//		for (int i = 0; i < kare.length; i++) {
//			for (int j = 0; j < kare[i].length; j++) {
//				if (kare[i][j] != null && kare[i][j].visible) {
////					x + i * sw, y + j * sh, 
//					sb.draw(ImageLoader.txtrRegBtn[kare[i][j].color], x + i * kare[i][j].width(), y + j * kare[i][j].height(), kare[i][j].width(),
//							kare[i][j].height());
//				}
//			}
//		}
//
//		sb.end();
//
//	}

	public void checkSame(Direction direction) {
		boolean tryAgain = true;
		while (tryAgain) {
			tryAgain = getSameColumn(direction) > 0;
			tryAgain = tryAgain || getSameRow(direction) > 0;
			direction = Direction.NOTHING;
		}
	}

	private int getSameRow(Direction direction) {
		int color = -1;
		for (int j = 1; j < kare[1].length - 1; j++) {
			color = kare[1][j].color;
			for (int i = 1; i < kare.length - 1; i++) {
				if (kare[i][j] != null && kare[i][j].visible && color != kare[i][j].color) {
					color = -1;
					break;
				}
			}

			if (color > -1) {
				System.out.println("Ayni Satir: " + j);

				action.moveByDirection(direction, true);
				action.deleteRow(j);

				if (kare[0].length > 3)
					action.moveByDirection(direction, false);
				return j;
			}
		}

		return -1;
	}

	private int getSameColumn(Direction direction) {
		int color = -1;
		for (int i = 1; i < kare.length - 1; i++) {
			color = kare[i][1].color;
			for (int j = 1; j < kare[i].length - 1; j++) {
				if (kare[i][j] != null && kare[i][j].visible && color != kare[i][j].color) {
					color = -1;
					break;
				}
			}

			if (color > -1) {
//				System.out.println("Ayni Sütun: " + i);

				action.moveByDirection(direction, true);
				action.deleteColumn(i);
				if (kare.length > 3)
					action.moveByDirection(direction, false);
				return i;
			}
		}

		return -1;
	}

	public int getKareRowLen() {
		return kare.length;
	}

	public int getKareColLen() {
		return kare[0].length;
	}

	public int x() {
		return this.x;
	}

	public int y() {
		return this.y;
	}

	public int width() {
		return width;
	}

	public int height() {
		return height;
	}

	public Kare[][] getKare() {
		return kare;
	}

	public void setKare(Kare[][] kare) {
		this.kare = kare;
	}

	public void print() {

		for (int i = 0; i < kare.length; i++) {
			for (int j = 0; j < kare[i].length; j++) {
				if (kare[i][j] != null && kare[i][j].visible)
					System.out.print(kare[i][j].color + " ");
				else
					System.out.print("x ");
			}
			System.out.println();
		}
		System.out.println();

	}

	@Override
	public String toString() {
		return "Tahta [width=" + width + ", height=" + height + ", x=" + x + ", y=" + y + ", row=" + row + ", col=" + col + "]";
	}

}
