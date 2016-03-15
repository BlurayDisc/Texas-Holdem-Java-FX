package com.run.poker.card;

import com.run.poker.entity.FixedEntityGroup;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Consists of 5 cards being dealt facing up.
 * 
 * @author RuN
 *
 */
public class CommunityCards extends FixedEntityGroup<Card> {

	@Override
	public void draw(GraphicsContext gc) {
		super.draw(gc);
		gc.setFill(Color.WHITE);
		gc.setFont(new Font(15));
		gc.fillText("Community Cards", x - 125, y + 50);
	}
}