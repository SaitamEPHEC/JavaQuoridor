package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {

	@Test
	void moveUpTest() {
		
		//P1 est bloque contre le bord superieur du board
		Board test1 = new Board();
		test1.setTurn(test1.getPlayer1());
		test1.setP1Y(0);
		assertEquals(2, test1.moveUp());
		
		//P1 est bloque par une barriere au-dessus
		Board test2 = new Board();
		test2.setP1Y(2);
		test2.setP1X(2);
		test2.drawBarrierH(new Barrier(1,2,1,4));
		test2.setTurn(test2.getPlayer1());
		assertEquals(3,test2.moveUp());
		
		//P1 est en-dessous de P2 et ne peux pas sauter au-dessus de lui a cause du bord superieur du board
		Board test3 = new Board();
		test3.setP1Y(2);
		test3.setP1X(2);
		test3.setP2Y(0);
		test3.setP2X(2);
		test3.setTurn(test3.getPlayer1());
		assertEquals(4,test3.moveUp());	
	
		//P1 est en-dessous de P2 et ne peut pas sauter au-dessus de P2 a cause d'une barriere
		Board test4 = new Board();
		test4.setP1Y(4);
		test4.setP1X(4);
		test4.setP2Y(2);
		test4.setP2X(4);
		test4.drawBarrierH(new Barrier(1,4,1,6));
		test4.setTurn(test4.getPlayer1());
		assertEquals(5,test4.moveUp());
		
		//P1 est en-dessous de P2 et peut sauter au-dessus de lui
		Board test5 = new Board();
		test5.setP1Y(4);
		test5.setP1X(4);
		test5.setP2Y(2);
		test5.setP2X(4);
		test5.setTurn(test5.getPlayer1());
		assertEquals(0,test5.moveUp());
		int positionYAttendue1 = 0;
		int positionXAttendue1 = 4; 
		int positionYObtenue1 = test5.getP1Y();
		int positionXObtenue1 = test5.getP1X();
		assertEquals(positionYAttendue1,positionYObtenue1);
		assertEquals(positionXAttendue1,positionXObtenue1);
		
		//P1 se deplace bien d'une case en haut et P2 n'est pas en face de P1
		Board test6 = new Board();
		test6.setP1Y(6);
		test6.setP1X(6);
		test6.setTurn(test6.getPlayer1());
		assertEquals(0,test6.moveUp());
		int positionYAttendue2 = 4;
		int positionXAttendue2 = 6; 
		int positionYObtenue2 = test6.getP1Y();
		int positionXObtenue2 = test6.getP1X();
		assertEquals(positionYAttendue2,positionYObtenue2);
		assertEquals(positionXAttendue2,positionXObtenue2);
		
		//P2 est bloque contre le bord superieur du board
		Board test7 = new Board();
		test7.setTurn(test7.getPlayer2());
		test7.setP2Y(0);
		assertEquals(6, test7.moveUp());
		
		//P2 est bloque par une barriere au-dessus
		Board test8 = new Board();
		test8.setP2Y(2);
		test8.setP2X(2);
		test8.drawBarrierH(new Barrier(1,2,1,4));
		test8.setTurn(test8.getPlayer2());
		assertEquals(7,test8.moveUp());
		
		//P2 est en-dessous de P1 et ne peux pas sauter au-dessus de lui a cause du bord superieur du board
		Board test9 = new Board();
		test9.setP1Y(0);
		test9.setP1X(2);
		test9.setP2Y(2);
		test9.setP2X(2);
		test9.setTurn(test9.getPlayer2());
		assertEquals(8,test9.moveUp());	
		
		//P2 est en-dessous de P1 et ne peut pas sauter au-dessus de P1 a cause d'une barriere
		Board test10 = new Board();
		test10.setP1Y(2);
		test10.setP1X(4);
		test10.setP2Y(4);
		test10.setP2X(4);
		test10.drawBarrierH(new Barrier(1,4,1,6));
		test10.setTurn(test10.getPlayer2());
		assertEquals(9,test10.moveUp());
		
		//P2 est en-dessous de P1 et peut sauter au-dessus de lui
		Board test11 = new Board();
		test11.setP1Y(2);
		test11.setP1X(4);
		test11.setP2Y(4);
		test11.setP2X(4);
		test11.setTurn(test11.getPlayer2());
		assertEquals(1,test11.moveUp());
		int positionYAttendue3 = 0;
		int positionXAttendue3 = 4; 
		int positionYObtenue3 = test11.getP2Y();
		int positionXObtenue3 = test11.getP2X();
		assertEquals(positionYAttendue3,positionYObtenue3);
		assertEquals(positionXAttendue3,positionXObtenue3);
		
		//P2 se deplace bien d'une case en haut et P1 n'est pas en face de P2
		Board test12 = new Board();
		test12.setP2Y(6);
		test12.setP2X(6);
		test12.setTurn(test12.getPlayer2());
		assertEquals(1,test12.moveUp());
		int positionYAttendue4 = 4;
		int positionXAttendue4 = 6; 
		int positionYObtenue4 = test12.getP2Y();
		int positionXObtenue4 = test12.getP2X();
		assertEquals(positionYAttendue4,positionYObtenue4);
		assertEquals(positionXAttendue4,positionXObtenue4);
	}
	
	@Test
	void moveDownTest() {
		
		//P1 est bloque contre le bord inferieur du board
		Board test1 = new Board();
		test1.setTurn(test1.getPlayer1());
		test1.setP1Y(16);
		assertEquals(2, test1.moveDown());
		
		//P1 est bloque par une barriere en-dessous
		Board test2 = new Board();
		test2.setP1Y(2);
		test2.setP1X(2);
		test2.drawBarrierH(new Barrier(3,2,3,4));
		test2.setTurn(test2.getPlayer1());
		assertEquals(3,test2.moveDown());
		
		//P1 est au-dessus de P2 et ne peux pas sauter au-dessus de lui a cause du bord inferieur du board
		Board test3 = new Board();
		test3.setP1Y(14);
		test3.setP1X(2);
		test3.setP2Y(16);
		test3.setP2X(2);
		test3.setTurn(test3.getPlayer1());
		assertEquals(4,test3.moveDown());	
	
		//P1 est au-dessus de P2 et ne peut pas sauter au-dessus de P2 a cause d'une barriere
		Board test4 = new Board();
		test4.setP1Y(2);
		test4.setP1X(4);
		test4.setP2Y(4);
		test4.setP2X(4);
		test4.drawBarrierH(new Barrier(5,4,5,6));
		test4.setTurn(test4.getPlayer1());
		assertEquals(5,test4.moveDown());
		
		//P1 est au-dessus de P2 et peut sauter au-dessus de lui
		Board test5 = new Board();
		test5.setP1Y(2);
		test5.setP1X(4);
		test5.setP2Y(4);
		test5.setP2X(4);
		test5.setTurn(test5.getPlayer1());
		assertEquals(1,test5.moveDown());
		int positionYAttendue1 = 6;
		int positionXAttendue1 = 4; 
		int positionYObtenue1 = test5.getP1Y();
		int positionXObtenue1 = test5.getP1X();
		assertEquals(positionYAttendue1,positionYObtenue1);
		assertEquals(positionXAttendue1,positionXObtenue1);
		
		//P1 se deplace bien d'une case en bas et P2 n'est pas en face de P1
		Board test6 = new Board();
		test6.setP1Y(6);
		test6.setP1X(6);
		test6.setTurn(test6.getPlayer1());
		assertEquals(1,test6.moveDown());
		int positionYAttendue2 = 8;
		int positionXAttendue2 = 6; 
		int positionYObtenue2 = test6.getP1Y();
		int positionXObtenue2 = test6.getP1X();
		assertEquals(positionYAttendue2,positionYObtenue2);
		assertEquals(positionXAttendue2,positionXObtenue2);
		
		//P2 est bloque contre le bord inferieur du board
		Board test7 = new Board();
		test7.setTurn(test7.getPlayer2());
		test7.setP2Y(16);
		assertEquals(6, test7.moveDown());
		
		//P2 est bloque par une barriere en-dessous
		Board test8 = new Board();
		test8.setP2Y(2);
		test8.setP2X(2);
		test8.drawBarrierH(new Barrier(3,2,3,4));
		test8.setTurn(test8.getPlayer2());
		assertEquals(7,test8.moveDown());
		
		//P2 est au-dessus de P1 et ne peux pas sauter au-dessus de lui a cause du bord inferieur du board
		Board test9 = new Board();
		test9.setP1Y(16);
		test9.setP1X(2);
		test9.setP2Y(14);
		test9.setP2X(2);
		test9.setTurn(test9.getPlayer2());
		assertEquals(8,test9.moveDown());	
		
		//P2 est au-dessus de P1 et ne peut pas sauter au-dessus de P1 a cause d'une barriere
		Board test10 = new Board();
		test10.setP1Y(4);
		test10.setP1X(4);
		test10.setP2Y(2);
		test10.setP2X(4);
		test10.drawBarrierH(new Barrier(5,4,5,6));
		test10.setTurn(test10.getPlayer2());
		assertEquals(9,test10.moveDown());
		
		//P2 est au-dessus de P1 et peut sauter au-dessus de lui
		Board test11 = new Board();
		test11.setP1Y(4);
		test11.setP1X(4);
		test11.setP2Y(2);
		test11.setP2X(4);
		test11.setTurn(test11.getPlayer2());
		assertEquals(0,test11.moveDown());
		int positionYAttendue3 = 6;
		int positionXAttendue3 = 4; 
		int positionYObtenue3 = test11.getP2Y();
		int positionXObtenue3 = test11.getP2X();
		assertEquals(positionYAttendue3,positionYObtenue3);
		assertEquals(positionXAttendue3,positionXObtenue3);
		
		//P2 se deplace bien d'une case en bas et P1 n'est pas en face de P2
		Board test12 = new Board();
		test12.setP2Y(6);
		test12.setP2X(6);
		test12.setTurn(test12.getPlayer2());
		assertEquals(0,test12.moveDown());
		int positionYAttendue4 = 8;
		int positionXAttendue4 = 6; 
		int positionYObtenue4 = test12.getP2Y();
		int positionXObtenue4 = test12.getP2X();
		assertEquals(positionYAttendue4,positionYObtenue4);
		assertEquals(positionXAttendue4,positionXObtenue4);
	}
	
	@Test
	void moveLeftTest() {
		
		//P1 est bloque contre le bord gauche du board
		Board test1 = new Board();
		test1.setTurn(test1.getPlayer1());
		test1.setP1X(0);
		assertEquals(1, test1.moveLeft());
		
		//P1 est bloque par une barriere a gauche
		Board test2 = new Board();
		test2.setP1Y(2);
		test2.setP1X(2);
		test2.drawBarrierH(new Barrier(0,1,2,1));
		test2.setTurn(test2.getPlayer1());
		assertEquals(2,test2.moveLeft());
		
		//P1 est a droite de P2 et ne peux pas sauter au-dessus de lui a cause du bord gauche du board
		Board test3 = new Board();
		test3.setP1Y(2);
		test3.setP1X(2);
		test3.setP2Y(2);
		test3.setP2X(0);
		test3.setTurn(test3.getPlayer1());
		assertEquals(3,test3.moveLeft());	
	
		//P1 est a droite de P2 et ne peut pas sauter au-dessus de P2 a cause d'une barriere
		Board test4 = new Board();
		test4.setP1Y(4);
		test4.setP1X(4);
		test4.setP2Y(4);
		test4.setP2X(2);
		test4.drawBarrierH(new Barrier(4,1,6,1));
		test4.setTurn(test4.getPlayer1());
		assertEquals(4,test4.moveLeft());
		
		//P1 est a droite de P2 et peut sauter au-dessus de lui
		Board test5 = new Board();
		test5.setP1Y(4);
		test5.setP1X(6);
		test5.setP2Y(4);
		test5.setP2X(4);
		test5.setTurn(test5.getPlayer1());
		assertEquals(0,test5.moveLeft());
		int positionYAttendue1 = 4;
		int positionXAttendue1 = 2; 
		int positionYObtenue1 = test5.getP1Y();
		int positionXObtenue1 = test5.getP1X();
		assertEquals(positionYAttendue1,positionYObtenue1);
		assertEquals(positionXAttendue1,positionXObtenue1);
		
		//P1 se deplace bien d'une case vers la gauche et P2 n'est pas en face de P1
		Board test6 = new Board();
		test6.setP1Y(6);
		test6.setP1X(6);
		test6.setTurn(test6.getPlayer1());
		assertEquals(0,test6.moveLeft());
		int positionYAttendue2 = 6;
		int positionXAttendue2 = 4; 
		int positionYObtenue2 = test6.getP1Y();
		int positionXObtenue2 = test6.getP1X();
		assertEquals(positionYAttendue2,positionYObtenue2);
		assertEquals(positionXAttendue2,positionXObtenue2);
		
		//P2 est bloque contre le bord gauche du board
		Board test7 = new Board();
		test7.setTurn(test7.getPlayer2());
		test7.setP2X(0);
		assertEquals(5, test7.moveLeft());
		
		//P2 est bloque par une barriere a gauche
		Board test8 = new Board();
		test8.setP2Y(2);
		test8.setP2X(2);
		test8.drawBarrierH(new Barrier(0,1,2,1));
		test8.setTurn(test8.getPlayer2());
		assertEquals(6,test8.moveLeft());
		
		//P2 est a droite de P1 et ne peux pas sauter au-dessus de lui a cause du bord gauche du board
		Board test9 = new Board();
		test9.setP1Y(2);
		test9.setP1X(0);
		test9.setP2Y(2);
		test9.setP2X(2);
		test9.setTurn(test9.getPlayer2());
		assertEquals(7,test9.moveLeft());	
		
		//P2 est a droite de P1 et ne peut pas sauter au-dessus de P1 a cause d'une barriere
		Board test10 = new Board();
		test10.setP1Y(4);
		test10.setP1X(2);
		test10.setP2Y(4);
		test10.setP2X(4);
		test10.drawBarrierH(new Barrier(4,1,6,1));
		test10.setTurn(test10.getPlayer2());
		assertEquals(8,test10.moveLeft());
		
		//P2 est a droite de P1 et peut sauter au-dessus de lui
		Board test11 = new Board();
		test11.setP1Y(4);
		test11.setP1X(2);
		test11.setP2Y(4);
		test11.setP2X(4);
		test11.setTurn(test11.getPlayer2());
		assertEquals(0,test11.moveLeft());
		int positionYAttendue3 = 4;
		int positionXAttendue3 = 0; 
		int positionYObtenue3 = test11.getP2Y();
		int positionXObtenue3 = test11.getP2X();
		assertEquals(positionYAttendue3,positionYObtenue3);
		assertEquals(positionXAttendue3,positionXObtenue3);
		
		//P2 se deplace bien d'une case a gauche et P1 n'est pas en face de P2
		Board test12 = new Board();
		test12.setP2Y(6);
		test12.setP2X(6);
		test12.setTurn(test12.getPlayer2());
		assertEquals(0,test12.moveLeft());
		int positionYAttendue4 = 6;
		int positionXAttendue4 = 4; 
		int positionYObtenue4 = test12.getP2Y();
		int positionXObtenue4 = test12.getP2X();
		assertEquals(positionYAttendue4,positionYObtenue4);
		assertEquals(positionXAttendue4,positionXObtenue4);
	}
	
	@Test
	void moveRightTest() {
		
		//P1 est bloque contre le bord droit du board
		Board test1 = new Board();
		test1.setTurn(test1.getPlayer1());
		test1.setP1X(16);
		assertEquals(1, test1.moveRight());
		
		//P1 est bloque par une barriere a droite
		Board test2 = new Board();
		test2.setP1Y(2);
		test2.setP1X(2);
		test2.drawBarrierH(new Barrier(0,3,2,3));
		test2.setTurn(test2.getPlayer1());
		assertEquals(2,test2.moveRight());
		
		//P1 est a gauche de P2 et ne peux pas sauter au-dessus de lui a cause du bord droit du board
		Board test3 = new Board();
		test3.setP1Y(2);
		test3.setP1X(14);
		test3.setP2Y(2);
		test3.setP2X(16);
		test3.setTurn(test3.getPlayer1());
		assertEquals(3,test3.moveRight());	
	
		//P1 est a gauche de P2 et ne peut pas sauter au-dessus de P2 a cause d'une barriere
		Board test4 = new Board();
		test4.setP1Y(4);
		test4.setP1X(2);
		test4.setP2Y(4);
		test4.setP2X(4);
		test4.drawBarrierH(new Barrier(2,5,4,5));
		test4.setTurn(test4.getPlayer1());
		assertEquals(4,test4.moveRight());
		
		//P1 est a gauche de P2 et peut sauter au-dessus de lui
		Board test5 = new Board();
		test5.setP1Y(4);
		test5.setP1X(2);
		test5.setP2Y(4);
		test5.setP2X(4);
		test5.setTurn(test5.getPlayer1());
		assertEquals(0,test5.moveRight());
		int positionYAttendue1 = 4;
		int positionXAttendue1 = 6; 
		int positionYObtenue1 = test5.getP1Y();
		int positionXObtenue1 = test5.getP1X();
		assertEquals(positionYAttendue1,positionYObtenue1);
		assertEquals(positionXAttendue1,positionXObtenue1);
		
		//P1 se deplace bien d'une case a droite et P2 n'est pas en face de P1
		Board test6 = new Board();
		test6.setP1Y(6);
		test6.setP1X(6);
		test6.setTurn(test6.getPlayer1());
		assertEquals(0,test6.moveRight());
		int positionYAttendue2 = 6;
		int positionXAttendue2 = 8; 
		int positionYObtenue2 = test6.getP1Y();
		int positionXObtenue2 = test6.getP1X();
		assertEquals(positionYAttendue2,positionYObtenue2);
		assertEquals(positionXAttendue2,positionXObtenue2);
		
		//P2 est bloque contre le bord droit du board
		Board test7 = new Board();
		test7.setTurn(test7.getPlayer2());
		test7.setP2X(16);
		assertEquals(5, test7.moveRight());
		
		//P2 est bloque par une barriere a droite
		Board test8 = new Board();
		test8.setP2Y(2);
		test8.setP2X(2);
		test8.drawBarrierH(new Barrier(0,3,2,3));
		test8.setTurn(test8.getPlayer2());
		assertEquals(6,test8.moveRight());
		
		//P2 est a gauche de P1 et ne peux pas sauter au-dessus de lui a cause du bord droit du board
		Board test9 = new Board();
		test9.setP1Y(0);
		test9.setP1X(16);
		test9.setP2Y(0);
		test9.setP2X(14);
		test9.setTurn(test9.getPlayer2());
		assertEquals(7,test9.moveRight());	
		
		//P2 est a gauche de P1 et ne peut pas sauter au-dessus de P1 a cause d'une barriere
		Board test10 = new Board();
		test10.setP1Y(4);
		test10.setP1X(4);
		test10.setP2Y(4);
		test10.setP2X(2);
		test10.drawBarrierH(new Barrier(2,5,4,5));
		test10.setTurn(test10.getPlayer2());
		assertEquals(8,test10.moveRight());
		
		//P2 est a gauche de P1 et peut sauter au-dessus de lui
		Board test11 = new Board();
		test11.setP1Y(4);
		test11.setP1X(4);
		test11.setP2Y(4);
		test11.setP2X(2);
		test11.setTurn(test11.getPlayer2());
		assertEquals(0,test11.moveRight());
		int positionYAttendue3 = 4;
		int positionXAttendue3 = 6; 
		int positionYObtenue3 = test11.getP2Y();
		int positionXObtenue3 = test11.getP2X();
		assertEquals(positionYAttendue3,positionYObtenue3);
		assertEquals(positionXAttendue3,positionXObtenue3);
		
		//P2 se deplace bien d'une case a droite et P1 n'est pas en face de P2
		Board test12 = new Board();
		test12.setP2Y(6);
		test12.setP2X(6);
		test12.setTurn(test12.getPlayer2());
		assertEquals(0,test12.moveRight());
		int positionYAttendue4 = 6;
		int positionXAttendue4 = 8; 
		int positionYObtenue4 = test12.getP2Y();
		int positionXObtenue4 = test12.getP2X();
		assertEquals(positionYAttendue4,positionYObtenue4);
		assertEquals(positionXAttendue4,positionXObtenue4);
	}
	
	@Test
	void isBarrierOnBoardTest() {
		Board test1 = new Board();
		Barrier bInList = new Barrier(1,2,1,4);
		Barrier bToCompareInList = new Barrier(1,2,1,4);
		Barrier bToCompareNotInList = new Barrier(1,4,1,6);
		test1.getBarriersOnBoard().add(bInList);
		assertEquals(true,test1.isBarrierOnBoard(bToCompareInList));
		assertEquals(false,test1.isBarrierOnBoard(bToCompareNotInList));
	}
	
	@Test
	/**
	 * Test la methode isPositionOfBarrierOnBoard(int posY, int posX)
	 */
	void isPositionOfBarrierOnBoardTest1() {
		Board test1 = new Board();
		Barrier b1InList = new Barrier(1,2,1,4);
		Barrier b2InList = new Barrier(6,5,4,5);
		test1.getBarriersOnBoard().add(b1InList);
		test1.getBarriersOnBoard().add(b2InList);
		int posY1BInList = 1;
		int posX1BInList = 2;
		int posY2BInList = 4;
		int posX2BInList = 5;
		int posYBNotInList = 8;
		int posXBNotInList = 5;
		assertEquals(true,test1.isPositionOfBarrierOnBoard(posY1BInList, posX1BInList));
		assertEquals(true,test1.isPositionOfBarrierOnBoard(posY2BInList, posX2BInList));
		assertEquals(false,test1.isPositionOfBarrierOnBoard(posYBNotInList, posXBNotInList));
	}
	
	@Test
	/**
	 * Test la methode isPositionOfBarrierOnBoard(Barrier b)
	 */
	void isPositionOfBarrierOnBoardTest2() {
		Board test1 = new Board();
		Barrier b1InList = new Barrier(1,2,1,4);
		Barrier b2InList = new Barrier(6,5,4,5);
		Barrier b3InList = new Barrier(8,7,6,7);
		test1.getBarriersOnBoard().add(b1InList);
		test1.getBarriersOnBoard().add(b2InList);
		test1.getBarriersOnBoard().add(b3InList);
		Barrier b1ToCompareInList = new Barrier(1,0,1,2);
		Barrier b2ToCompareInList = new Barrier(4,5,2,5);
		Barrier b3ToCompareInList = new Barrier(8,7,6,7);
		Barrier bToCompareNotInList = new Barrier(1,6,1,8);
		
		assertEquals(true,test1.isPositionOfBarrierOnBoard(b1ToCompareInList));
		assertEquals(true,test1.isPositionOfBarrierOnBoard(b2ToCompareInList));
		assertEquals(true,test1.isPositionOfBarrierOnBoard(b3ToCompareInList));
		assertEquals(false,test1.isPositionOfBarrierOnBoard(bToCompareNotInList));
	}
	
	@Test
	/**
	 * Test la methode player1HasWon()
	 */
	void player1HasWonTest() {
		Board test1 = new Board();
		test1.setP1Y(8);
		assertEquals(false,test1.player1HasWon());
		test1.setP1Y(16);
		assertEquals(true,test1.player1HasWon());
	}
	
	@Test
	/**
	 * Test la methode player2HasWon()
	 */
	void player2HasWonTest() {
		Board test1 = new Board();
		test1.setP2Y(8);
		assertEquals(false,test1.player2HasWon());
		test1.setP2Y(0);
		assertEquals(true,test1.player2HasWon());
	}

}
