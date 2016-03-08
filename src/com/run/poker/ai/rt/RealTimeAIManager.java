package com.run.poker.ai.rt;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

import com.run.poker.ai.decision.Decision;

/**
 * AI Manager contains a back trace queue which holds on to a 
 * list of generated decisions. These decisions are produced 
 * by a Logic Producer object. The back trace queue is associated 
 * with a Logic Consumer that processes the decision.
 * @author RuN
 *
 */
public class RealTimeAIManager {
	
	private LogicProducer producer;
	private LogicConsumer consumer;
	
	/**
	 * Main queue holding decisions produced by the producer.
	 */
	private BlockingQueue<Decision> backtrace;
	
	public RealTimeAIManager() {
		this.backtrace = new LinkedBlockingQueue<>();
	}
	
	/**
	 * Starts up the AIManager.
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public void startup() {
		
		this.consumer = new LogicConsumer(backtrace);
		consumer.startup();
		
		this.producer = new LogicProducer(backtrace);
		producer.startup();
	}
	
	public void shutdown() {
		
		producer.shutdown();
		consumer.shutdown();
		
		backtrace.clear();
		System.out.println("[Backtrace Queue][Cleared] " + backtrace.size() + " " + 
				"more items in the consumer queue but execution is skipped.");
	}
}