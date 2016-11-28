package nvacheishvili_test;

import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Deck;
import ks.common.model.Pile;
import ks.launcher.Main;
import nvacheishvili.Archway;
import nvacheishvili.MoveCardReserveToAce;
import nvacheishvili.MoveCardReserveToKing;
import nvacheishvili.MoveCardTableauToKing;
import nvacheishvili.MoveCardTableuToAce;

public class testMoveReserveToFoundation extends TestCase {
	Archway archway;
	GameWindow gw;
	
	@Override
	protected void setUp() {
		archway = new Archway();
		gw = Main.generateWindow(archway, Deck.OrderBySuit);
	}
	
	@Override
	protected void tearDown() {
		gw.dispose();
	}
	
	public void testReserveToAce() {
		Pile pile1=new Pile();
		Pile pile2 = new Pile();
		Card twoOfSpades = new Card(2,4);
		pile1.add(twoOfSpades);
		Card aceOfSpades = new Card(1,4);
		pile2.add(aceOfSpades);
		Card cardBiengDragged=twoOfSpades;
		MoveCardReserveToAce fm = new MoveCardReserveToAce(pile1,pile2,cardBiengDragged);
		assertTrue(fm.doMove(archway));
	}
	
	public void testReserveToAce1() {
		Pile pile1=new Pile();
		Card threeOfSpades = new Card(3,4);
		Card aceOfSpades = new Card(1,4);
		Pile pile2 = new Pile();
		pile2.add(aceOfSpades);
		pile1.add(threeOfSpades);
		Card cardbeingdragged = threeOfSpades;
		MoveCardReserveToAce fm = new MoveCardReserveToAce(pile1, pile2, cardbeingdragged);
		assertFalse(fm.doMove(archway));
	}
	
	public void testColumnToKing() {
		Pile pile1=new Pile();
		Pile pile2 = new Pile();
		Card queenOfSpades = new Card(12,4);
		pile1.add(queenOfSpades);
		Card kingOfSpades = new Card(13,4);
		pile2.add(kingOfSpades);
		Card cardBiengDragged=queenOfSpades;
		MoveCardReserveToKing fm = new MoveCardReserveToKing(pile1,pile2,cardBiengDragged);
		assertTrue(fm.doMove(archway));
	}
	
	public void testColumnToKing1() {
		Pile pile1=new Pile();
		Pile pile2 = new Pile();
		Card twoOfSpades = new Card(2,4);
		pile1.add(twoOfSpades);
		Card kingOfSpades = new Card(13,4);
		pile2.add(kingOfSpades);
		Card cardBiengDragged=twoOfSpades;
		MoveCardReserveToKing fm = new MoveCardReserveToKing(pile1,pile2,cardBiengDragged);
		assertFalse(fm.doMove(archway));
	}
	
}
