package com.run.poker.ai.condition;

import java.util.LinkedList;
import java.util.List;

import com.run.poker.card.Card;
import com.run.poker.card.Rank;

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
	
	/**
	 * Number of consecutive number of cards that satisfies 
	 * the flush and straight conditions.
	 */
	public static final int FLUSH_CONDITION = 5;
	public static final int STRAIGHT_CONDITION = 4;
	public static final int FOUR_OF_A_KIND_CONDITION = 4;
	public static final int THREE_OF_A_KIND_CONDITION = 3;
	public static final int TWO_OF_A_KIND_CONDITION = 2;
	
	protected LinkedList<Card> tempList;

	protected Rank rank;
	
	public Condition() {
		this.rank = Rank.HighCard;
		this.tempList = new LinkedList<>();
	}
	
	public Rank result() {
		return rank;
	}
	
	public List<Card> cards() {
		return tempList;
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