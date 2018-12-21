package model;

import java.awt.Color;

public class Pawn {
	private int posX;
	private int posY;
	private Color color;
	
	public Pawn() {
		super();
	}

	
	/**
	 * Constructeur de pawn
	 * @param posY
	 * @param posX
	 */
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

	/**
	 * Permet de recuperer la couleur du pion
	 * @return la couleur du pion
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Attribue la couleur color comme couleur du pion
	 * @param color la couleur du pion
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
}
