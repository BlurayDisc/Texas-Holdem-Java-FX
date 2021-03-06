package com.run.poker.ai.condition;

import java.util.List;

import com.run.poker.entity.card.Card;
import com.run.poker.entity.card.Rank;
import com.run.poker.utils.GameUtils;

public class FullHouse extends Condition {
	
	public FullHouse() {
		this.rank = Rank.FullHouse;
	}

	@Override
	public boolean check(List<Card> cards) {
		boolean three = false;
		boolean pair = false;
		for (Card card: cards) {
			int frequency = GameUtils.frequency(cards, card);
			if (frequency == THREE_OF_A_KIND_CONDITION) {
				tempList.add(card);
				three = true;
			} else if (frequency == TWO_OF_A_KIND_CONDITION) {
				tempList.add(card);
				pair = true;
			}
		}
		return three && pair;
	}

	@Override
	public void finalise(List<Card> cards) {
		//Checks if the show down cards has 2 pairs.
		if (tempList.size() > 5) {
			//Sorts this collection from smallest -> largest.
			tempList.sort(null);
			Card first = tempList.getFirst();
			int frequency = GameUtils.frequency(tempList, first);
			if (frequency == TWO_OF_A_KIND_CONDITION) {
				//remove first 2.
				tempList.removeFirst();
				tempList.removeFirst();
			} else {
				//remove 4th and 5th cards.
				tempList.remove(3);
				tempList.remove(4);
			}
		}
	}
}