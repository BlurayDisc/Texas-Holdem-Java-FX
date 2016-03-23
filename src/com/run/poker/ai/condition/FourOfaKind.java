package com.run.poker.ai.condition;

import java.util.List;

import com.run.poker.entity.card.Card;
import com.run.poker.entity.card.Rank;
import com.run.poker.utils.GameUtils;

public class FourOfaKind extends Condition {
	
	public FourOfaKind() {
		this.rank = Rank.FourOfaKind;
	}

	@Override
	public boolean check(List<Card> cards) {
		boolean fourOfaKind = false;
		for (Card card: cards) {
			int frequency = GameUtils.frequency(cards, card);
			if (frequency == FOUR_OF_A_KIND_CONDITION) {
				tempList.add(card);
				fourOfaKind = true;
			}
		}
		return fourOfaKind;
	}

	@Override
	public void finalise(List<Card> cards) {
		for (Card card: cards) {
			if (tempList.size() == 5) {
				break;
			}
			if (!tempList.contains(card)) {
				tempList.add(card);
			}
		}
	}
}
