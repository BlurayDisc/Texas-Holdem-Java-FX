package com.run.poker.entity;

import java.util.ArrayList;
import java.util.List;

import com.run.poker.card.Card;
import com.run.poker.hand.Analyser;
import com.run.poker.hand.Hand;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class BasePlayerEntity extends GameEntity {
	
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
	
	/**
	 * Current cards in hand.
	 */
	public List<Card> hands = new ArrayList<>();
	
	public BasePlayerEntity() {
		
	}
	
	/**
	 * Acquires one card.
	 * @param card A card.
	 */
	public void acquire(Card card) {
		hands.add(card);
	}
	
	public Hand calculateHand(){
		Analyser analyser = new Analyser(hands);
		return analyser.analyse();
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
		for (Card card: hands) {
			str += card + ", ";
		}
		return str;
	}
}