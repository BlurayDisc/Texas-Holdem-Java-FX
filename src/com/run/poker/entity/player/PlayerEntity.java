package com.run.poker.entity.player;

import java.util.Observable;
import java.util.Observer;

import com.run.poker.card.Card;
import com.run.poker.card.HoldCards;
import com.run.poker.card.Showdown;
import com.run.poker.entity.ArrayEntity;
import com.run.poker.utils.GameUtils;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * <p> Base entity for player/enemy objects.
 * <p> Players have different properties and their names can be modified 
 * by the user on request. Players are also drawn differently: their 
 * cards will be drawn on the screen and revealed to the user where as 
 * Enemy will have their cards hidden.
 * <p> Enemy are bots which inherits all the properties of a player but 
 * with additional build in AI and functions. 
 * 
 * @author RuN
 *
 */
public class PlayerEntity extends ArrayEntity 
		implements Observer, Comparable<PlayerEntity> {
	
	/**
	 * Player name binding
	 */
	protected StringProperty name;
	
	/**
	 * Player title binding
	 */
	protected StringProperty title;
	
	/**
	 * Player state
	 */
	protected State state;
	
	/**
	 * Player gold
	 */	
	protected IntegerProperty money;
	
	/**
	 * Player gold binding
	 */
	private StringBinding moneyBinding;
	
	
	/**
	 * Current hold cards.
	 */
	protected HoldCards holdCards;
	
	/**
	 * Final show down cards.
	 */
	private Showdown showDown;
	
	public PlayerEntity() {
		
		this.name = new SimpleStringProperty("New Player");
		this.title = new SimpleStringProperty("Beginner");
		this.money = new SimpleIntegerProperty(1000);
		this.moneyBinding = GameUtils.createBinding(money, "   $");
		this.state = State.None;
		
		this.holdCards = new HoldCards();
		this.showDown = new Showdown();

		add(holdCards);
		add(showDown);
		
		//TODO add show down cards to the GUI, 
		//it currently only appears in console.
	}
	
	public void check() {
		this.state = State.Checked;
	}
	
	public void call(int amount) {
		this.state = State.Called;
		subtractMoney(amount);
	}
	
	public void raise(int amount) {
		this.state = State.Raising;
		subtractMoney(amount);
	}
	
	public void fold() {
		this.state = State.Folded;
	}
	
	public void allIn() {
		raise(money.get());
	}
	
	public void reset() {
		this.state = State.None;
	}
	
	/**
	 * Acquires one card.
	 * @param card A card.
	 */
	public void acquire(Card card) {
		this.holdCards.add(card);
	}
	
	/**
	 * 
	 * @param amount
	 */
	public void addMoney(int amount) {
		this.money.set(money.get() + amount);
	}

	/**
	 * 
	 * @param amount
	 */
	public void subtractMoney(int amount) {
		if (amount > money.get()) {
			throw new IllegalArgumentException("Out of Money.");
		}
		this.money.set(money.get() - amount);
	}
	
	public void clearHands() {
		this.holdCards.clear();
		this.showDown.clear();
	}
	
	public StringProperty getName() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public StringProperty getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title.set(title);
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public State getState() {
		return state;
	}
	
	public IntegerProperty getMoney() {
		return money;
	}
	
	public StringBinding getMoneyBinding() {
		return moneyBinding;
	}
	
	public Showdown showDown() {
		return showDown;
	}
	
	public HoldCards holdCards() {
		return holdCards;
	}
	
	@Override
	public int compareTo(PlayerEntity that) {
		return this.showDown.compareTo(that.showDown);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(name.get() + ": " + arg);
	}

	@Override
	public void draw(GraphicsContext gc) {
        
		//Set Fill
		gc.setFill(Color.WHITE);
		
		//Draw Name
        gc.setFont(new Font(25 * scale));
        gc.fillText(name.get(), x, y - 25);
        
		//Draw Money
        gc.setFont(new Font(18 * scale));
        gc.fillText("$" + money.get(), x + 125  * scale, y - 25  * scale);
        
        //Draw Title
        gc.setFont(new Font(18 * scale));
        gc.fillText("The " + title.get(), x, y - 5);
        
        //Draw Status
        
        super.draw(gc);
	}
	
	@Override
	public String toString() {
		return name.get() + 
				" The " + title.get() + 
				" with $" + money.get() + 
				"\n    " + showDown;
	}
}