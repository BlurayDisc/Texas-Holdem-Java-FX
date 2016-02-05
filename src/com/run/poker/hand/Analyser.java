package com.run.poker.hand;

import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import com.run.poker.card.Card;

/**
 * Computes the ranks of each hands.
 * @author RuN
 *
 */
public class Analyser {
	
	private List<Card> cards;
	
	public Analyser(List<Card> cards) {
		this.cards = cards;
	}
	
	public Hand analyse() {
		Hand hand;
		if (isStraight()) {
			hand = Hand.Straight;
		} else if (isThreeOfaKind()) {
			hand = Hand.ThreeOfaKind;
		} else if (isTwoPair()) {
			hand = Hand.TwoPair;
		} else if (isOnePair()) {
			hand = Hand.OnePair;
		} else {
			hand = Hand.HighCard;
		}
		return hand;
	}
	
	private boolean isStraight() {
		Collections.sort(cards);
		boolean straight = false;
		Card previous = null;
		for (Card card: cards) {
			if (previous == null) {
				previous = card;
				continue;
			}
			if (card.getValue() > previous.getValue())
			previous = card;
		}
		return straight;
	}
	
	private boolean isThreeOfaKind() {
		boolean threeOfaKind = false;
		for (Card card: cards) {
			int frequency = Collections.frequency(cards, card);
			if (frequency >= 3) {
				threeOfaKind = true;
				break;
			}
		}
		return threeOfaKind;
	}
	
	private boolean isTwoPair() {
		TreeSet<Card> set = new TreeSet<>(cards);
		return cards.size() == set.size() + 1;
	}
	
	private boolean isOnePair() {
		TreeSet<Card> set = new TreeSet<>(cards);
		return cards.size() == set.size() + 1;
	}
}