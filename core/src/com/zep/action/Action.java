package com.zep.action;

import java.util.Random;

import com.zep.object.Direction;
import com.zep.ui.Kare;
import com.zep.ui.Tahta;

public class Action {

	private Tahta	tahta;
	private int		width, height;	// karenin boyutlari. boyut kucultmek icin
	public boolean	added;

	public Action(Tahta tahta, int width, int height) {
		super();
		this.tahta = tahta;
		this.width = width;
		this.height = height;
	}

	public void playAnimation() {
		// tüm satir ya da sutun ayni ise (silinme asamasi)
		// once silinecek kareler animasyon olarak isaretlenir, 
		// ardindan asagidaki gibi bir kod ile animasyon calistirilir
		// animasyon tamamlandiktan sonra silme metodu cagirilir.
		
		// kare koordinatlari: x + i * kare[i][j].width(), y + j * kare[i][j].height()
		if (added && tahta.speed.x + tahta.x() + tahta.getKareRowLen() * getKare()[0][0].width() < tahta.x() + tahta.width()) {
			tahta.speed.x += .5f;
		} else {
//					speed.x = 0;
			added = false;
		}
	}

	public void moveRight(boolean checkSame) {
		for (int i = 0; i < getKare().length; i++) {
			for (int j = 0; j < getKare()[i].length; j++) {
				if (getKare()[i][j] != null && getKare()[i][j].isActive()) {
					getKare()[i][j].moveRight(i, j, getKare()[i][j].color, false);
					if (checkSame)
						tahta.checkSame(Direction.RIGHT);
					tahta.print();
					return;
				}
			}
		}

	}

	public void moveLeft(boolean checkSame) {
		for (int i = 0; i < getKare().length; i++) {
			for (int j = 0; j < getKare()[i].length; j++) {
				if (getKare()[i][j] != null && getKare()[i][j].isActive()) {
					getKare()[i][j].moveLeft(i, j, getKare()[i][j].color, false);
					if (checkSame)
						tahta.checkSame(Direction.LEFT);
					tahta.print();
					return;
				}
			}
		}
	}

	public void moveDown(boolean checkSame) {
		for (int i = 0; i < getKare().length; i++) {
			for (int j = 0; j < getKare()[i].length; j++) {
				if (getKare()[i][j] != null && getKare()[i][j].isActive()) {
					getKare()[i][j].moveDown(i, j, getKare()[i][j].color, false);
					if (checkSame)
						tahta.checkSame(Direction.DOWN);
					tahta.print();
					return;
				}
			}
		}
	}

	public void moveUp(boolean checkSame) {
		for (int i = 0; i < getKare().length; i++) {
			for (int j = 0; j < getKare()[i].length; j++) {
				if (getKare()[i][j] != null && getKare()[i][j].isActive()) {
					getKare()[i][j].moveUp(i, j, getKare()[i][j].color, false);
					if (checkSame)
						tahta.checkSame(Direction.UP);
					tahta.print();
					return;
				}
			}
		}
	}

	public void deleteRow(int jD) {
		Kare[][] args = getKare().clone();
		if (args != null && args.length > 0) {
			setKare(new Kare[args.length][args[0].length - 1]);
			for (int i = 0; i < args.length; i++) {
				int newColId = 0;
				for (int j = 0; j < args[i].length; j++) {
					if (j != jD) {
						getKare()[i][newColId] = args[i][j];
						newColId++;
					}
				}
			}

		}

	}

	public void deleteColumn(int iD) {
		Kare[][] args = getKare().clone();
		if (args != null && args.length > 0) {
			setKare(new Kare[args.length - 1][args[0].length]);
			int newRowId = 0;
			for (int i = 0; i < args.length; i++) {
				if (i != iD) {
					for (int j = 0; j < args[i].length; j++) {
						getKare()[newRowId][j] = args[i][j];
					}
					newRowId++;
				}
			}

		}

	}

	public void moveByDirection(Direction direction, boolean decrease) {
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

	public void nextHistory() {
//		kare() = history.next(kare());
//		print(false);
	}

	public void prevHistory() {
//		kare() = history.previous(kare());
//		print(false);
	}

	public void addRow() {
		Kare[][] kClone = getKare().clone();
		setKare(new Kare[getKare().length][getKare()[0].length + 1]);

		copyArray(kClone);

		int color;
		boolean visible;
		int activeI = -1, activeJ = -1;
		for (int i = 0; i < getKare().length; i++) {
			for (int j = getKare()[0].length - 2; j < getKare()[0].length; j++) {
				if (getKare()[i][j] != null && getKare()[i][j].isActive()) {
					activeI = i;
					activeJ = j;
					continue;
				}
				visible = !(i == 0 || i == getKare().length - 1 || j == 0 || j == getKare()[i].length - 1);
				color = (i == 0 || i == getKare().length - 1 || j == 0 || j == getKare()[i].length - 1) ? 0 : (int) new Random().nextInt(Tahta.CN);
				getKare()[i][j] = new Kare(tahta, width, height, color, visible);
			}
		}

		shiftActiveKareToDown(activeI, activeJ);

		added = true;
	}

	public void addColumn() {
		Kare[][] kClone = getKare().clone();
		setKare(new Kare[getKare().length + 1][getKare()[0].length]);

		copyArray(kClone);

		int color;
		boolean visible;
		int activeI = -1, activeJ = -1;
		for (int i = getKare().length - 2; i < getKare().length; i++) {
			for (int j = 0; j < getKare()[i].length; j++) {
				if (getKare()[i][j] != null && getKare()[i][j].isActive()) {
					activeI = i;
					activeJ = j;
					continue;
				}
				visible = !(i == 0 || i == getKare().length - 1 || j == 0 || j == getKare()[i].length - 1);
				color = (i == 0 || i == getKare().length - 1 || j == 0 || j == getKare()[i].length - 1) ? 0 : (int) new Random().nextInt(Tahta.CN);
				getKare()[i][j] = new Kare(tahta, width, height, color, visible);
			}
		}

		shiftActiveKareToRight(activeI, activeJ);

	}

	private void copyArray(Kare[][] kClone) {
		for (int i = 0; i < kClone.length; i++) {
			for (int j = 0; j < kClone[i].length; j++) {
				if (kClone[i][j] != null) {
					getKare()[i][j] = kClone[i][j];
				}
			}
		}
	}

	private void shiftActiveKareToRight(int i, int j) {
		if (i > -1 && j > -1) { // aktif kare sondaysa (eklenen sütunda)
			getKare()[i + 1][j].color = getKare()[i][j].color;
			getKare()[i + 1][j].visible = true;
			getKare()[i + 1][j].setActive(true);

			getKare()[i][j].color = new Random().nextInt(Tahta.CN);
			getKare()[i][j].visible = (j > 0 && j < getKare()[0].length - 1);
			getKare()[i][j].setActive(false);

		}
	}

	private void shiftActiveKareToDown(int i, int j) {
		if (i > -1 && j > -1) { // aktif kare sondaysa (eklenen satirda)
			getKare()[i][j + 1].color = getKare()[i][j].color;
			getKare()[i][j + 1].visible = true;
			getKare()[i][j + 1].setActive(true);

			getKare()[i][j].color = new Random().nextInt(Tahta.CN);
			getKare()[i][j].visible = (i > 0 && i < getKare().length - 1);
			getKare()[i][j].setActive(false);

		}
	}

	public void drag(Direction direction) {
		System.out.println("Direction: " + direction);
		switch (direction) {
			case LEFT:
				moveLeft(true);
				break;
			case RIGHT:
				moveRight(true);
				break;
			case UP:
				moveUp(true);
				break;
			case DOWN:
				moveDown(true);
				break;
			default:
				// hareket etme
		}
	}

	private Kare[][] getKare() {
		return tahta.getKare();
	}

	private void setKare(Kare[][] kare) {
		tahta.setKare(kare);
	}

	public Tahta tahta() {
		return tahta;
	}

	public int width() {
		return width;
	}

	public int height() {
		return height;
	}

}
