package com.run.poker.entity.player;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The Player model.
 * @author RuN
 *
 */
public class Player extends PlayerEntity {
	
	public Player(String name) {
		this.name.set(name);
		this.title.set("Beginner");
		this.money.set(1000);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		
		//Set Fill
		gc.setFill(Color.WHITE);
		
		//Draw Name
        gc.setFont(new Font(25));
        gc.fillText(name.get(), x, y - 25);
        
		//Draw Money
        gc.setFont(new Font(18));
        gc.fillText("$" + money.get(), x + 125, y - 25);
        
        //Draw Title
        gc.setFont(new Font(18));
        gc.fillText("The " + title.get(), x, y - 5);

		//Draw Betting
        
		//Draw Cards
		this.holdCards.draw(gc);
	}
}