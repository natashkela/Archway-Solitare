package nvacheishvili;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import heineman.klondike.MoveCardToFoundationMove;
import heineman.klondike.MoveWasteToFoundationMove;
import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.games.Solitaire;
import ks.common.model.BuildablePile;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Move;
import ks.common.model.Pile;
import ks.common.view.BuildablePileView;
import ks.common.view.CardView;
import ks.common.view.ColumnView;
import ks.common.view.Container;
import ks.common.view.PileView;
import ks.common.view.Widget;

public class AceFoundationController extends SolitaireReleasedAdapter{
	Archway a;
	PileView aceFoundation;
	public AceFoundationController(Archway a, PileView aceFoundation) {
		super(a);
		this.a = a;
		this.aceFoundation = aceFoundation;
	}
	public void mouseReleased(MouseEvent me) {
		Container c = theGame.getContainer();

		/** Return if there is no card being dragged chosen. */
		Widget draggingWidget = c.getActiveDraggingObject();
		if (draggingWidget == Container.getNothingBeingDragged()) {
			System.err.println ("FoundationController::mouseReleased() unexpectedly found nothing being dragged.");
			c.releaseDraggingObject();		
			return;
		}

		/** Recover the from BuildablePile OR waste Pile */
		Widget fromWidget = c.getDragSource();
		if (fromWidget == null) {
			System.err.println ("FoundationController::mouseReleased(): somehow no dragSource in container.");
			c.releaseDraggingObject();
			return;
		}

		// Determine the To Pile
		Pile foundation = (Pile) aceFoundation.getModelElement();
		if(fromWidget instanceof PileView){
			Pile pile = (Pile) fromWidget.getModelElement();
			
			CardView cardView = (CardView) draggingWidget;
			Card theCard = (Card) cardView.getModelElement();
			Move move = new MoveCardReserveToAce(pile,foundation, theCard);
			if(move.doMove(a)){
				a.pushMove(move);
				a.refreshWidgets();
			}
			else{
				fromWidget.returnWidget(draggingWidget);
			}
			c.releaseDraggingObject();
			c.repaint();
		}
		else if(fromWidget instanceof ColumnView){
			Column column = (Column) fromWidget.getModelElement();
			
			CardView cardView = (CardView) draggingWidget;
			Card theCard = (Card) cardView.getModelElement();
			Move move = new MoveCardTableuToAce(column,foundation, theCard);
			if(move.doMove(a)){
				a.pushMove(move);
				a.refreshWidgets();
			}
			else{
				fromWidget.returnWidget(draggingWidget);
			}
			c.releaseDraggingObject();
			c.repaint();
		}
	}
	
}
