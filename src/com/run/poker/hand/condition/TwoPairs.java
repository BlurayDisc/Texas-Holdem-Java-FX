package com.run.poker.hand.condition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.run.poker.entity.Card;
import com.run.poker.hand.Rank;

public class TwoPairs extends Condition {
	
	private List<Integer> values = new ArrayList<>();

	public TwoPairs() {
		this.rank = Rank.TwoPair;
	}

	@Override
	public boolean check(List<Card> cards) {
		boolean twoPairs = false;
		for (Card card: cards) {
			int frequency = Collections.frequency(cards, card);
			if (frequency == 2) {
				values.add(card.getValue());
				twoPairs = true;
				break;
			}
		}
		return twoPairs;
	}

	@Override
	public void finalise(List<Card> cards) {
		Collections.sort(values, Collections.reverseOrder());
		for (int i = 0; i < 2; i++) {
			for (Card card: cards) {
				if (card.getValue() == values.get(i)) {
					cards.remove(card);
					showDown.add(card);
				}
			}
		}
		showDown.add(cards.get(0));
	}
}
