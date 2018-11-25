package view;

import java.util.Observable;
import java.util.Observer;

import controller.BoardController;
import model.Board;

public abstract class BoardVue implements Observer{
	
	protected Board model;
	protected BoardController controller;
	
	BoardVue(Board model, BoardController controller) {
		this.model = model;
		this.controller = controller;
		model.addObserver(this); // connexion entre vue et	modele
	}

	public abstract void affiche(String string);
	
	public abstract void update(Observable o, Object arg);
}

