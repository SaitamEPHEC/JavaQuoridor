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
		affiche("Pour déplacer votre pion, Entrez \"P\" puis appuyez sur Enter.\n"
				+ "Ensuite, tapez \"U\" pour déplacer votre pion d'une case en haut, \"D\" pour déplacer votre pion d'une case en bas,\n"
				+ "\"L\" pour déplacer votre pion d'une case à gauche et \"R\" pour déplacer votre pion d'une case à droite \n");
		affiche("Pour placer une barrière, Entrez \"B\" puis appuyez sur Enter.\n"
				+ "Ensuite, tapez les 4 coordonnées de la barrière suivant les coordonnées possible sur le plateau de jeu"
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
	
	
	
	//TODO : à MODIFIER avec notre projet
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
								String c1 = sc.next().toUpperCase();
								if((c1.length()!=1) || (listeLettres.indexOf(c1) == -1)) {
									affiche("Première lettre incorrecte, entrez une seule lettre entre A et I\n");
									printHelp();
								}
									
								int i1 = sc.nextInt();
								if(i1<0 || i1> 8){
									affiche("Premier numero de case incorrecte, entrez un chiffre entre 0 et 8\n");
									printHelp(); 
								}
								
								String c2 = sc.next().toUpperCase();
								if((c2.length()!=1) || (listeLettres.indexOf(c2) == -1)){
									affiche("Seconde lettre incorrecte, entrez une seule lettre entre A et I\n");
									printHelp();
								}
									
								int i2 = sc.nextInt();
								if(i2<0 || i2> 8){
									affiche("Second numero de case incorrect, entrez un chiffre entre 0 et 8\n");
									printHelp(); 
								}
								
								//caracterise une barriere
								if (!(((c1 == c2) && Math.abs(i1 - i2) ==1) || ((i1 == i2) && Math.abs(listeLettres.indexOf(c1) - listeLettres.indexOf(c2)) == 1))) {
									affiche("Position de la barrière incorrecte.\nExemple de barrière horizontale : A 1 B 1\nExemple de barrière verticale : A 5 A 6\n");
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
										printHelp();
										drawBoardConsole();
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
										printHelp();
										drawBoardConsole();
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
										printHelp();
										drawBoardConsole();
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
										printHelp();
										drawBoardConsole();
										break;
									}
									break;
								default : //On ne rentre normalement jamais dedans
									affiche("Mouvement incorrect, Vous avez entré autre chose que \"U\" \"D\" \"L\" ou \"R\" comme 2ème charactère"
											+ ",  veuillez réessayer\n");
									printHelp();
							}
							break;
						default : 
							affiche("Mouvement incorrect : Vous avez entré autre chose que \"B\" ou \"P\" comme 1er charactère, veuillez réessayer\n");
							printHelp();
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
}
