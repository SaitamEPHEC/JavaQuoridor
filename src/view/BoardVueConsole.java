package view;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;

import controller.BoardController;
import model.Board;

public class BoardVueConsole extends BoardVue {
	
	protected Scanner sc;
	private Board board = new Board();

	public BoardVueConsole(Board model,
			BoardController controller) {
		super(model, controller);
		this.board = model;
		update(null, null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();	
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(model);	
	}
	
	private void printHelp(){
		affiche("Pour d�placer votre pion, Entrez \"P\" puis appuyez sur Enter.\n"
				+ "Ensuite, tapez \"U\" pour d�placer votre pion d'une case en haut, \"D\" pour d�placer votre pion d'une case en bas,\n"
				+ "\"L\" pour d�placer votre pion d'une case � gauche et \"R\" pour d�placer votre pion d'une case � droite \n");
		affiche("Pour placer une barri�re, Entrez \"B\" puis appuyez sur Enter.\n"
				+ "Ensuite, tapez les 4 coordonn�es de la barri�re suivant les coordonn�es possible sur le plateau de jeu"
				+ ". Exemple : A 1 B 1 ou A 5 A 6\n");
	}
	
	private void drawBoardConsole() {
		
		for(int i = 0;i<this.board.getLength();i++) {
			for(int j = 0;j<this.board.getLength();j++) {
				if(j == this.board.getLength()-1) {
					System.out.println(this.board.getBoard()[i][j]);
				}
				else {
					System.out.print(this.board.getBoard()[i][j]);
				}
			}
		}
	}
	
	
	
	//TODO : � MODIFIER avec notre projet
	private class ReadInput implements Runnable{
		public void run() {
			String listeLettres = "ABCDEFGHI";
			printHelp();
			drawBoardConsole();
			while(true){
				try {
					String c = sc.next().toUpperCase();
					switch(c){
						case "B" : 	//Barriere
							
								boolean isBarrier = true; //si reste true, les chiffres et lettres sont valides par rapport au board
								
								int posY1 = 0; //Position Y1 de la barriere qui sera remplie si l'utilisateur entre une lettre valide
								int posX1 = 0; //Position X1 de la barriere qui sera remplie si l'utilisateur entre un chiffre valide
								int posY2 = 0; //Position Y2 de la barriere qui sera remplie si l'utilisateur entre une lettre valide
								int posX2 = 0; //Position X2 de la barriere qui sera remplie si l'utilisateur entre un chiffre valide
								
								String c1 = sc.next().toUpperCase();
								if((c1.length()!=1) || (listeLettres.indexOf(c1) == -1)) {
									affiche("Premi�re lettre incorrecte, entrez une seule lettre entre A et I\n");
									isBarrier = false; 
								}
								
								int i1 = sc.nextInt();
								if(i1<1 || i1> 9){
									affiche("Premier numero de case incorrecte, entrez un chiffre entre 1 et 9\n");
									isBarrier = false;
								}
								
								String c2 = sc.next().toUpperCase();
								if((c2.length()!=1) || (listeLettres.indexOf(c2) == -1)){
									affiche("Seconde lettre incorrecte, entrez une seule lettre entre A et I\n");
									isBarrier = false;
								}
								
								int i2 = sc.nextInt();
								if(i2<1 || i2> 9){
									affiche("Second numero de case incorrect, entrez un chiffre entre 1 et 9\n");
									isBarrier = false;
								}
								
								if(isBarrier) {
									
									boolean isBarrierHOrV= true;
									
									//cas de barriere horizontale
									if(c1.equals(c2)) {
										if(c1.equals("I") || c2.equals("I")) {
											affiche("Position de la barri�re incorrecte. Vous ne pouvez pas placer une barri�re horizontale"
													+ "sur la ligne I (Hors des limites du plateau de jeu)");
											printHelp();
											isBarrierHOrV = false; 
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
											affiche("Position de la barri�re incorrecte. Vous ne pouvez pas placer une barri�re verticale"
													+ "sur la colonne 9 (Hors des limites du plateau de jeu)");
											printHelp();
											isBarrierHOrV = false; 
										}
										else {
											posY1 = translateLetterToBoardV(c1);
											posX1 = translateNumberToBoardV(i1);
											posY2 = translateLetterToBoardV(c2);
											posX2 = translateNumberToBoardV(i2);
										}
									}

									if(isBarrierHOrV) {
										//test que les positions donnees sont bien celles d'une barriere horizontale OU verticale
										if (!(((posY1 == posY2) && Math.abs(posX1 - posX2) == 2) || ((posX1 == posX2) && Math.abs(posY1 - posY2) == 2))) {
											affiche("Position de la barri�re incorrecte.\nExemple de barri�re horizontale : A 1 B 1\nExemple de barri�re verticale : A 5 A 6\n");
											printHelp();
										}
										else {
											if(posY1 == posY2) {
												board.drawBarrierH(posY1, posX1, posY2, posX2);
												printHelp();
												drawBoardConsole();
											}
											else if(posX1 == posX2) {
												board.drawBarrierV(posY1, posX1, posY2, posX2);
												printHelp();
												drawBoardConsole();
											}
										}
									}
								}
								else {
									printHelp();
								}
								
							break;
						case "P" : //Pion
							String m = sc.next().toUpperCase();
							switch(m) {
								case "U" : 
									int up = board.moveUp();
									switch(up) {
									case 1 :
										affiche("Player 1 : Vous ne pouvez pas faire ce d�placement, vous �tes bloqu� contre le bord sup�rieur du plateau de jeu"
												+ ", veuillez r�essayer\n");
										printHelp();
										break;
									case 2 : 
										affiche("Player 1 : Une barri�re vous emp�che de vous d�placer d'une case en haut, veuillez r�essayer\n");
										printHelp();
										break;
									case 3 : 
										affiche("Player 2 : Vous ne pouvez pas faire ce d�placement, vous �tes bloqu� contre le bord sup�rieur du plateau de jeu"
												+ ", veuillez r�essayer\n");
										printHelp();
										break;
									case 4 : 
										affiche("Player 2 : Une barri�re vous emp�che de vous d�placer d'une case en haut, veuillez r�essayer\n");
										printHelp();
										break;
									default : //Mouvement correct 
										printHelp();
										drawBoardConsole();
										break;
									}
									break;
								case "D" : 
									int down = board.moveDown();
									switch(down) {
									case 1 :
										affiche("Player 1 : Vous ne pouvez pas faire ce d�placement, vous �tes bloqu� contre le bord inf�rieur du plateau de jeu"
												+ ", veuillez r�essayer\n");
										printHelp();
										break;
									case 2 : 
										affiche("Player 1 : Une barri�re vous emp�che de vous d�placer d'une case en bas, veuillez r�essayer\n");
										printHelp();
										break;
									case 3 : 
										affiche("Player 2 : Vous ne pouvez pas faire ce d�placement, vous �tes bloqu� contre le bord inf�rieur du plateau de jeu"
												+ ", veuillez r�essayer\n");
										printHelp();
										break;
									case 4 : 
										affiche("Player 2 : Une barri�re vous emp�che de vous d�placer d'une case en bas, veuillez r�essayer\n");
										printHelp();
										break;
									default : //Mouvement correct 
										printHelp();
										drawBoardConsole();
										break;
									}
									break;
								case "L" : 
									int left = board.moveLeft();
									switch(left) {
									case 1 :
										affiche("Player 1 : Vous ne pouvez pas faire ce d�placement, vous �tes bloqu� contre le bord lat�ral gauche du plateau de jeu"
												+ ", veuillez r�essayer\n");
										printHelp();
										break;
									case 2 : 
										affiche("Player 1 : Une barri�re vous emp�che de vous d�placer d'une case � gauche, veuillez r�essayer\n");
										printHelp();
										break;
									case 3 : 
										affiche("Player 2 : Vous ne pouvez pas faire ce d�placement, vous �tes bloqu� contre le bord lat�ral gauche du plateau de jeu"
												+ ", veuillez r�essayer\n");
										printHelp();
										break;
									case 4 : 
										affiche("Player 2 : Une barri�re vous emp�che de vous d�placer d'une case � gauche, veuillez r�essayer\n");
										printHelp();
										break;
									default : //Mouvement correct
										printHelp();
										drawBoardConsole();
										break;
									}
									break;
								case "R" : 
									int right = board.moveRight();
									switch(right) {
									case 1 :
										affiche("Player 1 : Vous ne pouvez pas faire ce d�placement, vous �tes bloqu� contre le bord lat�ral droit du plateau de jeu"
												+ ", veuillez r�essayer\n");
										printHelp();
										break;
									case 2 : 
										affiche("Player 1 : Une barri�re vous emp�che de vous d�placer d'une case � droite, veuillez r�essayer\n");
										printHelp();
										break;
									case 3 : 
										affiche("Player 2 : Vous ne pouvez pas faire ce d�placement, vous �tes bloqu� contre le bord lat�ral droit du plateau de jeu"
												+ ", veuillez r�essayer\n");
										printHelp();
										break;
									case 4 : 
										affiche("Player 2 : Une barri�re vous emp�che de vous d�placer d'une case � droite, veuillez r�essayer\n");
										printHelp();
										break;
									default : //Mouvement correct 
										printHelp();
										drawBoardConsole();
										break;
									}
									break;
								default : //On ne rentre normalement jamais dedans
									affiche("Mouvement incorrect, Vous avez entr� autre chose que \"U\" \"D\" \"L\" ou \"R\" comme 2�me charact�re"
											+ ",  veuillez r�essayer\n");
									printHelp();
									break;
							}
							break;
						default : 
							affiche("Mouvement incorrect : Vous avez entr� autre chose que \"B\" ou \"P\" comme 1er charact�re, veuillez r�essayer\n");
							printHelp();
							break;
					}
				}
				catch(InputMismatchException e){
					affiche("Format d'input incorrect");
					printHelp();
				}
			}
		}
	}

	@Override // TODO A modifier
	public void affiche(String string) {
		System.out.println(string);
		
	}
	
	/*
	 * @pre : Prend une lettre du board en parametre qui correspond a une position Y d'une barriere HORIZONTALE
	 * @post : L'utilisateur entre une lettre comme etant une position Y d'une des 2 positions d'une barriere HORIZONTALE. Cette fonction 
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
	
	/*
	 * @pre : Prend une lettre du board en parametre qui correspond a une position Y d'une barriere VERTICALE
	 * @post : L'utilisateur entre une lettre comme etant une position Y d'une des 2 positions d'une barriere VERTICALE. Cette fonction 
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
	
	
	/*
	 * @pre : Prend un chiffre du board en parametre qui correspond a une position X d'une barriere HORIZONTALE
	 * @post : L'utilisateur entre un chiffre comme etant une position X d'une des 2 positions d'une barriere HORIZONTALE. Cette fonction 
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
	
	/*
	 * @pre : Prend un chiffre du board en parametre qui correspond a une position X d'une barriere VERTICALE
	 * @post : L'utilisateur entre un chiffre comme etant une position X d'une des 2 positions d'une barriere VERTICALE. Cette fonction 
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
