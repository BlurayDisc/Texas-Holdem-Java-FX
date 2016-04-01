package com.run.poker.entity.table;

import com.run.poker.entity.card.Deck;
import com.run.poker.entity.player.PlayerEntity;
import com.run.poker.view.animation.DealCard;
import com.run.poker.view.animation.DrawCard;

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
	
	/**
	 * <p> Fills each of the player's hand with 1 card.
	 * <p> Deal 1 card for every player in this table.
	 */
	public void deal() {
		new DrawCard(table).playSequence();
	}
	
	/**
	 * Clears hold cards from players.
	 */
	public void clearHands() {
		for (PlayerEntity entity: table.playerList()) {
			entity.clearHands();
		}
	}
	
	//*******************
	//*  The show down  *
	//*******************
	
	/**
	 * Stage One: The flop.
	 */
	public void stageOne() {
		new DealCard(table).setTurns(3).playSequence();
	}
	
	/**
	 * The turn.
	 */
	public void stageTwo() {
		new DealCard(table).setTurns(1).playSequence();
	}
	
	/**
	 * The river.
	 */
	public void stageThree() {
		new DealCard(table).setTurns(1).playSequence();
	}
	
	public void clearCC() {
		table.communityCards().clear();
	}
	
	//***********************
	//*  The determination  *
	//***********************	
}