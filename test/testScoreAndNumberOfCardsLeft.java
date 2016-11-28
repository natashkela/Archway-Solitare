package nvacheishvili_test;
import java.awt.event.MouseEvent;
import junit.framework.TestCase;
import ks.common.model.Deck;
import ks.common.model.MultiDeck;
import ks.launcher.Main;
import ks.tests.model.ModelFactory;
import nvacheishvili.Archway;
import ks.common.games.*;
import ks.*;
import ks.client.gamefactory.GameWindow;
public class testScoreAndNumberOfCardsLeft extends TestCase {
	Archway archway;
	GameWindow gw;
	//MultiDeck multiDeck;
	@Override
	protected void setUp() {
		archway = new Archway();
		gw = Main.generateWindow(archway, Deck.OrderBySuit);
	}
	
	@Override
	protected void tearDown() {
		gw.dispose();
	}
	//test number of cars left when the game begins
	public void testNumberOfCardsLeft() {
		assertEquals(96,archway.multiDeck.count());
	}
	public void testScore(){
		assertEquals(0,archway.getScoreValue());
	}
}
