package com.run.poker.view.animation;

import java.util.ArrayList;
import java.util.List;

import com.run.poker.entity.card.Card;
import com.run.poker.entity.card.Suit;
import com.run.poker.utils.GameUtils;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JAVA FX Animation and Visual Effects Template.
 */
public class AnimationTest extends Application {
	
	private List<Card> list;
	
	@Override
	public void init() throws Exception {
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
        	 Card card = new Card(
        			 GameUtils.random(Suit.class),
        			 GameUtils.random(1, 14));
        	 list.add(card);
        }
	}
 
    @Override 
    public void start(Stage stage) {
        Group layout = new Group();
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
 
        layout.getChildren().addAll(list);
        stage.show();
        
        playTranslationAnimation();
    }
    
    public void playTranslationAnimation() {
    	
    	SequentialTransition sequence = new SequentialTransition(
    			move(200, 200), 
    			move(0, 200),
    			move(0, 0));
    	sequence.setCycleCount(Timeline.INDEFINITE);
    	sequence.setAutoReverse(false);
    	
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
            	frameCount++;
            	//Approximately 1 second
            	if (frameCount >= 20) {
            		for (Card card: list) {
                		System.out.println(card.getTranslateX() + ", " + card.getTranslateY());
            		}
            		frameCount = 0;
            	}
            }
        };
    	sequence.play();
        timer.start();
    }
    
    public Timeline move(double x, double y) {
    	Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
    	Card card = list.get(0);
    	timeline.getKeyFrames().add(new KeyFrame(
    			Duration.millis(300), 
    			new KeyValue(card.translateXProperty(), x), 
    			new KeyValue(card.translateYProperty(), y)));
    	return timeline;
    }
    
    //variable for storing actual frame
    private int frameCount = 0;
    
    public void playEnlargeAnimation(Stage stage) {
    	
//      //create a circle with effect
//      Rectangle front = new Rectangle(60, 80, Color.WHITE); //Color.rgb(156,216,255)
//      front.setStroke(Color.BLACK);
//      //card.setEffect(new Lighting());
//      //create a text inside a circle
//      Text text = new Text ("♠ " + value);
//      text.setStroke(Color.BLACK);
//      text.setX(8);
//      text.setY(20);
//      //create a layout for circle with text inside
//      Group card = new Group();
//      card.getChildren().addAll(front, text);
//      card.setLayoutX(30);
//      card.setLayoutY(30);
    	
        //create a timeline for moving the circle
    	Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
 
        //You can add a specific action when each frame is started.
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
            	frameCount++;
            	//Approximately 1 second
            	if (frameCount >= 60) {
            		for (Card card: list) {
    	                card.setSuit(GameUtils.random(Suit.class));
    	                card.setValue(GameUtils.random(1, 14));
            		}
            		frameCount = 0;
            	}
            }
        };
 
        //create a keyValue with factory: scaling the circle 2times
        Card card = list.get(0);
        KeyValue keyValueOpacity = new KeyValue(stage.opacityProperty(), 0.4);
        KeyValue keyValueX = new KeyValue(card.scaleXProperty(), 1.5);
        KeyValue keyValueY = new KeyValue(card.scaleYProperty(), 1.5);
 
        //create a keyFrame, the keyValue is reached at time 2s
        Duration duration = Duration.millis(3000);
        //one can add a specific action when the keyframe is reached
        EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                 //card.setTranslateX(Math.random()*200-100);
                 //card.setTranslateY(Math.random()*200-100);
                 //reset counter
                 //value = 0;
            }
        };
 
        KeyFrame keyFrame = new KeyFrame(duration, onFinished, keyValueOpacity, keyValueX, keyValueY);
 
        //add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);
 
        timeline.play();
        timer.start();
    }
        
        
    public static void main(String[] args) {
        Application.launch(args);
    }

	public void template(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                       new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                         new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                          new double[]{210, 210, 240, 240}, 4);
	}
	
	public void drawGradient(GraphicsContext gc) {
		gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
	    gc.beginPath();
	    gc.moveTo(50, 50);
	    gc.bezierCurveTo(150, 20, 150, 150, 75, 150);
	    gc.closePath();
	    
	    RadialGradient rg = new RadialGradient(0, 0, 0.5, 0.5, 0.1, true,
	               CycleMethod.REFLECT,
	               new Stop(0.0, Color.BLUE),
	               new Stop(1.0, Color.YELLOW));
	    gc.setFill(rg);
	    gc.fill();
	    
	    LinearGradient lg = new LinearGradient(0, 0, 1, 1, true,
	    		CycleMethod.REFLECT,
                new Stop(0.0, Color.BLUE),
                new Stop(1.0, Color.YELLOW));
	    gc.setStroke(lg);
	    gc.setLineWidth(20);
	    gc.stroke();
	    
	    gc.applyEffect(new DropShadow(20, 20, 0, Color.RED));
	    gc.applyEffect(new DropShadow(20, 0, 20, Color.YELLOW));
	    gc.applyEffect(new DropShadow(20, -20, 0, Color.GREEN));
	    gc.applyEffect(new DropShadow(20, 0, -20, Color.BLUE));
	}
}
