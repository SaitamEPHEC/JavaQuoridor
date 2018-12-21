package model;

import java.awt.Color;

/**
 * @author Nicky Riat, Mathias Gassmann, Adrien Chellé
 * Projet EPHEC 2TL1 
 */
public class Player {
	private final static int NBR_BARRIER_TOT = 10;
	private String nickname;
	private int nbrBarrierLeft;
	private Pawn myPawn;
	private static int playerCounter = 1;
	
	/**
	 * 
	 * Constructeur par defaut
	 */
	public Player(){
		nbrBarrierLeft = NBR_BARRIER_TOT;
		myPawn = new Pawn();
	}
	
	
	public String getNickname() {
		return nickname;
	}
	
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
	public int getNbrBarrierLeft() {
		return nbrBarrierLeft;
	}

	
	public Pawn getPawn() {
		return myPawn;
	}
	
	public void setNbrBarrierLeft(int nbr) {
		nbrBarrierLeft = nbr;
	}
	
	/**
	 * Permet de savoir si le pion d'un joueur est sur la case juste au-dessus du pion d'un autre joueur
	 * @param player un joueur
	 * @return true si le pion du joueur courant est juste au-dessus du pion du joueur en parametre, false sinon
	 */
	public boolean isJustBelow(Player player) {
		if(this.getPawn().getPosY() - player.getPawn().getPosY() == 2
		&& this.getPawn().getPosX() == player.getPawn().getPosX()){
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Permet de savoir si le pion d'un joueur est sur la case juste en-dessous du pion d'un autre joueur
	 * @param player un joueur
	 * @return true si le pion du joueur courant est juste en-dessous du pion du joueur en parametre, false sinon
	 */
	public boolean isJustAbove(Player player) {
		if(this.getPawn().getPosY() - player.getPawn().getPosY() == -2
		&& this.getPawn().getPosX() == player.getPawn().getPosX()){
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Permet de savoir si le pion d'un joueur est sur la case juste à gauche du pion d'un autre joueur
	 * @param player un joueur
	 * @return true si le pion du joueur courant est juste à gauche du pion du joueur en parametre, false sinon
	 */
	public boolean isJustToTheLeftOf(Player player) {
		if(this.getPawn().getPosY() == player.getPawn().getPosY()
		&& this.getPawn().getPosX() - player.getPawn().getPosX() == -2){
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Permet de savoir si le pion d'un joueur est sur la case juste à droite du pion d'un autre joueur
	 * @param player un joueur
	 * @return true si le pion du joueur courant est juste à droite du pion du joueur en parametre, false sinon
	 */
	public boolean isJustToTheRightOf(Player player) {
		if(this.getPawn().getPosY() == player.getPawn().getPosY()
		&& this.getPawn().getPosX() - player.getPawn().getPosX() == 2){
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Joueur " + playerCounter + " : " + nickname;
	}
	
	
	public Color getPawnColor() {
		return myPawn.getColor();
	}
	
	
	public void setPawnColor(Color pawnColor) {
		myPawn.setColor(pawnColor);
	}
	
}
