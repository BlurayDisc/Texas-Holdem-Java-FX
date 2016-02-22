package com.run.poker.hand.condition;

import java.util.List;

import com.run.poker.entity.Card;
import com.run.poker.hand.Rank;

public class HighCard extends Condition {

	public HighCard() {
		this.rank = Rank.HighCard;
	}	
	
	@Override
	public boolean check(List<Card> cards) {
		return true;
	}

	@Override
	public void finalise(List<Card> cards) {
		// TODO Auto-generated method stub
		
	}

}
