package packageQuoridor;

import java.util.List;

public class Board {
	private Object[][] board;
	private int turn;
	private List<Player> players;
	
	public Board() {
		super();
	}
	
	public Board(Object[][] board, int turn, List<Player> players) {
		super();
		this.board = board;
		this.turn = turn;
		this.players = players;
	}
	
	public void play() {
		//TODO
	}
	
	private void drawBoard() {
		//TODO
	}
	
}
