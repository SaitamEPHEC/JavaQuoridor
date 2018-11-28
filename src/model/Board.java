package model;

import java.util.ArrayList;
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
	
	public Board(String[][] board, int turn, ArrayList<Player> players) {
		super();
		this.board = board;
		this.turn = turn;
		this.players = players;
	}
	
	
	public void initiateBoardConsole() {
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				if(i%2 == 0)  {
					if(j%2==0) {
						board[i][j] = "  ";
					}
					else {
						board[i][j] = "  ";
					}
					
				}
				else {
					if(j == 16) {
						board[i][j] = " ";
					}
					else if(j%2==0) {
						board[i][j] = "  ";
					}
					else {
						board[i][j] = "+ ";
					}
				}
			}
		}
		
		player1 = new Player();
		player2 = new Player();
		
		player1.getPawn().setPosY(0);
		player1.getPawn().setPosX(8);
		player2.getPawn().setPosY(16);
		player2.getPawn().setPosX(8);
		drawPawn
		
	}

	public void drawPawn(int prevPosY, int prevPosX, int posX, int posY) {
		if(turn.equals(player1)) {
			board[player1.getPawn().getPosY()][player1.getPawn().getPosX()] = "P1";
			turn = player2;
			
		}
		else {
			board[player2.getPawn().getPosY()][player2.getPawn().getPosX()] = "P2";
			turn = player1;
		}
		player1.getPawn().getPosX();
	}
	
	
	/*
	 * 
	 * post : le pion bouge graphiquement d'une case vers le haut, retour true si pas d'erreur, false si erreur (rentre dans un mur)
	 */
	public boolean moveUp() {
		if(myPawn.getPosY() == 0) {return false;}
		else{
			myPawn.setPosY(myPawn.getPosY() - 2);
			return true;
			}
	}
	
	/*
	 * 
	 * post : le pion bouge graphiquement d'une case vers le bas, retour true si pas d'erreur, false si erreur (rentre dans un mur)
	 */
	public boolean moveDown() {
		if(myPawn.getPosY() == 16) {return false;}
		else{
			myPawn.setPosY(myPawn.getPosY() + 2);
			return true;
			}
	}
	
	/*
	 * 
	 * post : le pion bouge graphiquement d'une case vers la gauche, retour true si pas d'erreur, false si erreur (rentre dans un mur)
	 */
	public boolean moveLeft() {
		if(myPawn.getPosX() == 0) {return false;}
		else{
			myPawn.setPosX(myPawn.getPosX() - 2);
			return true;
			}
	}
	
	/*
	 * 
	 * post : le pion bouge graphiquement d'une case vers la droite, retour true si pas d'erreur, false si erreur (rentre dans un mur)
	 */
	public boolean moveRight() {
		if(myPawn.getPosX() == 16) {return false;}
		else{
			myPawn.setPosX(myPawn.getPosX() + 2);
			return true;
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

	
	
	
}
