package com.run.poker.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.run.poker.entity.card.Card;
import com.run.poker.entity.card.CardList;
import com.run.poker.entity.card.Suit;
import com.run.poker.entity.player.Names;
import com.run.poker.entity.player.Title;

import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

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
		return random(Names.list, true).toString();
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

	/**
	 * Produces a new list of E objects.
	 * @param lists
	 * @return
	 */
	@SafeVarargs
	public static List<Card> join(CardList... lists) {
		List<Card> result = new ArrayList<>();
		for (CardList list: lists) {
			Iterator<Node> it = list.getChildren().iterator();
			while (it.hasNext()) {
				Node node = it.next();
				if (node instanceof Card) {
					result.add((Card) node);
				}
			}
		}
		return result;
		
	}
	
    /**
     * Returns the number of cards in the specified collection equal to the
     * value of the specified card.  More formally, returns the number of elements
     * <tt>e</tt> in the collection such that
     * <tt>(o == null ? e == null : card.getValue() == e.getValue())</tt>.
     *
     * @param c the collection in which to determine the frequency
     *     of <tt>card</tt>
     * @param card the card whose frequency is to be determined
     * @return the number of elements in {@code c} with values equal to the value of 
     * {@code card}
     */
    public static int frequency(Collection<Card> c, Card card) {
        int result = 0;
        for (Card e : c)
            if (card.value == e.value)
                result++;
        return result;
    }
	
	public static StringBinding createBinding(ObservableValue<?> money) {
		return new StringBinding() {
	        {bind(money);}
			@Override
			protected String computeValue() {
				return "$" + money.getValue();
			}
		};
	}
}