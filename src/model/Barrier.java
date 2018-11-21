package model;


/**
 * 
 * @author Nicky Riat, Mathias Gassmann, Adrien Chellé
 * Projet Ephec 2TL1
 */

public class Barrier {
	private int posX1;
	private int posX2;
	private int posY1;
	private int posY2;
	
	/**
	 * Class constructor
	 * @param posX1
	 * @param posX2
	 * @param posY1
	 * @param posY2
	 */
	public Barrier(int posX1, int posX2, int posY1, int posY2) {
		this.posX1 = posX1;
		this.posX2 = posX2;
		this.posY1 = posY1;
		this.posY2 = posY2;
	}

	/**
	 * Get the first barrier's position on the abscissa axis
	 * @return
	 */
	public int getPosX1() {
		return posX1;
	}

	/**
	 * Get the second barrier's position on the abscissa axis
	 * @return
	 */
	public int getPosX2() {
		return posX2;
	}
	
	/**
	 * Get the first barrier's position on the ordinate axis
	 * @return
	 */
	public int getPosY1() {
		return posY1;
	}
	 
	/**
	 * Get the second barrier's position on the ordinate axis
	 * @return
	 */
	public int getPosY2() {
		return posY2;
	}
	
	

}
