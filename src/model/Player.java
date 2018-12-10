package model;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Nicky Riat, Mathias Gassmann, Adrien Chellé
 * Projet EPHEC 2TL1 
 */
public class Player {
	private final static int NBR_BARRIER_TOT = 10;
	private String nickname;
	private int nbrBarrierLeft;
	private ArrayList<Barrier> barriersOfThePlayer;
	private Pawn myPawn;
	private static int playerCounter = 1;
	private Scanner sc;
	
	/**
	 * 
	 * Constructeur par defaut
	 */
	public Player(){
		sc = new Scanner(System.in);
		System.out.println("\nQuel est le pseudo du joueur " + playerCounter + " ?\n");
		this.nickname = sc.nextLine();
		playerCounter++;
		nbrBarrierLeft = NBR_BARRIER_TOT;
		barriersOfThePlayer = new ArrayList<Barrier>();
		myPawn = new Pawn();
	}

	/**
	 * 
	 * Constructeur avec pseudo
	 */
	public Player(String nickname){
		this.nickname = nickname;
		nbrBarrierLeft = NBR_BARRIER_TOT;
		barriersOfThePlayer = new ArrayList<Barrier>();
		myPawn = new Pawn();
	}	

	/**
	 * 
	 * Constructeur avec tout configurable
	 */
	public Player(String nickname, int nbrBarrierLeft){
		this.nickname = nickname;
		this.nbrBarrierLeft = nbrBarrierLeft;
		barriersOfThePlayer = new ArrayList<Barrier>();
		myPawn = new Pawn();
	}		
	
	public boolean play() {
		
		return false;
	}

	/**
	 *  a faire
	 * @return :
	 */
	public boolean putBarrierOnField(int x1, int x2, int y1, int y2) {
		
		return true;
	}	

	/**
	 * a faire
	 * @return :
	 */
	public static boolean pathFinder(){
		// algorithm verifiant coup valable / qu'il existe tjs au moins 1 path possible
		return true;
	}
	
	/**
	 * a faire
	 * @return :
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
	public ArrayList<Barrier> getBarriersOfThePlayer() {
		return barriersOfThePlayer;
	}	

	/**
	 * @return the pawn
	 */
	public Pawn getPawn() {
		return myPawn;
	}
	
	public void addBarrier(Barrier b) {
		barriersOfThePlayer.add(b);
	}
	
	public void removeBarrier(Barrier b) {
		barriersOfThePlayer.remove(b);
	}
	
	public void setNbrBarrierLeft(int nbr) {
		nbrBarrierLeft = nbr;
	}
	
	@Override
	public String toString() {
		return "Joueur " + playerCounter + " : " + nickname;
	}
	
}
