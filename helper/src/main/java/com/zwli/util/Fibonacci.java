package com.zwli.util;

/**
 * 动态编程 是在序列分析中经常使用的一种算法技术。在可以使用递归，但因为递归重复解决相同的子问题造成效率低下的时候，<br>
 * 则可以采用动态编程。<br>
 * 例如，请看斐波纳契（Fibonacci）序列：0, 1, 1, 2, 3, 5, 8, 13, ... 第一个和第二个斐波纳契数字 分别定义为 0 和<br>
 * 1。第 n 个斐波纳契数字是前两个斐波纳契数字的和。
 * 
 * @author 李赵伟 Create: 11:12:01 AM Apr 25, 2008
 */
public class Fibonacci {
	public static void main(String args[]) {
		test();
	}

	static void test() {
		int n = 10;
		System.out.print(fibonacci2(n));
		// long s = System.currentTimeMillis();
		// for (int i = 0; i <= n; i++) {
		// // System.out.print(fibonacci1(i) + ", ");
		// System.out.print(fibonacci2(i) + ", ");
		// }
		// System.out.println();
		// long e = System.currentTimeMillis() - s;
		// System.out.println("Time: " + e);
	}

	/**
	 * 递归函数计算第 n 个斐波纳契数<br>
	 * 递归函数代码效率不高，因为它重复地解决相同的递归子问题
	 * 
	 * @param n
	 * @return
	 */
	public static long fibonacci1(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return fibonacci1(n - 1) + fibonacci1(n - 2);
		}
	}

	/**
	 * 从下往上计算斐波纳契数<br>
	 * 将中间结果保存在表格中，这样就能重复使用它们，而不是在抛弃之后再重复计算多次。确实，存储表格的内存使用效率较低，<br>
	 * 因为一次只使用表格中的两个条目，但是现在暂且将这件事放在一边。我们将在本文中使用相似的表格（不过是二维表格）处理<br>
	 * 比清单 1 复杂得多的示例。清单 2 中的实现所需的时间比清单 1 短许多，清单 2 的运行时间为 O(n)，而清单 1 中的递<br>
	 * 归实现的运行时间是 n 的指数。<br>
	 * <br>
	 * <br>
	 * 这就是动态编程的工作原理。遇到一个能用递归算法从上向下解决的问题，然后用从下向上迭代的方式解决。将中间结果保存在<br>
	 * 表格中供后续步骤使用；否则，需要重复计算它们 — 这显然是一种效率很低的算法。但是动态编程通常被用于最优化问题<br>
	 * （比如本文后 面的示例），而不是像斐波纳契数这样的问题。下面的示例是一个字符串算法，与计算生物学中经常使用的算法相似。
	 * 
	 * @param n
	 * @return
	 */
	public static int fibonacci2(int n) {
		int[] table = new int[n + 1];
		for (int i = 0; i < table.length; i++) {
			if (i == 0) {
				table[i] = 0;
			} else if (i == 1) {
				table[i] = 1;
			} else {
				table[i] = table[i - 2] + table[i - 1];
			}
		}
		return table[n];
	}
}
