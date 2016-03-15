package com.run.poker.utils;

import java.util.ArrayList;
import java.util.List;

import com.run.poker.card.Card;
import com.run.poker.card.Suit;
import com.run.poker.entity.FixedEntityGroup;
import com.run.poker.entity.GameEntity;
import com.run.poker.entity.player.Names;
import com.run.poker.entity.player.Title;

import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableValue;

/**
 * Utility class that manages common functions to the Card Entity, 
 * Math and Random.
 * 
 * @author RuN
 *
 */
public class GameUtils {
	
    /**
     * Circularly increment i.
     */
    public static int inc(int i, int max) {
        return (i == max) ? 0 : i + 1;
    }
	
	public static String randomName() {
		return random(Names.list(), true).toString();
	}
	
	public static String randomTitle() {
		return random(Title.class).toString();
	}

	/**
	 * Random and return any item from the list.
	 * @param list The list of values to be used as base.
	 * @param <T> Type of the list for auto-unboxing.
	 * @param remove Whether to remove a value on each retrieve.
	 * @return
	 */
	public static <T> T random(List<T> list, boolean remove){
		int index = (int) Math.round(Math.random() * (list.size() - 1));
		return remove ? list.remove(index) : list.get(index);
	}
	
	/**
	 * Generates a random card with its suits and value mixed.
	 * @return a card with random values.
	 */
	public static Card randomCard() {
		return new Card(random(Suit.class), random(1, 13));
	}
	
	/**
	 * Random a number ranges between the lower <-> upper range.
	 * @param lowerRange
	 * @param upperRange
	 * @return
	 */
	public static int random(int lowerRange, int upperRange) {
		return lowerRange + (int) Math.round(Math.random() * (upperRange - lowerRange));
	}
	
	/**
	 * Pulls a random value from the passed in enum class.
	 * @param enumeration
	 * @return
	 */
	public static <T extends Enum<T>> T random(Class<T> enumeration) {
		T[] constants = enumeration.getEnumConstants();
		return constants[(int) Math.round(Math.random() * (constants.length - 1))];
	}

	@SafeVarargs
	public static <E extends GameEntity> List<E> join(FixedEntityGroup<E>... groups) {
		List<E> result = new ArrayList<>();
		for (FixedEntityGroup<E> group: groups) {
			result.addAll(group.list());
		}
		return result;
		
	}
	
	public static StringBinding createBinding(ObservableValue<?> money, String suffix) {
		return new StringBinding() {
	        {
	            bind(money);
	        }
			@Override
			protected String computeValue() {
				return suffix + money.getValue();
			}
		};
	}
}