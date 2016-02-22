package com.run.poker.hand.condition;

import java.util.List;

import com.run.poker.entity.Card;

public class StraightFlush extends Condition {

	@Override
	public boolean check(List<Card> cards) {
		boolean sf = true;
		Card previous = null;
		for (Card current: cards) {
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

	@Override
	public void finalise(List<Card> cards) {
		// TODO Auto-generated method stub
		
	}

}
