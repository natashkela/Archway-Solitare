package nvacheishvili;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Move;
import ks.common.model.Pile;
import ks.common.view.CardView;
import ks.common.view.ColumnView;
import ks.common.view.Container;
import ks.common.view.PileView;
import ks.common.view.Widget;

/*
 * Mouse Released on king Foundation
 * */
public class KingFoundationController extends SolitaireReleasedAdapter{
	Archway a;
	PileView kingFoundation;
	public KingFoundationController(Archway a, PileView kingFoundation) {
		super(a);
		this.a = a;
		this.kingFoundation = kingFoundation;
	}
	
	public void mouseReleased(MouseEvent me) {
		Container c = a.getContainer();

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
		Pile foundation = (Pile) kingFoundation.getModelElement();
		if(fromWidget instanceof PileView){
			Pile pile = (Pile) fromWidget.getModelElement();
			
			CardView cardView = (CardView) draggingWidget;
			Card theCard = (Card) cardView.getModelElement();
			Move move = new MoveCardReserveToKing(pile,foundation, theCard);
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
			Move move = new MoveCardTableauToKing(column,foundation, theCard);
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
