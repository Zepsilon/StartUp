package com.zep.ui;

import java.util.Arrays;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.zep.images.ImageLoader;
import com.zep.inputhandler.BoardInput;
import com.zep.object.Direction;

public class Tahta {

	private OrthographicCamera	camera;
	private ShapeRenderer		sr;				// basit cizim yapmak icin

	private Kare[][]			kare;
	private int sw, sh;

	private int					width, height;	// boyutlar
	private int					x, y;			// x - y koordinatlari
	public int					row, col;		// satir - sutun sayisi
	private final int			CN	= 5;		// renk miktari

	public Tahta(int width, int height, int row, int col) {
		super();
		sr = new ShapeRenderer();

		this.width = width;
		this.height = height;

		this.row = row;
		this.col = col;

		this.x = (Gdx.graphics.getWidth() - width) / 2;
		this.y = (Gdx.graphics.getHeight() - height) / 2;

		sw = 30;
		sh = 30;

		camera = new OrthographicCamera();
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		kare = new Kare[row][col];

		int color;
		boolean visible;
		for (int i = 0; i < kare.length; i++) {
			for (int j = 0; j < kare[i].length; j++) {
				visible = !(i == 0 || i == kare.length - 1 || j == 0 || j == kare[i].length - 1);
				color = (i == 0 || i == kare.length - 1 || j == 0 || j == kare[i].length - 1) ? 0 : (int) new Random().nextInt(CN);
				kare[i][j] = new Kare(this, sw, sh, color, visible);
			}
		}

		kare[0][0] = new Kare(this, sw, sh, (int) new Random().nextInt(CN), true);
		kare[0][0].setActive(true);

		Gdx.input.setInputProcessor(new BoardInput(this)); // input processor atandi (tus hareketleri, dokunma, surukleme vs.)

		checkSame(null);
		print();
	}

	public void render(SpriteBatch sb, float delta) {
		sr.begin(ShapeType.Line);
		sr.setProjectionMatrix(camera.combined);
		sr.setColor(Color.RED);
		sr.line(x - 10, y - 10, x + width + 10, y - 10);
		sr.line(x - 10, y - 10, x - 10, y + height + 10);
		sr.setColor(Color.YELLOW);
		sr.rect(x, y, width, height);
		sr.end();

		sb.begin();
		sb.setProjectionMatrix(camera.combined);

		for (int i = 0; i < kare.length; i++) {
			for (int j = 0; j < kare[i].length; j++) {
				if (kare[i][j] != null && kare[i][j].visible) {
					sb.draw(ImageLoader.txtrRegBtn[kare[i][j].color], x + i * kare[i][j].width(), y + j * kare[i][j].height(), kare[i][j].width(),
							kare[i][j].height());
				}
			}
		}

		sb.end();
	}

	public void update(SpriteBatch sb, float delta) {

		sb.begin();
		sb.setProjectionMatrix(camera.combined);
		for (int i = 0; i < kare.length; i++) {
			for (int j = 0; j < kare[i].length; j++) {
				if (kare[i][j] != null && kare[i][j].visible) {
//					x + i * sw, y + j * sh, 
					sb.draw(ImageLoader.txtrRegBtn[kare[i][j].color], x + i * kare[i][j].width(), y + j * kare[i][j].height(), kare[i][j].width(),
							kare[i][j].height());
//					sb.draw(ImageLoader.txtrRegBtn[0], kare[i][j].centerX, kare[i][j].centerY, 5, 5);
//					sb.draw(ImageLoader.txtrRegBtn[1], kare[i][j].x(), kare[i][j].y(), 5, 5);
				}
			}
		}

		sb.end();

	}

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

				moveByDirection(direction, true);
				deleteRow(j);

				if (kare[0].length > 3)
					moveByDirection(direction, false);
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
				if (kare[j][i] != null && kare[i][j].visible && color != kare[i][j].color) {
					color = -1;
					break;
				}
			}

			if (color > -1) {
				System.out.println("Ayni Sütun: " + i);

				moveByDirection(direction, true);
				deleteColumn(i);
				if (kare.length > 3)
					moveByDirection(direction, false);
				return i;
			}
		}

		return -1;
	}

	private void deleteRow(int jD) {
		Kare[][] args = kare.clone();
		if (args != null && args.length > 0) {
			kare = new Kare[args.length][args[0].length - 1];
			for (int i = 0; i < args.length; i++) {
				int newColId = 0;
				for (int j = 0; j < args[i].length; j++) {
					if (j != jD) {
						kare[i][newColId] = args[i][j];
						newColId++;
					}
				}
			}

		}

	}

	private void deleteColumn(int iD) {
		Kare[][] args = kare.clone();
		if (args != null && args.length > 0) {
			kare = new Kare[args.length - 1][args[0].length];
			int newRowId = 0;
			for (int i = 0; i < args.length; i++) {
				if (i != iD) {
					for (int j = 0; j < args[i].length; j++) {
						kare[newRowId][j] = args[i][j];
					}
					newRowId++;
				}
			}

		}

	}

	private void moveByDirection(Direction direction, boolean decrease) {
		switch (direction) {
			case LEFT:
			case RIGHT:
				if (decrease)
					moveUp(false);
				else
					moveDown(false);
				break;

			case UP:
			case DOWN:
				if (decrease)
					moveLeft(false);
				else
					moveRight(false);
			default:
				// hareket etme
		}
	}

	public void moveRight(boolean checkSame) {
		for (int i = 0; i < kare.length; i++) {
			for (int j = 0; j < kare[i].length; j++) {
				if (kare[i][j] != null && kare[i][j].isActive()) {
					kare[i][j].moveRight(i, j, kare[i][j].color, false);
					if (checkSame)
						checkSame(Direction.RIGHT);
					print();
					return;
				}
			}
		}

	}

	public void moveLeft(boolean checkSame) {
		for (int i = 0; i < kare.length; i++) {
			for (int j = 0; j < kare[i].length; j++) {
				if (kare[i][j] != null && kare[i][j].isActive()) {
					kare[i][j].moveLeft(i, j, kare[i][j].color, false);
					if (checkSame)
						checkSame(Direction.LEFT);
					print();
					return;
				}
			}
		}
	}

	public void moveDown(boolean checkSame) {
		for (int i = 0; i < kare.length; i++) {
			for (int j = 0; j < kare[i].length; j++) {
				if (kare[i][j] != null && kare[i][j].isActive()) {
					kare[i][j].moveDown(i, j, kare[i][j].color, false);
					if (checkSame)
						checkSame(Direction.DOWN);
					print();
					return;
				}
			}
		}
	}

	public void moveUp(boolean checkSame) {
		for (int i = 0; i < kare.length; i++) {
			for (int j = 0; j < kare[i].length; j++) {
				if (kare[i][j] != null && kare[i][j].isActive()) {
					kare[i][j].moveUp(i, j, kare[i][j].color, false);
					if (checkSame)
						checkSame(Direction.UP);
					print();
					return;
				}
			}
		}
	}

	public void nextHistory() {
//		kare = history.next(kare);
//		print(false);
	}

	public void prevHistory() {
//		kare = history.previous(kare);
//		print(false);
	}

	public void addRow() {
		// TODO Auto-generated method stub

	}

	public void addColumn() {
		Kare[][] args = kare.clone();
		kare = new Kare[kare.length + 1][kare[0].length];
		System.arraycopy(args, 0, kare, 0, args.length);
		for (int i = 0; i < args.length; i++) {
			System.arraycopy(args[i], 0, kare[i], 0, args[i].length);
		}
		
		int color;
		boolean visible;
		for (int i = kare.length-2; i < kare.length; i++) {
			for (int j = 0; j < kare[i].length; j++) {
				if (kare[i][j] != null && kare[i][j].isActive())
					continue;
				visible = !(i == 0 || i == kare.length - 1 || j == 0 || j == kare[i].length - 1);
				color = (i == 0 || i == kare.length - 1 || j == 0 || j == kare[i].length - 1) ? 0 : (int) new Random().nextInt(CN);
				kare[i][j] = new Kare(this, sw, sh, color, visible);
			}
		}
		
	}

	public void clicked() {
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
