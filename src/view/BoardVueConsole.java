package view;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;

import controller.BoardController;
import controller.BoardControllerConsole;
import model.Board;

public class BoardVueConsole extends BoardVue {
	protected BoardControllerConsole controllerConsole;
	protected Scanner sc;

	public BoardVueConsole(Board model,
			BoardController controller){
		super(model, controller);
		this.model = model;
		update(null, null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();	
	}
	
	@Override
	public void update(Observable o, Object arg) {
		String[][] temp = this.model.getContours();
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
		for(int i = 0;i<this.model.getLength();i++) {
			for(int j = 0;j<this.model.getLength();j++) {
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
						
					if(j == (this.model.getLength()-1)) {
						System.out.println(this.model.getBoard()[i][j]); // board
					}
					else {
						System.out.print(this.model.getBoard()[i][j]); // board
					}
				}
			}
		}	
	}
	
	public void printHelp(){
		affiche("\nPour déplacer votre pion, Entrez \"P\" puis appuyez sur Enter.\n"
				+ "Ensuite, tapez \"U\" pour déplacer votre pion d'une case en haut, \"D\" pour déplacer votre pion d'une case en bas,\n"
				+ "\"L\" pour déplacer votre pion d'une case à gauche et \"R\" pour déplacer votre pion d'une case à droite \n");
		affiche("Pour placer une barrière, Entrez \"B\" puis appuyez sur Enter.\n"
				+ "Ensuite, tapez les 4 coordonnées de la barrière suivant les coordonnées possible sur le plateau de jeu.\n"
				+ "Si vous placez une barrière horizontale, elle sera placée au-dessus des coordonnées indiquées, si vous"
				+ " placez une barrière verticale, elle sera placée à droite des coordonnées indiquées.\n"
				+ "Exemple : A 1 B 1 ou G 8 G 9\n");
	}

	
	private class ReadInput implements Runnable{
		public void run() {
			boolean endOfGame = false;
			String listeLettres = "ABCDEFGHI";
			while(!endOfGame){
				try {
					String c = sc.next().toUpperCase();
					switch(c){
						case "B" : 	//Barriere
								
								if(isValid) {
									
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

									if(caseNotOutOfBounds) {
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
												if(model.getBoard()[posY1][posX1] == "――" || model.getBoard()[posY2][posX2] == "――") {
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
													if (model.getBoard()[posY1+1][posX1+1] == " | " && model.getBoard()[posY1-1][posX1+1] == " | "){
														//test si la barriere horizontale entree passe a travers une barriere verticale existante
														if(model.isBarrierOnBoard(posY1+1, posX1+1,posY1-1,posX1+1)){
															affiche("Position de la barrière incorrecte : Vous ne pouvez pas croiser votre barrière horizontale avec "
																	+ "une barrière verticale déjà existante.\n");
															printHelp();
														}
														//la barriere horizontale entree est entre 2 barrieres verticales existantes et peut donc etre placee
														else{
															model.drawBarrierH(posY1, posX1, posY2, posX2);
														}
													}
													else {
														model.drawBarrierH(posY1, posX1, posY2, posX2);
													}
												}
											}
											//barriere verticale
											if(posX1 == posX2) {
												//test si aucun symbole de barriere verticale " | " n'existe deja aux positions donnees pour placer
												//la barriere verticale
												if(model.getBoard()[posY1][posX1] == " | " || model.getBoard()[posY2][posX2] == " | ") {
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
													if (model.getBoard()[posY1-1][posX1-1] == "――" && model.getBoard()[posY1-1][posX1+1] == "――"){
														//test si la barriere verticale entree passe a travers une barriere horizontale existante
														if(model.isBarrierOnBoard(posY1-1, posX1-1,posY1-1,posX1+1)){
															affiche("Position de la barrière incorrecte : Vous ne pouvez pas croiser votre barrière verticale avec "
																	+ "une barrière horizontale déjà existante.\n");
															printHelp();
														}
														//la barriere verticale entree est entre 2 barrieres horizontales existantes et peut donc etre placee
														else{
															model.drawBarrierV(posY1, posX1, posY2, posX2);
														}
													}
													else {
														model.drawBarrierV(posY1, posX1, posY2, posX2);
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
									int up = model.moveUp();
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
									case 5 : //mouvement correct du joueur 2 + check si victoire
										if(model.getP2Y() == 0) {
											affiche("Bravo joueur 2, vous avez gagné! Félicitations !");
											endOfGame = true;
										}
										break;
									default : //mouvement correct du joueur 1
										break;
									}
									break;
								case "D" : 
									int down = model.moveDown();
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
									case 5 : //mouvement correct du joueur 1 + check si victoire
										if(model.getP1Y() == 16) {
											affiche("Bravo joueur 1, vous avez gagné! Félicitations !");
											endOfGame = true;
										}
										break;
									default : //Mouvement correct du joueur 2
										break;
									}
									break;
								case "L" : 
									int left = model.moveLeft();
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
									int right = model.moveRight();
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

	@Override
	public void affiche(String string) {
		System.out.println(string);
		
	}
	
}
