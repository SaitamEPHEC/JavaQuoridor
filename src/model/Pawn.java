package model;

public class Pawn {
	private int posX;
	private int posY;
	
	public Pawn() {
		super();
	}

	public Pawn(int posY, int posX) {
		this.posY = posY;
		this.posX = posX;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
}
