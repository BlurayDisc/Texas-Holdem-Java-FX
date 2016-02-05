package com.run.poker.player;

import com.run.poker.entity.BasePlayerEntity;
import com.run.poker.utils.CardUtils;

import javafx.scene.canvas.GraphicsContext;

/**
 * Enemy Bots.
 * @author RuN
 *
 */
public class Enemy extends BasePlayerEntity {
	
	public Enemy() {
		this.name.set(CardUtils.random(Names.asList(), true).name());
		this.title.set(CardUtils.random(Title.class).name());
		this.gold.set(CardUtils.random(500, 5000));
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

}
