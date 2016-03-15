package com.run.poker.card;

import com.run.poker.entity.FixedEntityGroup;

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
public class ShowdownCards extends FixedEntityGroup<Card> 
		implements Comparable<ShowdownCards> {
	
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
	public int compareTo(ShowdownCards that) {
		int result = this.rank.order - that.rank.order;
		if (result == 0) {
			switch (rank) {
			case Flush:
				result = this.get(0).getSuit().getRank() - 
						that.get(0).getSuit().getRank();
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