package com.run.poker.card;


/**
 * The Ranking of Poker Hands.
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

	int order;
	 
	private Rank(int order) {
		this.order = order;
	}
}
