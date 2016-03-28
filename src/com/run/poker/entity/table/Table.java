package com.run.poker.entity.table;

import java.util.LinkedList;

import com.run.poker.entity.card.CardList;
import com.run.poker.entity.card.Deck;
import com.run.poker.entity.chips.Chips;
import com.run.poker.entity.chips.ChipsManager;
import com.run.poker.entity.player.Enemy;
import com.run.poker.entity.player.Player;
import com.run.poker.entity.player.PlayerEntity;
import com.run.poker.utils.GameUtils;
import com.run.poker.view.GameStage;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * <p> Main Model.
 * 
 * <p> Table entity that consist of a list of players, a dealer and a 
 * deck of cards.
 * 
 * <p> It's API are not exposed to public use is due to the OO design. 
 * Developers should be calling the {@link #callDealer()} method 
 * first and then send commands to the dealer.
 * 
 * @author RuN
 *
 */
public class Table extends Pane {

	private LinkedList<PlayerEntity> playerList;
	private Player player;
	
	private Dealer dealer;
	private Deck deck;
	
	/**
	 * Consists of 5 cards being dealt facing up.
	 */
	private CardList communityCards;
	
	private ChipsManager manager;
	
	private GameStage gs;
	
	private int bigIndex;
	private int smallIndex;
	
	/**
	 * Draws the game entity object on the canvas. 
	 * <p> This method repaints the entire canvas.
	 * 
	 * @TODO Implement optimization which only repaints 
	 * each game entity by itself.
	 * @param gc
	 */
	public Table() {
		
		//Components
		this.bigIndex = 0;
		this.smallIndex = bigIndex + 1;
		this.playerList = new LinkedList<>();
		this.dealer = new Dealer(this);
		this.manager = new ChipsManager();
		this.player = null;
		this.gs = null;
		
		//Effect
		//Windows Color "0x3b596d"
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.BLACK);

		//Draw Table
		Rectangle table = new Rectangle(795, 595);
		table.setStrokeWidth(1);
		table.setStroke(Color.BLACK);
		Image image = new Image("images/pattern.png");
		table.setFill(new ImagePattern(image, 0, 0, 50, 50, false));
		table.setEffect(dropShadow);
		table.relocate(1, 1);
		
		Text ccText = new Text("Community Cards");
		ccText.setFont(new Font(13));
		ccText.setFill(Color.BLACK);
		ccText.relocate(80, 2);
		
		communityCards = new CardList();
		communityCards.relocate(80, 18);
		
		Text dealerText = new Text("Dealer");
		dealerText.setFont(new Font(13));
		dealerText.setFill(Color.BLACK);
		dealerText.relocate(650, 10);
		
		deck = new Deck();
		deck.relocate(600, 30);
		
		Chips chips = new Chips();
		chips.relocate(50, 400);
		
		getChildren().addAll(table, ccText, communityCards, dealerText, deck, chips);
	}
	
	/**
	 * Adds a player to the table.
	 * @param entity The player.
	 */
	public void createPlayer(String name) {
		this.player = new Player(name);
		sitDown(player);
	}
	
	/**
	 * Add n number of bots.
	 * @param n
	 */
	public void addBots(int n) {
		for (int i = 0; i < n; i++) {
			Enemy enemy = new Enemy();
			sitDown(enemy);
		}
	}
	
	public void endRound() {
		//Circularly moves Big and Small Heads.
		int max = playerList.size() - 1;
		GameUtils.inc(bigIndex, max);
		GameUtils.inc(smallIndex, max);
		
		playerList.offer(playerList.poll());
	}

	
	/**
	 * Sits down every players to the table.
	 * <p> This method must be called after all players 
	 * have been added to the table.
	 */
	private void sitDown(PlayerEntity entity) {
		applyTranslation(entity);
		getChildren().add(entity);
		playerList.add(entity);
		//System.out.println("Entity: " + entity.getLayoutX() + ", " + entity.getLayoutY());
	}
	
	/**
	 * A two-dimensional array holding a list of coordinates.
	 * The first coord is the player and the rest are enemies. 
	 */
	public static final double[][] POS = new double[][] {
		{300, 400},
		{20, 200}, 
		{300, 150}, 
		{600, 200}
	};
	
	private void applyTranslation(PlayerEntity entity) {
		if (entity instanceof Player) {
			entity.relocate(POS[0][0], POS[0][1]);
		} else if (entity instanceof Enemy) {
			Enemy enemy = (Enemy) entity;
			int n = playerList.size();
			enemy.relocate(POS[n][0], POS[n][1]);
		}
	}
	
	//*********************
	//*  GUI Controllers  *
	//*********************
	
	public void setGameStage(GameStage gs) {
		this.gs = gs;
	}

	public void enablePlayerOptions() {
		gs.setPlayerOptions(true);
	}
	
	public void swapButton() {
		gs.swap(0);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Returns the current ranking of the passed in player 
	 * entity amongst all other players.
	 * @param entity
	 * @return
	 */
	public int getCurrentRanking(PlayerEntity entity) {
		return playerList.indexOf(entity) + 1;
	}

	
	/**
	 * Requests the Dealer for user commands and actions.
	 * @return the Dealer in this table.
	 */
	public Dealer callDealer() {
		return dealer;
	}
	
	public ChipsManager callManager() {
		return manager;
	}
	
	/**
	 * 
	 * @return
	 */
	public LinkedList<PlayerEntity> playerList() {
		return playerList;
	}
	
	/**
	 * 
	 * @return
	 */
	public CardList communityCards() {
		return communityCards;
	}
	
	/**
	 * 
	 * @return
	 */
	public Deck deck() {
		return deck;
	}
	
	@Override
	public String toString() {
		String str = "Current Deck: " + deck + "\n";
		str += "  Players: \n";
		for (PlayerEntity p: playerList) {
			str += "  " + p + "\n";
		}
		return str;
	}
}