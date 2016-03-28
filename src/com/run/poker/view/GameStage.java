package com.run.poker.view;

import java.util.Optional;

import com.run.poker.Poker;
import com.run.poker.entity.card.Card;
import com.run.poker.entity.player.Names;
import com.run.poker.entity.table.Dealer;
import com.run.poker.entity.table.Table;
import com.run.poker.utils.GameUtils;

import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Slider;
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
	
	/**
	 * GUI Components.
	 */
	private ToolBar playerOptions;
	private Slider slider;
	private Text name;
	private Text money;
	private Button check;
	private Button call;
	
	public GameStage() {

		//Scene Layouts.
		BorderPane layout = new BorderPane();
		layout.getStylesheets().add("css/gameStyle.css");
		Scene game = new Scene(layout);
		setScene(game);
		
		//Main Model.
		Table table = new Table();
		table.createPlayer("Newb");
		table.addBots(3);
		table.setGameStage(this);
		table.setPrefSize(800, 600);
		layout.setCenter(table);
		
		// Game Tool Bar
		Button deal = new Button("Deal");
		deal.getStyleClass().add("button1");
		deal.setPrefSize(150, 50);
		deal.setOnAction(event -> {
			deal.setDisable(true);
			playerOptions.setDisable(true);
			Dealer dealer = table.callDealer();
			dealer.clearCC();
			dealer.clearHands();
			dealer.newDeck();
			dealer.deal();
			dealer.betStart();
			deal.setDisable(false);
		});
		
		Button draw = new Button("Draw");
		draw.getStyleClass().add("button1");
		draw.setPrefSize(150, 50);
		draw.setOnAction(event -> {
			draw.setDisable(true);
			playerOptions.setDisable(true);
			Dealer dealer = table.callDealer();
			dealer.clearCC();
			dealer.stageOne();
			dealer.betStart();
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
		exit.setOnAction(event -> {
			closeAndShowOwner();
		});

		ToolBar toolbar = new ToolBar(deal, draw, fullScreen, exit);
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
		});
		
		call = new Button("Call");
		call.getStyleClass().add("button1");
		call.setPrefSize(100, 50);
		call.setOnAction(e -> {
			table.getPlayer().call(50);
			table.callManager().set(50);
		});
		
		Button raise = new Button("Raise");
		raise.getStyleClass().add("button1");
		raise.setPrefSize(100, 50);
		raise.setOnAction(e -> {
			table.getPlayer().raise(50);
			table.callManager().add(50);
		});
		
		Button all = new Button("All In");
		all.getStyleClass().add("button2");
		all.setPrefSize(100, 50);
		all.setOnAction(e -> {
			table.getPlayer().allIn();
			table.callManager().set(table.getPlayer().getMoney().get());
		});
		
		Button fold = new Button("Fold");
		fold.getStyleClass().add("button2");
		fold.setPrefSize(100, 50);
		fold.setOnAction(e -> {
			table.getPlayer().fold();
			table.callManager().set(0);
		});
		
		playerOptions = new ToolBar(name, s1, money, s2, check, raise, all, fold);
        BorderPane.setAlignment(playerOptions, Pos.CENTER);
        layout.setBottom(playerOptions);
        
        //TODO move slider to Player
        slider = new Slider();
        slider.setPrefSize(100, 100);
        slider.setBlockIncrement(25);
        slider.setMinorTickCount(1);
        slider.setMajorTickUnit(25);
        //slider.minorTickCountProperty().bind(table.getPlayer().getMoney().divide(25));
        //slider.majorTickUnitProperty().bind(table.getPlayer().getMoney().divide(10));
        //slider.maxProperty().bind(table.getPlayer().getMoney());
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(true);
        slider.setOrientation(Orientation.VERTICAL);
        slider.setOnMouseClicked(e -> System.out.println(slider.getValue()));
        //layout.setRight(slider);
        //BorderPane.setAlignment(slider, Pos.CENTER);
		
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
	
	public void setPlayerOptions(boolean enabled) {
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