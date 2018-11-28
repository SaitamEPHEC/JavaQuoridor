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
		board[0][8] = "P1";
		board [16][8] = "P2";
		
		
	}

	public void drawPawn() {
		if(turn.equals(player1)) {
			board[player1.getPawn().getPosY()][player1.getPawn().getPosX()] = "P1";
			
		}
		else {
			board[player2.getPawn().getPosY()][player2.getPawn().getPosX()] = "P2";
		}
		player1.getPawn().getPosX();
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
