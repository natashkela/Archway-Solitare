package nvacheishvili;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ks.common.model.Column;
import ks.common.model.Pile;
import ks.common.view.CardView;
import ks.common.view.Container;
import ks.common.view.PileView;
import ks.common.view.Widget;

/*
 * Mouse presses on reserve
 * */
public class reserveController extends MouseAdapter{
	Archway a;
	PileView reserveView;
	public reserveController(Archway a, PileView reserveView) {
		this.a = a;
		this.reserveView = reserveView;
	}

	@Override
	public void mousePressed(MouseEvent me) {
		// The container manages several critical pieces of information; namely, it
				// is responsible for the draggingObject; in our case, this would be a CardView
				// Widget managing the card we are trying to drag between two piles.
				Container c = a.getContainer();
				
				/** Return if there is no card to be chosen. */
				Pile p = (Pile) reserveView.getModelElement();
				if (p.count() == 0) {
					c.releaseDraggingObject();
					return;
				}
			
				// Get a card to move from PileView. Note: this returns a CardView.
				// Note that this method will alter the model for PileView if the condition is met.
				CardView cardView = reserveView.getCardViewForTopCard(me);
				
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
				c.setDragSource (reserveView);
			
				// The only widget that could have changed is ourselves. If we called refresh, there
				// would be a flicker, because the dragged widget would not be redrawn. We simply
				// force the WastePile's image to be updated, but nothing is refreshed on the screen.
				// This is patently OK because the card has not yet been dragged away to reveal the
				// card beneath it.  A bit tricky and I like it!
				reserveView.redraw();
	}
}
