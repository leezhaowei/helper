package com.zwli.algorithm;

public class MarsagliaPolarRandom {

	private static double spare;
	private static boolean isSpareReady = false;

	public static synchronized double getGaussian(double mean, double stdDev) {
		if (isSpareReady) {
			isSpareReady = false;
			return spare * stdDev + mean;
		} else {
			double u, v, s;
			do {
				u = Math.random() * 2 - 1;
				v = Math.random() * 2 - 1;
				s = u * u + v * v;
			} while (s >= 1 || s == 0);
			double mul = Math.sqrt(-2.0 * Math.log(s) / s);
			spare = v * mul;
			isSpareReady = true;
			return mean + stdDev * u * mul;
		}
	}

	public static void main(String[] args) {

		double mean = 1D;
		double stdDev = 100D;
		for (int i = 0; i < 100; i++) {
			double result = getGaussian(mean, stdDev);
			System.out.println(result);
		}

	}

}
