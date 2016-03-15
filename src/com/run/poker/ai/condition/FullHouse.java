package com.run.poker.ai.condition;

import java.util.Collections;
import java.util.List;

import com.run.poker.card.Card;
import com.run.poker.card.Rank;

public class FullHouse extends Condition {
	
	public FullHouse() {
		this.rank = Rank.FullHouse;
	}

	@Override
	public boolean check(List<Card> cards) {
		boolean three = false;
		boolean pair = false;
		for (Card card: cards) {
			int frequency = Collections.frequency(cards, card);
			if (frequency == THREE_OF_A_KIND_CONDITION) {
				showDown.add(card);
				three = true;
			} else if (frequency == TWO_OF_A_KIND_CONDITION) {
				showDown.add(card);
				pair = true;
			}
		}
		return three && pair;
	}

	@Override
	public void finalise(List<Card> cards) {
		//Checks if the show down cards has 2 pairs.
		if (showDown.size() > 5) {
			//Sorts this collection from smallest -> largest.
			showDown.sort();
			Card first = showDown.getFirst();
			int frequency = showDown.frequency(first);
			if (frequency == TWO_OF_A_KIND_CONDITION) {
				//remove first 2.
				showDown.removeFirst();
				showDown.removeFirst();
			} else {
				//remove 4th and 5th cards.
				showDown.remove(3);
				showDown.remove(4);
			}
		}
	}
}