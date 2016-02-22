package com.run.poker.hand.condition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.run.poker.entity.Card;
import com.run.poker.hand.Rank;

public class ThreeOfaKind extends Condition {
	
	private List<Integer> values = new ArrayList<>();
	
	public ThreeOfaKind() {
		this.rank = Rank.ThreeOfaKind;
	}

	@Override
	public boolean check(List<Card> cards) {
		boolean threeOfaKind = false;
		for (Card card: cards) {
			int frequency = Collections.frequency(cards, card);
			if (frequency >= 3) {
				values.add(card.getValue());
				threeOfaKind = true;
				break;
			}
		}
		return threeOfaKind;
	}

	@Override
	public void finalise(List<Card> cards) {
		Collections.sort(values, Collections.reverseOrder());
		int value = values.get(0);
		for (Card card: cards) {
			if (card.getValue() == value) {
				cards.remove(card);
				showDown.add(card);
			}
		}
		showDown.add(cards.get(0));
		showDown.add(cards.get(1));
	}
}