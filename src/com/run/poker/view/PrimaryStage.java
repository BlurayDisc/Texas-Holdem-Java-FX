package com.run.poker.view;

import com.run.poker.Poker;
import com.run.poker.entity.Card;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Main Stage, before game starts.
 * @author RuN
 *
 */
public class PrimaryStage extends Stage {
	
	/**
	 * Initializes the stage.
	 */
	public PrimaryStage() {
		
		VBox layout = new VBox(20);
		Scene start = new Scene(layout);
		layout.getStylesheets().add("com/run/poker/view/menuStyle.css");
		layout.setPrefSize(500, 500);
		layout.setAlignment(Pos.CENTER);
		
        Text title = new Text(Poker.APP_NAME);
        title.setFont(new Font("SansSerif", 22));
        
        ImageView imageView = new ImageView(Card.BACK);
        
		Button newButton = new Button("New Game");
		newButton.getStyleClass().add("button2");
		newButton.setPrefSize(150, 50);
		newButton.setOnAction(event -> {
			this.hide();
			GameStage stage = new GameStage();
			stage.initOwner(this);
			stage.show();
		});
		
		Button loadButton = new Button("Continue");
		loadButton.getStyleClass().add("button1");
		loadButton.setPrefSize(150, 50);
		
		Button exitButton = new Button("Exit");
		exitButton.getStyleClass().add("button1");
		exitButton.setPrefSize(150, 50);
		exitButton.setOnAction(event -> System.exit(0));
		
		layout.getChildren().addAll(title, imageView, newButton, loadButton, exitButton);
		
		this.setTitle(Poker.APP_NAME);
		this.getIcons().add(Card.BACK);
		this.setScene(start);
	}
	
	/**
	 * Template
	 */
	public void addSpacing() {
		Pane spacing = new Pane();
		spacing.setPrefSize(100, 100);
	}
}