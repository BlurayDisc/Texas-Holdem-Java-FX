package com.run.poker.utils;

import java.util.List;

public class CardUtils {

	/**
	 * Random and return any item from the list.
	 * @param list The list of values to be used as base.
	 * @param <T> Type of the list for auto unboxing.
	 * @param remove Whether to remove a value on each retrieve.
	 * @return
	 */
	public static <T> T randomAny(List<T> list, boolean remove){
		int index = (int) Math.round(Math.random() * (list.size() - 1));
		return remove ? list.remove(index) : list.get(index);
	}
	
	/**
	 * Randoms a number ranges between the lower <-> upper range.
	 * @param lowerRange
	 * @param upperRange
	 * @return
	 */
	public static int randomNumber(int lowerRange, int upperRange) {
		return lowerRange + (int) Math.round(Math.random() * (upperRange - lowerRange));
	}
}
