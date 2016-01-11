package com.run.poker.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.run.poker.hand.Hand;

public abstract class PlayerEntity extends GameEntity {
	
	protected static final List<String> TITLES = new ArrayList<>(
			Arrays.asList("Beginner", "Intermediate", "Professional")
		);
	
	private String name;
	private String title;
	private int gold;
	private List<Card> cards;
	
	public PlayerEntity() {
		this.setName("New Player");
		this.setTitle("Beginner");
		this.setGold(1000);
		this.cards = new ArrayList<>();
	}
	
	public void acquire(Card card) {
		cards.add(card);
	}
	
	public Hand calculateHand(){
		return new Hand(cards);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}
	
	@Override
	public String toString() {
		String str = name + " The " + title + " with $" + gold + ".";
		str +=  " Hands: ";
		for (Card card: cards) {
			str += card + ", ";
		}
		return str;
	}
}