package com.run.poker.entity;

import com.run.poker.card.CommunityCards;
import com.run.poker.card.HoldCards;
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
	
	/**
	 * <p> Fills the player's hand with 2 random cards.
	 * <p> Deal 2 number of cards for every player in this table.
	 * Re-arranges hold cards in each player's hand. This is used for visual effects 
	 * and has no impact on game logics.
	 */
	public void deal() {
		Deck deck = table.deck();
		for (PlayerEntity entity: table.playerList()) {
			entity.clearHands();
		}
		for (int i = 0; i < 2; i++) {
			for (PlayerEntity entity: table.playerList()) {
				entity.acquire(deck.removeFirst());
			}
		}
		for (PlayerEntity entity: table.playerList()) {
			HoldCards holdCards = entity.holdCards();
			holdCards.reverseSort();
		}
	}
	
	//*******************
	//*  The show down  *
	//*******************
	
	/**
	 * The flop.
	 */
	public void stageOne() {
		CommunityCards cc = table.communityCards();
		cc.clear();
		Deck deck = table.deck();
		for (int i = 0; i < 3; i++) {
			cc.add(deck.removeFirst());
		}
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