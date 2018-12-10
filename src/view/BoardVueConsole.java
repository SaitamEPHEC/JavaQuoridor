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
		String[][] temp = this.board.getContours();
		printHelp();
/*		for(int i = 0;i<this.board.getLength();i++) {
	*		for(int j = 0;j<this.board.getLength();j++) {
	*			if(j == this.board.getLength()-1) {
	*				System.out.println(this.board.getBoard()[i][j]);
	*			}
	*			else {
	*				System.out.print(this.board.getBoard()[i][j]);
	*			}
	*		}
	*	}	
	*/
		for(int i = 0;i<this.board.getLength();i++) {
			for(int j = 0;j<this.board.getLength();j++) {
				if(i == 0 && j == 0) { // premiere case
					for(int l = 0;l<temp.length-1;l++) { // premiere ligne de contour
						System.out.print(temp[i][l]); // affiche 1 a 8
					}
					System.out.println(temp[i][temp.length-1]); // affiche 9 + \n
					//System.out.println("");
					System.out.print("I    ");
				}
				else { // board normal plus contour
					if(j == 0 && (i%2 == 0)){ // premiere colonne contour
						System.out.print(temp[i][j]); // H - A
					}
					if(j == 0 && (i%2 != 0)) { // allignement des lignes sans lettres
						System.out.print("   ");
					}
						
					if(j == (this.board.getLength()-1)) {
						System.out.println(this.board.getBoard()[i][j]); // board
					}
					else {
						System.out.print(this.board.getBoard()[i][j]); // board
					}
				}
			}
		}	
	}
	
	private void printHelp(){
		affiche("Pour déplacer votre pion, Entrez \"P\" puis appuyez sur Enter.\n"
				+ "Ensuite, tapez \"U\" pour déplacer votre pion d'une case en haut, \"D\" pour déplacer votre pion d'une case en bas,\n"
				+ "\"L\" pour déplacer votre pion d'une case à gauche et \"R\" pour déplacer votre pion d'une case à droite \n");
		affiche("Pour placer une barrière, Entrez \"B\" puis appuyez sur Enter.\n"
				+ "Ensuite, tapez les 4 coordonnées de la barrière suivant les coordonnées possible sur le plateau de jeu"
				+ ". Exemple : A 1 B 1 ou A 5 A 6\n");
	}

	
	//TODO : à MODIFIER avec notre projet
	private class ReadInput implements Runnable{
		public void run() {
			String listeLettres = "ABCDEFGHI";
			while(true){
				try {
					String c = sc.next().toUpperCase();
					switch(c){
						case "B" : 	//Barriere
							
								boolean isBarrier = true; //si reste true, les chiffres et lettres sont valides par rapport au board
								
								int posY1 = 0; //Position Y1 de la barriere dans le board qui sera remplie si l'utilisateur entre une lettre valide
								int posX1 = 0; //Position X1 de la barriere dans le board qui sera remplie si l'utilisateur entre un chiffre valide
								int posY2 = 0; //Position Y2 de la barriere dans le board qui sera remplie si l'utilisateur entre une lettre valide
								int posX2 = 0; //Position X2 de la barriere dans le board qui sera remplie si l'utilisateur entre un chiffre valide
								
								String c1 = sc.next().toUpperCase();
								if((c1.length()!=1) || (listeLettres.indexOf(c1) == -1)) {
									affiche("Première lettre incorrecte, entrez une seule lettre entre A et I\n");
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
											affiche("Position de la barrière incorrecte. Vous ne pouvez pas placer une barrière horizontale "
													+ "sur la ligne I (Hors des limites du plateau de jeu)\n");
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
											affiche("Position de la barrière incorrecte. Vous ne pouvez pas placer une barrière verticale "
													+ "sur la colonne 9 (Hors des limites du plateau de jeu)\n");
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
											affiche("Position de la barrière incorrecte.\nExemple de barrière horizontale : A 1 B 1\nExemple de barrière verticale : A 5 A 6\n");
											printHelp();
										}
										else {
											//barriere horizontale
											if(posY1 == posY2) {
												//test si aucun symbole de barriere horizontale "――" n'existe deja aux positions donnees pour placer
												//la barriere horizontale
												if(board.getBoard()[posY1][posX1] == "――" || board.getBoard()[posY2][posX2] == "――") {
													affiche("Position de la barrière incorrecte : Vous ne pouvez pas placer une barrière sur une"
															+ " barrière déjà existante.\n");
													printHelp();
												}
												else {
													//Inverse la premiere coordonnee X entree avec la seconde coordonnee X entree
													//si la premiere coordonnee X entree par l'utilisateur est plus grande que la seconde.
													//Cela permet d'avoir toujours en posX1 la coordonnee X la plus petite. 
													if (posX1 > posX2){
														int posX = posX1;
														posX1 = posX2;
														posX2 = posX;
													}
													
													//test si la barriere horizontale entree est entre 2 symboles de barriere verticales
													if (board.getBoard()[posY1+1][posX1+1] == " | " && board.getBoard()[posY1-1][posX1+1] == " | "){
														//test si la barriere horizontale entree passe a travers une barriere verticale existante
														if(board.isBarrierOnBoard(posY1+1, posX1+1,posY1-1,posX1+1)){
															affiche("Position de la barrière incorrecte : Vous ne pouvez pas croiser votre barrière horizontale avec "
																	+ "une barrière verticale déjà existante.\n");
															printHelp();
														}
														//la barriere horizontale entree est entre 2 barrieres verticales existantes et peut donc etre placee
														else{
															board.drawBarrierH(posY1, posX1, posY2, posX2);
														}
													}
													else {
														board.drawBarrierH(posY1, posX1, posY2, posX2);
													}
												}
											}
											//barriere verticale
											if(posX1 == posX2) {
												//test si aucun symbole de barriere verticale " | " n'existe deja aux positions donnees pour placer
												//la barriere verticale
												if(board.getBoard()[posY1][posX1] == " | " || board.getBoard()[posY2][posX2] == " | ") {
													affiche("Position de la barrière incorrecte : Vous ne pouvez pas placer une barrière sur une"
															+ " barrière déjà existante.\n");
													printHelp();
												}
												else {
													//Inverse la premiere coordonnee Y entree avec la seconde coordonnee Y entree
													//si la premiere coordonnee Y entree par l'utilisateur est plus petite que la seconde.
													//Cela permet d'avoir toujours en posY1 la coordonnee Y la plus grande. 
													if (posY1 < posY2){
														int posY = posY1;
														posY1 = posY2;
														posY2 = posY;
													}
													
													//test si la barriere verticale entree est entre 2 symboles de barriere horizontales
													if (board.getBoard()[posY1-1][posX1-1] == "――" && board.getBoard()[posY1-1][posX1+1] == "――"){
														//test si la barriere verticale entree passe a travers une barriere horizontale existante
														if(board.isBarrierOnBoard(posY1-1, posX1-1,posY1-1,posX1+1)){
															affiche("Position de la barrière incorrecte : Vous ne pouvez pas croiser votre barrière verticale avec "
																	+ "une barrière horizontale déjà existante.\n");
															printHelp();
														}
														//la barriere verticale entree est entre 2 barrieres horizontales existantes et peut donc etre placee
														else{
															board.drawBarrierV(posY1, posX1, posY2, posX2);
														}
													}
													else {
														board.drawBarrierV(posY1, posX1, posY2, posX2);
													}
												}
												
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
										affiche("Player 1 : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord supérieur du plateau de jeu"
												+ ", veuillez réessayer\n");
										printHelp();
										break;
									case 2 : 
										affiche("Player 1 : Une barrière vous empêche de vous déplacer d'une case en haut, veuillez réessayer\n");
										printHelp();
										break;
									case 3 : 
										affiche("Player 2 : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord supérieur du plateau de jeu"
												+ ", veuillez réessayer\n");
										printHelp();
										break;
									case 4 : 
										affiche("Player 2 : Une barrière vous empêche de vous déplacer d'une case en haut, veuillez réessayer\n");
										printHelp();
										break;
									default : //Mouvement correct 
										break;
									}
									break;
								case "D" : 
									int down = board.moveDown();
									switch(down) {
									case 1 :
										affiche("Player 1 : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord inférieur du plateau de jeu"
												+ ", veuillez réessayer\n");
										printHelp();
										break;
									case 2 : 
										affiche("Player 1 : Une barrière vous empêche de vous déplacer d'une case en bas, veuillez réessayer\n");
										printHelp();
										break;
									case 3 : 
										affiche("Player 2 : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord inférieur du plateau de jeu"
												+ ", veuillez réessayer\n");
										printHelp();
										break;
									case 4 : 
										affiche("Player 2 : Une barrière vous empêche de vous déplacer d'une case en bas, veuillez réessayer\n");
										printHelp();
										break;
									default : //Mouvement correct 
										break;
									}
									break;
								case "L" : 
									int left = board.moveLeft();
									switch(left) {
									case 1 :
										affiche("Player 1 : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord latéral gauche du plateau de jeu"
												+ ", veuillez réessayer\n");
										printHelp();
										break;
									case 2 : 
										affiche("Player 1 : Une barrière vous empêche de vous déplacer d'une case à gauche, veuillez réessayer\n");
										printHelp();
										break;
									case 3 : 
										affiche("Player 2 : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord latéral gauche du plateau de jeu"
												+ ", veuillez réessayer\n");
										printHelp();
										break;
									case 4 : 
										affiche("Player 2 : Une barrière vous empêche de vous déplacer d'une case à gauche, veuillez réessayer\n");
										printHelp();
										break;
									default : //Mouvement correct
										break;
									}
									break;
								case "R" : 
									int right = board.moveRight();
									switch(right) {
									case 1 :
										affiche("Player 1 : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord latéral droit du plateau de jeu"
												+ ", veuillez réessayer\n");
										printHelp();
										break;
									case 2 : 
										affiche("Player 1 : Une barrière vous empêche de vous déplacer d'une case à droite, veuillez réessayer\n");
										printHelp();
										break;
									case 3 : 
										affiche("Player 2 : Vous ne pouvez pas faire ce déplacement, vous êtes bloqué contre le bord latéral droit du plateau de jeu"
												+ ", veuillez réessayer\n");
										printHelp();
										break;
									case 4 : 
										affiche("Player 2 : Une barrière vous empêche de vous déplacer d'une case à droite, veuillez réessayer\n");
										printHelp();
										break;
									default : //Mouvement correct 
										break;
									}
									break;
								default :
									affiche("Mouvement incorrect, Vous avez entré autre chose que \"U\" \"D\" \"L\" ou \"R\" comme 2ème charactère"
											+ ",  veuillez réessayer\n");
									printHelp();
									break;
							}
							break;
						default : 
							affiche("Mouvement incorrect : Vous avez entré autre chose que \"B\" ou \"P\" comme 1er charactère, veuillez réessayer\n");
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
