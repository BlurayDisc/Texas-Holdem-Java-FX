package com.run.poker.hand;

import java.util.List;

import com.run.poker.entity.Card;
import com.run.poker.hand.condition.Condition;

/**
 * <p> Computes the ranks of each hands.
 * <p> List of poker hands rankings
 * <pre>
 * 1 Straight flush
 * 2 Four of a kind
 * 3 Full house
 * 4 Flush
 * 5 Straight
 * 6 Three of a kind
 * 7 Two pair
 * 8 One pair
 * 9 High card
 *	</pre>
 *
 * @author RuN
 * @see https://en.wikipedia.org/wiki/List_of_poker_hands
 */
public class Analyser {
	
	public Analyser() {
		
	}
	
	/**
	 * Analysis the rank of the current hand.
	 * @param hand
	 */
	public ShowDownCards analyse(List<Card> hand) {
		for (Condition condition: Condition.all()) {
			if (condition.check(hand)) {
				condition.finalise(hand);
				return condition.result();
			}
		}
		throw new IllegalStateException();
	}
}