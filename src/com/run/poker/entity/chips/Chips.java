package com.run.poker.entity.chips;

import com.run.poker.utils.FileUtils;
import com.run.poker.utils.GameUtils;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Chips extends ImageView {
	
	public static final Image IMAGE = FileUtils.loadFromJar("images/chips.png", 353, 350);
	public static final double HEIGHT = IMAGE.getHeight() / 2.0;
	public static final double WIDTH = IMAGE.getWidth() / 3.0;
	public static final double[][] ARRAY_2D ;
	
	static {
		ARRAY_2D = new double[][] {
			{0, 0}, {1, 0}, {2, 0}, 
			{0, 1}, {1, 1}, {2, 1}
		};
	}
	
	public Chips() {
		super(IMAGE);
		random();
	}
	
	public void random() {
		int x = GameUtils.random(0, 5);
		Rectangle2D viewPort= new Rectangle2D(
				WIDTH * ARRAY_2D[x][0], 
				HEIGHT * ARRAY_2D[x][1], 
				WIDTH, 
				HEIGHT);
		setViewport(viewPort);
	}
	
	/**
	 * 	  0, 0
	 *	350, 0
	 *	700, 0
	 *	  0, 350
	 *	350, 350
	 *	700, 350
	 */
	public void algorithm() {
		for (int i = 0; i < ARRAY_2D.length; i++) {
			for (int j = 0; j < ARRAY_2D[i].length; j++) {
				ARRAY_2D[i][j] = WIDTH * i;
			}
		}
	}
	
	public String toString() {
		String str = "";
		for (double[] array: ARRAY_2D) {
			for (double i: array) {
				str += " " + i;
			}
			str += "\n";
		}
		return str;
	}
}