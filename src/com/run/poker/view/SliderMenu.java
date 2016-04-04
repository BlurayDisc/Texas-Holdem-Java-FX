package com.run.poker.view;

import com.run.poker.entity.card.Card;
import com.run.poker.entity.table.Table;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SliderMenu extends Stage {

	public SliderMenu(Table table) {
        Pane sliderLayout = new Pane();
        sliderLayout.getStylesheets().add("css/gameStyle.css");
        sliderLayout.setPrefSize(300, 125);
        
        Slider slider = new Slider();
        slider.relocate(25, 25);
        slider.setPrefSize(250, 50);
        slider.setBlockIncrement(25);
        slider.setMajorTickUnit(100);
        slider.setMinorTickCount(3);
        slider.maxProperty().bind(table.getPlayer().getMoney());
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(true);
        slider.setOrientation(Orientation.HORIZONTAL);
        slider.setOnMouseClicked(e -> System.out.println(slider.getValue()));
        
        Button enterButton = new Button("Confirm");
        enterButton.getStyleClass().add("button1");
        enterButton.setOnAction(e -> {
        	int amount = (int) slider.getValue();
        	table.getPlayer().subtractMoney(amount);
        	hide();
        });
        enterButton.relocate(200, 80);

        sliderLayout.getChildren().addAll(slider, enterButton);
        
        setScene(new Scene(sliderLayout));
        getIcons().add(Card.BACK);
        setOnCloseRequest(e -> hide());
        setTitle("Raise");
        setAlwaysOnTop(true);
	}
}