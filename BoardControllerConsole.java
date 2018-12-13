package controller;

import java.util.Scanner;

import model.Barrier;
import model.Board;

public class BoardControllerConsole extends BoardController{
	private Scanner sc; 
	
	public BoardControllerConsole(Board model) {
		super(model);
		sc = new Scanner(System.in);
	}


	@Override
	public void movePawn() {
			String m = sc.next().toUpperCase();
			switch(m) {
			case "U" : 
				moveUpAffichage();
				break;
			case "D" : 
				moveDownAffichage();
				break;
			case "L" : 
				moveLeftAffichage();
				break;
			case "R" : 
				moveRightAffichage();
				break;
			default :
				this.vue.affiche("Mouvement incorrect, Vous avez entré autre chose que \"U\" \"D\" \"L\" ou \"R\" comme 2ème charactère"
						+ ",  veuillez réessayer\n");
				break;
			}
	}
	
	@Override
	public void putBarrier() {
		Barrier b;
		String[] inputs = askBarrier();
		
		if(checkInputs(inputs)) {
			if(isBarrierH(inputs)){
				//Barriere Horizontale
				b = translateH(inputs);
				if(model.isPositionOfBarrierOnBoard(b)) {
					this.vue.affiche("Position de la barrière incorrecte : Vous ne pouvez pas placer une barrière sur une"
							+ " barrière déjà existante.\n");
				}
				else {
					if(crossBarrierV(b)) {
						this.vue.affiche("Position de la barrière incorrecte : Vous ne pouvez pas croiser votre barrière horizontale avec "
								+ "une barrière verticale déjà existante.\n");
					}
					else {
						model.drawBarrierH(b);
					}
				}
			}
			else if(isBarrierV(inputs)) {
				//Barriere Verticale
				b = translateV(inputs);
				if(model.isPositionOfBarrierOnBoard(b)) {
					this.vue.affiche("Position de la barrière incorrecte : Vous ne pouvez pas placer une barrière sur une"
							+ " barrière déjà existante.\n");
				}
				else {
					if(crossBarrierH(b)) {
						this.vue.affiche("Position de la barrière incorrecte : Vous ne pouvez pas croiser votre barrière verticale avec "
								+ "une barrière horizontale déjà existante.\n");
					}
					else {
						model.drawBarrierV(b);
					}
				}
			}
			else {
				this.vue.affiche("Les coordonnées entrées ne correspondent pas à une barrière horizontale ou verticale.\n"
						+ "Exemple debarrière horizontale : A 1 A 2 => Attention : pas de barriere horizontale sur la ligne I (hors du plateau de jeu)!\n"
						+ "Exemple de barrière verticale : G 8 H 8 => Attention : pas de barriere verticale sur la colonne 9 (hors du plateau de jeu)!\n");
			}
		}
		else {
		}
	}
	
	public void moveUpAffichage() {
		int up = model.moveUp();
		switch(up) {
		case 1 :
			this.vue.affiche("Player 1 : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord supérieur du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 2 : 
			this.vue.affiche("Player 1 : Une barrière vous empêche de vous déplacer d'une case en haut, veuillez réessayer\n");
			break;
		case 3 : 
			this.vue.affiche("Player 2 : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord supérieur du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 4 : 
			this.vue.affiche("Player 2 : Une barrière vous empêche de vous déplacer d'une case en haut, veuillez réessayer\n");
			break;
		case 5 : //mouvement correct du joueur 2 + check si victoire
			if(model.player2HasWon()) {
				this.vue.affiche("Bravo joueur 2, vous avez gagné! Félicitations !");
				this.vue.setEndOfGame();
			}
			break;
		default : //mouvement correct du joueur 1
			break;
		}
	}
	
	public void moveDownAffichage() {
		int down = model.moveDown();
		switch(down) {
		case 1 :
			this.vue.affiche("Player 1 : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord inférieur du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 2 : 
			this.vue.affiche("Player 1 : Une barrière vous empêche de vous déplacer d'une case en bas, veuillez réessayer\n");
			break;
		case 3 : 
			this.vue.affiche("Player 2 : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord inférieur du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 4 : 
			this.vue.affiche("Player 2 : Une barrière vous empêche de vous déplacer d'une case en bas, veuillez réessayer\n");
			break;
		case 5 : //mouvement correct du joueur 1 + check si victoire
			if(model.player1HasWon()) {
				this.vue.affiche("Bravo joueur 1, vous avez gagné! Félicitations !");
				this.vue.setEndOfGame();
			}
			break;
		default : //Mouvement correct du joueur 2
			break;
		}
	}
	
	public void moveLeftAffichage() {
		int left = model.moveLeft();
		switch(left) {
		case 1 :
			this.vue.affiche("Player 1 : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord latéral gauche du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 2 : 
			this.vue.affiche("Player 1 : Une barrière vous empêche de vous déplacer d'une case à gauche, veuillez réessayer\n");
			break;
		case 3 : 
			this.vue.affiche("Player 2 : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord latéral gauche du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 4 : 
			this.vue.affiche("Player 2 : Une barrière vous empêche de vous déplacer d'une case à gauche, veuillez réessayer\n");
			break;
		default : //Mouvement correct
			break;
		}
	}
	
	public void moveRightAffichage() {
		int right = model.moveRight();
		switch(right) {
		case 1 :
			this.vue.affiche("Player 1 : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord latéral droit du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 2 :
			this.vue.affiche("Player 1 : Une barrière vous empêche de vous déplacer d'une case à droite, veuillez réessayer\n");
			break;
		case 3 :
			this.vue.affiche("Player 2 : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord latéral droit du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 4 :
			this.vue.affiche("Player 2 : Une barrière vous empêche de vous déplacer d'une case à droite, veuillez réessayer\n");
			break;
		default : //Mouvement correct 
			break;
		}
	}
	
	
	/**
	 * 
	 * @return les 4 coordonnees de la barriere entrees par l'utilisateur. 
	 */
	public String[] askBarrier() {
		
		String c1 = sc.next().toUpperCase();
		
		String i1 = sc.next().toUpperCase();
		
		String c2 = sc.next().toUpperCase();
		
		String i2 = sc.next().toUpperCase();
		
		String[] coordonnees = {c1,i1,c2,i2};
		
		return coordonnees;
		
	}
	
	/**
	 * @param c un tableau de 4 coordonnees correspondant aux coordonnees de la barriere entrees par l'utilisateur.
	 * @return true si le format d'input de la barriere est correcte, c'est a dire que les coordonnees entrees sont dans
	 * l'ensemble des coordonnees du plateau de jeu, false sinon.
	 */
	public boolean checkInputs(String[] c) {
		boolean isValid = true; //si reste true, les chiffres et lettres sont valides par rapport aux limites du board
		
		if((c[0].length()!=1) || (LETTRES_AXE_Y.indexOf(c[0]) == -1)) {
			this.vue.affiche("1ère coordonnée de barrière incorrecte, la 1ère coordonnée doit être une lettre entre A et I\n");
			isValid = false; 
		}
		
		if((c[1].length()!=1) || (CHIFFRES_AXE_X.indexOf(c[1]) == -1)) {
			this.vue.affiche("2ème coordonnée de barrière incorrecte, la 2ème coordonnée doit être une un chiffre entre 1 et 9\n");
			isValid = false; 
		}
		
		if((c[2].length()!=1) || (LETTRES_AXE_Y.indexOf(c[2]) == -1)) {
			this.vue.affiche("3ème coordonnée de barrière incorrecte, la 3ème coordonnée doit être une lettre entre A et I\n");
			isValid = false; 
		}
		
		if((c[3].length()!=1) || (CHIFFRES_AXE_X.indexOf(c[3]) == -1)) {
			this.vue.affiche("4ème coordonnée de barrière incorrecte, la 4ème coordonnée doit être une un chiffre entre 1 et 9\n");
			isValid = false;
		}
		
		return isValid;
	}

}
