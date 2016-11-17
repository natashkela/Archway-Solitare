package nvacheishvili;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Pile;

public class MoveCardReserveToAce {
	Pile source;
	Pile target; 
	Card cardBeingDragged;
	MoveCardReserveToAce(Pile a, Pile b, Card c){
		this.source = a;
		this.target = b;
		this.cardBeingDragged =c;
	}

	boolean doMove(Solitaire a){
		if(!valid(a)){
			return false;
		}
		Card c = source.get();//removes card from the source too
		target.add(c);
		a.updateScore(+1);
		a.updateNumberCardsLeft(-1);
		return true;
	}
	
	boolean undo(Solitaire a){
		Card c = target.get();
		source.add(c);
		a.updateScore(-1);
		a.updateNumberCardsLeft(+1);
		return true;
	}
	
	boolean valid(Solitaire a){
		Card c = target.get();
		if((cardBeingDragged.getRank()-1)==c.getRank()&&
				cardBeingDragged.getSuit()==c.getSuit()){
			target.add(c);//add the card back
			return true;
		}
		else{
			target.add(c);//add the card back
			return false;
		}
	}
}
