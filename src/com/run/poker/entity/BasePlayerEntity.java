package com.run.poker.entity;

import java.util.Collections;

import com.run.poker.card.Card;
import com.run.poker.hand.Analyser;
import com.run.poker.hand.Hand;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * <p> Base entity for player/enemy objects.
 * <p> Players have different properties and their names can be modified 
 * by the user on request. Players are also drawn differently: their 
 * cards will be drawn on the screen and revealed to the user where as 
 * Enemy will have their cards hidden.
 * <p> Enemy are bots which inherits all the properties of a player but 
 * with additional build in AI and functions. 
 * 
 * @author RuN
 *
 */
public abstract class BasePlayerEntity extends GameEntity {
	
	/**
	 * Current cards in hand.
	 */
	protected Hand hand = new Hand();
	
	/**
	 * Player name.
	 */
	protected StringProperty name = new SimpleStringProperty("New Player");
	
	/**
	 * Player title
	 */
	protected StringProperty title = new SimpleStringProperty("Beginner");
	
	/**
	 * Player gold
	 */
	protected IntegerProperty gold = new SimpleIntegerProperty(1000);
	
	public BasePlayerEntity() {
		
	}
	
	/**
	 * Acquires one card.
	 * @param card A card.
	 */
	public void acquire(Card card) {
		hand.acquire(card);
	}
	
	/**
	 * Sorts the cards in this player's hand in reverse order.
	 * <p> i.e. A, K, 10, 3, 3
	 */
	public void sort() {
		hand.sort(Collections.reverseOrder());
	}
	
	/**
	 * Analysis the ranking of the current hand.
	 */
	public void analyse(){
		Analyser analyser = new Analyser();
		analyser.analyse(hand);
	}
	
	/**
	 * Clears and resets and current hand.
	 */
	public void clear() {
		hand.clear();
	}

	public StringProperty getName() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public StringProperty getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public IntegerProperty getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold.set(gold);
	}
	
	@Override
	public String toString() {
		String str = name + " The " + title + " with $" + gold + ".";
		str +=  " Hands: ";
		for (Card card: hand) {
			str += card + ", ";
		}
		return str;
	}
}