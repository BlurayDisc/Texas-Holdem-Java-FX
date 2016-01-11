package com.run.poker.hand;

/**
 * <p> List of poker hands
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
public enum Rank {

	 StraightFlush(9),
	 FourOfaKind(8),
	 FullHouse(7),
	 Flush(6),
	 Straight(5),
	 ThreeOfaKind(4),
	 TwoPair(3),
	 OnePair(2),
	 HighCard(1);
	 
	 private int rank;
	 
	 private Rank(int rank) {
		 this.rank = rank;
	 }
	 
	 public int getRank(){
		 return rank;
	 }
}