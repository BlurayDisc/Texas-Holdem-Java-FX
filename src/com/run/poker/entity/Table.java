package com.run.poker.entity;

import java.util.LinkedList;

import com.run.poker.ai.AIManager;
import com.run.poker.ai.Analyser;
import com.run.poker.card.CommunityCards;
import com.run.poker.chips.ChipsManager;
import com.run.poker.entity.player.Enemy;
import com.run.poker.entity.player.Player;
import com.run.poker.entity.player.PlayerEntity;
import com.run.poker.utils.GameUtils;
import com.run.poker.view.GameStage;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

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
public class Table extends FreeEntityGroup {

	private LinkedList<PlayerEntity> playerList;
	private Player player;
	
	private Dealer dealer;
	private Deck deck;
	
	private CommunityCards communityCards;
	
	private ChipsManager manager;
	
	private GameStage gs;
	
	private int bigIndex;
	private int smallIndex;
	
	public Table() {
		this.bigIndex = 0;
		this.smallIndex = bigIndex + 1;
		this.playerList = new LinkedList<>();
		this.communityCards = new CommunityCards();
		this.dealer = new Dealer(this);
		this.manager = new ChipsManager();
		this.deck = null;
		this.player = null;
		this.gs = null;

		add(communityCards);
		add(dealer);
		
		this.communityCards.move(150, 15);
		this.communityCards.setScale(0.75);
	}
	
	/**
	 * Adds a player to the table.
	 * @param entity The player.
	 */
	public void createPlayer(String name) {
		this.player = new Player(name);
		sitDown(player);
		gs.bindPlayerProperties();
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

	/**
	 * <p> Do a test sort and analyze of hands.
	 */
	public void analyse() {
		Analyser analyser = new Analyser(this);
		analyser.analyse();
		System.out.println(this);
	}
	
	/**
	 * 
	 */
	public void startBetting() {
		analyse();
		AIManager manager = new AIManager(this);
		for (PlayerEntity entity: playerList) {
			if (entity instanceof Enemy) {
				Enemy enemy = (Enemy) entity;
				manager.process(enemy);
			} else {
				gs.enablePlayerOptions();
			}
		}
	}
	
	public void endRound() {
		//Circularly moves Big and Small Heads.
		int max = playerList.size() - 1;
		GameUtils.inc(bigIndex, max);
		GameUtils.inc(smallIndex, max);
		
		playerList.offer(playerList.poll());
	}
	
	//*********************
	//*  GUI Controllers  *
	//*********************
	
	public void setGameStage(GameStage gs) {
		this.gs = gs;
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
	public CommunityCards communityCards() {
		return communityCards;
	}
	
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	/**
	 * 
	 * @return
	 */
	Deck deck() {
		return deck;
	}
	
	private static final double[][] POS = new double[][] {
		{20, 250}, 
		{300, 175}, 
		{575, 250}
	};
	
	/**
	 * Sits down every players to the table.
	 * <p> This method must be called after all players 
	 * have been added to the table.
	 */
	private void sitDown(PlayerEntity entity) {
//		System.out.println("Player in table: " + playerList.size());
//		System.out.println("Sitting down " + entity.getName().get() + " to table");
		if (entity instanceof Player) {
			entity.move(275, 425);
		} else if (entity instanceof Enemy) {
			Enemy enemy = (Enemy) entity;
			int n = playerList.size() - 1;
			enemy.move(POS[n][0], POS[n][1]);
		}
		add(entity);
		playerList.add(entity);
//		System.out.println(entity.getName().get() + " " + entity.x + " " + entity.y); 
	}
	
	/**
	 * Draws the game entity object on the canvas. 
	 * <p> This method repaints the entire canvas.
	 * 
	 * @TODO Implement optimization which only repaints 
	 * each game entity by itself.
	 * @param gc
	 */
	@Override
	public void draw(GraphicsContext gc) {
		
		Canvas canvas = gc.getCanvas();
		
		//Clear Canvas
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		//Draw Table
		gc.strokeRect(1, 1, canvas.getWidth() - 2, canvas.getHeight() - 2);
		gc.strokeRect(4, 4, canvas.getWidth() - 8, canvas.getHeight() - 8);
		
		super.draw(gc);
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