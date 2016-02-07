package com.run.poker.hand;

import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import com.run.poker.card.Card;
import com.run.poker.entity.BasePlayerEntity;
import com.run.poker.hand.Hand.Rank;

/**
 * Computes the ranks of each hands.
 * @author RuN
 *
 */
public class Analyser {
	
	private Hand hand;
	
	public Analyser() {
		
	}
	
	/**
	 * Analysis the rank of the current hand.
	 * @param hand
	 */
	public void analyse(Hand hand) {
		this.hand = hand;
		if (isStraight()) {
			hand.setRank(Rank.Straight);
		} else if (isThreeOfaKind()) {
			hand.setRank(Rank.ThreeOfaKind);
		} else if (isTwoPair()) {
			hand.setRank(Rank.TwoPair);
		} else if (isOnePair()) {
			hand.setRank(Rank.OnePair);
		} else {
			hand.setRank(Rank.HighCard);
		}
	}
	
	/**
	 * Analysis the rank orders of all the passed in players.
	 * @param players
	 */
	public void analyse(List<BasePlayerEntity> players) {
		
	}
	
	private boolean isStraight() {
		Collections.sort(hand);
		Card previous = null;
		for (Card card: hand) {
			if (card != null) {
				if (card.getValue() <= previous.getValue()) {
					return false;
				}
			}
			previous = card;
		}
		return true;
	}
	
	private boolean isThreeOfaKind() {
		boolean threeOfaKind = false;
		for (Card card: hand) {
			int frequency = Collections.frequency(hand, card);
			if (frequency >= 3) {
				threeOfaKind = true;
				break;
			}
		}
		return threeOfaKind;
	}
	
	private boolean isTwoPair() {
		TreeSet<Card> set = new TreeSet<>(hand);
		return set.size() == 3;
	}
	
	private boolean isOnePair() {
		TreeSet<Card> set = new TreeSet<>(hand);
		return set.size() == 4;
	}
}