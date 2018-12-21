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

}
