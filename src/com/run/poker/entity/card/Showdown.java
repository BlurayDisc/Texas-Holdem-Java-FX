package com.run.poker.entity.card;

import java.util.ArrayList;

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
public class Showdown extends ArrayList<Card> implements Comparable<Showdown> {
	
	private static final long serialVersionUID = 5401423260967132347L;
	
	/**
	 * Initial rank.
	 */
	private Rank rank = Rank.HighCard;
	
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	@Override
	public int compareTo(Showdown that) {
		int result = this.rank.order - that.rank.order;
		if (result == 0) {
			switch (rank) {
			case Flush:
				result = this.get(0).suit.rank - 
						that.get(0).suit.rank;
				break;
			case StraightFlush: case FourOfaKind: case FullHouse: 
			case Straight: case ThreeOfaKind: case TwoPair: 
			case OnePair: case HighCard: 
				for (int i = 0; i < size(); i++) {
					result = this.get(i).compareTo(that.get(i));
					if (result != 0) {
						break;
					}
				}
				break;
			}
		}
		return result > 0 ? 1 : -1;
	}

	@Override
	public String toString() {
		return super.toString() + " Rank: " + rank;
	}
}