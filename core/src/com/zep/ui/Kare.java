package com.zep.ui;


public class Kare {

	private Tahta	tahta;
	private int		width, height;	// boyutlar
	public int		color;			// renk no
	private boolean	active;			// kontrol edilebilen
	public boolean	visible;

	public Kare(Tahta tahta, int width, int height, int color, boolean ignore) {
		this.tahta = tahta;
		this.width = width;
		this.height = height;
		this.color = color;
		this.visible = ignore;

	}

	public int width() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int height() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void moveRight(int i, int j, int color, boolean visibility) {
		this.active = false;
		if (visible) {
			if (i + 1 < tahta.getKareRowLen() && tahta.getKare()[i + 1][j] != null) { // bir sonraki kare visible ise onun bilgilerini al ve bu kareyi gorunmez yap
				tahta.getKare()[i + 1][j].moveRight(i + 1, j, this.color, true);
				this.color = color;
				this.visible = visibility;
			} else {
				this.active = true;
			}
		} else {
			this.color = color;
			this.active = true;
			this.visible = true;
		}
	}

	public void moveLeft(int i, int j, int color, boolean visibility) {
		this.active = false;
		if (visible) {
			if (i > 0 && tahta.getKare()[i - 1][j] != null) { // bir onceki kare visible ise onun bilgilerini al ve bu kareyi gorunmez yap
				tahta.getKare()[i - 1][j].moveLeft(i - 1, j, this.color, true);
				this.color = color;
				this.visible = visibility;
			} else {
				this.active = true;
			}
		} else {
			this.color = color;
			this.active = true;
			this.visible = true;
		}
	}

	public void moveUp(int i, int j, int color, boolean visibility) {
		this.active = false;
		if (visible) {
			if (j > 0 && tahta.getKare()[i][j - 1] != null) { // bir onceki kare visible ise onun bilgilerini al ve bu ekrani gorunmez yap
				tahta.getKare()[i][j - 1].moveUp(i, j - 1, this.color, true);
				this.color = color;
				this.visible = visibility;
			} else {
				this.active = true;
			}
		} else {
			this.color = color;
			this.active = true;
			this.visible = true;
		}

	}

	public void moveDown(int i, int j, int color, boolean visibility) {
		this.active = false;
		if (visible) {
			if (j + 1 < tahta.getKareColLen() && tahta.getKare()[i][j + 1] != null) { // bir onceki kare visible ise onun bilgilerini al ve bu ekrani gorunmez yap
				tahta.getKare()[i][j + 1].moveDown(i, j + 1, this.color, true);
				this.color = color;
				this.visible = visibility;
			} else {
				this.active = true;
			}
		} else {
			this.color = color;
			this.active = true;
			this.visible = true;
		}

	}

	@Override
	public String toString() {
		return "Kare [color=" + color + ", visible=" + visible + ", active=" + active + ", width=" + width + ", height=" + height + "]";
	}

}
