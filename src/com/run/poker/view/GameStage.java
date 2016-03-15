package com.run.poker.view;

import java.util.Optional;

import com.run.poker.Poker;
import com.run.poker.card.Card;
import com.run.poker.entity.Dealer;
import com.run.poker.entity.Table;

import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Slider;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
	 * Main Model.
	 */
	private Table table;
	
	/**
	 * The gc of the GameStage.
	 */
	private GraphicsContext gc;
	
	/**
	 * GUI Components.
	 */
	private ToolBar playerBar;
	private Slider slider;
	private Text name;
	private Text money;
	private Button check;
	private Button call;
	
	public GameStage(Table model) {
		//Register model
		this.table = model;
		
		// Stage&Scene Layouts.
		BorderPane layout = new BorderPane();
		//TODO port the layout to grid pane
		@SuppressWarnings("unused")
		GridPane grid = new GridPane();
		Scene game = new Scene(layout);
		layout.setPrefSize(500, 500);
		layout.getStylesheets().add("com/run/poker/view/menuStyle.css");
		
		// Game Tool Bar
		Button deal = new Button("Deal");
		deal.getStyleClass().add("button1");
		deal.setPrefSize(150, 50);
		deal.setOnAction(event -> {
			Dealer dealer = table.callDealer();
			dealer.newDeck();
			dealer.deal();
			table.draw(gc);
			//table.startBetting();
		});
		
		Button oneTwoThree = new Button("Draw");
		oneTwoThree.getStyleClass().add("button1");
		oneTwoThree.setPrefSize(150, 50);
		oneTwoThree.setOnAction(event -> {
			Dealer dealer = table.callDealer();
			dealer.stageOne();
			dealer.stageTwo();
			dealer.stageThree();
			table.draw(gc);
			//table.startBetting();
		});
		
		Button analyse = new Button("Analyse");
		analyse.getStyleClass().add("button1");
		analyse.setPrefSize(150, 50);
		analyse.setOnAction(event -> {
			table.analyse();
			table.draw(gc);
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

		ToolBar toolbar = new ToolBar(deal, oneTwoThree, analyse, fullScreen, exit);
		layout.setTop(toolbar);
		
		// Main Canvas for GC
		Canvas canvas = new Canvas(800, 600);
		this.gc = canvas.getGraphicsContext2D();
		layout.setCenter(canvas);
		
		// Player Tool bar
		name = new Text();
		name.setFont(new Font(32));
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
			table.draw(gc);
		});

		money = new Text();
		money.setFont(new Font(32));
		
		Pane spacing = new Pane();
		spacing.setPrefSize(50, 50);
		
		check = new Button("Check");
		check.getStyleClass().add("button1");
		check.setPrefSize(100, 50);
		check.setOnAction(e -> {
			swap(0);
			table.getPlayer().check();
			table.draw(gc);
			table.callManager().set(0);
		});
		
		call = new Button("Call");
		call.getStyleClass().add("button1");
		call.setPrefSize(100, 50);
		call.setOnAction(e -> {
			table.getPlayer().call(50);
			table.draw(gc);
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
		
		playerBar = new ToolBar(name, money, spacing, check, raise, all, fold);
        BorderPane.setAlignment(playerBar, Pos.CENTER);
        layout.setBottom(playerBar);
        playerBar.setDisable(true);
        
        slider = new Slider();
        slider.setPrefSize(100, 100);
        slider.setBlockIncrement(25);
        slider.setMinorTickCount(1);
        slider.setMajorTickUnit(25);
        //slider.minorTickCountProperty().bind(table.getPlayer().getMoney().divide(25));
        //slider.majorTickUnitProperty().bind(table.getPlayer().getMoney().divide(10));
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(true);
        slider.setOrientation(Orientation.VERTICAL);
        slider.setOnMouseClicked(e -> System.out.println(slider.getValue()));
        layout.setRight(slider);
        BorderPane.setAlignment(slider, Pos.CENTER);
		
		this.setTitle(Poker.APP_NAME);
		this.getIcons().add(Card.BACK);
		this.setOnCloseRequest(event -> {
			closeAndShowOwner();
		});
		this.setScene(game);
		
		table.draw(gc);
	}
	
	public void bindPlayerProperties() {
		name.textProperty().bind(table.getPlayer().getName());
		money.textProperty().bind(table.getPlayer().getMoneyBinding());
        slider.maxProperty().bind(table.getPlayer().getMoney());
	}
	
	public void swap(int option) {
		Button oldButton = option == 0 ? check : call;
		Button newButton = option == 0 ? call : check;
		ObservableList<Node> list = playerBar.getItems();
		if (list.contains(oldButton)) {
			list.add(list.indexOf(oldButton), newButton);
			list.remove(oldButton);
		}
	}
	
	public void enablePlayerOptions() {
		this.playerBar.setDisable(false);
	}
	
	/**
	 * Hides this Stages and shows it's owner.
	 */
	private void closeAndShowOwner() {
		//this.manager.shutdown();
		this.hide();
		((Stage) getOwner()).show();
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