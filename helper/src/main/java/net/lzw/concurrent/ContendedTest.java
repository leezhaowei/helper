package net.lzw.concurrent;

public class ContendedTest extends Thread {

	private static class DataHolder {
		private volatile long l1 = 0;
		private volatile long l2 = 0;
		private volatile long l3 = 0;
		private volatile long l4 = 0;
	}

	private static DataHolder dh = new DataHolder();
	private static long nLoops;

	public ContendedTest(Runnable r) {
		super(r);
	}

	public static void main(String[] args) throws Exception {
		// nLoops = Long.parseLong(args[0]);
		nLoops = 100000000;

		ContendedTest[] tests = new ContendedTest[4];
		tests[0] = new ContendedTest(() -> {
			for (long i = 0; i < nLoops; i++) {
				dh.l1 += i;
			}
		});
		tests[1] = new ContendedTest(() -> {
			for (long i = 0; i < nLoops; i++) {
				dh.l2 += i;
			}
		});
		tests[2] = new ContendedTest(() -> {
			for (long i = 0; i < nLoops; i++) {
				dh.l3 += i;
			}
		});
		tests[3] = new ContendedTest(() -> {
			for (long i = 0; i < nLoops; i++) {
				dh.l4 += i;
			}
		});

		long then = System.currentTimeMillis();
		for (ContendedTest ct : tests) {
			ct.start();
		}
		for (ContendedTest ct : tests) {
			ct.join();
		}
		long now = System.currentTimeMillis();
		System.out.println("Duration: " + (now - then) + " ms");

	}
}
