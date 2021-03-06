package test;

import controller.BoardController;
import model.Board;
import view.BoardVueConsole;
import view.BoardVueGui;

public class BoardMVC {
	
	public BoardMVC() {
		
		//Création du modèle
		Board modele = new Board();


		//Création des contrôleurs : Un pour chaque vue
		//Chaque contrôleur doit avoir une référence vers le modèle pour pouvoir le commander
		BoardController controleurConsole = new BoardController(modele);
		BoardController controleurGui = new BoardController(modele);
		
		//Creation des vues.
		//Chaque vue doit connaitre son controleur et avoir une reference vers le modele pour pouvoir l'observer
		BoardVueConsole vueConsole = new BoardVueConsole(modele, controleurConsole);
		BoardVueGui vueGui = new BoardVueGui(modele, controleurGui);
		
		//On donne la reference �  la vue pour chaque contrôleur
		controleurConsole.addView(vueConsole);
		controleurGui.addView(vueGui);
		
		
	}
	
	public static void main(String args[]) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BoardMVC();
			}
		});
	}
}