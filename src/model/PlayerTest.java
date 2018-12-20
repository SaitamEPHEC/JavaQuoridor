package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void isJustAboveTest() {
		
		Player playerTest1 = new Player();
		Player playerTest2 = new Player();
		
		//cas ou playerTest1 est au dessus de playerTest2 et sur la meme colonne 
		playerTest1.getPawn().setPosY(5);
		playerTest2.getPawn().setPosY(7);
		playerTest1.getPawn().setPosX(3);
		playerTest2.getPawn().setPosX(3);
		assertEquals(false, playerTest2.isJustAbove(playerTest1));
		
		//cas ou playerTest1 est en dessous de playerTest2 et sur la meme colonne
		playerTest1.getPawn().setPosY(7);
		playerTest2.getPawn().setPosY(5);
		playerTest1.getPawn().setPosX(3);
		playerTest2.getPawn().setPosX(3);
		assertEquals(true, playerTest2.isJustAbove(playerTest1));
		
		//cas ou playerTest1 est au dessus de playerTest2 et sur une colonne differente
		playerTest1.getPawn().setPosY(5);
		playerTest2.getPawn().setPosY(7);
		playerTest1.getPawn().setPosX(3);
		playerTest2.getPawn().setPosX(5);
		assertEquals(false, playerTest2.isJustAbove(playerTest1));
		
		//cas ou playerTest1 est en dessous de playerTest2 et sur une colonne differente
		playerTest1.getPawn().setPosY(7);
		playerTest2.getPawn().setPosY(5);
		playerTest1.getPawn().setPosX(3);
		playerTest2.getPawn().setPosX(5);
		assertEquals(false, playerTest2.isJustAbove(playerTest1));
	}
	
	@Test
	public void isJustBelowTest() {
		
		Player playerTest1 = new Player();
		Player playerTest2 = new Player();
		
		//cas ou playerTest1 est en dessous de playerTest2 et sur la meme colonne
		playerTest1.getPawn().setPosY(5);
		playerTest2.getPawn().setPosY(7);
		playerTest1.getPawn().setPosX(3);
		playerTest2.getPawn().setPosX(3);
		assertEquals(false, playerTest2.isJustAbove(playerTest1));
		
		//cas ou playerTest1 est au dessus de playerTest2 et sur la meme colonne
		playerTest1.getPawn().setPosY(7);
		playerTest2.getPawn().setPosY(5);
		playerTest1.getPawn().setPosX(3);
		playerTest2.getPawn().setPosX(3);
		assertEquals(true, playerTest2.isJustAbove(playerTest1));
		
		//cas ou playerTest1 est au dessus de playerTest2 et sur une colonne differente
		playerTest1.getPawn().setPosY(7);
		playerTest2.getPawn().setPosY(5);
		playerTest1.getPawn().setPosX(3);
		playerTest2.getPawn().setPosX(5);
		assertEquals(false, playerTest2.isJustAbove(playerTest1));
				
		//cas ou playerTest1 est en dessous de playerTest2 et sur une colonne differente
		playerTest1.getPawn().setPosY(5);
		playerTest2.getPawn().setPosY(7);
		playerTest1.getPawn().setPosX(3);
		playerTest2.getPawn().setPosX(5);
		assertEquals(false, playerTest2.isJustAbove(playerTest1));		
	}
	
	@Test
	public void isJustToTheLeftTest() {
		Player playerTest1 = new Player();
		Player playerTest2 = new Player();
		
		//cas ou playerTest1 est a gauche de playerTest2 et sur la meme ligne
		playerTest1.getPawn().setPosY(5);
		playerTest2.getPawn().setPosY(5);
		playerTest1.getPawn().setPosX(3);
		playerTest2.getPawn().setPosX(5);
		assertEquals(false, playerTest2.isJustToTheLeftOf(playerTest1));
		
		//cas ou playerTest1 est a droite de playerTest2 et sur la meme ligne
		playerTest1.getPawn().setPosY(5);
		playerTest2.getPawn().setPosY(5);
		playerTest1.getPawn().setPosX(5);
		playerTest2.getPawn().setPosX(3);
		assertEquals(true, playerTest2.isJustToTheLeftOf(playerTest1));
		
		//cas ou playerTest1 est a gauche de playerTest2 et sur une ligne differente
		playerTest1.getPawn().setPosY(7);
		playerTest2.getPawn().setPosY(5);
		playerTest1.getPawn().setPosX(3);
		playerTest2.getPawn().setPosX(5);
		assertEquals(false, playerTest2.isJustToTheLeftOf(playerTest1));
				
		//cas ou playerTest1 est a droite de playerTest2 et sur une ligne differente
		playerTest1.getPawn().setPosY(5);
		playerTest2.getPawn().setPosY(7);
		playerTest1.getPawn().setPosX(5);
		playerTest2.getPawn().setPosX(3);
		assertEquals(false, playerTest2.isJustToTheLeftOf(playerTest1));	
	}
	
	@Test
	public void isJustToTheRightTest() {
		Player playerTest1 = new Player();
		Player playerTest2 = new Player();
		
		//cas ou playerTest1 est a droite de playerTest2 et sur la meme ligne
		playerTest1.getPawn().setPosY(5);
		playerTest2.getPawn().setPosY(5);
		playerTest1.getPawn().setPosX(3);
		playerTest2.getPawn().setPosX(5);
		assertEquals(true, playerTest2.isJustToTheRightOf(playerTest1));
		
		//cas ou playerTest1 est a gauche de playerTest2 et sur la meme ligne
		playerTest1.getPawn().setPosY(5);
		playerTest2.getPawn().setPosY(5);
		playerTest1.getPawn().setPosX(5);
		playerTest2.getPawn().setPosX(3);
		assertEquals(false, playerTest2.isJustToTheRightOf(playerTest1));
		
		//cas ou playerTest1 est a droite de playerTest2 et sur une ligne differente
		playerTest1.getPawn().setPosY(7);
		playerTest2.getPawn().setPosY(5);
		playerTest1.getPawn().setPosX(3);
		playerTest2.getPawn().setPosX(5);
		assertEquals(false, playerTest2.isJustToTheRightOf(playerTest1));
				
		//cas ou playerTest1 est a gauche de playerTest2 et sur une ligne differente
		playerTest1.getPawn().setPosY(5);
		playerTest2.getPawn().setPosY(7);
		playerTest1.getPawn().setPosX(5);
		playerTest2.getPawn().setPosX(3);
		assertEquals(false, playerTest2.isJustToTheRightOf(playerTest1));	
	}

}
