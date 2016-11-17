package nvacheishvili;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedList;
import heineman.Klondike;
import ks.client.gamefactory.GameWindow;
import ks.common.games.Solitaire;
import ks.common.model.*;
import ks.common.view.*;
import ks.launcher.Main;

public class Archway extends Solitaire{
	//int num;
	MultiDeck multiDeck;//104 cards needed 
	//-----------------------reservePiles------------------------------------
	//for reserve cards create 13 piles
	Pile reservePiles[] = new Pile[13];
	//-------------------------columns-----------------------------
	//for four columns in the middle that holds 12 cards in each
	Column column[] = new Column[4];
	
	/*Should I call it Pile or AcePile is there anything for foundation?*/
	//-----------------------------AcePile and KingPile------------------------
	//Foundation of aces 
	Pile aceFoundation[] = new Pile[4];
	//foundation of kings
	Pile kingFoundation[] = new Pile[4];
	
	//-------------------------------Viewes----------------------------------
	
	//-----------------------------Reserve View------------------------------
	//PileView array of 13 for reserve card views
	PileView reserveView[] = new PileView[13];
	
	//--------------------------ace and king foundation View--------------------------
	//PileView array of 4 for ace foundation pile view
	PileView aceView[] = new PileView[4];
	//PileView array of 4 for king foundation pile view
	PileView kingView[] = new PileView[4];
	//-----------------------Column View--------------------------------------
	ColumnView columnView[] = new ColumnView[4];
	
	//-----------------------Score and Number of Cards Left View--------------
	IntegerView scoreView;
	IntegerView numLeftView;
	
	@Override
	public String getName() {
		return "Archway";
	}

	@Override
	public boolean hasWon() {
		// TODO Auto-generated method stub
		return false;
	}

	public Dimension getPreferredSize() {
		return new Dimension (1280, 1280);
	}
		
	@Override
	public void initialize() {
		// initialize model
		initializeModel(getSeed());
		initializeView();
		initializeControllers();
		
		/*Should I do it here? Or is it better to move it somewhere else? If yes, where should I
		 * move it ?*/
		//-----------------------------Building up Ace and King foundation----------------------
		Card aceOfSpades = new Card(1,4);//Ace of Spades
		aceFoundation[0].add(aceOfSpades);
		
		Card aceOfClubs = new Card(1,3);//Ace of Clubs
		aceFoundation[1].add(aceOfClubs);
		
		Card aceOfDiamonds= new Card(1,2);//Ace of Diamonds
		aceFoundation[2].add(aceOfDiamonds);
		
		Card aceOfHearts = new Card(1,1);//Ace of Hearts
		aceFoundation[3].add(aceOfHearts);
		
		Card kingOfSpades = new Card(13,4); //King of Spades
		kingFoundation[0].add(kingOfSpades);
		
		Card kingOfClubs = new Card(13,3); //King of Clubs
		kingFoundation[1].add(kingOfClubs);
		
		Card kingOfDiamonds = new Card(13,2); //King of Diamonds
		kingFoundation[2].add(kingOfDiamonds);
		
		Card kingOfHearts = new Card(13,1); //King of Hearts
		kingFoundation[3].add(kingOfHearts);
		
		// MAKE SURE deck removes these cards...
		Stack tmp = new Stack();
		ArrayList<Card> acesSeen = new ArrayList<Card>();
		ArrayList<Card> kingsSeen = new ArrayList<Card>();
		
		while (!multiDeck.empty()) {
			Card c = multiDeck.get();
			if (c.isAce()) {
				if (!acesSeen.contains(c)) {
					acesSeen.add(c);
				} 
				else {
					tmp.add(c);
				}
			} 
			else if (c.getRank() == Card.KING) {
				if(!kingsSeen.contains(c)){
					kingsSeen.add(c);
				}
				else{
					tmp.add(c);
				}
			} 
			else {
				tmp.add(c);
			}
		}
		
		// restack the deck from the cards 
		restack(tmp);
		//num = multiDeck.count();
		//Putting cards into columns
		Stack temp1 = new Stack();
		for(int i=0;i<4;i++){
			for(int j=0;j<12;j++){
				Card c = multiDeck.get();
				column[i].add(c);
				temp1.add(c);
			}
		}
		restack(temp1);
		Stack temp = new Stack();
		
		//put the cards into reserve piles
		while(!(multiDeck.count()==0)){
			Card c = multiDeck.get();
			if(c.getRank()==1){
				reservePiles[0].add(c);
			}
			else if(c.getRank()==2){
				reservePiles[1].add(c);
			}
			else if(c.getRank()==3){
				reservePiles[2].add(c);
			}
			else if(c.getRank()==4){
				reservePiles[3].add(c);
			}
			else if(c.getRank()==5){
				reservePiles[4].add(c);
			}
			else if(c.getRank()==6){
				reservePiles[5].add(c);
			}
			else if(c.getRank()==7){
				reservePiles[6].add(c);
			}
			else if(c.getRank()==8){
				reservePiles[7].add(c);
			}
			else if(c.getRank()==9){
				reservePiles[8].add(c);
			}
			else if(c.getRank()==10){
				reservePiles[9].add(c);
			}
			else if(c.getRank()==11){
				reservePiles[10].add(c);
			}
			else if(c.getRank()==12){
				reservePiles[11].add(c);
			}
			else if(c.getRank()==13){
				reservePiles[12].add(c);
			}
			temp.add(c);
		}
		restack(temp);
		updateNumberCardsLeft(multiDeck.count()); 
	}

	//To restack the cards gotten from the multiDeck
	//needed so that cards are not going to be deleted when grabbed from multiDeck
	private void restack(Stack temp){
		while(!temp.empty()){
			multiDeck.add(temp.get());
		}
	}
	private void initializeControllers() {
		// TODO Auto-generated method stub
		
	}

	private void initializeView() {
		CardImages ci = getCardImages(); //get the images of the cards
		//We do not have deckView at all
		//73 97 Width Height of the cards!
		//-----------------------------reserveViews---------------------------------
		reserveView[0] = new PileView(reservePiles[0]);
		reserveView[0].setBounds (20, 20, ci.getWidth(), ci.getHeight());;
		container.addWidget (reserveView[0]);
		
		reserveView[1] = new PileView(reservePiles[1]);
		reserveView[1].setBounds (30+ci.getWidth(), 20, ci.getWidth(), ci.getHeight());
		container.addWidget (reserveView[1]);
		
		reserveView[2] = new PileView(reservePiles[2]);
		reserveView[2].setBounds (40+2*ci.getWidth(), 20, ci.getWidth(), ci.getHeight());
		container.addWidget (reserveView[2]);
		
		reserveView[3] = new PileView(reservePiles[3]);
		reserveView[3].setBounds (50+3*ci.getWidth(), 20, ci.getWidth(), ci.getHeight());
		container.addWidget (reserveView[3]);
		
		reserveView[4] = new PileView(reservePiles[4]);
		reserveView[4].setBounds (60+4*ci.getWidth(), 20, ci.getWidth(), ci.getHeight());
		container.addWidget (reserveView[4]);
		
		reserveView[5] = new PileView(reservePiles[5]);
		reserveView[5].setBounds (70+5*ci.getWidth(), 20, ci.getWidth(), ci.getHeight());
		container.addWidget (reserveView[5]);
		
		reserveView[6] = new PileView(reservePiles[6]);
		reserveView[6].setBounds (80+6*ci.getWidth(), 20, ci.getWidth(), ci.getHeight());
		container.addWidget (reserveView[6]);
		
		reserveView[7] = new PileView(reservePiles[7]);
		reserveView[7].setBounds (90+7*ci.getWidth(), 20, ci.getWidth(), ci.getHeight());
		container.addWidget (reserveView[7]);
		
		reserveView[8] = new PileView(reservePiles[8]);
		reserveView[8].setBounds (100+8*ci.getWidth(), 20, ci.getWidth(), ci.getHeight());
		container.addWidget (reserveView[8]);
		
		reserveView[9] = new PileView(reservePiles[9]);
		reserveView[9].setBounds (110+9*ci.getWidth(), 20, ci.getWidth(), ci.getHeight());
		container.addWidget (reserveView[9]);
		
		reserveView[10] = new PileView(reservePiles[10]);
		reserveView[10].setBounds (120+10*ci.getWidth(), 20, ci.getWidth(), ci.getHeight());
		container.addWidget (reserveView[10]);
		
		reserveView[11] = new PileView(reservePiles[11]);
		reserveView[11].setBounds (130+11*ci.getWidth(), 20, ci.getWidth(), ci.getHeight());
		container.addWidget (reserveView[11]);
		
		reserveView[12] = new PileView(reservePiles[12]);
		reserveView[12].setBounds (140+12*ci.getWidth(), 20, ci.getWidth(), ci.getHeight());
		container.addWidget (reserveView[12]);
		
		//---------------------------------Ace and king Pile Views-------------------------
		//ACES
		
		aceView[0] = new PileView(aceFoundation[0]);
		aceView[0].setBounds (20, 570, ci.getWidth(), ci.getHeight());
		container.addWidget (aceView[0]);
		
		aceView[1] = new PileView(aceFoundation[1]);
		aceView[1].setBounds (30+ci.getWidth(), 570, ci.getWidth(), ci.getHeight());
		container.addWidget (aceView[1]);
		
		aceView[2] = new PileView(aceFoundation[2]);
		aceView[2].setBounds (40+2*ci.getWidth(), 570, ci.getWidth(), ci.getHeight());
		container.addWidget (aceView[2]);
		
		aceView[3] = new PileView(aceFoundation[3]);
		aceView[3].setBounds (50+3*ci.getWidth(), 570, ci.getWidth(), ci.getHeight());
		container.addWidget (aceView[3]);
		
		//KINGS
		kingView[0] = new PileView(kingFoundation[0]);
		kingView[0].setBounds (800, 570, ci.getWidth(), ci.getHeight());
		container.addWidget (kingView[0]);
		
		kingView[1] = new PileView(kingFoundation[1]);
		kingView[1].setBounds (810+ci.getWidth(), 570, ci.getWidth(), ci.getHeight());
		container.addWidget (kingView[1]);
		
		kingView[2] = new PileView(kingFoundation[2]);
		kingView[2].setBounds (820+2*ci.getWidth(), 570, ci.getWidth(), ci.getHeight());
		container.addWidget (kingView[2]);
		
		kingView[3] = new PileView(kingFoundation[3]);
		kingView[3].setBounds (830+3*ci.getWidth(), 570, ci.getWidth(), ci.getHeight());
		container.addWidget (kingView[3]);
		
		//COLUMNS
		columnView[0] = new ColumnView(column[0]);
		columnView[0].setBounds (410, 200, ci.getWidth(), 4*ci.getHeight());
		container.addWidget (columnView[0]);
		
		columnView[1] = new ColumnView(column[1]);
		columnView[1].setBounds (420+ci.getWidth(), 200, ci.getWidth(), 4*ci.getHeight());
		container.addWidget (columnView[1]);
		
		columnView[2] = new ColumnView(column[2]);
		columnView[2].setBounds (430+2*ci.getWidth(), 200, ci.getWidth(), 4*ci.getHeight());
		container.addWidget (columnView[2]);
		
		columnView[3] = new ColumnView(column[3]);
		columnView[3].setBounds (440+3*ci.getWidth(), 200, ci.getWidth(), 4*ci.getHeight());
		container.addWidget (columnView[3]);
		
		//------------------------------ScoreView----------------------------------
		scoreView = new IntegerView (getScore());
		scoreView.setFontSize (14);
		scoreView.setBounds (0, 160, 160, 60);
		container.addWidget (scoreView);
		
		//-----------------------------NumLeftview-----------------------------------
		numLeftView = new IntegerView (getNumLeft());
		numLeftView.setFontSize (14);
		numLeftView.setBounds (30, 230, ci.getWidth(), 20);
		container.addWidget (numLeftView);
		
	}

	private void initializeModel(int seed) {
		//Instead of "d" we put 2, because MultiDeck takes in numDecks instead of Strings and
		//numDecks is number of decks, which is 2 in our case because we need 104 cards.
		
		multiDeck = new MultiDeck(2); 
		multiDeck.create(seed);
		//num=multiDeck.count();
		//num=multiDeck.numCardsLeftFoundation();
		
		model.addElement (multiDeck);   // add to our model (as defined within our superclass).
		
		//-------------ReservePile declarations----------------------
		//1
		reservePiles[0] = new Pile("AceReservePile");
		model.addElement(reservePiles[0]);
		//2
		reservePiles[1] = new Pile("TwoReservePile");
		model.addElement(reservePiles[1]);
		//3
		reservePiles[2] = new Pile("ThreeReservePile");
		model.addElement(reservePiles[2]);
		//4
		reservePiles[3] = new Pile("FourReservePile");
		model.addElement(reservePiles[3]);
		//5
		reservePiles[4] = new Pile("FiveReservePile");
		model.addElement(reservePiles[4]);
		//6
		reservePiles[5] = new Pile("SixReservePile");
		model.addElement(reservePiles[5]);
		//7
		reservePiles[6] = new Pile("SevenReservePile");
		model.addElement(reservePiles[6]);
		//8
		reservePiles[7] = new Pile("EightReservePile");
		model.addElement(reservePiles[7]);
		//9
		reservePiles[8] = new Pile("NineReservePile");
		model.addElement(reservePiles[8]);
		//10
		reservePiles[9] = new Pile("TenReservePile");
		model.addElement(reservePiles[9]);
		//Jack
		reservePiles[10] = new Pile("JackReservePile");
		model.addElement(reservePiles[10]);
		//Queen
		reservePiles[11] = new Pile("QueenReservePile");
		model.addElement(reservePiles[11]);
		//King
		reservePiles[12] = new Pile("KingReservePile");
		model.addElement(reservePiles[12]);
		
		//------------------------Column declarations---------------------
		//The leftmost one
		column[0] = new Column();
		model.addElement(column[0]);
		//The left-middle one
		column[1] = new Column();
		model.addElement(column[1]);
		//The right-middle one
		column[2] = new Column();
		model.addElement(column[2]);
		//The right one
		column[3] = new Column();
		model.addElement(column[3]);
		
		//------------------------Ace Foundation declarations---------------
		aceFoundation[0] = new Pile("aceFoundation1");
		model.addElement(aceFoundation[0]);
		aceFoundation[1] = new Pile("aceFoundation2");
		model.addElement(aceFoundation[1]);
		aceFoundation[2] = new Pile("aceFoundation3");
		model.addElement(aceFoundation[2]);
		aceFoundation[3] = new Pile("aceFoundation4");
		model.addElement(aceFoundation[3]);
		
		//------------------------King Foundation declarations------------
		kingFoundation[0] = new Pile("kingFoundation1");
		model.addElement(kingFoundation[0]);
		kingFoundation[1] = new Pile("kingFoundation2");
		model.addElement(kingFoundation[1]);
		kingFoundation[2] = new Pile("kingFoundation3");
		model.addElement(kingFoundation[2]);
		kingFoundation[3] = new Pile("kingFoundation4");
		model.addElement(kingFoundation[3]);
		
		updateScore(0);
		/*Is this after I deal this cards or before the cards are dealt?*/
		 
		
	}
	
	/** Code to launch solitaire variation. */
	public static void main (String []args) {
		// Seed is to ensure we get the same initial cards every time.
		// Here the seed is to "order by suit."
		GameWindow gw = Main.generateWindow(new Archway(), Deck.OrderBySuit);
		gw.getPreferredSize();
		//gw.setSkin(SkinCatalog.MULTIPLE_BOUNCING_BALLS);

	}

}
