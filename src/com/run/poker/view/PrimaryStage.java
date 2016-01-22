package com.run.poker.view;

import com.run.poker.ls.Persistence;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PrimaryStage extends Stage {

	
	public void init() {
		VBox layout = new VBox(20);
		Scene start = new Scene(layout);
		layout.getStylesheets().add("com/run/poker/view/menuStyle.css");
		layout.setPrefSize(500, 500);
		layout.setAlignment(Pos.CENTER);
		
        Text title = new Text("Texas Hold'em Poker");
        title.setFont(new Font("SansSerif", 22));
        
        Image icon = new Image("file:resources/back.jpg");
        ImageView imageView = new ImageView(icon);
        
		Button newButton = new Button("New Game");
		newButton.getStyleClass().add("button2");
		newButton.setMinSize(150, 50);
		newButton.setOnAction(event -> {
			GameStage stage = new GameStage();
			stage.setStage(this);
			stage.init();
			stage.show();
			this.hide();
		});
		
		Button loadButton = new Button("Continue");
		loadButton.getStyleClass().add("button1");
		loadButton.setMinSize(150, 50);
		loadButton.setOnAction(event -> Persistence.load());
		
		Button exitButton = new Button("Exit");
		exitButton.getStyleClass().add("button1");
		exitButton.setMinSize(150, 50);
		exitButton.setOnAction(event -> System.exit(0));
		
		Pane spacing = new Pane();
		spacing.setPrefSize(100, 100);
		
		layout.getChildren().addAll(title, imageView, newButton, loadButton, exitButton);
		
		this.setTitle("Texas Poker Hold'em");
		this.setScene(start);
		this.setOnCloseRequest(e -> Persistence.save());
	}
}
