package com.zwli.algorithm;

import java.util.Random;

public class NumberToWords {
	private static final String[] lowNames = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
	        "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };

	private static final String[] tensNames = { "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };

	private static final String[] bigNames = { "thousand", "million", "billion" };

	public static String convert(int n) {
		if (n < 0) {
			return "minus " + convert(-n);
		}
		if (n <= 999) {
			return convert999(n);
		}
		String s1 = null;
		int t = 0;
		while (n > 0) {
			if (n % 1000 != 0) {
				String s2 = convert999(n % 1000);
				if (t > 0) {
					s2 = s2 + " " + bigNames[t - 1];
				}
				if (s1 == null) {
					s1 = s2;
				} else {
					s1 = s2 + ", " + s1;
				}
			}
			n /= 1000;
			t++;
		}
		return s1;
	}

	// Range 0 to 999.
	private static String convert999(int n) {
		String s1 = lowNames[n / 100] + " hundred";
		String s2 = convert99(n % 100);
		if (n <= 99) {
			return s2;
		} else if (n % 100 == 0) {
			return s1;
		} else {
			return s1 + " " + s2;
		}
	}

	// Range 0 to 99.
	private static String convert99(int n) {
		if (n < 20) {
			return lowNames[n];
		}
		String s = tensNames[n / 10 - 2];
		if (n % 10 == 0) {
			return s;
		}
		return s + "-" + lowNames[n % 10];
	}

	public static void main(String[] args) {
		Random random = new Random();
		int N = 1234567890;
		int len = 1;
		int o;
		String s;
		for (int i = 0; i < len; i++) {
			o = random.nextInt(N);
			s = NumberToWords.convert(o);
			System.out.println(o + " => " + s);
		}
	}
}
