package com.run.poker.entity;

import com.run.poker.card.Card;
import com.run.poker.hand.CommunityCards;
import com.run.poker.hand.Hand;
import com.run.poker.hand.HoldCards;

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
public abstract class BasePlayerEntity extends GameEntity implements Comparable<BasePlayerEntity> {
	
	/**
	 * final cards in hand.
	 */
	protected Hand hand = new Hand();
	
	/**
	 * Current hold cards.
	 */
	protected HoldCards holdCards = new HoldCards();
	
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
		holdCards.add(card);
	}
	
	/**
	 * Joins the player's hold cards with the community cards on the table.
	 */
	public void join(CommunityCards communityCards) {
		for (Card card: holdCards) {
			hand.acquire(card);
		}
		for (Card card: communityCards) {
			hand.acquire(card.copy());
		}
	}
	
	public Hand hand() {
		return hand;
	}
	
	public HoldCards holdCards() {
		return holdCards;
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
	public int compareTo(BasePlayerEntity that) {
		return this.hand.compareTo(that.hand);
	}
	
	@Override
	public String toString() {
		String str = name + " The " + title + " with $" + gold + ".";
		str +=  " Hands: " + hand.getRank() + " ";
		for (Card card: hand) {
			str += card + ", ";
		}
		return str;
	}
}