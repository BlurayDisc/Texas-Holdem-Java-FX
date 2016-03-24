package com.run.poker.entity.chips;

import com.run.poker.utils.FileUtils;
import com.run.poker.utils.GameUtils;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Chips extends ImageView {
	
	public static final Image IMAGE = FileUtils.loadFromJar("images/chips.png");
	public static final double FIT_SCALE =  1.0 / 3.0;
	public static final double H_SCALE = 1.0 / 3.0;
	public static final double V_SCALE = 1.0 / 2.0;
	public static final double[][] COORDS = new double[6][2];
	
	static {
		initCoords();
	}
	
	public Chips() {
		super(IMAGE);
		setPreserveRatio(true);
		setSmooth(true);
		setFitWidth(IMAGE.getWidth() * FIT_SCALE * H_SCALE);
		setFitHeight(IMAGE.getHeight() * FIT_SCALE * V_SCALE);
		selectImage();
	}
	
	/**
	 * This algorithm produces a 2D array specifying the 
	 * vertices of a rectangle 2D shape.
	 * {0, 0} 
	 * {1, 0} 
	 * {2, 0} 
	 * {0, 1} 
	 * {1, 1} 
	 * {2, 1}
	 */
	public static void initCoords() {
		int index = 0;
		for (int i = 0; i < COORDS[0].length; i++) {
			for (int j = 0; j < COORDS.length / 2; j++) {
				COORDS[index][0] = j;
				COORDS[index][1] = i;
				index++;
			}
		}
	}
	
	/**
	 * Randomly selects an image by using the randomly generated number 
	 * multiply by the horizontal and vertical scales and the coords.
	 */
	public void selectImage() {
		int i = GameUtils.random(0, 5);
		Rectangle2D viewPort = new Rectangle2D(
				IMAGE.getWidth() * H_SCALE * COORDS[i][0], 
				IMAGE.getHeight() * V_SCALE * COORDS[i][1], 
				IMAGE.getWidth() * H_SCALE, 
				IMAGE.getHeight() * V_SCALE);
		setViewport(viewPort);
		
		System.out.println(IMAGE.getWidth() + ", " + IMAGE.getHeight());
		System.out.println(getFitWidth() + ", " + getFitHeight());
		System.out.println(viewPort);
	}
	
	@Override
	public String toString() {
		String str = "";
		for (double[] array: COORDS) {
			for (double i: array) {
				str += " " + i;
			}
			str += "\n";
		}
		return str;
	}
}