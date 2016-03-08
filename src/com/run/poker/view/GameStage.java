package com.run.poker.view;

import java.util.Optional;

import com.run.poker.Poker;
import com.run.poker.controller.GameController;
import com.run.poker.entity.Card;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
	
	/**
	 * The gc of the GameStage.
	 */
	private GraphicsContext gc;
	
	public GameStage() {
		
		GameController controller = GameController.getInstance();
		controller.createPlayer("Newb");
		controller.addBots(3);
		
		// Stage&Scene Layouts.
		BorderPane layout = new BorderPane();
		Scene game = new Scene(layout);
		layout.setPrefSize(500, 500);
		layout.getStylesheets().add("com/run/poker/view/menuStyle.css");
		
		// Game Tool Bar
		Button deal = new Button("Deal");
		deal.getStyleClass().add("button1");
		deal.setPrefSize(150, 50);
		deal.setOnAction(event -> {
			controller.newDeck();
			controller.deal();
			controller.draw(gc);
		});
		
		Button sort = new Button("Sort");
		sort.getStyleClass().add("button1");
		sort.setPrefSize(150, 50);
		sort.setOnAction(event -> {
			controller.sort();
			controller.draw(gc);
		});
		
		Button analyse = new Button("Analyse");
		analyse.getStyleClass().add("button1");
		analyse.setPrefSize(150, 50);
		analyse.setOnAction(event -> {
			controller.analyse();
			controller.draw(gc);
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
			controller.cleanUp();
			closeAndShowOwner();
		});

		ToolBar toolbar = new ToolBar(deal, sort, analyse, fullScreen, exit);
		layout.setTop(toolbar);
		
		// Main Canvas for GC
		Canvas canvas = new Canvas(800, 600);
		this.gc = canvas.getGraphicsContext2D();
		layout.setCenter(canvas);
		
		// Player Tool bar
		Text name = new Text();
		name.setFont(new Font(32));
		name.textProperty().bind(controller.getPlayer().getName());
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
			result.ifPresent(value -> controller.getPlayer().setName(value));
			//Repaint Player
			controller.draw(gc);
		});
		
		Text money = new Text();
		money.setFont(new Font(32));
		money.textProperty().bind(controller.getPlayer().getMoneyBinding());
		
		Pane spacing = new Pane();
		spacing.setPrefSize(50, 50);
		
		Button check = new Button("Check");
		check.getStyleClass().add("button1");
		check.setPrefSize(100, 50);
		
		Button call = new Button("Call");
		call.getStyleClass().add("button1");
		call.setPrefSize(100, 50);
		call.setOnAction(e -> {
			controller.getPlayer().subtractMoney(50);
			controller.draw(gc);
		});
		
		Button raise = new Button("Raise");
		raise.getStyleClass().add("button1");
		raise.setPrefSize(100, 50);
		
		Button all = new Button("All In");
		all.getStyleClass().add("button2");
		all.setPrefSize(100, 50);
		
		Button fold = new Button("Fold");
		fold.getStyleClass().add("button2");
		fold.setPrefSize(100, 50);
		
		ToolBar playerBar = new ToolBar(name, money, spacing, check, call, raise, all, fold);
        BorderPane.setAlignment(playerBar, Pos.CENTER);
        layout.setBottom(playerBar);
		
		this.setTitle(Poker.APP_NAME);
		this.getIcons().add(Card.BACK);
		this.setOnCloseRequest(event -> {
			controller.cleanUp();
			closeAndShowOwner();
		});
		this.setScene(game);
		
		controller.draw(gc);
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