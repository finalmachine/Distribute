package com.gbi.distribute.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

	static class TestRun implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 10; ++i) {
				System.out.println(Thread.currentThread().getName() + " loop of " + i);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void FixedThreadPoolTest() {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 20; ++i) {
			threadPool.execute(new TestRun());
			System.out.println("-----------");
		}
		threadPool.shutdown();
	}
	
	public static void CachedThreadPoolTest() {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 20; ++i) {
			threadPool.execute(new TestRun());
			System.out.println("-----------");
		}
		threadPool.shutdown();
	}

	public static void main(String[] args) {
		CachedThreadPoolTest();
	}
}
