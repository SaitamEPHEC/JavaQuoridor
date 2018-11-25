package view;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import controller.BoardController;
import model.Board;
import tp7.controller.BibliothequeController;
import tp7.model.Bibliotheque;
import tp7.view.BibliothequeVueConsole.ReadInput;

public class BoardVueConsole extends BoardVue {
	
	protected Scanner sc;

	public BoardVueConsole(Board model,
			BoardController controller) {
		super(model, controller);
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
		affiche("Pour déplacer le pion, tapez : U (move Up), D (move Down), L (move Left), R (move Right)");
		affiche("Pour déplacer la barriere, tapez les 4 coordonnées de la barrière. Exemple : A1B1 ou A2B2");
	}
	
	//TODO : à MODIFIER avec notre projet
	private class ReadInput implements Runnable{
		public void run() {
			while(true){
				try{
					String c = sc.next();
					if(c.length()!=1){
						affiche("Format d'input incorrect");
						printHelp();
					}
						
					int i = sc.nextInt();
					if(i<0 || i> 9){
						affiche("Numero du livre incorrect");
						printHelp(); 
					}
					switch(c){
						case "R" :
							controller.rendreLivre(i);
							break;
						case "E" : 
							controller.emprunteLivre(i);
							break;
						default : 
							affiche("Operation incorrecte");
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
