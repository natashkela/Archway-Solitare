package nvacheishvili_test;
import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Deck;
import ks.common.model.Pile;
import ks.launcher.Main;
import nvacheishvili.Archway;
import nvacheishvili.MoveCardTableauToKing;
import nvacheishvili.MoveCardTableuToAce;

public class testMoveColumnToFoundation extends TestCase {
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
	
	public void testColumnToAce() {
		Column newColumn=new Column();
		Card twoOfSpades = new Card(2,4);
		Card aceOfSpades = new Card(1,4);
		Pile pile = new Pile();
		pile.add(aceOfSpades);
		newColumn.add(twoOfSpades);
		Card cardbeingdragged = twoOfSpades;
		MoveCardTableuToAce fm = new MoveCardTableuToAce(newColumn, pile, cardbeingdragged);
		assertTrue(fm.doMove(archway));
		assertTrue(fm.undo(archway));
	}
	
	public void testColumnToAce1() {
		Column newColumn=new Column();
		Card threeOfSpades = new Card(3,4);
		Card aceOfSpades = new Card(1,4);
		Pile pile = new Pile();
		pile.add(aceOfSpades);
		newColumn.add(threeOfSpades);
		Card cardbeingdragged = threeOfSpades;
		MoveCardTableuToAce fm = new MoveCardTableuToAce(newColumn, pile, cardbeingdragged);
		assertFalse(fm.doMove(archway));
	}
	
	public void testColumnToKing() {
		Column newColumn=new Column();
		Card queenOfSpades = new Card(12,4);
		Card kingOfSpades = new Card(13,4);
		Pile pile = new Pile();
		pile.add(kingOfSpades);
		newColumn.add(queenOfSpades);
		Card cardbeingdragged = queenOfSpades;
		MoveCardTableauToKing fm = new MoveCardTableauToKing(newColumn, pile, cardbeingdragged);
		assertTrue(fm.doMove(archway));
	}
	
	public void testColumnToKing1() {
		Column newColumn=new Column();
		Card jackOfSpades = new Card(11,4);
		Card kingOfSpades = new Card(13,4);
		Pile pile = new Pile();
		pile.add(kingOfSpades);
		newColumn.add(jackOfSpades);
		Card cardbeingdragged = jackOfSpades;
		MoveCardTableauToKing fm = new MoveCardTableauToKing(newColumn, pile, cardbeingdragged);
		assertFalse(fm.doMove(archway));
	}
}
