package com.run.poker.entity.table;

import com.run.poker.card.CommunityCards;
import com.run.poker.card.HoldCards;
import com.run.poker.entity.GameEntity;
import com.run.poker.entity.player.PlayerEntity;

import javafx.scene.canvas.GraphicsContext;

/**
 * A Dealer is associated with a table.
 * <p> A Dealer can be created with the {@link Table#callDealer()}
 * call and then perform card drawing or comparison operations.
 * @author RuN
 *
 */
public class Dealer extends GameEntity {

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
		Deck deck = new Deck();
		deck.fill();
		deck.shuffle();
		table.setDeck(deck);
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
		Deck deck = table.deck();
		for (PlayerEntity entity: table.playerList()) {
			entity.acquire(deck.removeFirst());
		}
	}
	
	/**
	 * Re-arranges hold cards in each player's hand. 
	 * <p> This is used for visual effects and has no impact 
	 * on game logics.
	 */
	public void sortHoldCards() {
		for (PlayerEntity entity: table.playerList()) {
			HoldCards holdCards = entity.holdCards();
			holdCards.reverseSort();
		}
	}
	
	//*******************
	//*  The show down  *
	//*******************
	
	public void clearCommunityCards() {
		CommunityCards cc = table.communityCards();
		cc.clear();
	}
	
	/**
	 * Draws one card to the community card stack.
	 */
	public void draw() {
		CommunityCards cc = table.communityCards();
		Deck deck = table.deck();
		cc.add(deck.removeFirst());
	}
	
	/**
	 * The turn.
	 */
	public void stageTwo() {
		CommunityCards cc = table.communityCards();
		Deck deck = table.deck();
		cc.add(deck.removeFirst());
	}
	
	/**
	 * The river.
	 */
	public void stageThree() {
		CommunityCards cc = table.communityCards();
		Deck deck = table.deck();
		cc.add(deck.removeFirst());
	}
	
	//***********************
	//*  The determination  *
	//***********************

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
}