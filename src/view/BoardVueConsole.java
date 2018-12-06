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
		affiche("Entrez \"B\" pour choisir de placer une barriere, ou \"P\" pour choisir de déplacer votre pion\n");
		affiche("Pour déplacer le pion, tapez : U (move Up), D (move Down), L (move Left), R (move Right)\n");
		affiche("Pour placer la barriere, tapez les 4 coordonnées de la barrière. Exemple : A 1 B 1 ou A 5 A 6\n");
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
			drawBoardConsole();
			String listeLettres = "ABCDEFGHI";
			String listeMvmt = "UDRL";
			while(true){
				try {
					String c = sc.next().toUpperCase();
					switch(c){
						case "B" :
							//barrière
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
								
								//caractérise une barrière
								if (!(((c1 == c2) && Math.abs(i1 - i2) ==1) || ((i1 == i2) && Math.abs(listeLettres.indexOf(c1) - listeLettres.indexOf(c2)) == 1))) {
									affiche("Position de la barrière incorrecte.\nExemple de barrière horizontale : A 1 B 1\nExemple de barrière verticale : A 5 A 6\n");
									printHelp();
								}
							break;
						case "P" :
							//pion
							String m = sc.next().toUpperCase();
							if(listeMvmt.indexOf(m) == -1) {
								affiche("Mouvement incorrect, entrez U pour monter, D pour descendre, L pour aller à gauche, et R pour aller à droite\n");
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
										affiche("Mouvement incorrect, entrez U pour monter, D pour descendre, L pour aller à gauche, et R pour aller à droite\n");
										printHelp();
								}
								
							}
							break;
						default : 
							affiche("Operation incorrecte; entrez \"B\" pour placer une barriere, ou \"P\" pour déplacer votre pion\n");
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
