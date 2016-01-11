package com.run.poker.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.run.poker.utils.CardUtils;

import javafx.scene.canvas.GraphicsContext;

/**
 * 
 * @author RuN
 *
 */
public class Enemy extends PlayerEntity {

	private static final List<String> NAMES = new ArrayList<>(
		Arrays.asList(
				"Chris",
				"Michael", 
				"Brendan", 
				"Yong", 
				"Kevin", 
				"Callum", 
				"Run",
				"Kosta",
				"Adam",
				"Xandar",
				"Gavin",
				"Luke",
				"Davi",
				""
		)
	);
	
	public Enemy() {
		this.setName(CardUtils.randomAny(NAMES, true));
		this.setTitle(CardUtils.randomAny(TITLES, false));
		this.setGold(CardUtils.randomNumber(500, 5000));
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

}
