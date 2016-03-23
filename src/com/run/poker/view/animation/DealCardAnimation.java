package com.run.poker.view.animation;

import java.util.List;

import com.run.poker.entity.card.Card;
import com.run.poker.entity.card.CardList;
import com.run.poker.entity.table.Table;

import javafx.animation.Animation;
import javafx.util.Duration;

public class DealCardAnimation extends BaseAnimation {
	
	private int turns;
	
	public DealCardAnimation(Table table) {
		super(table);
	}
	
	@Override
	public void createSequence(List<Animation> animations) {
		CardList cards = table.communityCards();
		for (int i = 0; i < turns; i++) {
			Card card = deck.get(i);
			Animation animation = card.move(
					cards, 
					Duration.millis(300), 
					e -> cards.add(deck.pop()));
			animations.add(animation);
		}
	}
	
	public DealCardAnimation setTurns(int turns) {
		this.turns = turns;
		return this;
	}
}