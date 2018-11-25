package model;

public class Pawn {
	private int posX;
	private int posY;
	
	public Pawn() {
		super();
	}

	public Pawn(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
		//TODO : dessiner directement le pion aux positions indiquées, dans le board
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
