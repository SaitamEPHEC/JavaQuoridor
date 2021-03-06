package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;
import java.util.Stack;


public class Board extends Observable {
	private String board[][]= new String [17][17];
	private Player turn;
	private Player player1;
	private Player player2;
	private ArrayList<Barrier> barriersOnBoard;
	private String contours[][]= new String [17][17];
	private String premiereLigneContours = "123456789000";
	private String lettreContours = "HHGGFFEEDDCCBBAA000";
	private Stack<Board> history = new Stack<Board>();
	private int nbrBarrierLeftP1;
	private int nbrBarrierLeftP2;
	private int posXP1; 
	private int posYP1;
	private int posXP2; 
	private int posYP2;
	
	/**
	 * Constructeur a vide
	 */
	public Board(){
		super();
		initiateBoard();
		barriersOnBoard = new ArrayList<Barrier>();
	}
	/**
	 * Constructeur pour les retours en arriere
	 * @param board
	 * @param nbrBarrierLeftP1
	 * @param nbrBarrierLeftP2
	 * @param posXP1
	 * @param posYP1
	 * @param posXP2
	 * @param posYP1
	 */
	public Board(String board[][],ArrayList<Barrier> barriersOnBoard, int nbrBarrierLeftP1, int nbrBarrierLeftP2, int posXP1, int posYP1, int posXP2, int posYP2) {
		this.board = board;
		this.nbrBarrierLeftP1 = nbrBarrierLeftP1;
		this.nbrBarrierLeftP2 = nbrBarrierLeftP2;
		this.posXP1 = posXP1;
		this.posYP1 = posYP1;
		this.posXP2 = posXP2;
		this.posYP2 = posYP2;
		this.barriersOnBoard = barriersOnBoard;
		}
	
	/**
	 * Dessine le plateau de jeu au demarrage de la partie. Instancie les 2 joueurs et place les pions a leurs positions de depart.
	 */
	public void initiateBoard() {
		
		//Initialise le board
		initiateEmptyBoardConsole();
		//Initialise le contours "aide" du board 
		initiateContoursConsole();
		
		//Initialise les 2 joueurs
		player1 = new Player();
		player2 = new Player();
		player1.setNickname("joueur 1");
		player2.setNickname("joueur 2");
		player1.setPawnColor(Color.BLUE);
		player2.setPawnColor(Color.RED);
		
		//Attribue les positions initiales aux pions
		setP1Y(0);
		setP1X(8);
		setP2Y(16);
		setP2X(8);
		
		//dessine les pions aux cases initiales
		drawP1(new Pawn(0,0),new Pawn(getP1Y(),getP1X()));
		drawP2(new Pawn(0,0),new Pawn(getP2Y(),getP2X()));
		
		//le joueur 1 commence
		turn = player1;
	}
	
	/**
	 * Initialise les contours du board dans la console (les lettres a gauche et les chiffres en haut)
	 */
	public void initiateContoursConsole() {
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
					contours[i][j] = "A" + "";
				}
				
				else if(i == 0 && j%2 == 0) {
					contours[i][j] = "";
				}
				
				else if(j == 0) {//1ere colonne	
					contours[i][j] = (Character.toString(lettreContours.charAt(k)) + "");
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
	
	/**
	 * Permet de recuperer le tableau contenant les lettres a gauche du board et les chiffres au-dessus du board place 
	 * de maniere a etre bien positionne par rapport au board
	 * @return une matrice de String
	 */
	public String[][] getContours() {
		return contours;
	}
	
	/**
	 * Place le pion du joueur 1 a sa nouvelle position sur le board et le retire de sa position precedente
	 * @param prevPawn position precedente du pion
	 * @param currentPawn nouvelle position du pion
	 */
	public void drawP1(Pawn prevPawn, Pawn currentPawn) {
		board[prevPawn.getPosY()][prevPawn.getPosX()] = "  ";
		board[currentPawn.getPosY()][currentPawn.getPosX()] = "P1";
		setP1Y(currentPawn.getPosY());
		setP1X(currentPawn.getPosX());
	}
	
	/**
	 * Place le pion du joueur 2 a sa nouvelle position sur le board et le retire de sa position precedente
	 * @param prevPawn position precedente du pion
	 * @param currentPawn nouvelle position du pion
	 */
	public void drawP2(Pawn prevPawn, Pawn currentPawn) {
		board[prevPawn.getPosY()][prevPawn.getPosX()] = "  ";
		board[currentPawn.getPosY()][currentPawn.getPosX()] = "P2";
		setP2Y(currentPawn.getPosY());
		setP2X(currentPawn.getPosX());
	}
	
	/**
	 * Modifie le board en ajoutant une barriere HORIZONTALE sur 2 positions adjacentes du board. Le symbole est  "――".
	 * @param b une barriere 
	 */
	public void drawBarrierH(Barrier b) {
			board[b.getPosY1()][b.getPosX1()] = "――";
			board[b.getPosY2()][b.getPosX2()] = "――";
			if(turn.equals(player1)) {
				player1.setNbrBarrierLeft(player1.getNbrBarrierLeft() - 1);
				barriersOnBoard.add(b);
				turn = player2;
				setChanged();
				notifyObservers();
			}
			else {
				player2.setNbrBarrierLeft(player2.getNbrBarrierLeft() - 1);
				barriersOnBoard.add(b);
				turn = player1;
				setChanged();
				notifyObservers();
			}
	}
	
	/**
	 * Modifie le board en ajoutant une barriere VERTICALE sur 2 positions adjacentes du board. Le symbole est  " | ".
	 * @param b une barriere
	 */
	public void drawBarrierV(Barrier b) {
			board[b.getPosY1()][b.getPosX1()] = " | ";
			board[b.getPosY2()][b.getPosX2()] = " | ";
			if(turn.equals(player1)) {
				player1.setNbrBarrierLeft(player1.getNbrBarrierLeft() - 1);
				barriersOnBoard.add(b);
				turn = player2;
				setChanged();
				notifyObservers();
			}
			else {
				player2.setNbrBarrierLeft(player2.getNbrBarrierLeft() - 1);
				barriersOnBoard.add(b);
				turn = player1;
				setChanged();
				notifyObservers();
			}
	}
	
	/**
	 * Déplace le pion d'une case au-dessus dans le board si cela est possible, sinon renvoie un entier representant
	 * un cas specifique (certains entiers peuvent etre reutilises afin d'afficher un message d'erreur).
	 * @return 0 si P1 a été déplacé d'une ou deux cases au-dessus, 1 si P2 a été déplacé d'une ou deux cases 
	 * au-dessus, 2,3,4,5,6,7,8 ou 9 si une erreur s'est produite et que le pion n'a pas pu être déplacé.
	 */
	public int moveUp() {
		if(turn.equals(player1)){
			//P1 est bloque contre le bord superieur du board
			if(getP1Y() == 0){
				return 2;
			}
			//P1 est bloque par une barriere au-dessus
			else if(isPositionOfBarrierOnBoard(getP1Y()-1,getP1X())) {
				return 3;
			}
			//P1 est en-dessous de P2 et ne peux pas sauter au-dessus de lui a cause du bord superieur du board
			else if(player1.isJustBelow(player2) && getP1Y() == 2) {
				return 4;
			}
			//P1 est a plus d'une case du bord superieur du board
			else if(getP1Y() > 2) {
				//P1 est en-dessous de P2 et ne peut pas sauter au-dessus de P2 a cause d'une barriere
				if(player1.isJustBelow(player2) && isPositionOfBarrierOnBoard(getP1Y()-3, getP1X())) {
					return 5;
				}
				//P1 est en-dessous de P2 et peut sauter au-dessus de lui
				else if(player1.isJustBelow(player2)) {
					save();
					drawP1(new Pawn(getP1Y(),getP1X()),new Pawn(getP1Y()-4,getP1X()));
					turn = player2;
					setChanged();
					notifyObservers();
					return 0;
				}
				else{
					save();
					drawP1(new Pawn(getP1Y(),getP1X()),new Pawn(getP1Y()-2,getP1X()));
					turn = player2;
					setChanged();
					notifyObservers();
					return 0;
				}
			}
			else{
				save();
				drawP1(new Pawn(getP1Y(),getP1X()),new Pawn(getP1Y()-2,getP1X()));
				turn = player2;
				setChanged();
				notifyObservers();
				return 0;
			}
		}
		else{
			//P2 est bloque contre le bord superieur du board
			if(getP2Y() == 0) {
				return 6;
			}
			//P2 est bloque par une barriere au-dessus
			else if(isPositionOfBarrierOnBoard(getP2Y()-1,getP2X())) {
				return 7;
			}
			//P2 est en-dessous de P1 et ne peux pas sauter au-dessus de lui a cause du bord superieur du board
			else if(player2.isJustBelow(player1) && getP2Y() == 2) {
				return 8;
			}
			//P2 est a plus d'une case du bord superieur du board
			else if(getP2Y() > 2) {
				//P2 est en-dessous de P1 et ne peut pas sauter au-dessus de P1 a cause d'une barriere
				if(player2.isJustBelow(player1) && isPositionOfBarrierOnBoard(getP2Y()-3, getP2X())) {
					return 9;
				}
				//P2 est en-dessous de P1 et peut sauter au-dessus de lui
				else if(player2.isJustBelow(player1)) {
					save();
					drawP2(new Pawn(getP2Y(),getP2X()),new Pawn(getP2Y()-4,getP2X()));
					turn = player1;
					setChanged();
					notifyObservers();
					return 1;
				}
				else{
					save();
					drawP2(new Pawn(getP2Y(),getP2X()),new Pawn(getP2Y()-2,getP2X()));
					turn = player1;
					setChanged();
					notifyObservers();
					return 1;
				}
			}
			else{
				save();
				drawP2(new Pawn(getP2Y(),getP2X()),new Pawn(getP2Y()-2,getP2X()));
				turn = player1;
				setChanged();
				notifyObservers();
				return 1;
			}
		}
	}
	
	/**
	 * Déplace le pion d'une case en-dessous dans le board si cela est possible, sinon renvoie un entier representant
	 * un cas specifique (certains entiers peuvent etre reutilises afin d'afficher un message d'erreur).
	 * @return 0 si P2 a été déplacé d'une ou deux cases en-dessous, 1 si P1 a été déplacé d'une case ou deux cases
	 * en-dessous, 2,3,4,5,6,7,8 ou 9 si une erreur s'est produite et que le pion n'a pas pu être déplacé.
	 */
	public int moveDown() {
		if(turn.equals(player1)) {
			//P1 est bloque contre le bord inferieur du board
			if(getP1Y() == 16) {
				return 2;
			}
			//P1 est bloque par une barriere en-dessous
			else if(isPositionOfBarrierOnBoard(getP1Y()+1,getP1X())) {
				return 3;
			}
			//P1 est au-dessus de P2 et ne peux pas sauter en-dessous de lui a cause du bord inferieur du board
			else if(player1.isJustAbove(player2) && getP1Y() == 14) {
				return 4;
			}
			//P1 est a plus d'une case du bord inferieur du board
			else if(getP1Y() < 14) {
				//P1 est au-dessus de P2 et ne peut pas sauter en-dessous de P2 a cause d'une barriere
				if(player1.isJustAbove(player2) && isPositionOfBarrierOnBoard(getP1Y()+3, getP1X())) {
					return 5;
				}
				//P1 est au-dessus de P2 et peut sauter en-dessous de lui
				else if(player1.isJustAbove(player2)) {
					save();
					drawP1(new Pawn(getP1Y(),getP1X()),new Pawn(getP1Y()+4,getP1X()));
					turn = player2;
					setChanged();
					notifyObservers();
					return 1;
				}
				else{
					save();
					drawP1(new Pawn(getP1Y(),getP1X()),new Pawn(getP1Y()+2,getP1X()));
					turn = player2;
					setChanged();
					notifyObservers();
					return 1;
				}
			}
			else{
				save();
				drawP1(new Pawn(getP1Y(),getP1X()),new Pawn(getP1Y()+2,getP1X()));
				turn = player2;
				setChanged();
				notifyObservers();
				return 1;
			}
		}
		else{
			//P2 est bloque contre le bord inferieur du board
			if(getP2Y() == 16) {
				return 6;
			}
			//P2 est bloque par une barriere en-dessous
			else if(isPositionOfBarrierOnBoard(getP2Y()+1,getP2X())) {
				return 7;
			}
			//P2 est au-dessus de P1 et ne peux pas sauter en-dessous de lui a cause du bord inferieur du board
			else if(player2.isJustAbove(player1) && getP2Y() == 14) {
				return 8;
			}
			//P2 est a plus d'une case du bord inferieur du board
			else if(getP2Y() < 14) {
				//P2 est au-dessus de P1 et ne peut pas sauter en-dessous de P1 a cause d'une barriere
				if(player2.isJustAbove(player1) && isPositionOfBarrierOnBoard(getP2Y()+3, getP2X())) {
					return 9;
				}
				//P2 est au-dessus de P1 et peut sauter en-dessous de lui
				else if(player2.isJustAbove(player1)) {
					save();
					drawP2(new Pawn(getP2Y(),getP2X()),new Pawn(getP2Y()+4,getP2X()));
					turn = player1;
					setChanged();
					notifyObservers();
					return 0;
				}
				else{
					save();
					drawP2(new Pawn(getP2Y(),getP2X()),new Pawn(getP2Y()+2,getP2X()));
					turn = player1;
					setChanged();
					notifyObservers();
					return 0;
				}
			}
			else{
				save();
				drawP2(new Pawn(getP2Y(),getP2X()),new Pawn(getP2Y()+2,getP2X()));
				turn = player1;
				setChanged();
				notifyObservers();
				return 0;
			}
		}
	}
	
	/**
	 * Déplace le pion d'une case a gauche dans le board si cela est possible, sinon renvoie un entier representant
	 * un cas specifique (certains entiers peuvent etre reutilises afin d'afficher un message d'erreur).
	 * @return 0 si P1 ou P2 a été déplacé d'une ou deux cases a gauche, 1,2,3,4,5,6,7 ou 8 si une erreur s'est produite 
	 * et que le pion n'a pas pu être déplacé.
	 */
	public int moveLeft() {
		if(turn.equals(player1)) {
			//P1 est bloque contre le bord gauche du board
			if(getP1X() == 0) {
				return 1;
			}
			//P1 est bloque par une barriere a gauche
			else if(isPositionOfBarrierOnBoard(getP1Y(),getP1X()-1)) {
				return 2;
			}
			//P1 est a droite de P2 et ne peux pas sauter a gauche de lui a cause du bord lateral gauche du board
			else if(player1.isJustToTheRightOf(player2) && getP1X() == 2) {
				return 3;
			}
			//P1 est a plus d'une case du bord lateral gauche du board
			else if(getP1X() > 2) {
				//P1 est a droite de P2 et ne peut pas sauter a gauche de P2 a cause d'une barriere
				if(player1.isJustToTheRightOf(player2) && isPositionOfBarrierOnBoard(getP1Y(), getP1X()-3)) {
					return 4;
				}
				//P1 est a droite de P2 et peut sauter a gauche de lui
				else if(player1.isJustToTheRightOf(player2)) {
					save();
					drawP1(new Pawn(getP1Y(),getP1X()),new Pawn(getP1Y(),getP1X()-4));
					turn = player2;
					setChanged();
					notifyObservers();
					return 0;
				}
				else{
					save();
					drawP1(new Pawn(getP1Y(),getP1X()),new Pawn(getP1Y(),getP1X()-2));
					turn = player2;
					setChanged();
					notifyObservers();
					return 0;
				}
			}
			else{
				save();
				drawP1(new Pawn(getP1Y(),getP1X()),new Pawn(getP1Y(),getP1X()-2));
				turn = player2;
				setChanged();
				notifyObservers();
				return 0;
			}
		}
		else{
			//P2 est bloque contre le bord gauche du board
			if(getP2X() == 0) {
				return 5;
			}
			//P2 est bloque par une barriere a gauche
			else if(isPositionOfBarrierOnBoard(getP2Y(),getP2X()-1)) {
				return 6;
			}
			//P2 est a droite de P1 et ne peux pas sauter a gauche de lui a cause du bord lateral gauche du board
			else if(player2.isJustToTheRightOf(player1) && getP2X() == 2) {
				return 7;
			}
			//P2 est a plus d'une case du bord lateral gauche du board
			else if(getP2X() > 2) {
				//P2 est a droite de P1 et ne peut pas sauter a gauche de P1 a cause d'une barriere
				if(player2.isJustToTheRightOf(player1) && isPositionOfBarrierOnBoard(getP2Y(), getP2X()-3)) {
					return 8;
				}
				//P2 est a droite de P1 et peut sauter a gauche de lui
				else if(player2.isJustToTheRightOf(player1)) {
					save();
					drawP2(new Pawn(getP2Y(),getP2X()),new Pawn(getP2Y(),getP2X()-4));
					turn = player1;
					setChanged();
					notifyObservers();
					return 0;
				}
				else{
					save();
					drawP2(new Pawn(getP2Y(),getP2X()),new Pawn(getP2Y(),getP2X()-2));
					turn = player1;
					setChanged();
					notifyObservers();
					return 0;
				}
			}
			else{
				save();
				drawP2(new Pawn(getP2Y(),getP2X()),new Pawn(getP2Y(),getP2X()-2));
				turn = player1;
				setChanged();
				notifyObservers();
				return 0;
			}
		}
	}
	
	
	/**
	 * Déplace le pion d'une case a droite dans le board si cela est possible, sinon renvoie un entier representant
	 * un cas specifique (certains entiers peuvent etre reutilises afin d'afficher un message d'erreur).
	 * @return 0 si P1 ou P2 a été déplacé d'une ou deux cases a droite, 1,2,3,4,5,6,7 ou 8 si une erreur s'est produite 
	 * et que le pion n'a pas pu être déplacé.
	 */
	public int moveRight() {
		if(turn.equals(player1)) {
			//P1 est bloque contre le bord droit du board
			if(getP1X() == 16) {
				return 1;
			}
			//P1 est bloque par une barriere a droite
			else if(isPositionOfBarrierOnBoard(getP1Y(),getP1X()+1)) {
				return 2;
			}
			//P1 est a gauche de P2 et ne peux pas sauter a droite de lui a cause du bord lateral droit du board
			else if(player1.isJustToTheLeftOf(player2) && getP1X() == 14) {
				return 3;
			}
			//P1 est a plus d'une case du bord lateral droit du board
			else if(getP1X() < 14) {
				//P1 est a gauche de P2 et ne peut pas sauter a droite de P2 a cause d'une barriere
				if(player1.isJustToTheLeftOf(player2) && isPositionOfBarrierOnBoard(getP1Y(), getP1X()+3)) {
					return 4;
				}
				//P1 est a gauche de P2 et peut sauter a droite de lui
				else if(player1.isJustToTheLeftOf(player2)) {
					save();
					drawP1(new Pawn(getP1Y(),getP1X()),new Pawn(getP1Y(),getP1X()+4));
					turn = player2;
					setChanged();
					notifyObservers();
					return 0;
				}
				else{
					save();
					drawP1(new Pawn(getP1Y(),getP1X()),new Pawn(getP1Y(),getP1X()+2));
					turn = player2;
					setChanged();
					notifyObservers();
					return 0;
				}
			}
			else{
				save();
				drawP1(new Pawn(getP1Y(),getP1X()),new Pawn(getP1Y(),getP1X()+2));
				turn = player2;
				setChanged();
				notifyObservers();
				return 0;
			}
		}
		else{
			//P2 est bloque contre le bord droit du board
			if(getP2X() == 16) {
				return 5;
			}
			//P2 est bloque par une barriere a droite
			else if(isPositionOfBarrierOnBoard(getP2Y(),getP2X()+1)) {
				return 6;
			}
			//P2 est a gauche de P1 et ne peux pas sauter a droite de lui a cause du bord lateral droit du board
			else if(player2.isJustToTheLeftOf(player1) && getP2X() == 14) {
				return 7;
			}
			//P2 est a plus d'une case du bord lateral droit du board
			else if(getP2X() < 14) {
				//P2 est a gauche de P1 et ne peut pas sauter a droite de P1 a cause d'une barriere
				if(player2.isJustToTheLeftOf(player1) && isPositionOfBarrierOnBoard(getP2Y(), getP2X()+3)) {
					return 8;
				}
				//P2 est a gauche de P1 et peut sauter a droite de lui
				else if(player2.isJustToTheLeftOf(player1)) {
					save();
					drawP2(new Pawn(getP2Y(),getP2X()),new Pawn(getP2Y(),getP2X()+4));
					turn = player1;
					setChanged();
					notifyObservers();
					return 0;
				}
				else{
					save();
					drawP2(new Pawn(getP2Y(),getP2X()),new Pawn(getP2Y(),getP2X()+2));
					turn = player1;
					setChanged();
					notifyObservers();
					return 0;
				}
			}
			else{
				save();
				drawP2(new Pawn(getP2Y(),getP2X()),new Pawn(getP2Y(),getP2X()+2));
				turn = player1;
				setChanged();
				notifyObservers();
				return 0;
			}
		}
	}	
	
	/**
	 * 
	 * @return le plateau de jeu
	 */
	public String[][] getBoard() {
		return board;
	}
	
	/**
	 * 
	 * @return la longueur du plateau de jeu
	 */
	public int getLength() {
		return this.board.length;
	}
	
	/**
	 * 
	 * @return la longueur d'une ligne du plateau de jeu
	 */
	public int getLineLength() {
		return this.board[0].length;
	}
	
	/**
	 * @return le joueur a qui est le tour
	 */
	public Player getTurn() {
		return turn;
	}
	
	/**
	 * Permet de recuperer le joueur 1
	 * @return le joueur 1
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * Permet de recuperer le joueur 2
	 * @return le joueur 2
	 */
	public Player getPlayer2() {
		return player2;
	}

	/**
	 * @return le nom du joueur 1
	 */
	public String getPlayer1Nickname() {
		return player1.getNickname();
	}
	
	/**
	 * @return le nom du joueur 2
	 */
	public String getPlayer2Nickname() {
		return player2.getNickname();
	}
	
	/**
	 * Attribue nicknameP1 comme pseudo du joueur 1 et
	 * nicknameP2 comme pseudo du joueur 2
	 * @param nicknameP1 pseudo du joueur 1
	 * @param nicknameP2 pseudo du joueur 2
	 */
	public void setPlayerNicknames(String nicknameP1, String nicknameP2) {
		 player1.setNickname(nicknameP1);
		 player2.setNickname(nicknameP2);
		 setChanged();
		 notifyObservers();
		 
	}
	
	/**
	 * Permet d'obtenir le nombre de barrieres restantes du joueur 1
	 * @return le nombre de barrieres restantes du joueur 1
	 */
	public int getPlayer1BarrierLeft() {
		return player1.getNbrBarrierLeft();
	}
	
	/**
	 * Permet d'obtenir le nombre de barrieres restantes du joueur 2
	 * @return le nombre de barrieres restantes du joueur 2
	 */
	public int getPlayer2BarrierLeft() {
		return player2.getNbrBarrierLeft();
	}
	
	/**
	 * Permet d'obtenir la position du pion du joueur 1 sur l'axe des ordonnees (Y)
	 * @return la position Y du pion du joueur 1
	 */
	public int getP1Y() {
		return player1.getPawn().getPosY();
	}
	
	/**
	 * Permet d'obtenir la position du pion du joueur 1 sur l'axe des abscisses (X))
	 * @return la position X du pion du joueur 1
	 */
	public int getP1X() {
		return player1.getPawn().getPosX();
	}
	
	/**
	 * Permet d'obtenir la position du pion du joueur 2 sur l'axe des ordonnees (Y)
	 * @return la position Y du pion du joueur 2
	 */
	public int getP2Y() {
		return player2.getPawn().getPosY();
	}
	
	/**
	 * Permet d'obtenir la position du pion du joueur 2 sur l'axe des abscisses (X)
	 * @return la position X du pion du joueur 2
	 */
	public int getP2X() {
		return player2.getPawn().getPosX();
	}
	
	/**
	 * Modifie la position Y du pion du joueur 1
	 * @param y Un entier representant la nouvelle position y du pion du joueur 1
	 */
	public void setP1Y(int y) {
		player1.getPawn().setPosY(y);
	}
	
	/**
	 * Modifie la position X du pion du joueur 1
	 * @param x Un entier representant la nouvelle position x du pion du joueur 1
	 */
	public void setP1X(int x) {
		player1.getPawn().setPosX(x);
	}
	
	/**
	 * Modifie la position Y du pion du joueur 2
	 * @param y Un entier representant la nouvelle position y du pion du joueur 2
	 */
	public void setP2Y(int y) {
		player2.getPawn().setPosY(y);
	}
	
	/**
	 * Modifie la position X du pion du joueur 2
	 * @param x Un entier representant la nouvelle position x du pion du joueur 2
	 */
	public void setP2X(int x) {
		player2.getPawn().setPosX(x);
	}
	
	/**
	 * Permet de savoir si une barriere est deja sur le board en parcourant la liste des barrieres existantes
	 * sur le board
	 * @param b une barriere
	 * @return true si b est une barriere sur le board, false sinon.
	 */
	public boolean isBarrierOnBoard(Barrier b) {
		Iterator<Barrier> it = barriersOnBoard.iterator();
		 
		while (it.hasNext()) {
		       Barrier bInList= it.next();
		       if(bInList.equals(b)) {
		    	   return true;
		       }
		}
		return false; 
	}
	
	/**
	 * Permet de savoir si l'une des 2 positions de la barriere est deja presente sur le board ou non. Si c'est le cas,
	 * cela voudra dire qu'une des 2 positions d'une autre barriere occupe deja cette place.
	 * @param b une barriere
	 * @return true si l'une des 2 positions de la barriere b est deja sur le board, false sinon.
	 */
	public boolean isPositionOfBarrierOnBoard(Barrier b) {
		Iterator<Barrier> it = barriersOnBoard.iterator();
		 
		while (it.hasNext()) {
		       Barrier bInList= it.next();
		       if(bInList.getPosY2() == b.getPosY1() && bInList.getPosX2() == b.getPosX1()
		       || bInList.getPosY1() == b.getPosY2() && bInList.getPosX1() == b.getPosX2()
		       || bInList.getPosY1() == b.getPosY1() && bInList.getPosX1() == b.getPosX1()){
		    	   return true;
		       }
		}
		return false; 
	}
	
	/**
	 * Permet de savoir si une position avec une cordonnee Y et une coordonnee X correspond a une position de barriere
	 * existante sur le board ou non.
	 * @param posY entier representant la position Y1 ou Y2 d'une barriere
	 * @param posX entier representant la position X1 ou X2 d'une barriere
	 * @return true si la position passee en parametre represente une position de barriere existante dans le board,
	 * false sinon.
	 */
	public boolean isPositionOfBarrierOnBoard(int posY, int posX) {
		Iterator<Barrier> it = barriersOnBoard.iterator();
		 
		while (it.hasNext()) {
		       Barrier bInList= it.next();
		       if(bInList.getPosY2() == posY && bInList.getPosX2() == posX
		       || bInList.getPosY1() == posY && bInList.getPosX1() == posX){
		    	   return true;
		       }
		}
		return false; 
	}
	
	/**
	 * 
	 * @return true si le joueur 1 a gagne la partie, false sinon
	 */
	public boolean player1HasWon() {
		if(getP1Y() == 16) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 
	 * @return true si le joueur 2 a gagne la partie, false sinon
	 */
	public boolean player2HasWon() {
		if(getP2Y() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Check si la position au dessus est atteignable, qu'il n'y a pas de barriere
	 * @param soit X et Y la position dans la matrice Board et Board la matrice
	 * @return True si case au dessus atteignable, false sinon
	 */
	public boolean checkUp(int X, int Y, String[][] board) {
		if(Y+2 > 16) {return false;}
		else{
			String temp = board[Y+1][X];
			return (temp != "――");
			}	
	}
	
	/**
	 * Retourne la position en haut de la position passée en param
	 * @param soit X et Y la position actuelle
	 * @return la case en haut de la position initiale passée en param
	 */
	public Case positionUp(int X, int Y) {
		Y = Y+2;
		Case newCase = new Case(X, Y);	
		return newCase;
	}
	
	/**
	 * Check si la position du dessous est atteignable, qu'il n'y a pas de barriere
	 * @param soit X et Y la position dans la matrice Board et Board la matrice
	 * @return True si case du dessous atteignable, false sinon
	 */
	public boolean checkDown(int X, int Y, String[][] board) {
		if(Y-2 < 0) {return false;}
		else {
			String temp = board[Y-1][X];
			return (temp != "――");
		}
	}
	
	/**
	 * Retourne la position en bas de la position passée en param
	 * @param soit X et Y la position actuelle
	 * @return la case en bas de la position initiale passée en param
	 */
	public Case positionDown(int X, int Y) {
		Y = Y-2;
		Case newCase = new Case(X, Y);	
		return newCase;
	}
	
	/**
	 * Check si la position de gauche est atteignable, qu'il n'y a pas de barriere
	 * @param soit X et Y la position dans la matrice Board et Board la matrice
	 * @return True si case de gauche est atteignable, false sinon
	 */
	public boolean checkLeft(int X, int Y, String[][] board) {
		if(X-2 < 0) {return false;}
		else {
			String temp = board[Y][X-1];
			return (temp != " | ");
		}
	}
	
	/**
	 * Retourne la position a gauche de la position passée en param
	 * @param soit X et Y la position actuelle
	 * @return la case a gauche de la position initiale passée en param
	 */
	public Case positionLeft(int X, int Y) {
		X = X-2;
		Case newCase = new Case(X, Y);	
		return newCase;
	}
	
	/**
	 * Check si la position de droite est atteignable, qu'il n'y a pas de barriere
	 * @param soit X et Y la position dans la matrice Board et Board la matrice
	 * @return True si case de droite  atteignable, false sinon
	 */
	public boolean checkRight(int X, int Y, String[][] board) {
		if(X+2 > 16) {return false;}
		else {
			String temp = board[Y][X+1];
			return (temp != " | ");
		}
	}
	
	/**
	 * Retourne la position a droite de la position passée en param
	 * @param soit X et Y la position actuelle
	 * @return la case a droite de la position initiale passée en param
	 */
	public Case positionRight(int X, int Y) {
		X = X+2;
		Case newCase = new Case(X, Y);	
		return newCase;
	}
	
	/**
	 * Compare une case a un set de case
	 * @param le set composé de case, et la case comparée
	 * @return retourne true si positionCase se trouve dans le set
	 */
	public boolean setComparator(Set<Case> set, Case positionCase) {
		boolean truefalse = false;
		
		for(Case caseSet : set){
			if(caseSet.equals(positionCase)) {
				truefalse = true;
			}
				
		}
		return truefalse;
	}
	
	/**
	 * Compare une case a un stack de case
	 * @param le stack composé de case, et la case comparée
	 * @return retourne true si positionCase se trouve dans le stack
	 */
	public boolean stackComparator(Stack<Case> stack, Case positionCase) {
		boolean truefalse = false;
		
		for(Case caseStack : stack){
			if(caseStack.equals(positionCase)) {
				truefalse = true;
			}
				
		}
		return truefalse;
	}
	/**
	 * check si un chemin existe apres avoir posé la barriere passée en param
	 * @param la barriere que l'on pose avant de tester si un chemin existe tjs
	 * @return true si un chemin existe, false si il n'existe plus de chemin, et que le posage de barriere est donc illegal
	 */
	public boolean pathFinder(Barrier b, char c){
		String[][] boardTemp = new String[17][17];
		Stack<Case> casesAParcourir = new Stack<Case>();
		Set<Case> casesParcourues = new HashSet<Case>();
		
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				boardTemp[i][j] = this.board[i][j];
			}
		}	
		boolean chemin = false;
		
		if(!(c != 'v' || c != 'h')) { // si c n est ni v ni h, on ne saura pas draw barrier et donc on return false
			return false;
		}
		
		if(c == 'v') { //si barriere verticale
			drawBarrierVPathFinder(b, boardTemp);
		}
		
		if(c == 'h') { //si barriere verticale
			drawBarrierHPathFinder(b, boardTemp);
		}

		 // test par rapport a joueur 1, a.k.a doit arriver sur la ligne 16
			int won = 16;
			Case positionCase = new Case(this.getP1X(), this.getP1Y());
			if(positionCase.getY() == won) {chemin = true;}
			else {
				casesParcourues.add(positionCase);
				if(checkUp(positionCase.getX(), positionCase.getY(), boardTemp)) {casesAParcourir.push(positionUp(positionCase.getX(), positionCase.getY()));}
				if(checkLeft(positionCase.getX(), positionCase.getY(), boardTemp)) {casesAParcourir.push(positionLeft(positionCase.getX(), positionCase.getY()));}
				if(checkRight(positionCase.getX(), positionCase.getY(), boardTemp)) {casesAParcourir.push(positionRight(positionCase.getX(), positionCase.getY()));}
				if(checkDown(positionCase.getX(), positionCase.getY(), boardTemp)) {casesAParcourir.push(positionDown(positionCase.getX(), positionCase.getY()));}
	
				
				while(!casesAParcourir.empty()){ // tant que notre pile n'est pas vide, a.k.a qu'il nous reste des cases non testees
					positionCase = casesAParcourir.pop(); // on prend la suivante
					if(positionCase.getY() == won) {chemin = true;} // si position qui gagne (0, X) ou (16, X) en fct du joueur
	
					casesParcourues.add(positionCase); // on ajoute la position que l'on vient de testee a notre set 
			
					if( (checkUp(positionCase.getX(), positionCase.getY(), boardTemp)) && ((!(stackComparator(casesAParcourir, positionUp(positionCase.getX(), positionCase.getY()))) && (!(setComparator(casesParcourues, positionUp(positionCase.getX(), positionCase.getY()))))))) {casesAParcourir.push(positionUp(positionCase.getX(), positionCase.getY()));}
					if( (checkLeft(positionCase.getX(), positionCase.getY(), boardTemp)) && (!(stackComparator(casesAParcourir, positionLeft(positionCase.getX(), positionCase.getY()))) && (!(setComparator(casesParcourues, positionLeft(positionCase.getX(), positionCase.getY())))))) {casesAParcourir.push(positionLeft(positionCase.getX(), positionCase.getY()));}
					if( (checkRight(positionCase.getX(), positionCase.getY(), boardTemp)) && (!(stackComparator(casesAParcourir, positionRight(positionCase.getX(), positionCase.getY()))) && (!(setComparator(casesParcourues, positionRight(positionCase.getX(), positionCase.getY())))))) {casesAParcourir.push(positionRight(positionCase.getX(), positionCase.getY()));}
					if( (checkDown(positionCase.getX(), positionCase.getY(), boardTemp)) && (!(stackComparator(casesAParcourir, positionDown(positionCase.getX(), positionCase.getY()))) && (!(setComparator(casesParcourues, positionDown(positionCase.getX(), positionCase.getY())))))) {casesAParcourir.push(positionDown(positionCase.getX(), positionCase.getY()));}			
				}
			}
			if(!chemin) {return chemin;}// si chemin n'existe pas pour le joueur 1, on arrete ici	
			chemin = false;
			casesParcourues.clear(); // on clear notre set
			casesAParcourir.clear(); // on clear le stack
			won = 0; // le joueur 2 doit arriver ligne 0
			positionCase = new Case(this.getP2X(), this.getP2Y());
			if(positionCase.getY() == won) {return true;}
			
			casesParcourues.add(positionCase);
			if(checkUp(positionCase.getX(), positionCase.getY(), boardTemp)) {casesAParcourir.push(positionUp(positionCase.getX(), positionCase.getY()));}
			if(checkDown(positionCase.getX(), positionCase.getY(), boardTemp)) {casesAParcourir.push(positionDown(positionCase.getX(), positionCase.getY()));}
			if(checkLeft(positionCase.getX(), positionCase.getY(), boardTemp)) {casesAParcourir.push(positionLeft(positionCase.getX(), positionCase.getY()));}
			if(checkRight(positionCase.getX(), positionCase.getY(), boardTemp)) {casesAParcourir.push(positionRight(positionCase.getX(), positionCase.getY()));}

			while(!casesAParcourir.empty()){ // tant que notre pile n'est pas vide, a.k.a qu'il nous reste des cases non testees
				positionCase = casesAParcourir.pop(); // on prend la suivante
				if(positionCase.getY() == won) {chemin = true; break;} // si position qui gagne (0, X) ou (16, X) en fct du joueur

				casesParcourues.add(positionCase); // on ajoute la position que l'on vient de testee a notre set 
		
				if( (checkUp(positionCase.getX(), positionCase.getY(), boardTemp)) && ((!(stackComparator(casesAParcourir, positionUp(positionCase.getX(), positionCase.getY()))) && (!(setComparator(casesParcourues, positionUp(positionCase.getX(), positionCase.getY()))))))) {casesAParcourir.push(positionUp(positionCase.getX(), positionCase.getY()));}
				if( (checkDown(positionCase.getX(), positionCase.getY(), boardTemp)) && (!(stackComparator(casesAParcourir, positionDown(positionCase.getX(), positionCase.getY()))) && (!(setComparator(casesParcourues, positionDown(positionCase.getX(), positionCase.getY())))))) {casesAParcourir.push(positionDown(positionCase.getX(), positionCase.getY()));}
				if( (checkLeft(positionCase.getX(), positionCase.getY(), boardTemp)) && (!(stackComparator(casesAParcourir, positionLeft(positionCase.getX(), positionCase.getY()))) && (!(setComparator(casesParcourues, positionLeft(positionCase.getX(), positionCase.getY())))))) {casesAParcourir.push(positionLeft(positionCase.getX(), positionCase.getY()));}
				if( (checkRight(positionCase.getX(), positionCase.getY(), boardTemp)) && (!(stackComparator(casesAParcourir, positionRight(positionCase.getX(), positionCase.getY()))) && (!(setComparator(casesParcourues, positionRight(positionCase.getX(), positionCase.getY())))))) {casesAParcourir.push(positionRight(positionCase.getX(), positionCase.getY()));}
			}
		
	return chemin;	
	}
	/**
	 * Modifie le board en ajoutant une barriere HORIZONTALE sur 2 positions adjacentes du board. Le symbole est  "――".
	 * Methode utilisée par PathFinder seulement
	 * @param b une barriere 
	 */
	public void drawBarrierHPathFinder(Barrier b, String[][] boardTemp) {
			boardTemp[b.getPosY1()][b.getPosX1()] = "――";
			boardTemp[b.getPosY2()][b.getPosX2()] = "――";
	}
	
	/**
	 * Modifie le board en ajoutant une barriere VERTICALE sur 2 positions adjacentes du board. Le symbole est  " | ".
	 * Methode utilisée par PathFinder seulement
	 * @param b une barriere
	 */
	public void drawBarrierVPathFinder(Barrier b, String[][] boardTemp) {
			boardTemp[b.getPosY1()][b.getPosX1()] = " | ";
			boardTemp[b.getPosY2()][b.getPosX2()] = " | ";
	}
	
	/**
	 * Initialise le board a vide dans un format adapte pour la console 
	 */
	public void initiateEmptyBoardConsole() {
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				if(i%2 == 0)  {
					//Emplacement Pion
					if(j%2==0) {
						board[i][j] = "  ";
					}
					//Emplacement Barriere Verticale
					else {
						board[i][j] = "   ";
					}
					
				}
				else {
					//Emplacement Barriere Horizontale
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
	}
	/**
	 * Reinitialise le board a son etat initiale (debut de partie)
	 */
	public void resetBoard() {
		//initialise le board a vide
		initiateEmptyBoardConsole();
		//vide la liste de barrieres sur le board
		barriersOnBoard.clear();
		//remet le compteur de barriere des 2 joueurs a 10
		getPlayer1().setNbrBarrierLeft(10);
		getPlayer2().setNbrBarrierLeft(10);
		
		//Attribue les positions initiales aux pions
		setP1Y(0);
		setP1X(8);
		setP2Y(16);
		setP2X(8);
		
		//dessine les pions aux cases initiales
		drawP1(new Pawn(0,0),new Pawn(getP1Y(),getP1X()));
		drawP2(new Pawn(0,0),new Pawn(getP2Y(),getP2X()));
		turn = player1;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Rewind permet d'annuler le dernier coup joué.
	 * @return true si le rewind s'est effectué, false sinon
	 */
	public boolean rewind() { // methode permettant de revenir un coup en arriere
		if(history.empty()) {return false;} // si on n'a rien dans la pile et donc tour 1.
		else {
			Board previousTurn = history.pop(); // on prends le dernier tour qui a ete push, soit celui du tour d'avant
			String[][] previousStrBoard = previousTurn.getBoard();
			for(int i = 0;i<this.board.length;i++) {
				for(int j = 0;j<this.board[0].length;j++) {
					this.board[i][j] = previousStrBoard[i][j]; // on redonne a board ses valeurs du tour d'avant
				}
			}
			if(turn.equals(player1)) { // on inverse aussi le tour vu que l'on revient un coup en arriere
				turn = player2;
			}
			else {
				turn = player1;
			}
			
			this.barriersOnBoard = previousTurn.barriersOnBoard;
			player1.setNbrBarrierLeft(previousTurn.getNbrBarrierLeftP1()); // on remet au bon nombre le nombre de barriere restantes pour P1
			player2.setNbrBarrierLeft(previousTurn.getNbrBarrierLeftP2()); // on remet au bon nombre le nombre de barriere restantes pour P2
			
			player1.getPawn().setPosX(previousTurn.getPosXP1()); // on donne la pos du pion
			player1.getPawn().setPosY(previousTurn.getPosYP1());
			
			player2.getPawn().setPosX(previousTurn.getPosXP2());
			player2.getPawn().setPosY(previousTurn.getPosYP2());
			
			setChanged();
			notifyObservers();
		}
		return true;
	}
	
	/**
	 * la methode save() copie l'ensemble des elements pouvant changer apres un coup.
	 */
	public void save() {
		String[][] strTemp = new String[17][17];
		ArrayList<Barrier> arrayBarrierTemp = new ArrayList<Barrier>(this.barriersOnBoard);
		int tempPX1 = getP1X();
		int tempPY1 = getP1Y();
		
		int tempPX2 = getP2X();
		int tempPY2 = getP2Y();
		
		for(int i = 0;i<this.board.length;i++) {
			for(int j = 0;j<this.board[0].length;j++) {
				strTemp[i][j] = this.board[i][j]; // copie du board actuelle
			}
		}
		
		Board temp = new Board(strTemp, arrayBarrierTemp, player1.getNbrBarrierLeft(), player2.getNbrBarrierLeft(), tempPX1, tempPY1, tempPX2, tempPY2);
		history.push(temp); // ajout du board avant la mise en place du coup joué dans notre stack
	}	
	
	/**
	 * recupere le nombre de barrieres restante du joueur 1 dans un autre objet que this.board
	 * Utilisé par save() et rewind() uniquement
	 * @return le nombre de barrieres restantes de joueur 1 avec int < = 0
	 */
	public int getNbrBarrierLeftP1() {
		return nbrBarrierLeftP1;
	}
	
	/**
	 * recupere le nombre de barrieres restante du joueur 2 dans un autre objet que this.board
	 * Utilisé par save() et rewind() uniquement
	 * @return le nombre de barrieres restantes de joueur 2 avec int < = 0
	 */
	public int getNbrBarrierLeftP2() {
		return nbrBarrierLeftP2;
	}
	
	/**
	 * recuperer la position X du pion du joueur 1 dans un objet autre que this.board
	 * Utilisé par save() et rewind() uniquement
	 * @return un int entre 0 et board.length
	 */
	public int getPosXP1() {
		return posXP1;
	}
	
	/**
	 * recuperer la position Y du pion du joueur 1 dans un objet autre que this.board
	 * Utilisé par save() et rewind() uniquement
	 * @return un int entre 0 et board.length
	 */
	public int getPosYP1() {
		return posYP1;
	}
	
	/**
	 * recuperer la position X du pion du joueur 2 dans un objet autre que this.board
	 * Utilisé par save() et rewind() uniquement
	 * @return un int entre 0 et board.length
	 */
	public int getPosXP2() {
		return posXP2;
	}
	
	/**
	 * recuperer la position Y du pion du joueur 2 dans un objet autre que this.board
	 * Utilisé par save() et rewind() uniquement
	 * @return un int entre 0 et board.length
	 */
	public int getPosYP2() {
		return posYP2;
	}
	
	/**
	 * retourne le tour de jeu de board au moment actuel
	 * Utilisé par save() et rewind() uniquement
	 * @param turn le tour de jeu
	 */
	public void setTurn(Player turn) {
		this.turn = turn;
	}
	
	/**
	 * Permet de recuperer la liste de barriere du board
	 * @return barriersOnBoard : la liste de barriere du board
	 */
	public ArrayList<Barrier> getBarriersOnBoard() {
		return barriersOnBoard;
	}
	
	public void setBoard(int X, int Y, String str) {
		board[X][Y] = str;
	}
}	


