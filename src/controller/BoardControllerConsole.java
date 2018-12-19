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
	public void putBarrier(inputs) {
		Barrier b;
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
}
