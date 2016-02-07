package com.run.poker.deck;

/**
 * A Dealer is associated with a table.
 * A Dealer can be created with the {@link Table#callDealer()}
 * call and then perform card drawing or comparison operations.
 * @author RuN
 *
 */
public class Dealer {

	private Table table;
	
	Dealer() { }

	void setTable(Table table) {
		this.table = table;
	}
	
	public void newDeck() {
		table.newDeck();
	}
	
	public void sort() {
		
	}
	
	public void clear() {
		
	}
	
	/**
	 * Draw two cards of each of the players in the table.
	 * {@link Table#drawTwo}
	 */
	public void drawTwo() {
		table.drawTwo();
	}
	
	/**
	 * Next round.
	 */
	public void drawOne(){
		table.drawOne();
	}
}
