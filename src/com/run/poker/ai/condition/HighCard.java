package com.run.poker.ai.condition;

import java.util.List;

import com.run.poker.entity.card.Card;
import com.run.poker.entity.card.Rank;

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
		for (Card card: cards) {
			if (tempList.size() == 5) {
				break;
			}
			tempList.add(card);
		}
	}
}