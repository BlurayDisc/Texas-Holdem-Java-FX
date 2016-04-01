package com.run.poker.entity.player;

import com.run.poker.entity.card.CardList;
import com.run.poker.entity.card.Showdown;
import com.run.poker.utils.GameUtils;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
public class PlayerEntity extends Pane implements Comparable<PlayerEntity> {
	
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
	 * Consists of 2 cards being dealt facing down.
	 * <p> The best five card poker hand is obtained by using the necessary 
	 * cards from the community and/or a particular player's hold cards.
	 */
	protected CardList holdCards;
	
	/**
	 * Final show down cards.
	 */
	private Showdown showDown;
	
	private Rectangle highlight;
	
	public PlayerEntity() {
		
		this.name = new SimpleStringProperty("New Player");
		this.title = new SimpleStringProperty("Beginner");
		this.money = new SimpleIntegerProperty(1000);
		this.state = State.None;
		
		//Name
		Text name = new Text();
		name.textProperty().bind(this.name);
		name.setFont(new Font(18));
		name.setFill(Color.BLACK);
		name.relocate(0, 0);
        
		//Money
		Text money = new Text();
		money.textProperty().bind(GameUtils.createBinding(this.money));
		money.setFont(new Font(14));
		money.setFill(Color.BLACK);
		money.relocate(100, 0);
        
        //Draw Title
		Text title = new Text();
		title.textProperty().bind(this.title);
		title.setFont(new Font(14));
		title.setFill(Color.BLACK);
		title.relocate(0, 25);
		
		//Current Hold Cards
		holdCards = new CardList();
		holdCards.relocate(0, 55);
		
		//Player's turn highlight
		highlight = new Rectangle(160, 55);
		highlight.setOpacity(1);
		highlight.setStrokeWidth(4);
		highlight.setStroke(Color.web("#3090C7"));
		highlight.setFill(Color.TRANSPARENT);
		highlight.setEffect(new Bloom(0.1));
		highlight.relocate(-10, -5);
		highlight.setVisible(false);
		
		//TODO add show down cards to the GUI, it currently only appears in console.
		showDown = new Showdown();
		
		getChildren().addAll(name, money, title, holdCards, highlight);
	}
	
	public void setActive(boolean active) {
		highlight.setVisible(active);
	}
	
	/**
	 * 
	 */
	public void check() {
		this.state = State.Checked;
	}
	
	/**
	 * 
	 * @param amount
	 */
	public void call(int amount) {
		this.state = State.Called;
		subtractMoney(amount);
	}
	
	/**
	 * 
	 * @param amount
	 */
	public void raise(int amount) {
		this.state = State.Raising;
		subtractMoney(amount);
	}
	
	/**
	 * 
	 */
	public void fold() {
		this.state = State.Folded;
	}
	
	/**
	 * 
	 */
	public void allIn() {
		raise(money.get());
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
	
	public Showdown showDown() {
		return showDown;
	}
	
	public CardList holdCards() {
		return holdCards;
	}
	
	@Override
	public int compareTo(PlayerEntity that) {
		return this.showDown.compareTo(that.showDown);
	}
	
	@Override
	public String toString() {
		return name.get() + 
				" The " + title.get() + 
				" with $" + money.get() + 
				"\n    " + showDown;
	}
}