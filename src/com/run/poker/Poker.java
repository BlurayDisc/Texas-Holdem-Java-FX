package com.run.poker;

import com.run.poker.deck.Dealer;
import com.run.poker.deck.Table;
import com.run.poker.entity.Player;

public class Poker {
	
	public static void main(String[] args) {
		
		Table table = new Table();
		table.addPlayer(new Player("Ying"));
		table.addBot();
		table.addBot();
		table.addBot();
		
		Dealer dealer = table.getDealer();
		dealer.drawTwo();
		
		System.out.println(table);
	}
}