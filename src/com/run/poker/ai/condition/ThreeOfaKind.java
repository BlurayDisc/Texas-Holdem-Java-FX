package com.run.poker.ai.condition;

import java.util.Collections;
import java.util.List;

import com.run.poker.card.Card;
import com.run.poker.card.Rank;

public class ThreeOfaKind extends Condition {
	
	public ThreeOfaKind() {
		this.rank = Rank.ThreeOfaKind;
	}

	@Override
	public boolean check(List<Card> cards) {
		for (Card card: cards) {
			int frequency = Collections.frequency(cards, card);
			if (frequency >= THREE_OF_A_KIND_CONDITION) {
				//Continue adding values as 7 cards may form two 3s.
				showDown.add(card);
			}
		}
		return showDown.size() == THREE_OF_A_KIND_CONDITION;
	}

	@Override
	public void finalise(List<Card> cards) {
		for (Card card: cards) {
			//Fill in the last 2 missing cards.
			if (showDown.size() == 5) {
				break;
			} 
			if (!showDown.contains(card)) {
				showDown.add(card);
			}
		}
	}
}