package com.run.poker.hand.condition;

import java.util.List;

import com.run.poker.entity.Card;
import com.run.poker.hand.Rank;

public class FullHouse extends Condition {
	
	public FullHouse() {
		this.rank = Rank.FullHouse;
	}

	@Override
	public boolean check(List<Card> cards) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void finalise(List<Card> cards) {
		// TODO Auto-generated method stub
		
	}

}
