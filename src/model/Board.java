package model;

import java.util.ArrayList;
import java.util.Observable;


public class Board extends Observable {
	private String board[][]= new String [17][17];
	private int turn;
	private ArrayList<Player> players;
	
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
						board[i][j] = "| ";
					}
					
				}
				else {
					if(j == 16) {
						board[i][j] = "-";
					}
					else if(j%2==0) {
						board[i][j] = "--";
					}
					else {
						board[i][j] = "+-";
					}
				}
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
		return "Salut Roi Soleil";
	}

	
	
	
}
