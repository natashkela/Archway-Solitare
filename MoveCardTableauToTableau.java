package nvacheishvili;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Pile;

public class MoveCardTableauToTableau {
	Column source, target; 
	Card cardBeingDragged;
	MoveCardTableauToTableau(Column a, Column b, Card c){
		this.source = a;
		this.target = b;
		this.cardBeingDragged =c;
	}

	boolean doMove(Solitaire a){
		if(!valid(a)){
			return false;
		}
		Card c = source.get();
		target.add(c);
		return true;
	}
	
	boolean undo(Solitaire a){
		Card c = target.get();
		source.add(c);
		return true;
	}
	
	boolean valid(Solitaire a){
		if(target.empty()){
			return true;
		}
		else{
			return false;
		}
	}
}
