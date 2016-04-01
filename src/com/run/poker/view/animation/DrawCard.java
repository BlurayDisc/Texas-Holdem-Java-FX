package com.run.poker.view.animation;

import java.util.Iterator;
import java.util.List;

import com.run.poker.entity.card.Card;
import com.run.poker.entity.card.CardList;
import com.run.poker.entity.player.PlayerEntity;
import com.run.poker.entity.table.Table;

import javafx.animation.Animation;
import javafx.scene.Node;
import javafx.util.Duration;

public class DrawCard extends BaseAnimation {
	
	public DrawCard(Table table) {
		super(table);
	}
	
	@Override
	public void createSequence(List<Animation> animations) {
		Iterator<Node> it = deck.getChildren().iterator();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < table.playerList().size(); j++) {
				PlayerEntity entity = table.playerList().get(j);
				CardList cards = entity.holdCards();
				Card card = (Card) it.next();
				Animation animation = card.move(
						entity, 
						Duration.millis(300), 
						e -> cards.add(deck.pop()));
				animations.add(animation);
			}
		}
		//Creates a delayed update on view.
		for (PlayerEntity entity: table.playerList()) {
			animations.add(entity.holdCards().reverseSort());
		}
	}
}