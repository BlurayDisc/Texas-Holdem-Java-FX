package com.run.poker.ai;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.run.poker.ai.decision.Call;
import com.run.poker.ai.decision.Check;
import com.run.poker.ai.decision.Decision;
import com.run.poker.ai.decision.Fold;
import com.run.poker.ai.decision.Raise;
import com.run.poker.utils.GameUtils;

/**
 * 
 * @author RuN
 *
 */
public class LogicProducer {
	
	private BlockingQueue<Decision> backtrace;
	private ScheduledExecutorService scheduledExecutor;
	
	public LogicProducer(BlockingQueue<Decision> backtrack) {
		this.backtrace = backtrack;
		this.scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
	}
	
	/**
	 * 
	 */
	public void startup() {
		
		Task task = new Task();
		scheduledExecutor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);		
		System.out.println("[Producer][Started]");
		
		MonitorTask monitor = new MonitorTask();
		scheduledExecutor.scheduleAtFixedRate(monitor, 3, 3, TimeUnit.SECONDS);
		System.out.println("[Monitor][Started]");
	}
	
	/**
	 * 
	 */
	public void shutdown() {
		List<Runnable> failedList = scheduledExecutor.shutdownNow();
		System.out.println("[Producer][Stopped] " + failedList.size() + " " + 
				"more tasks were scheduled but execution is skipped.");
	}
	
	static final Decision[] ITEMS = new Decision[] {
			new Call(), new Fold(), new Raise(), new Check()};
	
	/**
	 * 
	 * @author RuN
	 *
	 */
	class Task implements Runnable {

		@Override
		public void run() {
			int index = GameUtils.random(0, ITEMS.length - 1);
			try {
				backtrace.put(ITEMS[index]);
			} catch (InterruptedException e) {}
		}
	}
	
	/**
	 * 
	 * @author RuN
	 *
	 */
	class MonitorTask implements Runnable {
		
		@Override
		public void run() {
			System.out.println("[Monitor] " + backtrace.size() + " " + 
					"items in back track queue: " + backtrace);
		}
	}
}