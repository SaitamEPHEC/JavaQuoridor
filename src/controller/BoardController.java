package controller;

import model.Barrier;
import model.Board;
import view.BoardVue;

public class BoardController {
	protected Board model;
	protected BoardVue vue;
	protected static final String LETTRES_AXE_Y = "ABCDEFGHI";
	protected static final String CHIFFRES_AXE_X = "123456789";
	
	public BoardController(Board model) {
		this.model = model;
	}
	
	
	public void addView(BoardVue vue) {
		this.vue = vue;
	}
	
	/**
	 * Place une barriere sur le plateau de jeu si elle respecte les conditions du jeu, sinon affiche la raison
	 * pour laquelle la barriere ne peut pas etre placee.
	 * @param inputs un tableau de 4 caracteres correspondants aux coordonnees d'une barriere
	 */
	public void putBarrier(char[] inputs) {
		Barrier b;
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
						if(model.pathFinder(b,'h')) {
							model.save();
							model.drawBarrierH(b);
						}
						else {
							this.vue.affiche("\n" + "Vous ne pouvez pas placer votre barrière ici : elle bloquerait le chemin d'un des joueurs\n");
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
						if(model.pathFinder(b,'v')) {
							model.save();
							model.drawBarrierV(b);
						}
						else {
							this.vue.affiche("\n" + "Vous ne pouvez pas placer votre barrière ici : elle bloquerait le chemin d'un des joueurs\n");
						}
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
	
	/**
	 * Deplace le pion vers le haut et test les differents cas d'erreur possibles en envoyant un message associé
	 */
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
	
	/**
	 * Deplace le pion vers le bas et test les differents cas d'erreur possibles en envoyant un message associé
	 */
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
	
	/**
	 * Deplace le pion vers la gauche et test les differents cas d'erreur possibles en envoyant un message associé
	 */
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
	
	/**
	 * Deplace le pion vers la droite et test les differents cas d'erreur possibles en envoyant un message associé
	 */
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
	 * @param c un tableau de 4 coordonnees correspondant aux coordonnees de la barriere entrees par l'utilisateur.
	 * @return true si les coordonnees entree correspondent a une barriere horizontale, false sinon.
	 */ 
	public boolean isBarrierH(char[] c) {
		if(c[0] == c[2] && Math.abs(CHIFFRES_AXE_X.indexOf(c[1]) - CHIFFRES_AXE_X.indexOf(c[3])) == 1) {
			if(c[0] == 'I') {
				return false;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param c un tableau de 4 coordonnees correspondant aux coordonnees de la barriere entrees par l'utilisateur.
	 * @return true si les coordonnees entree correspondent a une barriere verticale, false sinon.
	 */
	public boolean isBarrierV(char[] c) {
		if(c[1] == c[3] && Math.abs(LETTRES_AXE_Y.indexOf(c[0]) - LETTRES_AXE_Y.indexOf(c[2])) == 1) {
			if(c[1] == '9'){
				return false;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Permet de savoir si la barriere horizontale b croise une barriere verticale.
	 * @param b une barriere
	 * @return true si la barriere horizontale b croise une barriere verticale, false sinon.
	 */
	public boolean crossBarrierV(Barrier b) {
		Barrier dummyBarrier = new Barrier(b.getPosY1()+1,b.getPosX1()+1,b.getPosY1()-1,b.getPosX1()+1);
		if(model.isBarrierOnBoard(dummyBarrier)) {
			return true; 
		}
		else {
			return false;
		}
	}
	
	/**
	 * Permet de savoir si la barriere verticale b croise une barriere horizontale.
	 * @param b une barriere
	 * @return true si la barriere verticale b croise une barriere horizontale, false sinon.
	 */
	public boolean crossBarrierH(Barrier b) {
		Barrier dummyBarrier = new Barrier(b.getPosY1()-1,b.getPosX1()-1,b.getPosY1()-1,b.getPosX1()+1);
		if(model.isBarrierOnBoard(dummyBarrier)) {
			return true; 
		}
		else {
			return false;
		}
	}
	
	/**
	 * Traduit les coordonnees de la barriere HORIZONTALE entrees par l'utilisateur en leur coordonnees correspondantes dans le board 
	 * et comprehensibles par celui-ci. Instancie ensuite une barriere en lui assignant ses coordonnees dans le board.
	 * 
	 * @param c	un tableau de 4 coordonnees correspondant aux coordonnees de la barriere entrees par l'utilisateur.
	 * @return une barriere HORIZONTALE avec ses 4 coordonnees dans le board.
	 * 
	 */
	public Barrier translateH(char[] c) {
		int posY1 = translateLetterH(c[0]);
		int posX1 = translateNumberH(c[1]);
		int posY2 = translateLetterH(c[2]);
		int posX2 = translateNumberH(c[3]);
		
		Barrier b = new Barrier(posY1, posX1, posY2, posX2);
		
		b.reordering();
		
		return b;		
	}
	
	/**
	 * Traduit les coordonnees de la barriere VERTICALE entrees par l'utilisateur en leur coordonnees correspondantes dans le board 
	 * et comprehensibles par celui-ci. Instancie ensuite une barriere en lui assignant ses coordonnees dans  le board.
	 * 
	 * @param c un tableau de 4 coordonnees correspondant aux coordonnees de la barriere entrees par l'utilisateur.
	 * @return une barriere VERTICALE avec ses 4 coordonnees dans le board.
	 */
	public Barrier translateV(char[] c) {
		int posY1 = translateLetterV(c[0]);
		int posX1 = translateNumberV(c[1]);
		int posY2 = translateLetterV(c[2]);
		int posX2 = translateNumberV(c[3]);
		
		Barrier b = new Barrier(posY1, posX1, posY2, posX2);
		
		b.reordering();
		
		return b;
		
	}
	
	/**
	 * @param c Une lettre entree par l'utilisateur et correspondant a la coordonnee Y d'une des 2 positions d'une barriere HORIZONTALE.
	 * @return : Cette fonction traduit la lettre entree dans sa coordonnee Y correspondante dans le board et comprehensible par celui-ci. 
	 */
	public int translateLetterH(char c) {
		switch(c) {
			case 'A' :
				return 15;
			case 'B' : 
				return 13;
			case 'C' :
				return 11;
			case 'D' : 
				return 9;
			case 'E' : 
				return 7;
			case 'F' :
				return 5;
			case 'G' : 
				return 3; 
			case 'H' : 
				return 1;
			default :
				return -1;
		}
	} 
	
	/**
	 * @param c Une lettre entree par l'utilisateur et correspondant a la coordonnee Y d'une des 2 positions d'une barriere VERTICALE.
	 * @return : Cette fonction traduit la lettre entree dans sa coordonnee Y correspondante dans le board et comprehensible par celui-ci. 
	 */
	public int translateLetterV(char c) {
		switch(c) {
			case 'A' :
				return 16;
			case 'B' : 
				return 14;
			case 'C' :
				return 12;
			case 'D' : 
				return 10;
			case 'E' : 
				return 8;
			case 'F' :
				return 6;
			case 'G' : 
				return 4; 
			case 'H' : 
				return 2;
			case 'I' : 
				return 0;
			default :
				return -1;
		}
	} 
	
	
	/**
	 * @param i Un chiffre entre par l'utilisateur et correspondant a la coordonnee X d'une des 2 positions d'une barriere HORIZONTALE.
	 * @return : Cette fonction traduit le chiffre entre dans sa coordonnee X correspondante dans le board et comprehensible par celui-ci. 
	 */
	public int translateNumberH(char i) {
		switch(i) {
			case '1' :
				return 0;
			case '2' : 
				return 2;
			case '3' :
				return 4;
			case '4' : 
				return 6;
			case '5' : 
				return 8;
			case '6' :
				return 10;
			case '7' : 
				return 12; 
			case '8' : 
				return 14;
			case '9' : 
				return 16;
			default :
				return -1;
		}
	}
	
	/**
	 * @param i Un chiffre entre par l'utilisateur et correspondant a la coordonnee X d'une des 2 positions d'une barriere VERTICALE.
	 * @return : Cette fonction traduit le chiffre entre dans sa coordonnee X correspondante dans le board et comprehensible par celui-ci. 
	 */
	public int translateNumberV(char i) {
		switch(i) {
			case '1' :
				return 1;
			case '2' : 
				return 3;
			case '3' :
				return 5;
			case '4' : 
				return 7;
			case '5' : 
				return 9;
			case '6' :
				return 11;
			case '7' : 
				return 13; 
			case '8' : 
				return 15;
			default :
				return -1;
		}
	}
	
	public boolean rewind() {
		this.vue.affiche("Vous avez demandé a revenir en arriere");
		return model.rewind();
	}
}



