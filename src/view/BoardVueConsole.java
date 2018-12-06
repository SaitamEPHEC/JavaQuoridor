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
		printHelp();	
	}
	
	private void printHelp(){
		affiche("Entrez \"B\" pour choisir de placer une barriere, ou \"P\" pour choisir de d�placer votre pion\n");
		affiche("Pour d�placer le pion, tapez : U (move Up), D (move Down), L (move Left), R (move Right)\n");
		affiche("Pour placer la barriere, tapez les 4 coordonn�es de la barri�re. Exemple : A 1 B 1 ou A 5 A 6\n");
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
			drawBoardConsole();
			String listeLettres = "ABCDEFGHI";
			String listeMvmt = "UDRL";
			while(true){
				try {
					String c = sc.next().toUpperCase();
					switch(c){
						case "B" :
							//barri�re
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
								
								//caract�rise une barri�re
								if (!(((c1 == c2) && Math.abs(i1 - i2) ==1) || ((i1 == i2) && Math.abs(listeLettres.indexOf(c1) - listeLettres.indexOf(c2)) == 1))) {
									affiche("Position de la barri�re incorrecte.\nExemple de barri�re horizontale : A 1 B 1\nExemple de barri�re verticale : A 5 A 6\n");
									printHelp();
								}
							break;
						case "P" :
							//pion
							String m = sc.next().toUpperCase();
							if(listeMvmt.indexOf(m) == -1) {
								affiche("Mouvement incorrect, entrez U pour monter, D pour descendre, L pour aller � gauche, et R pour aller � droite\n");
								printHelp();
							}
							else {
								switch(m) {
									case "U" : 
										board.moveUp();
										drawBoardConsole();
										break;
									case "D" : 
										board.moveDown();
										drawBoardConsole();
										break;
									case "L" : 
										board.moveLeft();
										drawBoardConsole();
										break;
									case "R" : 
										board.moveRight();
										drawBoardConsole();
										break;
									default :
										affiche("Mouvement incorrect, entrez U pour monter, D pour descendre, L pour aller � gauche, et R pour aller � droite\n");
										printHelp();
								}
								
							}
							break;
						default : 
							affiche("Operation incorrecte; entrez \"B\" pour placer une barriere, ou \"P\" pour d�placer votre pion\n");
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
