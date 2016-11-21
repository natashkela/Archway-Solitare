package nvacheishvili;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import heineman.klondike.DealCardMove;
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
 * Mouse Presses on columns
 * */
public class ArchwayColumnController extends MouseAdapter{
	
	Archway a;
	ColumnView column;
	public ArchwayColumnController(Archway a, ColumnView column) {
		this.a = a;
		this.column = column;
	}
	@Override
	public void mousePressed(MouseEvent me) {
		// The container manages several critical pieces of information; namely, it
				// is responsible for the draggingObject; in our case, this would be a CardView
				// Widget managing the card we are trying to drag between two piles.
				Container c = a.getContainer();
				
				/** Return if there is no card to be chosen. */
				Column p = (Column) column.getModelElement();
				if (p.count() == 0) {
					c.releaseDraggingObject();
					return;
				}
			
				// Get a card to move from PileView. Note: this returns a CardView.
				// Note that this method will alter the model for PileView if the condition is met.
				CardView cardView = column.getCardViewForTopCard(me);
				
				// an invalid selection of some sort.
				if (cardView == null) {
					c.releaseDraggingObject();
					return;
				}
				
				// If we get here, then the user has indeed clicked on the top card in the PileView and
				// we are able to now move it on the screen at will. For smooth action, the bounds for the
				// cardView widget reflect the original card location on the screen.
				Widget w = c.getActiveDraggingObject();
				if (w != Container.getNothingBeingDragged()) {
					System.err.println ("WastePileController::mousePressed(): Unexpectedly encountered a Dragging Object during a Mouse press.");
					return;
				}
			
				// Tell container which object is being dragged, and where in that widget the user clicked.
				c.setActiveDraggingObject (cardView, me);
				
				// Tell container which source widget initiated the drag
				c.setDragSource (column);
			
				// The only widget that could have changed is ourselves. If we called refresh, there
				// would be a flicker, because the dragged widget would not be redrawn. We simply
				// force the WastePile's image to be updated, but nothing is refreshed on the screen.
				// This is patently OK because the card has not yet been dragged away to reveal the
				// card beneath it.  A bit tricky and I like it!
				column.redraw();
	}
	
	//This is here because of column Release.
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
		Column col = (Column) column.getModelElement();
		if (fromWidget instanceof PileView) {
			Pile pile = (Pile) fromWidget.getModelElement();
			CardView cardView = (CardView) draggingWidget;
			Card theCard = (Card) cardView.getModelElement();
			Move move = new MoveCardReserveToTableau(pile,col, theCard);
			if(move.doMove(a)){
				a.pushMove(move);
				a.refreshWidgets();
			}
			else{
				fromWidget.returnWidget(draggingWidget);
			}
			c.releaseDraggingObject();
			c.repaint();
			
		} else if (fromWidget instanceof ColumnView) {
			
			Column column = (Column) fromWidget.getModelElement();
			
			CardView cardView = (CardView) draggingWidget;
			Card theCard = (Card) cardView.getModelElement();
			Move move = new MoveCardTableauToTableau(column,col, theCard);
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
