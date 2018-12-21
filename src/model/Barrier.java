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
	 * 
	 * @param posX1
	 * @param posX2
	 * @param posY1
	 * @param posY2
	 */
	public Barrier(int posY1, int posX1, int posY2, int posX2) {
		this.posX1 = posX1;
		this.posX2 = posX2;
		this.posY1 = posY1;
		this.posY2 = posY2;
	}

	/**
	 * Si necessaire, inter-change les coordonnees X d'une barriere afin d'avoir X1 < X2 (sa position X1
	 * sera donc plus petite que sa position X2 => Lecture de gauche à droite sur le board). Inter-change également les 
	 * coordonnees Y  afin d'avoir Y1 > Y2 (sa position Y1 sera donc plus grande que sa position Y2 => Lecture
	 * de bas en haut sur le board).
	 */
	public void reordering() {
		if(posY1 < posY2) {
			int posY = posY1;
			posY1 = posY2;
			posY2 = posY;
		}
		else if(posX1 > posX2) {
			int posX = posX1;
			posX1 = posX2;
			posX2 = posX;
		}
	}
	/**
	 * Permet d'obtenir la premiere position de la barriere sur l'axe des ordonnees (Y)
	 * @return la position Y1
	 */
	public int getPosY1() {
		return posY1;
	}
	
	/**
	 * Permet d'obtenir la premiere position de la barriere sur l'axe des abscisses (X)
	 * @return la position X1
	 */
	public int getPosX1() {
		return posX1;
	}

	/**
	 * Permet d'obtenir la seconde position de la barriere sur l'axe des ordonnees (Y)
	 * @return la position Y2
	 */
	public int getPosY2() {
		return posY2;
	}

	/**
	 * Permet d'obtenir la seconde position de la barriere sur l'axe des abscisses (X)
	 * @return la position X2
	 */
	public int getPosX2() {
		return posX2;
	}

	/**
	 * Permet de modifier la valeur de la premiere position de la barriere sur l'axe des ordonnees (Y)
	 * @param posY1 la position Y1 a modifier
	 */
	public void setPosY1(int posY1) {
		this.posY1 = posY1;
	}

	/**
	 * Permet de modifier la valeur de la premiere position de la barriere sur l'axe des abscisses (X)
	 * @param posX1 la position X1 a modifier
	 */
	public void setPosX1(int posX1) {
		this.posX1 = posX1;
	}

	/**
	 * Permet de modifier la valeur de la seconde position de la barriere sur l'axe des ordonnees (Y)
	 * @param posY2 la position Y2 a modifier
	 */
	public void setPosY2(int posY2) {
		this.posY2 = posY2;
	}
	
	/**
	 * Permet de modifier la valeur de la seconde position de la barriere sur l'axe des abscisses (X)
	 * @param posX2 la position X2 a modifier
	 */
	public void setPosX2(int posX2) {
		this.posX2 = posX2;
	}
	
	@Override
	/**
	 * Permet de comparer deux objets barrieres sur base de leur position
	 * @param obj une barriere
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Barrier other = (Barrier) obj;
		if (posX1 != other.posX1)
			return false;
		if (posX2 != other.posX2)
			return false;
		if (posY1 != other.posY1)
			return false;
		if (posY2 != other.posY2)
			return false;
		return true;
	}

}
