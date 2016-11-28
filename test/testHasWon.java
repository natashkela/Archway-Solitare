package nvacheishvili_test;

import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Deck;
import ks.launcher.Main;
import nvacheishvili.Archway;

public class testHasWon extends TestCase {
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
	public void testHasWon(){
		assertFalse(archway.hasWon());
	}
}
