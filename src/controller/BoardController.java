package controller;

import model.Board;
import view.BoardVue;

public class BoardController {
	Board model;
	BoardVue vue;
	
	//TODO : Les différentes actions que le joueur peut faire
	
	public BoardController(Board model) {
		this.model = model;
	}
	
	public void addView(BoardVue vue) {
		this.vue = vue;
	}
	
}


