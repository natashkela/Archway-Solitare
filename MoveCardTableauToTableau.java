package nvacheishvili;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Move;
import ks.common.model.Pile;

public class MoveCardTableauToTableau extends Move{
	Column source, target; 
	Card cardBeingDragged;
	public MoveCardTableauToTableau(Column a, Column b, Card c){
		this.source = a;
		this.target = b;
		this.cardBeingDragged =c;
	}

	public boolean doMove(Solitaire a){
		if(!valid(a)){
			return false;
		}
		target.add(cardBeingDragged);
		return true;
	}
	
	public boolean undo(Solitaire a){
		source.add(cardBeingDragged);
		target.get();
		return true;
	}
	
	public boolean valid(Solitaire a){
		if(target.empty()){
			return true;
		}
		else{
			return false;
		}
	}
}
