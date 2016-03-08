package com.run.poker.hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.run.poker.entity.Card;
import com.run.poker.entity.GameEntity;

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
	private List<Card> holdCards;
	
	public HoldCards() {
		this.holdCards = new ArrayList<>();
		this.image = null;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public void clear() {
		holdCards.clear();
	}
	
	public List<Card> list() {
		return holdCards;
	}
	
	public void add(Card card) {
		moveCard(card, holdCards.size());
		holdCards.add(card);
	}

	/**
	 * Sorts and moves the cards based on their new indexes.
	 */
	public void sort() {
		Collections.sort(holdCards, Collections.reverseOrder());
		int n = 0;
		for (Card card: holdCards) {
			moveCard(card, n);
			n++;
		}
	}
	
	/**
	 * 
	 * @param card
	 * @param n
	 */
	public void moveCard(Card card, int n) {
		double width = Card.FRONT.getWidth() + Card.GAP;
		double x = this.x + scale * n * width;
		double y = this.y;
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