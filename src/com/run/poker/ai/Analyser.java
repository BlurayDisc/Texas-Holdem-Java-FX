package com.run.poker.ai;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.run.poker.ai.condition.Condition;
import com.run.poker.ai.condition.Flush;
import com.run.poker.ai.condition.FourOfaKind;
import com.run.poker.ai.condition.FullHouse;
import com.run.poker.ai.condition.HighCard;
import com.run.poker.ai.condition.OnePair;
import com.run.poker.ai.condition.Straight;
import com.run.poker.ai.condition.ThreeOfaKind;
import com.run.poker.ai.condition.TwoPairs;
import com.run.poker.entity.card.Card;
import com.run.poker.entity.player.PlayerEntity;
import com.run.poker.entity.table.Table;
import com.run.poker.utils.GameUtils;

/**
 * <p> Computes the ranks of each hands.
 * <p> List of poker hands rankings
 * <pre>
 * 1 Straight flush
 * 2 Four of a kind
 * 3 Full house
 * 4 Flush
 * 5 Straight
 * 6 Three of a kind
 * 7 Two pair
 * 8 One pair
 * 9 High card
 *	</pre>
 *
 * @author RuN
 * @see https://en.wikipedia.org/wiki/List_of_poker_hands
 */
public class Analyser {
	
	private Table table;
	
	public Analyser(Table table) {
		this.table = table;
	}
	
	/**
	 * <p> Joins the player's hold cards with the community cards.
	 * <p> Sorts the cards in each of the player's hands, based on the 
	 * natural ordering of the card object.
	 * <p> i.e. A, K, 10, 3, 3
	 * <p> then analysis the ranking of the current hand for every players. 
	 * The results are then compares and at last sort the players in 
	 * descending order.
	 */
	public void analyse() {
		
		for (PlayerEntity entity: table.playerList()) {
			
			//Joins the hold cards with the community cards 
			//for each player.
			List<Card> cards = GameUtils.join(
					entity.holdCards(), 
					table.communityCards());
			
			//Sort cards by it's natural ordering.
			Collections.sort(cards, Collections.reverseOrder());
			
			//Analyze of hands and produces showdown cards for 
			//each player.
			analyse(entity, cards);
		}
		//Sort players by their hand ranking.
		Collections.sort(table.resultList(), Collections.reverseOrder());
		
		System.out.println(table);
	}

	/**
	 * Analysis the rank of the current hand.
	 * @param hand List of Cards to be used.
	 */
	public void analyse(PlayerEntity entity, List<Card> hand) {
		boolean finalise = hand.size() >= 7;
		for (Condition condition: all()) {
			if (condition.check(hand)) {
				if (finalise) {
					condition.finalise(hand);
				}
				entity.showDown().setRank(condition.result());
				entity.showDown().addAll(condition.cards());
				break;
			}
		}
	}
	
	public List<Condition> all() {
		return Arrays.asList(
				new FourOfaKind(),
				new FullHouse(),
				new Flush(),
				new Straight(), 
				new ThreeOfaKind(),
				new TwoPairs(),
				new OnePair(),
				new HighCard()
		);
	}
}