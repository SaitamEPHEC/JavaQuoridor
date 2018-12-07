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
								String c1 = sc.next().toUpperCase();
								if((c1.length()!=1) || (listeLettres.indexOf(c1) == -1)) {
									affiche("Premi�re lettre incorrecte, entrez une seule lettre entre A et I\n");
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
									affiche("Position de la barri�re incorrecte.\nExemple de barri�re horizontale : A 1 B 1\nExemple de barri�re verticale : A 5 A 6\n");
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
							}
							break;
						default : 
							affiche("Mouvement incorrect : Vous avez entr� autre chose que \"B\" ou \"P\" comme 1er charact�re, veuillez r�essayer\n");
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
