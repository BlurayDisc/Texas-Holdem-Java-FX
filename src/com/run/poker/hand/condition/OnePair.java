package com.run.poker.hand.condition;

import java.util.Collections;
import java.util.List;

import com.run.poker.entity.Card;
import com.run.poker.hand.Rank;

public class OnePair extends Condition {
	
	public OnePair() {
		this.rank = Rank.OnePair;
	}

	@Override
	public boolean check(List<Card> cards) {
		for (Card card: cards) {
			int frequency = Collections.frequency(cards, card);
			if (frequency == TWO_OF_A_KIND_CONDITION) {
				showDown.add(card);
			}
		}
		return showDown.size() == TWO_OF_A_KIND_CONDITION;
	}

	@Override
	public void finalise(List<Card> cards) {
		for (Card card: cards) {
			//Fill in the last 3 missing cards. 
			if (showDown.size() == 5) {
				break;
			}
			if (!showDown.contains(card)) {
				showDown.add(card);
			}
		}
	}
}