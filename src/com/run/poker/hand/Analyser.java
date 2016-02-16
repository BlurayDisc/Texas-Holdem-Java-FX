package com.run.poker.hand;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.run.poker.card.Card;
import com.run.poker.entity.BasePlayerEntity;
import com.run.poker.hand.Hand.Rank;

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
	
	private Hand hand;
	
	public Analyser() {
		
	}
	
	/**
	 * Analysis the rank of the current hand.
	 * @param hand
	 */
	public void analyse(Hand hand) {
		this.hand = hand;
		if (isStraightFlush()) {
			hand.setRank(Rank.StraightFlush);
		} else if (isFourOfaKind()) {
			hand.setRank(Rank.FourOfaKind);
		} else if (isFlush()) {
			hand.setRank(Rank.Flush);
		} else if (isStraight()) {
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
	
	private boolean isStraightFlush() {
		boolean sf = true;
		Card previous = null;
		for (Card current: hand) {
			if (previous != null) {
				if (current.getValue() <= previous.getValue() || 
					current.getSuit() != previous.getSuit()) {
					sf = false;
					break;
				}
			}
			previous = current;
		}
		return sf;
	}
	
	private boolean isFourOfaKind() {
		boolean fourOfaKind = false;
		for (Card card: hand) {
			int frequency = Collections.frequency(hand, card);
			if (frequency == 4) {
				fourOfaKind = true;
				break;
			}
		}
		return fourOfaKind;
	}
	
	private boolean isFlush() {
		boolean flush = true;
		Card previous = null;
		for (Card current: hand) {
			if (previous != null) {
				if (current.getSuit() != previous.getSuit()) {
					flush = false;
					break;
				}
			}
			previous = current;
		}
		return flush;
	}
	
	private boolean isStraight() {
		boolean straight = true;
		Card previous = null;
		for (Card current: hand) {
			if (previous != null) {
				if (current.getValue() <= previous.getValue()) {
					straight = false;
					break;
				}
			}
			previous = current;
		}
		return straight;
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
		Set<Card> set = new HashSet<>(hand);
		return set.size() == 3;
	}
	
	private boolean isOnePair() {
		Set<Card> set = new HashSet<>(hand);
		return set.size() == 4;
	}
}