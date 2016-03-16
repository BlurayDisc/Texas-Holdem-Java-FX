package com.run.poker.ai.condition;

import java.util.List;

import com.run.poker.card.Card;
import com.run.poker.card.Rank;
import com.run.poker.card.Suit;

/**
 * Iterates through the list of cards in the current hand.
 * <p> This algorithm is O(n) where each of the cards will 
 * be checked and compared.
 * <p> After each iteration it sets the previous node the 
 * current node.
 * @return
 */
public class Flush extends Condition {
	
	private Suit suit;
	
	public Flush() {
		this.rank = Rank.Flush;
	}

	@Override
	public boolean check(List<Card> cards) {
		int counter[] = new int[4];
		for (Card card: cards) {
			Suit suit = card.getSuit();
			counter[suit.ordinal()]++;
		}
		boolean flush = false;
		for (int i = 0; i < counter.length; i ++) {
			if (counter[i] >= FLUSH_CONDITION) {
				suit = Suit.values()[i];
				flush = true;
				break;
			}
		}
		return flush;
	}
	
	@Override
	public void finalise(List<Card> cards) {
		int counter = 0;
		for (Card card: cards) {
			if (card.getSuit() == suit) {
				tempList.add(card);
				counter++;
				if (counter >= 5) {
					break;
				}
			}
		}
	}
}