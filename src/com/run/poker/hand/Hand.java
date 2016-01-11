package com.run.poker.hand;

import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import com.run.poker.entity.Card;

/**
 * @author RuN
 * @see https://en.wikipedia.org/wiki/List_of_poker_hands
 */
public class Hand {
	
	private Rank rank;
	
	public Hand(List<Card> cards) {
		this.setRank(Rank.HighCard);
		this.anaylse(cards);
	}
	
	public void anaylse(List<Card> cards) {
		
		if (isThreeOfaKind(cards)) {
			rank = Rank.ThreeOfaKind;
		} else if (isTwoPair(cards)) {
			rank = Rank.TwoPair;
		} else if (isOnePair(cards)) {
			rank = Rank.OnePair;
		} else {
			rank = Rank.HighCard;
		}
	}
	
	private static boolean isThreeOfaKind(List<Card> cards) {
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
	
	private static boolean isTwoPair(List<Card> cards) {
		TreeSet<Card> set = new TreeSet<>(cards);
		return cards.size() == set.size() + 1;
	}
	
	private static boolean isOnePair(List<Card> cards) {
		TreeSet<Card> set = new TreeSet<>(cards);
		return cards.size() == set.size() + 1;
	}
	
	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

}
