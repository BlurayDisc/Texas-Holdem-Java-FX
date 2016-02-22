package com.run.poker.hand.condition;

import java.util.Collections;
import java.util.List;

import com.run.poker.entity.Card;
import com.run.poker.hand.Rank;

public class FourOfaKind extends Condition {
	
	public FourOfaKind() {
		this.rank = Rank.FourOfaKind;
	}

	@Override
	public boolean check(List<Card> cards) {
		boolean fourOfaKind = false;
		for (Card card: cards) {
			int frequency = Collections.frequency(cards, card);
			if (frequency == FOUR_CONDITION) {
				fourOfaKind = true;
				break;
			}
		}
		return fourOfaKind;
	}

	@Override
	public void finalise(List<Card> cards) {
		// TODO Auto-generated method stub
		
	}

}
