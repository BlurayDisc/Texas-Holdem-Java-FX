package com.run.poker.player;

import com.run.poker.card.Card;
import com.run.poker.entity.BasePlayerEntity;

import javafx.scene.canvas.GraphicsContext;

/**
 * The Player model.
 * @author RuN
 *
 */
public class Player extends BasePlayerEntity {
	
	public Player(String name) {
		this.name.set(name);
		this.title.set("Beginner");
		this.gold.set(1000);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		//Displays all the cards in a player's hands.
		
		//Fully clears the canvas.
		//gc.clearRect(0, 0, 800, 600); 
		
		int i = 0;
		for (Card card: hand) {
			int x = this.x + i * (Card.WIDTH + Card.GAP);
			int y = this.y;
			card.move(x, y);
			card.draw(gc);
			i++;
			//gc.translate(i, i);
		}
	}
}