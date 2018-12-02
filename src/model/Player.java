package model;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Nicky Riat, Mathias Gassmann, Adrien Chellé
 * Projet EPHEC 2TL1 
 */
public class Player {
	final static int NBR_BARRIER_TOT = 10;
	protected String nickname;
	protected int nbrBarrierLeft;
	protected ArrayList<Barrier> barriersOnField;
	protected Pawn myPawn = new Pawn();
	protected static int genericNickname = 10000;
	Scanner sc = new Scanner(System.in);
	
	/*
	 * 
	 * Constructeur par default
	 */
	public Player(){
		System.out.println("Quel est le pseudo du joueur ?");
		this.nickname = sc.nextLine();
		nbrBarrierLeft = NBR_BARRIER_TOT;
		barriersOnField = new ArrayList<Barrier>();
		myPawn = new Pawn();
	}

	/*
	 * 
	 * Constructeur avec juste nickname
	 */
	public Player(String nickname){
		this.nickname = nickname;
		nbrBarrierLeft = NBR_BARRIER_TOT;
		barriersOnField = new ArrayList<Barrier>();
		myPawn = new Pawn();
	}	

	/*
	 * 
	 * Constructeur avec tout configurable
	 */
	public Player(String nickname, int nbrBarrierLeft){
		this.nickname = nickname;
		this.nbrBarrierLeft = nbrBarrierLeft;
		barriersOnField = new ArrayList<Barrier>();
		myPawn = new Pawn();
	}		
	
	public boolean play() {
		
		return false;
	}

	/*
	 *  a faire
	 * post :
	 */
	public boolean putBarrierOnField(int x1, int x2, int y1, int y2) {
		
		return true;
	}	

	/*
	 * a faire
	 * post :
	 */
	public static boolean pathFinder(){
		// algorithm verifiant coup valable / qu'il existe tjs au moins 1 path possible
		return true;
	}
	
	/*
	 * a faire
	 * post :
	 */
	public static void treatBonusCase(){

	}
	
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @return the nbrBarrierLeft
	 */
	public int getNbrBarrierLeft() {
		return nbrBarrierLeft;
	}
	
	/**
	 * @return the nbrBarrierLeft
	 */
	public ArrayList<Barrier> getBarriersOnField() {
		return barriersOnField;
	}	

	/**
	 * @return the pawn
	 */
	public Pawn getPawn() {
		return myPawn;
	}
	
	public void addBarrier(Barrier b) {
		barriersOnField.add(b);
	}
	
	public void removeBarrier(Barrier b) {
		barriersOnField.remove(b);
	}
	
	public int getNumBarrier() {
		return barriersOnField.size();
	}
	
	public void setNbrBarrierLeft(int nbr) {
		nbrBarrierLeft = nbr;
	}
}
