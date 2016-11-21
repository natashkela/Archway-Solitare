package nvacheishvili;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Move;
import ks.common.model.Pile;

public class MoveCardTableuToAce extends Move{
	Column source;
	Pile target; 
	Card cardBeingDragged;
	public MoveCardTableuToAce(Column a, Pile b,Card c){
		this.source = a;
		this.target = b;
		this.cardBeingDragged=c;
	}

	public boolean doMove(Solitaire a){
		if(!valid(a)){
			return false;
		}
		target.add(cardBeingDragged);
		a.updateNumberCardsLeft(-1);
		a.updateScore(+1);
		return true;
	}
	
	public boolean undo(Solitaire a){
		source.add(cardBeingDragged);
		target.get();
		a.updateNumberCardsLeft(+1);
		a.updateScore(-1);
		return true;
	}
	
	public boolean valid(Solitaire a){
		if((cardBeingDragged.getRank()-1)==target.peek().getRank()&&
				cardBeingDragged.getSuit()==target.peek().getSuit()){
			return true;
		}
		else{
			return false;
		}
	}
}