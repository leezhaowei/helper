package com.zwli.util;

/**
 * 某些数列前n项和
 * 
 * @author 李赵伟 Create: 11:55:05 PM Mar 5, 2008
 */
public class Arithmetic {
	public static void main(String args[]) {
		test();
	}

	static void test() {
		int n = 10;
		int r = sum1(n);
		System.out.println(r);
		r = sum2(n);
		System.out.println(r);
		r = sum3(n);
		System.out.println(r);
		r = sum4(n);
		System.out.println(r);
		r = sum5(n);
		System.out.println(r);
		r = sum6(n);
		System.out.println(r);
	}

	/**
	 * 1+2+3+4+5+6+7+8+9+…+n = n(n+1)/2
	 * 
	 * @param n
	 * @return
	 */
	public static int sum1(final int n) {
		return (n * (n + 1) / 2);
	}

	/**
	 * 1+3+5+7+9+11+13+15+…+(2n-1) = n^2
	 * 
	 * @param n
	 * @return
	 */
	public static int sum2(final int n) {
		return (int) (Math.pow(n, 2));
	}

	/**
	 * 2+4+6+8+10+12+14+…+(2n) = n(n+1)
	 * 
	 * @param n
	 * @return
	 */
	public static int sum3(final int n) {
		return (n * (n + 1));
	}

	/**
	 * 12+22+32+42+52+62+72+82+…+n2 = n(n+1)(2n+1)/6
	 * 
	 * @param n
	 * @return
	 */
	public static int sum4(final int n) {
		return (n * (n + 1) * (2 * n + 1) / 6);
	}

	/**
	 * 13+23+33+43+53+63+…n3 = n2(n+1)2/4
	 * 
	 * @param n
	 * @return
	 */
	public static int sum5(final int n) {
		return (n * 2 * (n + 1) * 2 / 4);
	}

	/**
	 * 1*2+2*3+3*4+4*5+5*6+6*7+…+n(n+1) = n(n+1)(n+2)/3
	 * 
	 * @param n
	 * @return
	 */
	public static int sum6(final int n) {
		return (n * (n + 1) * (n + 2) / 3);
	}
}
