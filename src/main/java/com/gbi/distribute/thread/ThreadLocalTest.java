package com.gbi.distribute.thread;

import java.util.Random;

public class ThreadLocalTest {
	
	private static ThreadLocal<Integer> x = new ThreadLocal<>();
	
	private static class A {
		public static void get() {
			Integer data = x.get();
			System.out.println("A:" + Thread.currentThread() + ">" + data);
		}
	}

	private static class B {
		public static void get() {
			Integer data = x.get();
			System.out.println("B:" + Thread.currentThread() + ">" + data);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 2; ++i) {
			new Thread() {
				@Override
				public void run() {
					Integer data = new Random().nextInt(100);
					System.out.println(Thread.currentThread().getName() + ":" + data);
					x.set(data);
					A.get();
					B.get();
				};
			}.start();
		}
	}
}
