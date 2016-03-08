package com.run.poker.entity.player;

import com.run.poker.entity.GameEntity;
import com.run.poker.utils.GameUtils;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class PlayerBaseEntity extends GameEntity implements Comparable<PlayerEntity> {

	/**
	 * Player name binding.
	 */
	protected StringProperty name;
	
	/**
	 * Player title binding.
	 */
	protected StringProperty title;
	
	/**
	 * Player gold
	 */	
	protected IntegerProperty money;
	
	/**
	 * Player Gold Binding
	 */
	private StringBinding moneyBinding;
	
	public PlayerBaseEntity() {
		this.name = new SimpleStringProperty("New Player");
		this.title = new SimpleStringProperty("Beginner");
		this.money = new SimpleIntegerProperty(1000);
		this.moneyBinding = GameUtils.createBinding(money, "   $");
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
	
	public StringBinding getMoneyBinding() {
		return moneyBinding;
	}

	/**
	 * @TODO change hard coded constants to scale.
	 */
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
	}
	
	@Override
	public String toString() {
		return name.get() + " The " + title.get() + " " + 
				"with $" + money.get();
	}
}