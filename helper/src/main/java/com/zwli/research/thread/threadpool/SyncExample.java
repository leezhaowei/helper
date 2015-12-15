package com.zwli.research.thread.threadpool;

public class SyncExample {
	private static Object lockObject = new Object();

	static int x, y;

	private static class Thread1 extends Thread {
		@Override
		public void run() {
			synchronized (lockObject) {
				x = y = 0;
				System.out.print(x + " ");
			}
		}
	}

	private static class Thread2 extends Thread {
		@Override
		public void run() {
			synchronized (lockObject) {
				x = y = 1;
				System.out.println(y);
			}
		}
	}

	public static void main(String[] args) {
		// new Thread1().run();
		// new Thread2().run();
		for (int i = 0; i < 100; i++) {
			new Thread1().run();
			new Thread2().run();
			System.out.println("########################################");
		}
	}

}
