package com.run.poker.hand.condition;

import java.util.List;

import com.run.poker.entity.Card;
import com.run.poker.hand.Rank;
import com.run.poker.hand.ShowDownCards;

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
public abstract class Condition {
	
	public static final int FOUR_CONDITION = 4;
	
	/**
	 * Number of consecutive number of cards that satisfies 
	 * the flush and straight conditions.
	 */
	public static final int SUIT_CONDITION = 5;
	
	public static final int CONSECUTIVES_CONDITION = 4;
	
	protected ShowDownCards showDown;

	protected Rank rank;
	
	public Condition() {
		this.rank = Rank.HighCard;
		this.showDown = new ShowDownCards();
	}

	/**
	 * 
	 * @return
	 */
	public static Condition[] all() {
		return new Condition[] {
			   new FourOfaKind(),
			   new FullHouse(),
			   new Flush(),
			   new Straight(), 
			   new ThreeOfaKind(),
			   new TwoPairs(),
			   new OnePair(),
			   new HighCard()
		};
	}
	
	public ShowDownCards result() {
		showDown.setRank(rank);
		return showDown;
	}
	
	
	/**
	 * Compares the newly formed hand with the current condition.
	 * @param cards
	 * @return
	 */
	public abstract boolean check(List<Card> cards);
	
	/**
	 * Retrieves the 5 best combination of cards from the list of 
	 * 7 cards.
	 * @param cards
	 */
	public abstract void finalise(List<Card> cards);
}