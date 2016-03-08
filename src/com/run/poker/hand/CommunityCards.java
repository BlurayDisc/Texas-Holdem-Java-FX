package com.run.poker.hand;

import java.util.ArrayList;
import java.util.List;

import com.run.poker.entity.Card;
import com.run.poker.entity.GameEntity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Consists of 5 cards being dealt facing up.
 * 
 * @author RuN
 *
 */
public class CommunityCards extends GameEntity {

	public static final double SCALE_FACTOR = 0.75;
	private List<Card> communityCards;
	
	public CommunityCards() {
		this.communityCards = new ArrayList<>();
	}

	/**
	 * {@inheritDoc}
	 * @param card
	 * @return
	 */
	public void add(Card card) {
		move(card);
		communityCards.add(card);
	}

	public void clear() {
		communityCards.clear();
	}
	
	public List<Card> list() {
		return communityCards;
	}
	
	public void move(Card card) {
		double n = communityCards.size();
		double width = Card.FRONT.getWidth() + Card.GAP;
		double x = this.x + SCALE_FACTOR * n * width;
		double y = this.y;
		card.move(x, y);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.setFont(new Font(15));
		gc.fillText("Community Cards", x - 125, y + 50);
		for (Card card: communityCards) {
			card.draw(gc, Card.FRONT, SCALE_FACTOR);
		}
	}
}