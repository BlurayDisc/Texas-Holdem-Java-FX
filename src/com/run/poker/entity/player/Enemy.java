package com.run.poker.entity.player;

import com.run.poker.utils.GameUtils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Enemy Bots.
 * @author RuN
 *
 */
public class Enemy extends PlayerEntity {
	
	public Enemy() {
		this.name.set(GameUtils.randomName());
		this.title.set(GameUtils.randomTitle());
		this.money.set(GameUtils.random(500, 5000));
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		
		gc.setFill(Color.WHITE);
		
		//Draw Name
        gc.setFont(new Font(20));
        gc.fillText(name.get(), x, y - 20);
        
		//Draw Money
        gc.setFont(new Font(15));
        gc.fillText("$" + money.get(), x + 100, y - 20);
        
        //Draw Title
        gc.setFont(new Font(15));
        gc.fillText("The " + title.get(), x, y - 5);
        
		//Draw Betting
        
		//Draw Cards
		this.holdCards.draw(gc);
	}
}