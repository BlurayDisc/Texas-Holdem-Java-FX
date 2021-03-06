package com.run.poker.view.animation;

import java.util.ArrayList;
import java.util.List;

import com.run.poker.entity.card.CardList;
import com.run.poker.entity.card.Deck;
import com.run.poker.entity.player.PlayerEntity;
import com.run.poker.entity.table.Table;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Base Animation class.
 * @author RuN
 *
 */
public abstract class BaseAnimation {
	
	protected Table table;
	protected Deck deck;
	
	public BaseAnimation(Table table) {
		this.table = table;
		this.deck = table.deck();
	}
	
	/**
	 * Plays a sequence animation.
	 * Implementations must override the {@link BaseAnimation#createSequence} method.
	 */
	public void playSequence() {
		SequentialTransition sequence = new SequentialTransition();
		sequence.setCycleCount(1);
		sequence.setAutoReverse(false);
		createSequence(animations);
		sequence.getChildren().addAll(animations);
		sequence.play();
	}
	
	private List<Animation> animations = new ArrayList<>();
	
	/**
	 * Override this method to return a list of animations to 
	 * be played in a sequence.
	 * @return
	 */
	public void createSequence(List<Animation> animations) { }

	/**
	 * Creates a transition animation.
	 * @param node The node to be be performed the translation with.
	 * @param target The targeting point wish to be moved.
	 * @param duration desired time for this frame.
	 * @param e The on finish event handler.
	 * @return A Translate Animation.
	 */
	public static Animation move(Node node, Node target, Duration duration, EventHandler<ActionEvent> e) {
		//Computes the parent node.
		while (!(target instanceof CardList) && 
			   !(target instanceof PlayerEntity)) {
			target = target.getParent();
		}
		Node current = node;
		while (!(current instanceof Deck) && 
			   !(current instanceof PlayerEntity)) {
			current = current.getParent();
		}
		//Create Translation
		TranslateTransition tt = new TranslateTransition(duration, node);
		tt.setByX(target.getLayoutX() - current.getLayoutX());
		tt.setByY(target.getLayoutY() - current.getLayoutY());
     	tt.setOnFinished(e);
     	return tt;
	}
	
	/**
	 * Creates a delayed animation which updates the View from one of more changes in the 
	 * Model specified in the EventHandler class.
	 * @param duration desired time for this frame.
	 * @param e The on finish event handler.
	 * @return A Delayed Update Animation
	 */
    public static Animation delay(Duration duration, EventHandler<ActionEvent> e) {
		Timeline timeline = new Timeline();
		KeyFrame frame = new KeyFrame(duration, e);
        timeline.getKeyFrames().add(frame);
        return timeline;
    }
}
