package controller;

import java.util.Scanner;

import model.Barrier;
import model.Board;

public class BoardControllerConsole extends BoardController{
	private Scanner sc; 
	//private Scanner sc1;
	
	public BoardControllerConsole(Board model) {
		super(model);
		sc = new Scanner(System.in);
		//sc1 = new Scanner(System.in);
	}


	
	
	@Override
	public void putBarrier() {
		Barrier b;
		char[] inputs = askBarrier();
		
		if(checkInputs(inputs)) {
			if(isBarrierH(inputs)){
				//Barriere Horizontale
				b = translateH(inputs);
				if(model.isPositionOfBarrierOnBoard(b)) {
					this.vue.affiche("\n" + "Position de la barrière incorrecte : Vous ne pouvez pas placer une barrière sur une"
							+ " barrière déjà existante.\n");
				}
				else {
					if(crossBarrierV(b)) {
						this.vue.affiche("\n" + "Position de la barrière incorrecte : Vous ne pouvez pas croiser votre barrière horizontale avec "
								+ "une barrière verticale déjà existante.\n");
					}
					else {
						if(model.getTurn().getNbrBarrierLeft() > 0) {
							if(model.pathFinder(b,'h')) {
								model.drawBarrierH(b);
							}
							else {
								this.vue.affiche("\n" + "Vous ne pouvez pas placer votre barrière ici : elle bloquerait le chemin d'un des joueurs\n");
							}
						}
						else {
							this.vue.affiche("\n" + model.getTurn().getNickname() + " : Vous n'avez plus de barrières disponibles! Il ne vous reste plus qu'à déplacer votre pion.\n");
						}
					}
				}
			}
			else if(isBarrierV(inputs)) {
				//Barriere Verticale
				b = translateV(inputs);
				if(model.isPositionOfBarrierOnBoard(b)) {
					this.vue.affiche("\n" + "Position de la barrière incorrecte : Vous ne pouvez pas placer une barrière sur une"
							+ " barrière déjà existante.\n");
				}
				else {
					if(crossBarrierH(b)) {
						this.vue.affiche("\n" + "Position de la barrière incorrecte : Vous ne pouvez pas croiser votre barrière verticale avec "
								+ "une barrière horizontale déjà existante.\n");
					}
					else {
						if(model.getTurn().getNbrBarrierLeft() > 0) {
							if(model.pathFinder(b,'v')) {
								model.drawBarrierV(b);
							}
							else {
								this.vue.affiche("\n" + "Vous ne pouvez pas placer votre barrière ici : elle bloquerait le chemin d'un des joueurs\n");
							}
						}
						else {
							this.vue.affiche("\n" + model.getTurn().getNickname() + " : Vous n'avez plus de barrières disponibles! Il ne vous reste plus qu'à déplacer votre pion.\n");
						}
					}
				}
			}
			else {
				this.vue.affiche("\n" + "Les coordonnées entrées ne correspondent pas à une barrière horizontale ou verticale.\n"
						+ "Exemple de barrière horizontale : A 1 A 2 => Attention : pas de barriere horizontale sur la ligne I (hors du plateau de jeu)!\n"
						+ "Exemple de barrière verticale : G 8 H 8 => Attention : pas de barriere verticale sur la colonne 9 (hors du plateau de jeu)!\n");
			}
		}
		else {
		}
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
	
	/**
	 * @param c un tableau de 4 coordonnees correspondant aux coordonnees de la barriere entrees par l'utilisateur.
	 * @return true si le format d'input de la barriere est correcte, c'est a dire que les coordonnees entrees sont dans
	 * l'ensemble des coordonnees du plateau de jeu, false sinon.
	 */
	public boolean checkInputs(char[] c) {
		boolean isValid = true; //si reste true, les chiffres et lettres sont valides par rapport aux limites du board
		
		if(LETTRES_AXE_Y.indexOf(c[0]) == -1) {
			this.vue.affiche("1ère coordonnée de barrière incorrecte, la 1ère coordonnée doit être une lettre entre A et I\n");
			isValid = false; 
		}
		
		if(CHIFFRES_AXE_X.indexOf(c[1]) == -1) {
			this.vue.affiche("2ème coordonnée de barrière incorrecte, la 2ème coordonnée doit être une un chiffre entre 1 et 9\n");
			isValid = false; 
		}
		
		if(LETTRES_AXE_Y.indexOf(c[2]) == -1) {
			this.vue.affiche("3ème coordonnée de barrière incorrecte, la 3ème coordonnée doit être une lettre entre A et I\n");
			isValid = false; 
		}
		
		if(CHIFFRES_AXE_X.indexOf(c[3]) == -1) {
			this.vue.affiche("4ème coordonnée de barrière incorrecte, la 4ème coordonnée doit être une un chiffre entre 1 et 9\n");
			isValid = false;
		}
		
		return isValid;
	}

}
