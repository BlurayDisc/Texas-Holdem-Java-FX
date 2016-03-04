package com.run.poker.hand;

import java.util.Collections;
import java.util.LinkedList;

import com.run.poker.entity.Card;

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
public class ShowDownCards extends LinkedList<Card> implements Comparable<ShowDownCards> {

	private static final long serialVersionUID = -270924839715825415L;
	
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
	
	public void sort() {
		Collections.sort(this, Collections.reverseOrder());
	}
	
	@Override
	public int compareTo(ShowDownCards that) {
		int result = this.rank.order - that.rank.order;
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
				if (this.size() <= 0 || that.size() <= 0) {
					break;
				}
				for (int i = 0; i < this.size(); i++) {
					int value = this.get(i).compareTo(that.get(i));
					if (value == 0) continue;
					return value;
				}
				break;
			}
		}
		return result > 0 ? 1 : -1;
	}
}