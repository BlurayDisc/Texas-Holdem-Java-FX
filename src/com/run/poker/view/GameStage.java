package com.run.poker.view;

import java.util.Optional;

import com.run.poker.Poker;
import com.run.poker.entity.card.Card;
import com.run.poker.entity.player.Names;
import com.run.poker.entity.table.Dealer;
import com.run.poker.entity.table.Table;
import com.run.poker.manager.GameManager;
import com.run.poker.utils.GameUtils;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Window for game stage.
 * @author RuN
 *
 */
public class GameStage extends Stage {
	
	private GameManager manager;
	
	/**
	 * GUI Components.
	 */
	private ToolBar playerOptions;
	private Text name;
	private Text money;
	private Button check;
	private Button call;
	
	private Stage sliderMenu;
	
	public GameStage() {

		//Scene Layouts.
		BorderPane layout = new BorderPane();
		layout.getStylesheets().add("css/gameStyle.css");
		setScene(new Scene(layout));
		
		//Main Model.
		Table table = new Table();
		table.createPlayer("Newb");
		table.addBots(3);
		table.setGameStage(this);
		table.setPrefSize(800, 600);
		layout.setCenter(table);
		
		manager = new GameManager(table);
        sliderMenu = new SliderMenu(table);
        sliderMenu.initOwner(this);
		
		// Game Tool Bar
		Button dealButton = new Button("Deal");
		dealButton.getStyleClass().add("button1");
		dealButton.setPrefSize(150, 50);
		dealButton.setOnAction(event -> {
			dealButton.setDisable(true);
			Dealer dealer = table.callDealer();
			dealer.clearCC();
			dealer.clearHands();
			dealer.newDeck();
			dealer.deal();
			manager.beforePlayerAction();
		});
		
        Button fullScreen = new Button("Full Screen");
        fullScreen.getStyleClass().add("button1");
        fullScreen.setPrefSize(150, 50);
        fullScreen.setOnAction(event -> {
			this.setFullScreen(!isFullScreen());
		});
        
		Button exit = new Button("Exit");
		exit.getStyleClass().add("button1");
		exit.setPrefSize(150, 50);
		exit.setOnAction(event -> closeAndShowOwner());

		ToolBar toolbar = new ToolBar(dealButton, fullScreen, exit);
		layout.setTop(toolbar);
		
		// Player Tool bar
		name = new Text();
		name.setFont(new Font(32));
		name.textProperty().bind(table.getPlayer().getName());
		name.setOnMouseClicked(event -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.initOwner(this);
			dialog.setTitle("New Name");
			dialog.setHeaderText("");
			dialog.setContentText("Please re-enter your name:");
			//ImageView
			ImageView iv = new ImageView(Card.BACK);
			iv.setFitWidth(60);
			iv.setFitHeight(80);
			dialog.setGraphic(iv);
			//Validation
			Node button = dialog.getDialogPane().lookupButton(ButtonType.OK);
			button.setDisable(true);
			dialog.getEditor().textProperty().addListener((observable, oldValue, newValue) -> 
					button.setDisable(newValue.trim().isEmpty())
			);
			//Result
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(value -> table.getPlayer().setName(value));
			
			//Repaint Player
		});
		
		Pane s1 = new Pane();
		s1.setPrefSize(50, 50);

		money = new Text();
		money.setFont(new Font(32));
		money.textProperty().bind(GameUtils.createBinding(table.getPlayer().getMoney()));
		
		Pane s2 = new Pane();
		s2.setPrefSize(50, 50);
		
		check = new Button("Check");
		check.getStyleClass().add("button1");
		check.setPrefSize(100, 50);
		check.setOnAction(e -> {
			swap(0);
			table.getPlayer().check();
			table.callManager().set(0);
			manager.afterPlayerAction();
		});
		
		call = new Button("Call");
		call.getStyleClass().add("button1");
		call.setPrefSize(100, 50);
		call.setOnAction(e -> {
			table.getPlayer().call(50);
			table.callManager().set(50);
			manager.afterPlayerAction();
		});
		
		Button raise = new Button("Raise");
		raise.getStyleClass().add("button1");
		raise.setPrefSize(100, 50);
		raise.setOnAction(e -> {
			table.getPlayer().raise(50);
			table.callManager().add(50);
			sliderMenu.setX(getX() + getWidth() / 2 - 150);
			sliderMenu.setY(getY() + getHeight() / 2 + 100);
	        sliderMenu.show();
			manager.afterPlayerAction();
			System.out.println(this.getX() + " " + this.getY());
		});
		
		Button all = new Button("All In");
		all.getStyleClass().add("button2");
		all.setPrefSize(100, 50);
		all.setOnAction(e -> {
			table.getPlayer().allIn();
			table.callManager().set(table.getPlayer().getMoney().get());
			manager.afterPlayerAction();
		});
		
		Button fold = new Button("Fold");
		fold.getStyleClass().add("button2");
		fold.setPrefSize(100, 50);
		fold.setOnAction(e -> {
			table.getPlayer().fold();
			table.callManager().set(0);
			manager.afterPlayerAction();
		});
		
		playerOptions = new ToolBar(name, s1, money, s2, check, raise, all, fold);
		playerOptions.setDisable(true);
        BorderPane.setAlignment(playerOptions, Pos.CENTER);
        layout.setBottom(playerOptions);

		setTitle(Poker.APP_NAME);
		getIcons().add(Card.BACK);
		setOnCloseRequest(event -> closeAndShowOwner());
	}
	
	public void swap(int option) {
		Button oldButton = option == 0 ? check : call;
		Button newButton = option == 0 ? call : check;
		ObservableList<Node> list = playerOptions.getItems();
		if (list.contains(oldButton)) {
			list.add(list.indexOf(oldButton), newButton);
			list.remove(oldButton);
		}
	}
	
	public void enablePlayerOptions(boolean enabled) {
		this.playerOptions.setDisable(!enabled);
	}
	
	/**
	 * Hides this Stages and shows it's owner.
	 */
	private void closeAndShowOwner() {
		//this.manager.shutdown();
		this.hide();
		((Stage) getOwner()).show();
		Names.reset();
	}
	
	/**
	 * Template
	 */
	public void drawCircle() {
		Circle circle = new Circle(100, Color.web("white", 0.5));
		circle.setStrokeType(StrokeType.OUTSIDE);
		circle.setStroke(Color.web("black", 0.16));
		circle.setStrokeWidth(4);
	}
	
	/**
	 * Template
	 */
	public void virus() {
		this.setFullScreenExitHint("You can not exist fullscreen mode.");
	}
}