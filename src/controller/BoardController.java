package controller;

import model.Barrier;
import model.Board;
import view.BoardVue;
import view.BoardVueConsole;

public abstract class BoardController {
	protected Board model;
	protected BoardVue vue;
	
	public BoardController(Board model) {
		this.model = model;
	}
	
	
	public void addView(BoardVue vue) {
		this.vue = vue;
	}
	
	/**
	 * @param la barriere a tester
	 * @return true si la barriere peut etre placee, false sinon.
	 */
	public abstract boolean isValidBarrier(Barrier b);
	
	/**
	 * 
	 * Place une barriere sur le plateau de jeu si elle respecte les conditions du jeu. Sinon, ne fait rien.
	 */
	public abstract void putBarrier();
	/**
	 * 
	 * Deplace le pion 
	 */
	public abstract void movePawn();
}


