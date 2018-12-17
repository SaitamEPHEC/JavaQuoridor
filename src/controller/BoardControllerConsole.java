package controller;

import java.util.Scanner;

import model.Barrier;
import model.Board;

public class BoardControllerConsole extends BoardController{
	private Scanner sc; 
	//private Scanner sc1;
	
	public BoardControllerConsole(Board model) {
		super(model);
		sc = new Scanner(System.in);
		//sc1 = new Scanner(System.in);
	}


	@Override
	public void movePawn() {
			char m = Character.toUpperCase(sc.next().trim().charAt(0));
			switch(m) {
			case 'U' : 
				moveUpAffichage();
				break;
			case 'D' : 
				moveDownAffichage();
				break;
			case 'L' : 
				moveLeftAffichage();
				break;
			case 'R' : 
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
		char[] inputs = askBarrier();
		
		if(checkInputs(inputs)) {
			if(isBarrierH(inputs)){
				//Barriere Horizontale
				b = translateH(inputs);
				if(model.isPositionOfBarrierOnBoard(b)) {
					this.vue.affiche("\n" + "Position de la barrière incorrecte : Vous ne pouvez pas placer une barrière sur une"
							+ " barrière déjà existante.\n");
				}
				else {
					if(crossBarrierV(b)) {
						this.vue.affiche("\n" + "Position de la barrière incorrecte : Vous ne pouvez pas croiser votre barrière horizontale avec "
								+ "une barrière verticale déjà existante.\n");
					}
					else {
						if(model.getTurn().getNbrBarrierLeft() > 0) {
							if(model.pathFinder(b)) {
								model.drawBarrierH(b);
							}
							else {
								this.vue.affiche("\n" + "Vous ne pouvez pas placer votre barriere ici : elle bloquerait le chemin d'un des joueurs");
							}
						}
						else {
							this.vue.affiche("\n" + model.getTurn().getNickname() + " : Vous n'avez plus de barrières disponibles! Il ne vous reste plus qu'à déplacer votre pion.\n");
						}
					}
				}
			}
			else if(isBarrierV(inputs)) {
				//Barriere Verticale
				b = translateV(inputs);
				if(model.isPositionOfBarrierOnBoard(b)) {
					this.vue.affiche("\n" + "Position de la barrière incorrecte : Vous ne pouvez pas placer une barrière sur une"
							+ " barrière déjà existante.\n");
				}
				else {
					if(crossBarrierH(b)) {
						this.vue.affiche("\n" + "Position de la barrière incorrecte : Vous ne pouvez pas croiser votre barrière verticale avec "
								+ "une barrière horizontale déjà existante.\n");
					}
					else {
						if(model.getTurn().getNbrBarrierLeft() > 0) {
							model.drawBarrierV(b);
						}
						else {
							this.vue.affiche("\n" + model.getTurn().getNickname() + " : Vous n'avez plus de barrières disponibles! Il ne vous reste plus qu'à déplacer votre pion.\n");
						}
					}
				}
			}
			else {
				this.vue.affiche("\n" + "Les coordonnées entrées ne correspondent pas à une barrière horizontale ou verticale.\n"
						+ "Exemple de barrière horizontale : A 1 A 2 => Attention : pas de barriere horizontale sur la ligne I (hors du plateau de jeu)!\n"
						+ "Exemple de barrière verticale : G 8 H 8 => Attention : pas de barriere verticale sur la colonne 9 (hors du plateau de jeu)!\n");
			}
		}
		else {
		}
	}
	
	public void moveUpAffichage() {
		int up = model.moveUp();
		switch(up) {
		case 0 : 
			break;
		case 1 : //mouvement correct du joueur 2 + check si victoire
			if(model.player2HasWon()) {
				this.vue.affiche("\n" + "Bravo " + model.getPlayer2Nickname() + ", vous avez gagné! Félicitations !");
				this.vue.setEndOfGame();
			}
			break;
		case 2 :
			this.vue.affiche("\n" + model.getPlayer1Nickname() + " : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord supérieur du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 3 : 
			this.vue.affiche("\n" + model.getPlayer1Nickname() + " : Une barrière vous empêche de vous déplacer d'une case en haut, veuillez réessayer\n");
			break;
		case 4 : 
			this.vue.affiche("\n" + model.getPlayer1Nickname() + " : Vous ne pouvez pas sauter au-dessus du pion de " + model.getPlayer2Nickname() + " à cause du bord supérieur du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 5 :
			this.vue.affiche("\n" + model.getPlayer1Nickname() + " : Vous ne pouvez pas sauter au-dessus du pion de " + model.getPlayer2Nickname() + " à cause d'une barrière"
					+ ", veuillez réessayer\n");
			break;
		case 6 : 
			this.vue.affiche("\n" + model.getPlayer2Nickname() + " : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord supérieur du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 7 : 
			this.vue.affiche("\n" + model.getPlayer2Nickname() + " : Une barrière vous empêche de vous déplacer d'une case en haut, veuillez réessayer\n");
			break;
		case 8 : 
			this.vue.affiche("\n" + model.getPlayer2Nickname() + " : Vous ne pouvez pas sauter au-dessus du pion de " + model.getPlayer1Nickname() + " à cause du bord supérieur du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 9 :
			this.vue.affiche("\n" + model.getPlayer2Nickname() + " : Vous ne pouvez pas sauter au-dessus du pion de " + model.getPlayer1Nickname() + " à cause d'une barrière"
					+ ", veuillez réessayer\n");
			break;
		default :
			this.vue.affiche("Erreur dans le moveUp(), cas imprévu");
			break;
		}
	}
	
	public void moveDownAffichage() {
		int down = model.moveDown();
		switch(down) {
		case 0 : 
			break;
		case 1 : //mouvement correct du joueur 1 + check si victoire
			if(model.player1HasWon()) {
				this.vue.affiche("\n" + "Bravo " + model.getPlayer1Nickname() + ", vous avez gagné! Félicitations !");
				this.vue.setEndOfGame();
			}
			break;
		case 2 :
			this.vue.affiche("\n" + model.getPlayer1Nickname() + " : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord inférieur du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 3 : 
			this.vue.affiche("\n" + model.getPlayer1Nickname() + " : Une barrière vous empêche de vous déplacer d'une case en bas, veuillez réessayer\n");
			break;
		case 4 : 
			this.vue.affiche("\n" + model.getPlayer1Nickname() + " : Vous ne pouvez pas sauter en-dessous du pion de " + model.getPlayer2Nickname() + " à cause du bord inférieur du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 5 :
			this.vue.affiche("\n" + model.getPlayer1Nickname() + " : Vous ne pouvez pas sauter en-dessous du pion de " + model.getPlayer2Nickname() + " à cause d'une barrière"
					+ ", veuillez réessayer\n");
			break;
		case 6 : 
			this.vue.affiche("\n" + model.getPlayer2Nickname() + " : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord inférieur du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 7 : 
			this.vue.affiche("\n" + model.getPlayer2Nickname() + " : Une barrière vous empêche de vous déplacer d'une case en bas, veuillez réessayer\n");
			break;
		case 8 : 
			this.vue.affiche("\n" + model.getPlayer2Nickname() + " : Vous ne pouvez pas sauter en-dessous du pion de " + model.getPlayer1Nickname() + " à cause du bord inférieur du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 9 :
			this.vue.affiche("\n" + model.getPlayer2Nickname() + " : Vous ne pouvez pas sauter en-dessous du pion de " + model.getPlayer1Nickname() + " à cause d'une barrière"
					+ ", veuillez réessayer\n");
			break;
		default :
			this.vue.affiche("Erreur dans le moveDown(), cas imprévu");
			break;
		}
	}
	
	public void moveLeftAffichage() {
		int left = model.moveLeft();
		switch(left) {
		case 0 : 
			break;
		case 1 :
			this.vue.affiche("\n" + model.getPlayer1Nickname() + " : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord latéral gauche du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 2 : 
			this.vue.affiche("\n" + model.getPlayer1Nickname() + " : Une barrière vous empêche de vous déplacer d'une case à gauche, veuillez réessayer\n");
			break;
		case 3 : 
			this.vue.affiche("\n" + model.getPlayer1Nickname() + " : Vous ne pouvez pas sauter à gauche du pion de " + model.getPlayer2Nickname() + " à cause du bord latéral gauche du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 4 :
			this.vue.affiche("\n" + model.getPlayer1Nickname() + " : Vous ne pouvez pas sauter à gauche du pion de " + model.getPlayer2Nickname() + " à cause d'une barrière"
					+ ", veuillez réessayer\n");
			break;
		case 5 : 
			this.vue.affiche("\n" + model.getPlayer2Nickname() + " : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord latéral gauche du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 6 : 
			this.vue.affiche("\n" + model.getPlayer2Nickname() + " : Une barrière vous empêche de vous déplacer d'une case à gauche, veuillez réessayer\n");
			break;
		case 7 : 
			this.vue.affiche("\n" + model.getPlayer2Nickname() + " : Vous ne pouvez pas sauter à gauche du pion de " + model.getPlayer1Nickname() + " à cause du bord latéral gauche du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 8 :
			this.vue.affiche("\n" + model.getPlayer2Nickname() + " : Vous ne pouvez pas sauter à gauche du pion de " + model.getPlayer1Nickname() + " à cause d'une barrière"
					+ ", veuillez réessayer\n");
			break;
		default :
			this.vue.affiche("Erreur dans le moveLeft(), cas imprévu");
			break;
		}
	}
	
	public void moveRightAffichage() {
		int right = model.moveRight();
		switch(right) {
		case 0 : 
			break;
		case 1 :
			this.vue.affiche("\n" + model.getPlayer1Nickname() + " : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord latéral droit du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 2 : 
			this.vue.affiche("\n" + model.getPlayer1Nickname() + " : Une barrière vous empêche de vous déplacer d'une case à droite, veuillez réessayer\n");
			break;
		case 3 : 
			this.vue.affiche("\n" + model.getPlayer1Nickname() + " : Vous ne pouvez pas sauter à droite du pion de " + model.getPlayer2Nickname() + " à cause du bord latéral droit du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 4 :
			this.vue.affiche("\n" + model.getPlayer1Nickname() + " : Vous ne pouvez pas sauter à droite du pion de " + model.getPlayer2Nickname() + " à cause d'une barrière"
					+ ", veuillez réessayer\n");
			break;
		case 5 : 
			this.vue.affiche("\n" + model.getPlayer2Nickname() + " : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord latéral droit du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 6 : 
			this.vue.affiche("\n" + model.getPlayer2Nickname() + " : Une barrière vous empêche de vous déplacer d'une case à droite, veuillez réessayer\n");
			break;
		case 7 : 
			this.vue.affiche("\n" + model.getPlayer2Nickname() + " : Vous ne pouvez pas sauter à droite du pion de " + model.getPlayer1Nickname() + " à cause du bord latéral droit du plateau de jeu"
					+ ", veuillez réessayer\n");
			break;
		case 8 :
			this.vue.affiche("\n" + model.getPlayer2Nickname() + " : Vous ne pouvez pas sauter à droite du pion de " + model.getPlayer1Nickname() + " à cause d'une barrière"
					+ ", veuillez réessayer\n");
			break;
		default :
			this.vue.affiche("Erreur dans le moveRight(), cas imprévu");
			break;
		}
	}
	
	
	/**
	 * 
	 * @return les 4 coordonnees de la barriere entrees par l'utilisateur. 
	 */
	public char[] askBarrier() {
		char c1 = Character.toUpperCase(sc.next().trim().charAt(0));
		
		char i1 = Character.toUpperCase(sc.next().trim().charAt(0));
		
		char c2 = Character.toUpperCase(sc.next().trim().charAt(0));
		
		char i2 = Character.toUpperCase(sc.next().trim().charAt(0));
		
		char[] coordonnees = {c1,i1,c2,i2};
		
		return coordonnees;
		
	}
	
	/**
	 * @param c un tableau de 4 coordonnees correspondant aux coordonnees de la barriere entrees par l'utilisateur.
	 * @return true si le format d'input de la barriere est correcte, c'est a dire que les coordonnees entrees sont dans
	 * l'ensemble des coordonnees du plateau de jeu, false sinon.
	 */
	public boolean checkInputs(char[] c) {
		boolean isValid = true; //si reste true, les chiffres et lettres sont valides par rapport aux limites du board
		
		if(LETTRES_AXE_Y.indexOf(c[0]) == -1) {
			this.vue.affiche("1ère coordonnée de barrière incorrecte, la 1ère coordonnée doit être une lettre entre A et I\n");
			isValid = false; 
		}
		
		if(CHIFFRES_AXE_X.indexOf(c[1]) == -1) {
			this.vue.affiche("2ème coordonnée de barrière incorrecte, la 2ème coordonnée doit être une un chiffre entre 1 et 9\n");
			isValid = false; 
		}
		
		if(LETTRES_AXE_Y.indexOf(c[2]) == -1) {
			this.vue.affiche("3ème coordonnée de barrière incorrecte, la 3ème coordonnée doit être une lettre entre A et I\n");
			isValid = false; 
		}
		
		if(CHIFFRES_AXE_X.indexOf(c[3]) == -1) {
			this.vue.affiche("4ème coordonnée de barrière incorrecte, la 4ème coordonnée doit être une un chiffre entre 1 et 9\n");
			isValid = false;
		}
		
		return isValid;
	}

}
