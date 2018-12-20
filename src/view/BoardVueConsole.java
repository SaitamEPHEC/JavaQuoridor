package view;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;

import controller.BoardController;
import model.Board;

public class BoardVueConsole extends BoardVue {
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
		for(int i = 0;i<this.model.getLength();i++) {
			for(int j = 0;j<this.model.getLength();j++) {
				if(i == 0 && j == 0) { // premiere case
					//System.out.print("   "); // alignement
					for(int l = 0;l<temp.length-1;l++) { // premiere ligne de contour
						System.out.print(temp[i][l]); // affiche 1 a 8
					}
					System.out.print(temp[i][temp.length-1]); // affiche 9 + \n
//					System.out.print("| Tour de : " + this.model.getTurn().getNickname()); // tour de :
					System.out.println("   | Barrieres restantes de " + this.model.getPlayer1Nickname() + " (P1) : " + this.model.getPlayer1BarrierLeft()); // nombre barriere left du joueur du haut
					System.out.print("I " + this.model.getBoard()[i][j]);
				}
				else { // board normal plus contour
					if(j == 0 && i == (this.model.getLength()-1)) {
						System.out.print(temp[i][j] + " " + this.model.getBoard()[i][j]);
					}
					else if(j == 0 && (i%2 == 0)){ // premiere colonne contour
						System.out.print(temp[i][j] + " " + this.model.getBoard()[i][j]); // H - A
					}
					else if(j == 0 && (i%2 != 0)) { // allignement des lignes sans lettres
						System.out.print("  " + this.model.getBoard()[i][j]);
					}
					else if(j == (this.model.getLength()-1) && i == 8) {
						System.out.println(this.model.getBoard()[i][j] + ("   | Tour de : " + this.model.getTurn().getNickname()) ); // affiche le nom de la personne qui doit jouer
					}
						
					else if(j == (this.model.getLength()-1) && i != (this.model.getLength()-1) && i != 8) { //dernier char de chaque ligne
						System.out.println(this.model.getBoard()[i][j]); // board
					}
					else if(j == (this.model.getLength()-1) && i == (this.model.getLength()-1)) {
						System.out.print(this.model.getBoard()[i][j]);
						System.out.println("   | Barrieres restantes de " + this.model.getPlayer2Nickname() + " (P2) : " + this.model.getPlayer2BarrierLeft()); // nombre barriere left du joueur du bas
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
			while(!endOfGame){
				try {
					affiche("\n" + "Options possibles : b|p|r\n");
					char c = Character.toUpperCase(sc.next().trim().charAt(0));
					switch(c){
						case 'B' : 	//Barriere
							affiche("\n" + "Options possibles (4 coordonnées séparées par des espaces) : a-i 1-9 a-i 1-9\n");
							char[] inputs = askBarrier();
							if(checkInputs(inputs)) {
								controller.putBarrier(inputs);
							}
													
							break;
						case 'P' : //Pion
							affiche("\n" + "Options possibles : u|d|l|r\n");
							char m = Character.toUpperCase(sc.next().trim().charAt(0));
							switch(m) {
							case 'U' : 
								controller.moveUpAffichage();
								break;
							case 'D' : 
								controller.moveDownAffichage();
								break;
							case 'L' : 
								controller.moveLeftAffichage();
								break;
							case 'R' : 
								controller.moveRightAffichage();
								break;
							default :
								affiche("Mouvement incorrect, Vous avez entré autre chose que \"U\" \"D\" \"L\" ou \"R\" comme 2ème charactère"
										+ ",  veuillez réessayer\n");
								break;
							}
							
						case 'R' : //rewind
							if(controller.rewind()) {
								affiche("Le retour en arriere s'est bien effectué");
							}
							break;
						default : 
							affiche("\n" + "Mouvement incorrect : Vous avez entré autre chose que \"B\" ou \"P\" comme 1er charactère, veuillez réessayer\n");
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
	
	/**
	 * 
	 * @return les 4 coordonnees de la barriere entrees par l'utilisateur. 
	 */
	public char[] askBarrier() {
		char c1 = Character.toUpperCase(sc.next().trim().charAt(0));
		
		char i1 = Character.toUpperCase(sc.next().trim().charAt(0));
		
		char c2 = Character.toUpperCase(sc.next().trim().charAt(0));
		
		char i2 = Character.toUpperCase(sc.next().trim().charAt(0));
		
		char[] coordonnees = {c1,i1,c2,i2};
		
		return coordonnees;
		
	}
	
}
