package com.zwli.threadpool;

import java.util.Random;

/**
 * Creates ten threads to search for the maximum value of a large matrix. Each
 * thread searches one portion of the matrix.
 */
public class TenThreads {

	private static class WorkerThread extends Thread {
		int max = Integer.MIN_VALUE;
		int[] ourArray;

		public WorkerThread(int[] ourArray) {
			this.ourArray = ourArray;
		}

		// Find the maximum value in our particular piece of the array
		@Override
		public void run() {
			for (int element : ourArray) {
				max = Math.max(max, element);
			}
		}

		public int getMax() {
			return max;
		}
	}

	static int[][] getBigHairyMatrix() {
		int[][] ii = new int[1000][1000];
		for (int i = 0; i < ii.length; i++) {
			ii[i] = get();
		}

		// for (int i = 0; i < ii.length; i++) {
		// System.out.print(i + ", ");
		// for (int j = 0; j < ii[i].length; j++) {
		// System.out.print(ii[i][j] + " ");
		// }
		// System.out.println();
		// }

		return ii;
	}

	static int[] get() {
		final int N = 1000000;
		int[] ii = new int[10];
		Random r = new Random();
		for (int i = 0; i < ii.length; i++) {
			ii[i] = r.nextInt(N);
		}
		return ii;
	}

	public static void main(String[] args) {
		WorkerThread[] threads = new WorkerThread[10];
		int[][] bigMatrix = getBigHairyMatrix();
		int max = Integer.MIN_VALUE;

		// Give each thread a slice of the matrix to work with
		for (int i = 0; i < 10; i++) {
			threads[i] = new WorkerThread(bigMatrix[i]);
			threads[i].start();
		}

		// Wait for each thread to finish
		try {
			for (int i = 0; i < 10; i++) {
				threads[i].join();
				max = Math.max(max, threads[i].getMax());
			}
		} catch (InterruptedException e) {
			// fall through
		}

		System.out.println("Maximum value was " + max);
	}
}
