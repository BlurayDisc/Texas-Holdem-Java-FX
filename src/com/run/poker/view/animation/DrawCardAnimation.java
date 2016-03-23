package com.run.poker.view.animation;

import com.run.poker.entity.card.Deck;
import com.run.poker.entity.player.PlayerEntity;
import com.run.poker.entity.table.Table;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class DrawCardAnimation {
	
	private Table table;
	private Deck deck;
	
	public DrawCardAnimation(Table table) {
		this.table = table;
		this.deck = table.deck();
	}
	
	public void play() {
		SequentialTransition sequence = new SequentialTransition();
		sequence.setCycleCount(1);
		sequence.setAutoReverse(false);

		int index = 0;
		for (int rounds = 0; rounds < 2; rounds++) {
			for (PlayerEntity entity: table.playerList()) {
				Node card = deck.getChildren().get(index);
				sequence.getChildren().add(move(entity, card));
				index++;
			}
		}
		sequence.play();
	}
	
	public Animation move(PlayerEntity entity, Node node) {
		TranslateTransition tt = new TranslateTransition(Duration.millis(300), node);
     	tt.setByX(entity.getLayoutX() - node.getParent().getLayoutX());
     	tt.setByY(entity.getLayoutY() - node.getParent().getLayoutY());
     	tt.setOnFinished(e -> entity.holdCards().add(deck.pop()));
     	//System.out.println(entity.getName() + " (x " + tt.getByX() + ", y " + tt.getByY() + ")");
     	return tt;
	}
    
    public Timeline sort(PlayerEntity entity) {
		Timeline timeline = new Timeline();
		KeyFrame frame = new KeyFrame(
				Duration.millis(500), 
				e -> entity.holdCards().reverseSort());
        timeline.getKeyFrames().add(frame);
        return timeline;
    }
}