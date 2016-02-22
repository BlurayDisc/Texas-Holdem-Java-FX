package com.run.poker.hand.condition;

import java.util.Collections;
import java.util.List;

import com.run.poker.entity.Card;
import com.run.poker.hand.Rank;

public class OnePair extends Condition {
	
	private int value;
	
	public OnePair() {
		this.rank = Rank.OnePair;
	}

	@Override
	public boolean check(List<Card> cards) {
		boolean onePair = false;
		for (Card card: cards) {
			int frequency = Collections.frequency(cards, card);
			if (frequency == 2) {
				value = card.getValue();
				onePair = true;
				break;
			}
		}
		return onePair;
	}

	@Override
	public void finalise(List<Card> cards) {
		for (Card card: cards) {
			if (card.getValue() == value) {
				cards.remove(card);
				showDown.add(card);
			}
		}
		for (int i = 0; i < 3; i++) {
			showDown.add(cards.get(i));
		}
	}
}