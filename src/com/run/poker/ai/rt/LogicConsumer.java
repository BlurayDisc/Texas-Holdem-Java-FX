package com.run.poker.ai.rt;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.run.poker.ai.decision.Decision;
import com.run.poker.utils.GameUtils;

/**
 * 
 * @author RuN
 *
 */
public class LogicConsumer {
	
	private ExecutorService executor;
	private BlockingQueue<Decision> backtrace;
	
	public LogicConsumer(BlockingQueue<Decision> backtrack) {
		this.backtrace = backtrack;
		this.executor = Executors.newSingleThreadExecutor();
	}
	
	/**
	 * 
	 */
	public void startup() {
		System.out.println("[Consumer][Started]");
		Task task = new Task();
		executor.execute(task);
	}
	
	/**
	 * 
	 */
	public void shutdown() {
		executor.shutdownNow();
		System.out.println("[Consumer][Stopped]");
	}
	
	private class Task implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Decision decision = backtrace.take();
					System.out.println("[Consumer][Received][" + decision + "]");
					Thread.sleep(GameUtils.random(0, 3000));
				} catch (Exception exception) { }
			}
		}
	}
}