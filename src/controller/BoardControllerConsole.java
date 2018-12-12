package controller;

import java.util.Scanner;

import model.Barrier;
import model.Board;
import view.BoardVue;
import view.BoardVueConsole;

public class BoardControllerConsole extends BoardController{
	private BoardVueConsole vueConsole;
	private Scanner sc; 
	private static final String LETTRES_AXE_Y = "ABCDEFGHI";
	private static final String CHIFFRES_AXE_X = "123456789";
	
	public BoardControllerConsole(Board model) {
		super(model);
	}

	@Override
	public boolean isValidBarrier(Barrier b) {
		
		return false;
	}

	@Override
	public void movePawn() {
		
		
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
				this.vue.affiche("Les coordonnées entrées ne correspondent pas à une barrière horizontale ou verticale.\nExemple de "
						+ "barrière horizontale : A 1 A 2\nExemple de barrière verticale : G 8 H 8\n");
			}
		}
		else {
		}
	}
	
	/**
	 * 
	 * @return les 4 coordonnees de la barriere entrees par l'utilisateur. 
	 */
	public String[] askBarrier() {
		
		sc = new Scanner(System.in);
		
		String c1 = sc.next().toUpperCase();
		
		String i1 = sc.next().toUpperCase();
		
		String c2 = sc.next().toUpperCase();
		
		String i2 = sc.next().toUpperCase();
		
		String[] coordonnees = {c1,i1,c2,i2};
		
		return coordonnees;
		
	}
	
	/**
	 * @param un tableau de 4 coordonnees correspondant aux coordonnees de la barriere entrees par l'utilisateur.
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
	
	/**
	 * 
	 * @param c un tableau de 4 coordonnees correspondant aux coordonnees de la barriere entrees par l'utilisateur.
	 * @return true si les coordonnees entree correspondent a une barriere horizontale, false sinon.
	 */ 
	public boolean isBarrierH(String[] c) {
		if(c[0].equals(c[2]) && Math.abs(CHIFFRES_AXE_X.indexOf(c[1]) - CHIFFRES_AXE_X.indexOf(c[3])) == 1) {
			if(c[0].equals("I")) {
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
	public boolean isBarrierV(String[] c) {
		if(c[1].equals(c[3]) && Math.abs(LETTRES_AXE_Y.indexOf(c[0]) - LETTRES_AXE_Y.indexOf(c[2])) == 1) {
			if(c[1].equals("9")) {
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
	public Barrier translateH(String[] c) {
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
	 * et comprehensibles par celui-ci. Instancie ensuite une barriere en lui assignant ses coordonnees dans le board.
	 * 
	 * @param c un tableau de 4 coordonnees correspondant aux coordonnees de la barriere entrees par l'utilisateur.
	 * @return une barriere VERTICALE avec ses 4 coordonnees dans le board.
	 */
	public Barrier translateV(String[] c) {
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
	public int translateLetterH(String c) {
		switch(c) {
			case "A" :
				return 15;
			case "B" : 
				return 13;
			case "C" :
				return 11;
			case "D" : 
				return 9;
			case "E" : 
				return 7;
			case "F" :
				return 5;
			case "G" : 
				return 3; 
			case "H" : 
				return 1;
			default :
				return -1;
		}
	} 
	
	/**
	 * @param c Une lettre entree par l'utilisateur et correspondant a la coordonnee Y d'une des 2 positions d'une barriere VERTICALE.
	 * @return : Cette fonction traduit la lettre entree dans sa coordonnee Y correspondante dans le board et comprehensible par celui-ci. 
	 */
	public int translateLetterV(String c) {
		switch(c) {
			case "A" :
				return 16;
			case "B" : 
				return 14;
			case "C" :
				return 12;
			case "D" : 
				return 10;
			case "E" : 
				return 8;
			case "F" :
				return 6;
			case "G" : 
				return 4; 
			case "H" : 
				return 2;
			case "I" : 
				return 0;
			default :
				return -1;
		}
	} 
	
	
	/**
	 * @param i Un chiffre entre par l'utilisateur et correspondant a la coordonnee X d'une des 2 positions d'une barriere HORIZONTALE.
	 * @return : Cette fonction traduit le chiffre entre dans sa coordonnee X correspondante dans le board et comprehensible par celui-ci. 
	 */
	public int translateNumberH(String i) {
		switch(i) {
			case "1" :
				return 0;
			case "2" : 
				return 2;
			case "3" :
				return 4;
			case "4" : 
				return 6;
			case "5" : 
				return 8;
			case "6" :
				return 10;
			case "7" : 
				return 12; 
			case "8" : 
				return 14;
			case "9" : 
				return 16;
			default :
				return -1;
		}
	}
	
	/**
	 * @param i Un chiffre entre par l'utilisateur et correspondant a la coordonnee X d'une des 2 positions d'une barriere VERTICALE.
	 * @return : Cette fonction traduit le chiffre entre dans sa coordonnee X correspondante dans le board et comprehensible par celui-ci. 
	 */
	public int translateNumberV(String i) {
		switch(i) {
			case "1" :
				return 1;
			case "2" : 
				return 3;
			case "3" :
				return 5;
			case "4" : 
				return 7;
			case "5" : 
				return 9;
			case "6" :
				return 11;
			case "7" : 
				return 13; 
			case "8" : 
				return 15;
			default :
				return -1;
		}
	}

}
