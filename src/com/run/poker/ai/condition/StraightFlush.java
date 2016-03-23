package com.run.poker.ai.condition;

import java.util.List;

import com.run.poker.entity.card.Card;


public class StraightFlush extends Condition {

	@Override
	public boolean check(List<Card> cards) {
		boolean sf = true;
		Card previous = null;
		for (Card current: cards) {
			if (previous != null) {
				if (current.value <= previous.value || 
					current.suit != previous.suit) {
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
