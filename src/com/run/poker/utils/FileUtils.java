package com.run.poker.utils;

import javafx.scene.image.Image;

/**
 * Utility class for java fx Image reading.
 * @author RuN
 *
 */
public class FileUtils {
	
	/**
	 * <p> Reads an Image file from the provided jar path.
	 * <p> This method reads files insides the jar file.
	 */
	public static Image loadFromJar(String path) {
		return new Image(ClassLoader.getSystemResourceAsStream(path));
	}
	
	public static Image loadFromJar(String path, double requestedWidth, double requestedHeight) {
		return new Image(ClassLoader.getSystemResourceAsStream(path), requestedWidth, requestedHeight, true, true);
	}
	
	/**
	 * <p> Reads an Image file using an URL.
	 * <p> This method reads files outside of the compiled jar file.
	 * <p> Sample file URL: "file:resources/card.jpg".
	 */
	public static Image loadFromFile(String path) {
		return new Image("file:" + path);
	}
}