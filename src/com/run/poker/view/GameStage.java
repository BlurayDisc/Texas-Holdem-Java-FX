package com.run.poker.view;

import com.run.poker.player.Player;
import com.run.poker.utils.CardUtils;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

/**
 * Window for game stage.
 * @author RuN
 *
 */
public class GameStage extends Stage {
	
	private Stage previous;
	private GraphicsContext gc;

	/**
	 * Initializes the stage.
	 */
	public void init() {
		BorderPane layout = new BorderPane();
		Scene game = new Scene(layout);
		layout.setPrefSize(500, 500);
		layout.getStylesheets().add("com/run/poker/view/menuStyle.css");
		
		Button draw = new Button("Draw");
		draw.getStyleClass().add("button1");
		draw.setPrefSize(150, 50);
		draw.setOnAction(event -> {
			Player player = new Player("Run");
			player.setPosition(50, 400);
			player.acquire(CardUtils.randomCard());
			player.acquire(CardUtils.randomCard());
			player.acquire(CardUtils.randomCard());
			player.acquire(CardUtils.randomCard());
			player.acquire(CardUtils.randomCard());
			player.draw(gc);
		});
		
        Button fullScreen = new Button("Full Screen");
        fullScreen.getStyleClass().add("button1");
        fullScreen.setPrefSize(150, 50);
        fullScreen.setOnAction(event -> {
			this.setFullScreen(!isFullScreen());
		});
        
		Button exit = new Button("Exit");
		exit.getStyleClass().add("button1");
		exit.setMinSize(150, 50);
		exit.setOnAction(event -> {
			previous.show();
			this.hide();
		});

		ToolBar toolbar = new ToolBar(draw, fullScreen, exit);
		layout.setTop(toolbar);
		
		Canvas canvas = new Canvas(800, 600);
		this.gc = canvas.getGraphicsContext2D();
		gc.strokeRect(0, 0, 800, 600);
		layout.setCenter(canvas);
		
        Image icon = new Image("file:resources/card.jpg");
        ImageView imageView = new ImageView(icon);
        BorderPane.setAlignment(imageView, Pos.CENTER);
        layout.setBottom(imageView);
		
		this.setTitle("Texas Poker Hold'em");
		//this.setFullScreenExitHint("You can not exist fullscreen mode.");
		this.setScene(game);
		this.setOnCloseRequest(event -> {
			previous.show();
			this.hide();
		});
	}
	
	/**
	 * Assosciates with the previous stage.
	 * @param primaryStage
	 */
	public void setStage(Stage primaryStage) {
		this.previous = primaryStage;
	}
	
	public void drawCircle() {
		Circle circle = new Circle(100, Color.web("white", 0.5));
		circle.setStrokeType(StrokeType.OUTSIDE);
		circle.setStroke(Color.web("black", 0.16));
		circle.setStrokeWidth(4);
	}
}