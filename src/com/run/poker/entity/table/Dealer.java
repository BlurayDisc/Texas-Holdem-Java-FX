package com.run.poker.entity.table;

import com.run.poker.ai.AIManager;
import com.run.poker.ai.Analyser;
import com.run.poker.entity.card.Deck;
import com.run.poker.entity.player.Enemy;
import com.run.poker.entity.player.PlayerEntity;
import com.run.poker.view.animation.DealCardAnimation;
import com.run.poker.view.animation.DrawCardAnimation;

import javafx.concurrent.Task;

/**
 * A Dealer is associated with a table.
 * <p> A Dealer can be created with the {@link Table#callDealer()}
 * call and then perform card drawing or comparison operations.
 * @author RuN
 *
 */
public class Dealer {

	private Table table;
	private Analyser analyser;
	private AIManager manager;
	
	Dealer(Table table) { 
		this.table = table;
		this.analyser = new Analyser(table);
		this.manager = new AIManager(table);
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
		new DrawCardAnimation(table).playSequence();
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
		new DealCardAnimation(table).setTurns(5).playSequence();
	}
	
	/**
	 * The turn.
	 */
	public void stageTwo() {
		new DealCardAnimation(table).setTurns(1).playSequence();
	}
	
	/**
	 * The river.
	 */
	public void stageThree() {
		new DealCardAnimation(table).setTurns(1).playSequence();
	}
	
	public void clearCC() {
		table.communityCards().clear();
	}
	
	//***********************
	//*  The determination  *
	//***********************
	
	public void betStart() {
		new Thread(new BetTask()).start();
	}
	
	//TODO add index
	class BetTask extends Task<Void> {
		
		@Override
		protected Void call() throws Exception {
			
			Thread.sleep(3000);
			analyser.analyse();
			System.out.println(table);
			
			PlayerEntity previous = null;
			for (PlayerEntity current: table.playerList()) {
				if (previous != null) {
					previous.setActive(false);
				}
				current.setActive(true);
				if (current instanceof Enemy) {
					manager.process((Enemy) current);
				} else {
					table.enablePlayerOptions();
				}
				previous = current;
			}
			return null;
		}
	}
}