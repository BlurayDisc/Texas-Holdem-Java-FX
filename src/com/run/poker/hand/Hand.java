package com.run.poker.hand;

import java.util.ArrayList;

import com.run.poker.card.Card;

/**
 * <p> The best five card poker hand is obtained by using the necessary 
 * cards from the community and/or a particular player's hold cards. 
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
public class Hand extends ArrayList<Card> implements Comparable<Hand> {
	
	private static final long serialVersionUID = 1284988029743194947L;

	/**
	 * The Ranking of Poker Hands.
	 */
	enum Rank {
		StraightFlush(9),
		FourOfaKind(8),
		FullHouse(7),
		Flush(6),
		Straight(5),
		ThreeOfaKind(4),
		TwoPair(3),
		OnePair(2),
		HighCard(1);

		private int order;
		 
		private Rank(int order) {
			this.order = order;
		}
	}
	
	/**
	 * Initial rank.
	 */
	private Rank rank = Rank.HighCard;
	 
	/**
	 * Acquires one card.
	 * @param card A card.
	 */
	public void acquire(Card card) {
		if (this.size() >= 5) {
			throw new IllegalArgumentException("Max cards per hand reached.");
		}
		this.add(card);
	}
	
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
	public Rank getRank() {
		return rank;
	}

	@Override
	public int compareTo(Hand hand) {
		int result = this.rank.order - hand.rank.order;
		if (result == 0) {
			switch (rank) {
			case StraightFlush:
				break;
			case FourOfaKind:
				break;
			case FullHouse:
				break;
			case Flush:
				break;
			case Straight:
				break;
			case ThreeOfaKind:
				break;
			case TwoPair:
				break;
			case OnePair:
				break;
			case HighCard:
				for (int i = 0; i < 5; i++) {
					int value = this.get(i).compareTo(hand.get(i));
					if (value == 0) continue;
					return value;
				}
				break;
			}
		}
		return result > 0 ? 1 : -1;
	}
}