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
				tempList.add(card);
			}
		}
		return tempList.size() == THREE_OF_A_KIND_CONDITION;
	}

	@Override
	public void finalise(List<Card> cards) {
		for (Card card: cards) {
			//Fill in the last 2 missing cards.
			if (tempList.size() == 5) {
				break;
			} 
			if (!tempList.contains(card)) {
				tempList.add(card);
			}
		}
	}
}