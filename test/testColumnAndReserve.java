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
import nvacheishvili.MoveCardReserveToTableau;
import nvacheishvili.MoveCardTableauToTableau;

public class testColumnAndReserve extends TestCase {
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
	
	public void testReserveToColumn() {
		Pile pile=new Pile();
		Column column = new Column();
		Card twoOfSpades = new Card(2,4);
		pile.add(twoOfSpades);
		Card cardBiengDragged=twoOfSpades;
		MoveCardReserveToTableau fm = new MoveCardReserveToTableau(pile,column,cardBiengDragged);
		assertTrue(fm.doMove(archway));
	}
	public void testReserveToColumn1() {
		Pile pile=new Pile();
		Column column = new Column();
		Card twoOfSpades = new Card(2,4);
		pile.add(twoOfSpades);
		column.add(twoOfSpades);
		Card cardBiengDragged=twoOfSpades;
		MoveCardReserveToTableau fm = new MoveCardReserveToTableau(pile,column,cardBiengDragged);
		assertFalse(fm.doMove(archway));
	}
	
	public void testColumnToColumn(){
		Column col1 = new Column();
		Column col2 = new Column();
		Card twoOfSpades = new Card(2,4);
		col1.add(twoOfSpades);
		Card cardBeingDragged = twoOfSpades;
		MoveCardTableauToTableau fm = new MoveCardTableauToTableau(col1,col2,cardBeingDragged);
		assertTrue(fm.doMove(archway));
	}
	
	public void testColumnToColumn1(){
		Column col1 = new Column();
		Column col2 = new Column();
		Card twoOfSpades = new Card(2,4);
		col1.add(twoOfSpades);
		col2.add(twoOfSpades);
		Card cardBeingDragged = twoOfSpades;
		MoveCardTableauToTableau fm = new MoveCardTableauToTableau(col1,col2,cardBeingDragged);
		assertFalse
		(fm.doMove(archway));
	}
	
}
