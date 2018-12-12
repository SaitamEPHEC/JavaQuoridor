package controller;

import java.util.Scanner;

import model.Barrier;
import model.Board;
import view.BoardVue;
import view.BoardVueConsole;

public class BoardControllerConsole extends BoardController{
	private BoardVueConsole vueConsole;
	private Scanner sc; 
	
	public BoardControllerConsole(Board model) {
		super(model);
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
	public boolean checkFormatBarrier(String[] c) {
		String listeLettres = "ABCDEFGHI";
		boolean isValid = true; //si reste true, les chiffres et lettres sont valides par rapport aux limites du board
		
		if((c[0].length()!=1) || (listeLettres.indexOf(c[0]) == -1)) {
			this.vue.affiche("1ère coordonnée de barrière incorrecte, la 1ère coordonnée doit être une lettre entre A et I\n");
			isValid = false; 
		}
		
		if((c[1].length()!=1) || (listeLettres.indexOf(c[1]) == -1)) {
			this.vue.affiche("2ème coordonnée de barrière incorrecte, la 2ème coordonnée doit être une un chiffre entre 1 et 9\n");
			isValid = false; 
		}
		
		if((c[2].length()!=1) || (listeLettres.indexOf(c[2]) == -1)) {
			this.vue.affiche("3ème coordonnée de barrière incorrecte, la 3ème coordonnée doit être une lettre entre A et I\n");
			isValid = false; 
		}
		
		if((c[3].length()!=1) || (listeLettres.indexOf(c[3]) == -1)) {
			this.vue.affiche("4ème coordonnée de barrière incorrecte, la 4ème coordonnée doit être une un chiffre entre 1 et 9\n");
			isValid = false;
		}
		
		return isValid;
	}
	
	@Override
	public void putBarrier() {
		String[] coordonnees = askBarrier();
		
		if(checkFormatBarrier(coordonnees)) {
			//est mis a false si l'utilisateur a essaye de placer une barriere horizontale sur la ligne I
			//ou une barriere verticale sur la colonne 9 (hors des limites du plateau de jeu). Sinon, reste a true.
			boolean caseNotOutOfBounds= true;
			
			//cas de barriere horizontale
			if(c1.equals(c2)) {
				if(c1.equals("I") || c2.equals("I")) {
					affiche("Position de la barrière incorrecte. Vous ne pouvez pas placer une barrière horizontale "
							+ "sur la ligne I (Hors des limites du plateau de jeu)\n");
					printHelp();
					caseNotOutOfBounds = false;
				}
				else {
					posY1 = translateLetterToBoardH(c1);
					posX1 = translateNumberToBoardH(i1);
					posY2 = translateLetterToBoardH(c2);
					posX2 = translateNumberToBoardH(i2);
				}
			}
			
			//cas de barriere verticale
			if(i1 == i2) {
				if(i1 == 9 || i2 == 9) {
					affiche("Position de la barrière incorrecte. Vous ne pouvez pas placer une barrière verticale "
							+ "sur la colonne 9 (Hors des limites du plateau de jeu)\n");
					printHelp();
					caseNotOutOfBounds = false; 
				}
				else {
					posY1 = translateLetterToBoardV(c1);
					posX1 = translateNumberToBoardV(i1);
					posY2 = translateLetterToBoardV(c2);
					posX2 = translateNumberToBoardV(i2);
				}
			}
		}
	}
	
	/**
	 * @param un tableau de 4 coordonnees correspondant aux coordonnees de la barriere entrees par l'utilisateur.
	 * @return true si l'utilisateur a essaye de placer une barriere horizontale sur la ligne I
	 * ou une barriere verticale sur la colonne 9 (hors des limites du plateau de jeu) et affiche l'erreur en console. Sinon, return false.
	 */
	
	public boolean SpecificBarrierCase(String[] c) {
		
		//cas de barriere horizontale
		if(c[0].equals(c[2])) {
			if(c[0].equals("I") || c[2].equals("I")) {
				this.vue.affiche("Position de la barrière incorrecte. Vous ne pouvez pas placer une barrière horizontale "
							+ "sur la ligne I (Hors des limites du plateau de jeu)\n");
				this.vueConsole.printHelp();
				return true;
			}
			else {
				return false;
			}
		}
		//cas de barriere verticale
		else if(c[1].equals(c[3])) {
			if(c[1].equals("9") || c[3].equals("9")) {
				this.vue.affiche("Position de la barrière incorrecte. Vous ne pouvez pas placer une barrière verticale "
						+ "sur la colonne 9 (Hors des limites du plateau de jeu)\n");
				this.vueConsole.printHelp();
				return true;
			}
			else {
				return false;
			}
		}
		else {
			
		}
	}

	/**
	 * @param un tableau de 4 coordonnees correspondant aux coordonnees de la barriere entrees par l'utilisateur.
	 * @return true si les coordonnees de la barriere entrees en parametre correspondent bien a celles d'une barriere verticale ou horizontale, c'est a dire
	 * que soit les 2 positions Y sont les memes et les 2 positions X sont adjacentes, soit les 2 positions X sont les memes et les 2 positions Y sont
	 * adjacentes.
	 */
	public boolean isBarrierHOrV(String[] c) {
		//test que les positions donnees sont bien celles d'une barriere horizontale OU verticale
		if (!(((posY1 == posY2) && Math.abs(posX1 - posX2) == 2) || ((posX1 == posX2) && Math.abs(posY1 - posY2) == 2))) {
			affiche("Position de la barrière incorrecte.\nExemple de barrière horizontale : A 1 B 1\nExemple de barrière verticale : A 5 A 6\n");
			printHelp();
		}
	}
	
	@Override
	public boolean isValidBarrier(Barrier b) {
		
		return false;
	}

	@Override
	public void movePawn() {
		
		
	}
	
	
	
	/**
	 * @pre : Prend une lettre du board en parametre qui correspond a une position Y d'une barriere HORIZONTALE
	 * @return : L'utilisateur entre une lettre comme etant une position Y d'une des 2 positions d'une barriere HORIZONTALE. Cette fonction 
	 * 		   traduit la lettre entree dans sa position en entier dans le board. 
	 */
	public int translateLetterToBoardH(String c) {
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
	 * @pre : Prend une lettre du board en parametre qui correspond a une position Y d'une barriere VERTICALE
	 * @return : L'utilisateur entre une lettre comme etant une position Y d'une des 2 positions d'une barriere VERTICALE. Cette fonction 
	 * 		   traduit la lettre entree dans sa position en entier dans le board. 
	 */
	public int translateLetterToBoardV(String c) {
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
	 * @pre : Prend un chiffre du board en parametre qui correspond a une position X d'une barriere HORIZONTALE
	 * @return : L'utilisateur entre un chiffre comme etant une position X d'une des 2 positions d'une barriere HORIZONTALE. Cette fonction 
	 * 		   traduit le chiffre entree dans sa position en entier dans le board. 
	 */
	public int translateNumberToBoardH(int i) {
		switch(i) {
			case 1 :
				return 0;
			case 2 : 
				return 2;
			case 3 :
				return 4;
			case 4 : 
				return 6;
			case 5 : 
				return 8;
			case 6 :
				return 10;
			case 7 : 
				return 12; 
			case 8 : 
				return 14;
			case 9 : 
				return 16;
			default :
				return -1;
		}
	}
	
	/**
	 * @pre : Prend un chiffre du board en parametre qui correspond a une position X d'une barriere VERTICALE
	 * @return : L'utilisateur entre un chiffre comme etant une position X d'une des 2 positions d'une barriere VERTICALE. Cette fonction 
	 * 		   traduit le chiffre entree dans sa position en entier dans le board. 
	 */
	public int translateNumberToBoardV(int i) {
		switch(i) {
			case 1 :
				return 1;
			case 2 : 
				return 3;
			case 3 :
				return 5;
			case 4 : 
				return 7;
			case 5 : 
				return 9;
			case 6 :
				return 11;
			case 7 : 
				return 13; 
			case 8 : 
				return 15;
			default :
				return -1;
		}
	}

}
