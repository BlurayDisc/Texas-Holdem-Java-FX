package com.run.poker.view.task;

import com.run.poker.entity.table.Table;

import javafx.scene.canvas.GraphicsContext;

public abstract class FXTask extends javafx.concurrent.Task<Void> {
	
	final Table table;
	final GraphicsContext gc;
	
	public FXTask(Table table, GraphicsContext gc) {
		this.gc = gc;
		this.table = table;
	}
}
