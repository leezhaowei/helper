package com.zwli.research.thread.threadpool;

public class TwoThreads {
	public static class Thread1 extends Thread {
		@Override
		public void run() {
			System.out.println("A");
			System.out.println("B");
		}
	}

	public static class Thread2 extends Thread {
		@Override
		public void run() {
			System.out.println("1");
			System.out.println("2");
		}
	}

	public static void main(String[] args) {
		// new Thread1().start();
		// new Thread2().start();
		Thread t1 = new Thread1();
		Thread t2 = new Thread2();
		t1.start();
		t2.start();

		try {
			Thread.sleep(1000 * 1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String t1s = t1.getName();
		String t2s = t2.getName();
		System.out.println("Thread1 Name: " + t1s);
		System.out.println("Thread2 Name: " + t2s);
	}

}
