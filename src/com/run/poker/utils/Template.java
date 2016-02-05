package com.run.poker.utils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;

public class Template {

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
