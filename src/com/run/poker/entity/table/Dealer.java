package com.run.poker.entity.table;

import com.run.poker.entity.card.Card;
import com.run.poker.entity.card.CardList;
import com.run.poker.entity.card.Deck;
import com.run.poker.entity.player.PlayerEntity;
import com.run.poker.view.animation.DealCardAnimation;
import com.run.poker.view.animation.DrawCardAnimation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * A Dealer is associated with a table.
 * <p> A Dealer can be created with the {@link Table#callDealer()}
 * call and then perform card drawing or comparison operations.
 * @author RuN
 *
 */
public class Dealer {

	private Table table;
	
	Dealer(Table table) { 
		this.table = table;
	}
	
	//********************
	//*  Initialization  *
	//********************
	
	/**
	 * Puts a new deck on card (consisting of 52 cards) on the table.
	 */
	public void newDeck() {
		Deck deck = table.deck();
		deck.clear();
		deck.fill();
		deck.shuffle();
	}
	
	//**********************
	//*  Play of the hand  *
	//**********************
	
	public void clearHands() {
		for (PlayerEntity entity: table.playerList()) {
			entity.clearHands();
		}
	}
	
	/**
	 * <p> Fills each of the player's hand with 1 card.
	 * <p> Deal 1 card for every player in this table.
	 */
	public void deal() {
		new DrawCardAnimation(table).play();
	}
	
	/**
	 * Re-arranges hold cards in each player's hand. 
	 * <p> This is used for visual effects and has no impact 
	 * on game logics.
	 */
	public void sortHoldCards() {
		for (PlayerEntity entity: table.playerList()) {
			CardList holdCards = entity.holdCards();
			holdCards.reverseSort();
		}
	}
	
	//*******************
	//*  The show down  *
	//*******************
	
	public void clearCC() {
		table.communityCards().clear();
	}
	
	/**
	 * Stage One: The flop.
	 */
	public void stageOne() {
		new DealCardAnimation(table).play(3);
	}
	
	/**
	 * The turn.
	 */
	public void stageTwo() {
		drawCC(1);
	}
	
	/**
	 * The river.
	 */
	public void stageThree() {
		drawCC(1);
	}
	
	private void drawCC(int numCards) {
		Timeline timeline = new Timeline();
		timeline.setAutoReverse(false);
		timeline.setCycleCount(numCards);
		KeyFrame frame = new KeyFrame(Duration.millis(300), e -> drawForCC());
        timeline.getKeyFrames().add(frame);
        timeline.play();
	}
	
	private void drawForCC() {
		Deck deck = table.deck();
		Card card = deck.pop();
		table.communityCards().add(card);
		//System.out.println("CC: " + cc.getLayoutX() + ", " + cc.getLayoutY());
	}
	
	//***********************
	//*  The determination  *
	//***********************
}