package com.run.poker.ai.condition;

import java.util.Collections;
import java.util.List;

import com.run.poker.card.Card;
import com.run.poker.card.Rank;

/**
 * Two Pairs Condition.
 * 
 * @author RuN
 *
 * @TODO FIX wrong logics in the frequency algorithm
 * @TODO It currently compares every pair TWICE.
 */
public class TwoPairs extends Condition {
	
	public TwoPairs() {
		this.rank = Rank.TwoPair;
	}

	@Override
	public boolean check(List<Card> cards) {
		for (Card card: cards) {
			int frequency = Collections.frequency(cards, card);
			if (frequency == TWO_OF_A_KIND_CONDITION) {
				//Continue adding values as 7 cards may form 3 pairs.
				showDown.add(card);
			}
		}
		return showDown.size() >= TWO_OF_A_KIND_CONDITION * 2;
	}

	@Override
	public void finalise(List<Card> cards) {
		//Sorts the values list so that the first 2 largest values 
		//are retained for the two pairs condition.
		showDown.reverseSort();
		while (showDown.size() > 4) {
			showDown.removeLast();
		}
		for (Card card: cards) {
			//Fill in the last 1 missing card.
			if (showDown.size() == 5) {
				break;
			} 
			if (!showDown.contains(card)) {
				showDown.add(card);
			}
		}
	}
}