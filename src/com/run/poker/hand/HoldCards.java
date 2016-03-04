package com.run.poker.hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.run.poker.entity.Card;
import com.run.poker.entity.GameEntity;
import com.run.poker.entity.player.Player;
import com.run.poker.entity.player.PlayerEntity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Consists of 2 cards being dealt facing down.
 * <p> The best five card poker hand is obtained by using the necessary 
 * cards from the community and/or a particular player's hold cards.
 * 
 * @author RuN
 */
public class HoldCards extends GameEntity {

	public static final double PLAYER_SCALE_FACTOR = 1.0;
	public static final double ENEMY_SCALE_FACTOR = 0.75;
	
	private Image image;
	private double scale;
	private PlayerEntity owner;
	private List<Card> holdCards;
	
	/**
	 * Testing code, draws enemy hold cards.
	 */
	private boolean test = true;
	
	public HoldCards() {
		this.scale = 0.0;
		this.holdCards = new ArrayList<>();
		this.owner = null;
		this.image = null;
	}
	
	public void setOwner(PlayerEntity owner) {
		this.owner = owner;
		this.scale = owner instanceof Player ? 
					 PLAYER_SCALE_FACTOR : 
					 ENEMY_SCALE_FACTOR;
		this.image = owner instanceof Player ? 
					Card.FRONT : test ? 
					Card.FRONT : 
					Card.BACK;
	}
	
	public void add(Card card) {
		move(card);
		holdCards.add(card);
	}

	public void clear() {
		holdCards.clear();
	}
	
	public List<Card> list() {
		return holdCards;
	}
	
	public void sort() {
		Collections.sort(holdCards, Collections.reverseOrder());
		//Move the cards based on their new indexes.
		for (int i = 0; i < holdCards.size(); i++) {
			moveOnIndex(i);
		}
	}
	
	public void moveOnIndex(int n) {
		double width = Card.FRONT.getWidth() + Card.GAP;
		double x = this.owner.x + scale * n * width; 
		double y = this.owner.y;
		holdCards.get(n).move(x, y);
	}
	
	public void move(Card card) {
		double n = holdCards.size();
		double width = Card.FRONT.getWidth() + Card.GAP;
		double x = this.owner.x + scale * n * width; 
		double y = this.owner.y;
		card.move(x, y);
	}
	
	/**
	 * Displays all the cards in a player's hands.
	 */
	@Override
	public void draw(GraphicsContext gc) {
		//Displays all the cards in a player's hands.
		for (Card card: holdCards) {
			card.draw(gc, image, scale);
		}
	}
}