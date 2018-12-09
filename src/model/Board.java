package model;

import java.util.Observable;


public class Board extends Observable {
	private String board[][]= new String [17][17];
	private Player turn;
	private Player player1;
	private Player player2;
	
	public Board() {
		super();
		initiateBoardConsole();
	}
	
	public Board(String[][] board, Player turn, Player player1, Player player2) {
		super();
		this.board = board;
		this.turn = turn;
		this.player1 = player1;
		this.player2 = player2;
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
	}
	
	/*
	 * @pre : Les positions Y et X precedentes du pion, les nouvelles positions Y et X du pion
	 * 		  et une string p qui sera la representation du pion du joueur 1.
	 * @post : Modifie le board en retirant le pion de sa position YX precedente et en le mettant a sa nouvelle position YX
	 */
	public void drawP1(int prevPosY, int prevPosX, int posY, int posX) {
		board[prevPosY][prevPosX] = "  ";
		board[posY][posX] = "P1";
		setP1Y(posY);
		setP1X(posX);
	}
	
	/*
	 * @pre : Les positions Y et X precedentes du pion, les nouvelles positions Y et X du pion
	 * 		  et une string p qui sera la representation du pion du joueur 2..
	 * @post : Modifie le board en retirant le pion de sa position YX precedente et en le mettant a sa nouvelle position YX
	 */
	public void drawP2(int prevPosY, int prevPosX, int posY, int posX) {
		board[prevPosY][prevPosX] = "  ";
		board[posY][posX] = "P2";
		setP2Y(posY);
		setP2X(posX);
	}
	
	/*
	 * @pre : Les 2 positions Y et X representant une barriere horizontale. 
	 * @post : Modifie le board en ajoutant une barriere horizontale sur 2 positions adjacentes du board. Le symbole est "――". 
	 */
	public void drawBarrierH(int posY1, int posX1, int posY2, int posX2) {
			Barrier barrier = new Barrier(posY1, posX1, posY2, posX2);
			board[posY1][posX1] = "――";
			board[posY2][posX2] = "――";
			if(turn.equals(player1)) {
				player1.addBarrier(barrier);
				player1.setNbrBarrierLeft(player1.getNbrBarrierLeft() - 1);
				turn = player2;
			}
			else {
				player2.addBarrier(barrier);
				player2.setNbrBarrierLeft(player2.getNbrBarrierLeft() - 1);
				turn = player1;
			}
	}
	
	/*
	 * @pre : Les 2 positions Y et X representant une barriere verticale. 
	 * @post : Modifie le board en ajoutant une barriere verticale sur 2 positions adjacentes du board. Le symbole est  " | ". 
	 */
	public void drawBarrierV(int posY1, int posX1, int posY2, int posX2) {
			Barrier barrier = new Barrier(posY1, posX1, posY2, posX2);
			board[posY1][posX1] = " | ";
			board[posY2][posX2] = " | ";
			if(turn.equals(player1)) {
				player1.addBarrier(barrier);
				player1.setNbrBarrierLeft(player1.getNbrBarrierLeft() - 1);
				turn = player2;
			}
			else {
				player2.addBarrier(barrier);
				player2.setNbrBarrierLeft(player2.getNbrBarrierLeft() - 1);
				turn = player1;
			}
	}
	
	/*
	 * 
	 * post : le pion bouge graphiquement d'une case vers le haut, retour true si pas d'erreur, false si erreur (rentre dans un mur)
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
				return 0;
			}
		}
	}
	
	/*
	 * 
	 * post : le pion bouge graphiquement d'une case vers le bas, retour true si pas d'erreur, false si erreur (rentre dans un mur)
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
				return 0;
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
				return 0;
			}
		}
	}
	
	/*
	 * 
	 * post : le pion bouge graphiquement d'une case vers la gauche, retour true si pas d'erreur, false si erreur (rentre dans un mur)
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
				return 0;
			}
		}
	}
	
	/*
	 * 
	 * post : le pion bouge graphiquement d'une case vers la droite, retour true si pas d'erreur, false si erreur (rentre dans un mur)
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
				return 0;
			}
		}
	}	
	
	public String[][] getBoard() {
		return board;
	}
	
	public int getLength() {
		return this.board.length;
	}
	

	@Override
	public String toString() {
		//TODO AFFICHE LE BOARD EN CONSOLE
		return "";
	}
	
	/*
	 * @post : Retourne la position Y du pion du joueur 1
	 */
	public int getP1Y() {
		return player1.getPawn().getPosY();
	}
	
	/*
	 * @post : Retourne la position X du pion du joueur 1
	 */
	public int getP1X() {
		return player1.getPawn().getPosX();
	}
	
	/*
	 * @post : Retourne la position Y du pion du joueur 2
	 */
	public int getP2Y() {
		return player2.getPawn().getPosY();
	}
	
	/*
	 * @post : Retourne la position Y du pion du joueur 1
	 */
	public int getP2X() {
		return player2.getPawn().getPosX();
	}
	
	/*
	 * @pre : Un entier y reprÃ©sentant la nouvelle position y du pion du joueur 1
	 * @post : Modifie la position Y du pion du joueur 1
	 */
	public void setP1Y(int y) {
		player1.getPawn().setPosY(y);
	}
	
	/*
	 * @pre : Un entier x reprÃ©sentant la nouvelle position x du pion du joueur 1
	 * @post : Modifie la position X du pion du joueur 1
	 */
	public void setP1X(int x) {
		player1.getPawn().setPosX(x);
	}
	
	/*
	 * @pre : Un entier y reprÃ©sentant la nouvelle position y du pion du joueur 2
	 * @post : Modifie la position Y du pion du joueur 2
	 */
	public void setP2Y(int y) {
		player2.getPawn().setPosY(y);
	}
	
	/*
	 * @pre : Un entier x reprÃ©sentant la nouvelle position x du pion du joueur 2
	 * @post : Modifie la position X du pion du joueur 2
	 */
	public void setP2X(int x) {
		player2.getPawn().setPosX(x);
	}
	
	
}
