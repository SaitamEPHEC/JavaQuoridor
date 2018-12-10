package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;


public class Board extends Observable {
	private String board[][]= new String [17][17];
	private Player turn;
	private Player player1;
	private Player player2;
	private ArrayList<Barrier> barriersOnBoard;
	private int numberBarriersOnBoard = 0;
	private String contours[][]= new String [17][17];
	private String premiereLigneContours = "123456789000";
	private String lettreContours = "HHGGFFEEDDCCBBAA000";
	
	public Board(){
		super();
		initiateBoardConsole();
		barriersOnBoard = new ArrayList<Barrier>();
	}
	
	public Board(String[][] board, Player turn, Player player1, Player player2) {
		super();
		this.board = board;
		this.turn = turn;
		this.player1 = player1;
		this.player2 = player2;
		initiateBoardConsole();
		barriersOnBoard = new ArrayList<Barrier>();
	}
	
	
	public void initiateBoardConsole() {
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				if(i%2 == 0)  {
					//Emplacement Pion
					if(j%2==0) {
						board[i][j] = "  ";
					}
					//Emplacement Barriere
					else {
						board[i][j] = "   ";
					}
					
				}
				else {
					//Emplacement Barriere
					if(j%2==0) {
						board[i][j] = "  ";
					}
					//Emplacement ni Barriere ni Pion
					else {
						board[i][j] = " + ";
					}
				}
			}
		}
		
		//Initialise le contours "aide" du board 
		initiateContours();
		
		
		//Initialise les 2 joueurs
		player1 = new Player();
		player2 = new Player();
		
		//Attribue les positions initiales aux pions
		setP1Y(0);
		setP1X(8);
		setP2Y(16);
		setP2X(8);
		
		//dessine les pions aux cases initiales
		drawP1(0,0,getP1Y(),getP1X());
		drawP2(0,0,getP2Y(),getP2X());
		
		//le joueur 1 commence
		turn = player1;
		setChanged();
		notifyObservers();
	}
	
	public void initiateContours() {
		int k = 0;
		int m = 0;
		for(int i=0; i<contours.length; i++) {
			for(int j=0; j<contours[0].length; j++) {
				if(i == 0 && j%2 != 0 && j != 0)  {
					// 1ere ligne
					if(j == contours.length-2) {
						contours[i][j] = Character.toString(premiereLigneContours.charAt(m)) + "    9";
					}
					else {
						contours[i][j] = Character.toString(premiereLigneContours.charAt(m)) + "    ";
					}
					m++;
				}
				
				else if(i == contours.length-1 && j == 0 ) {
					contours[i][j] = "A" + "  ";
				}
				
				else if(i == 0 && j%2 == 0) {
					contours[i][j] = "";
				}
				
				else if(j == 0) {//1ere colonne	
					contours[i][j] = (Character.toString(lettreContours.charAt(k)) + "  ");
					k++;
				}
				
				else if(j == (contours.length-1)) {
					contours[i][j] = "";
				}
				
				else {
					contours[i][j] = "";
				}
			}
		}
		contours[0][0] = "   ";
	}
	
	public String[][] getContours() {
		return contours;
	}
	
	/**
	 * @pre : Les positions Y et X precedentes du pion, les nouvelles positions Y et X du pion
	 * 		  et une string p qui sera la representation du pion du joueur 1.
	 * @return : Modifie le board en retirant le pion de sa position YX precedente et en le mettant a sa nouvelle position YX
	 */
	public void drawP1(int prevPosY, int prevPosX, int posY, int posX) {
		board[prevPosY][prevPosX] = "  ";
		board[posY][posX] = "P1";
		setP1Y(posY);
		setP1X(posX);
	}
	
	/**
	 * @pre : Les positions Y et X precedentes du pion, les nouvelles positions Y et X du pion
	 * 		  et une string p qui sera la representation du pion du joueur 2..
	 * @return : Modifie le board en retirant le pion de sa position YX precedente et en le mettant a sa nouvelle position YX
	 */
	public void drawP2(int prevPosY, int prevPosX, int posY, int posX) {
		board[prevPosY][prevPosX] = "  ";
		board[posY][posX] = "P2";
		setP2Y(posY);
		setP2X(posX);
	}
	
	/**
	 * @pre : Les 2 positions Y et X representant une barriere horizontale. 
	 * @return : Modifie le board en ajoutant une barriere horizontale sur 2 positions adjacentes du board. Le symbole est "――". 
	 */
	public void drawBarrierH(int posY1, int posX1, int posY2, int posX2) {
			Barrier barrier = new Barrier(posY1, posX1, posY2, posX2);
			board[posY1][posX1] = "――";
			board[posY2][posX2] = "――";
			if(turn.equals(player1)) {
				player1.addBarrier(barrier);
				player1.setNbrBarrierLeft(player1.getNbrBarrierLeft() - 1);
				barriersOnBoard.add(barrier);
				numberBarriersOnBoard++;
				turn = player2;
				setChanged();
				notifyObservers();
			}
			else {
				player2.addBarrier(barrier);
				player2.setNbrBarrierLeft(player2.getNbrBarrierLeft() - 1);
				barriersOnBoard.add(barrier);
				numberBarriersOnBoard++;
				turn = player1;
				setChanged();
				notifyObservers();
			}
	}
	
	/**
	 * @pre : Les 2 positions Y et X representant une barriere verticale. 
	 * @return : Modifie le board en ajoutant une barriere verticale sur 2 positions adjacentes du board. Le symbole est  " | ". 
	 */
	public void drawBarrierV(int posY1, int posX1, int posY2, int posX2) {
			Barrier barrier = new Barrier(posY1, posX1, posY2, posX2);
			board[posY1][posX1] = " | ";
			board[posY2][posX2] = " | ";
			if(turn.equals(player1)) {
				player1.addBarrier(barrier);
				player1.setNbrBarrierLeft(player1.getNbrBarrierLeft() - 1);
				barriersOnBoard.add(barrier);
				numberBarriersOnBoard++;
				turn = player2;
				setChanged();
				notifyObservers();
			}
			else {
				player2.addBarrier(barrier);
				player2.setNbrBarrierLeft(player2.getNbrBarrierLeft() - 1);
				barriersOnBoard.add(barrier);
				numberBarriersOnBoard++;
				turn = player1;
				setChanged();
				notifyObservers();
			}
	}
	
	/**
	 * 
	 * @return : le pion bouge graphiquement d'une case vers le haut, retour true si pas d'erreur, false si erreur (rentre dans un mur)
	 */
	public int moveUp() {
		if(turn.equals(player1)){
			//P1 est bloque contre le bord superieur du board
			if(getP1Y() == 0){
				return 1;
			}
			//P1 est bloque par une barriere au-dessus
			else if(board[getP1Y()-1][getP1X()] == "――") {
				return 2;
			}
			else{
				drawP1(getP1Y(),getP1X(),getP1Y()-2,getP1X());
				turn = player2;
				setChanged();
				notifyObservers();
				return 0;
			}
		}
		else{
			//P2 est bloque contre le bord superieur du board
			if(getP2Y() == 0) {
				return 3;
			}
			//P2 est bloque par une barriere au-dessus
			else if(board[getP2Y()-1][getP2X()] == "――") {
				return 4;
			}
			else{
				drawP2(getP2Y(),getP2X(),getP2Y()-2,getP2X());
				turn = player1;
				setChanged();
				notifyObservers();
				return 5;
			}
		}
	}
	
	/**
	 * 
	 * @return : le pion bouge graphiquement d'une case vers le bas, retour true si pas d'erreur, false si erreur (rentre dans un mur)
	 */
	public int moveDown() {
		if(turn.equals(player1)) {
			//P1 est bloque contre le bord inferieur du board
			if(getP1Y() == 16) {
				return 1;
			}
			//P1 est bloque par une barriere en-dessous
			else if(board[getP1Y()+1][getP1X()] == "――"){
				return 2;
			}
			else{
				drawP1(getP1Y(),getP1X(),getP1Y()+2,getP1X());
				turn = player2;
				setChanged();
				notifyObservers();
				return 5;
			}
		}
		else{
			//P2 est bloque contre le bord inferieur du board
			if(getP2Y() == 16) {
				return 3;
			}
			//P2 est bloque par une barriere en-dessous
			else if(board[getP2Y()+1][getP2X()] == "――") {
				return 4;
			}
			else{
				drawP2(getP2Y(),getP2X(),getP2Y()+2,getP2X());
				turn = player1;
				setChanged();
				notifyObservers();
				return 0;
			}
		}
	}
	
	/**
	 * 
	 * @return : le pion bouge graphiquement d'une case vers la gauche, retour true si pas d'erreur, false si erreur (rentre dans un mur)
	 */
	public int moveLeft() {
		if(turn.equals(player1)) {
			//P1 est bloque contre le bord gauche du board
			if(getP1X() == 0) {
				return 1;
			}
			//P1 est bloque par une barriere a gauche
			else if(board[getP1Y()][getP1X()-1] == " | "){
				return 2;
			}
			else{
				drawP1(getP1Y(),getP1X(),getP1Y(),getP1X()-2);
				turn = player2;
				setChanged();
				notifyObservers();
				return 0;
			}
		}
		else{
			//P2 est bloque contre le bord gauche du board
			if(getP2X() == 0) {
				return 3;
			}
			//P2 est bloque par une barriere a gauche
			else if(board[getP2Y()][getP2X()-1] == " | "){
				return 4;
			}
			else{
				drawP2(getP2Y(),getP2X(),getP2Y(),getP2X()-2);
				turn = player1;
				setChanged();
				notifyObservers();
				return 0;
			}
		}
	}
	
	/**
	 * 
	 * @return : le pion bouge graphiquement d'une case vers la droite, retour true si pas d'erreur, false si erreur (rentre dans un mur)
	 */
	public int moveRight() {
		if(turn.equals(player1)) {
			//P1 est bloque contre le bord droit du board
			if(getP1X() == 16) {
				return 1;
			}
			//P1 est bloque par une barriere a droite
			else if(board[getP1Y()][getP1X()+1] == " | "){
				return 2;
			}
			else{
				drawP1(getP1Y(),getP1X(),getP1Y(),getP1X()+2);
				turn = player2;
				setChanged();
				notifyObservers();
				return 0;
			}
		}
		else{
			//P2 est bloque contre le bord droit du board
			if(getP2X() == 16) {
				return 3;
			}
			//P2 est bloque par une barriere a droite
			else if(board[getP2Y()][getP2X()+1] == " | "){
				return 4;
			}
			else{
				drawP2(getP2Y(),getP2X(),getP2Y(),getP2X()+2);
				turn = player1;
				setChanged();
				notifyObservers();
				return 0;
			}
		}
	}	
	
	/**
	 * 
	 * @return : Retourne le plateau de jeu
	 */
	public String[][] getBoard() {
		return board;
	}
	
	public int getLength() {
		return this.board.length;
	}
	
	/**
	 * @return : Retourne le joueur a qui est le tour
	 */
	public Player getTurn() {
		return turn;
	}
	
	/**
	 * @return : Retourne la position Y du pion du joueur 1
	 */
	public int getP1Y() {
		return player1.getPawn().getPosY();
	}
	
	/**
	 * @return : Retourne la position X du pion du joueur 1
	 */
	public int getP1X() {
		return player1.getPawn().getPosX();
	}
	
	/**
	 * @return : Retourne la position Y du pion du joueur 2
	 */
	public int getP2Y() {
		return player2.getPawn().getPosY();
	}
	
	/**
	 * @return : Retourne la position Y du pion du joueur 1
	 */
	public int getP2X() {
		return player2.getPawn().getPosX();
	}
	
	/**
	 * @pre : Un entier y representant la nouvelle position y du pion du joueur 1
	 * @return : Modifie la position Y du pion du joueur 1
	 */
	public void setP1Y(int y) {
		player1.getPawn().setPosY(y);
	}
	
	/**
	 * @pre : Un entier x representant la nouvelle position x du pion du joueur 1
	 * @return : Modifie la position X du pion du joueur 1
	 */
	public void setP1X(int x) {
		player1.getPawn().setPosX(x);
	}
	
	/**
	 * @pre : Un entier y representant la nouvelle position y du pion du joueur 2
	 * @return : Modifie la position Y du pion du joueur 2
	 */
	public void setP2Y(int y) {
		player2.getPawn().setPosY(y);
	}
	
	/**
	 * @pre : Un entier x representant la nouvelle position x du pion du joueur 2
	 * @return : Modifie la position X du pion du joueur 2
	 */
	public void setP2X(int x) {
		player2.getPawn().setPosX(x);
	}
	
	/**
	 * @pre : prend les 2 positions d'une barriere en parametre
	 * @return : Si les positions passees en parametre correspondent a une barriere sur le plateau de jeu, return true. Sinon, 
	 * 		   return false.
	 */
	public boolean isBarrierOnBoard(int posY1,int posX1, int posY2,int posX2) {
		Iterator<Barrier> it = barriersOnBoard.iterator();
		 
		while (it.hasNext()) {
		       Barrier b = it.next();
		       if(b.getPosY1() == posY1 && b.getPosX1() == posX1 && b.getPosY2() == posY2 && b.getPosX2() == posX2) {
		    	   return true;
		       }
		}
		return false; 
	}
	
}
