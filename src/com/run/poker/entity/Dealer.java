package com.run.poker.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.run.poker.hand.Analyser;
import com.run.poker.hand.CommunityCards;
import com.run.poker.hand.ShowDownCards;

/**
 * A Dealer is associated with a table.
 * <p> A Dealer can be created with the {@link Table#callDealer()}
 * call and then perform card drawing or comparison operations.
 * @author RuN
 *
 */
public class Dealer {

	private Table table;
	
	Dealer() { }

	void setTable(Table table) {
		this.table = table;
	}
	
	//********************
	//*  Initialization  *
	//********************
	
	/**
	 * Puts a new deck on card (consisting of 52 cards) on the table.
	 */
	public void newDeck() {
		table.newDeck();
	}
	
	//**********************
	//*  Play of the hand  *
	//**********************
	
	/**
	 * Deal 2 number of cards for every player in this table.
	 */
	public void deal() {
		Deck deck = table.deck();
		for (int i = 0; i < 2; i++) {
			for (PlayerEntity entity: table.playerList()) {
				entity.acquire(deck.poll());
			}
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
		Deck deck = table.deck();
		for (int i = 0; i < 3; i++) {
			cc.add(deck.poll());
		}
	}
	
	/**
	 * The turn.
	 */
	public void stageTwo() {
		CommunityCards cc = table.communityCards();
		Deck deck = table.deck();
		cc.add(deck.poll());
	}
	
	/**
	 * The river.
	 */
	public void stageThree() {
		CommunityCards cc = table.communityCards();
		Deck deck = table.deck();
		cc.add(deck.poll());
	}
	
	//***********************
	//*  The determination  *
	//***********************
	
	public void sort() {
		for (PlayerEntity entity: table.playerList()) {
			Collections.sort(entity.holdCards(), Collections.reverseOrder());
		}
	}
	
	/**
	 * <p> Joins the player's hold cards with the community cards.
	 * <p> Sorts the cards in each of the player's hands, based on the 
	 * natural ordering of the card object.
	 * <p> i.e. A, K, 10, 3, 3
	 * <p> then analysis the ranking of the current hand for every players. 
	 * The results are then compares and at last sort the players in 
	 * descending order.
	 */
	public void analyse() {
		List<ShowDownCards> showDownCards = new ArrayList<>();
		for (PlayerEntity entity: table.playerList()) {
			
			List<Card> cards = new ArrayList<>();
			cards.addAll(entity.holdCards());
			cards.addAll(table.communityCards());
			
			Collections.sort(cards, Collections.reverseOrder());
			
			Analyser analyser = new Analyser();
			ShowDownCards showdown = analyser.analyse(cards);
			showDownCards.add(showdown);
		}
		Collections.sort(table.playerList(), Collections.reverseOrder());
	}
	
	/**
	 * Clears out the current cards in each player's hands.
	 */
	public void clear() {
		table.communityCards().clear();
		for (PlayerEntity entity: table.playerList()) {
			entity.holdCards().clear();
			entity.showDownCards().clear();
		}
	}
}