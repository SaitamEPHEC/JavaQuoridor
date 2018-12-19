package view;

import java.util.Observable;
import java.util.Observer;

import controller.BoardController;
import model.Board;

public abstract class BoardVue implements Observer{
	
	protected Board model;
	protected BoardController controller;
	protected boolean endOfGame = false;
	protected boolean nickNameGiven = false;
	protected static final String LETTRES_AXE_Y = "ABCDEFGHI";
	protected static final String CHIFFRES_AXE_X = "123456789";
	
	BoardVue(Board model, BoardController controller) {
		this.model = model;
		this.controller = controller;
		model.addObserver(this); // connexion entre vue et	modele
	}

	public abstract void affiche(String string);
	
	public abstract void update(Observable o, Object arg);
	
	/**
	 * @param c un tableau de 4 coordonnees correspondant aux coordonnees de la barriere entrees par l'utilisateur.
	 * @return true si le format d'input de la barriere est correcte, c'est a dire que les coordonnees entrees sont dans
	 * l'ensemble des coordonnees du plateau de jeu, false sinon.
	 */
	public boolean checkInputs(char[] c) {
		boolean isValid = true; //si reste true, les chiffres et lettres sont valides par rapport aux limites du board
		
		if(LETTRES_AXE_Y.indexOf(c[0]) == -1) {
			affiche("1ère coordonnée de barrière incorrecte, la 1ère coordonnée doit être une lettre entre A et I\n");
			isValid = false; 
		}
		
		if(CHIFFRES_AXE_X.indexOf(c[1]) == -1) {
			affiche("2ème coordonnée de barrière incorrecte, la 2ème coordonnée doit être une un chiffre entre 1 et 9\n");
			isValid = false; 
		}
		
		if(LETTRES_AXE_Y.indexOf(c[2]) == -1) {
			affiche("3ème coordonnée de barrière incorrecte, la 3ème coordonnée doit être une lettre entre A et I\n");
			isValid = false; 
		}
		
		if(CHIFFRES_AXE_X.indexOf(c[3]) == -1) {
			affiche("4ème coordonnée de barrière incorrecte, la 4ème coordonnée doit être une un chiffre entre 1 et 9\n");
			isValid = false;
		}
		
		return isValid;
	}
	
	/**
	 * Met la variable endOfGame a true.
	 */
	public void setEndOfGame() {
		endOfGame = true; 
	}
	
	
}

