package model;

import java.util.ArrayList;
import java.util.Observable;

public class Board extends Observable {
	private Object[][] board;
	private int turn;
	private ArrayList<Player> players;
	
	public Board() {
		super();
	}
	
	public Board(Object[][] board, int turn, ArrayList<Player> players) {
		super();
		this.board = board;
		this.turn = turn;
		this.players = players;
	}
	
	public void play() {
		//TODO
		setChanged();
		notifyObservers();
	}
	
	private void drawBoard() {
		//TODO
		setChanged();
		notifyObservers();
	}
	
}
