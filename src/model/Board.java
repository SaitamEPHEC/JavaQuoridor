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
		board[0][8] = "P1";
		board [16][8] = "P2";
	}

	
	public String[][] getBoard() {
		return board;
	}
	
	public int getLength() {
		return this.board.length;
	}
	
	public void boardUpdate(int x, int y, String str) {
		board[x][y] = str;
	}
	
	public void boardUpdate(int x1, int x2, int y1, int y2, String str) {
		
	}

	public void drawBoardConsole() {
		
		for(int i = 0;i<this.getLength();i++) {
			for(int j = 0;j<this.getLength();j++) {
				if(j == this.getLength()-1) {
					System.out.println(this.getBoard()[i][j]);
				}
				else {
					System.out.print(this.getBoard()[i][j]);
				}
			}
		}
	}

	@Override
	public String toString() {
		//TODO AFFICHE LE BOARD EN CONSOLE
		return "";
	}

	
	
	
}
