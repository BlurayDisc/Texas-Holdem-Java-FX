package com.run.poker.view.animation;

import com.run.poker.entity.card.CardList;
import com.run.poker.entity.card.Deck;
import com.run.poker.entity.table.Table;

import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class DealCardAnimation {
	
	private Deck deck;
	private CardList list;
	
	public DealCardAnimation(Table table) {
		this.deck = table.deck();
		this.list = table.communityCards();
	}
	
	public void play(int rounds) {
		SequentialTransition sequence = new SequentialTransition();
		sequence.setCycleCount(1);
		sequence.setAutoReverse(false);

		int index = 0;
		for (int i = 0; i < rounds; i++) {
			Node card = deck.getChildren().get(index);
			sequence.getChildren().add(move(card));
			index++;
		}
		sequence.play();
	}
	
	public Animation move(Node node) {
		TranslateTransition tt = new TranslateTransition(Duration.millis(300), node);
     	tt.setByX(list.getLayoutX() - node.getParent().getLayoutX());
     	tt.setByY(list.getLayoutY() - node.getParent().getLayoutY());
     	tt.setOnFinished(e -> list.add(deck.pop()));
     	//System.out.println(entity.getName() + " (x " + tt.getByX() + ", y " + tt.getByY() + ")");
     	return tt;
	}
}
