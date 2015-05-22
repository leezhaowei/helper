package com.zwli.string;

/**
 * 加减乘除、位运算等操作<br>
 * 创建日期：2007-11-22
 * 
 * @author 李赵伟
 */
public class Operation {

	public static void main(String[] args) {
	}

	// ------------------------------------------------------------------------
	// 如何用位运算实现下面的要求？
	// 由1得到1
	// 由2得到2
	// 由3得到1+2
	// 由4得到4
	// 由5得到1+4
	// 由6得到2+4
	// 由7得到1+2+4
	// 由8得到8
	// 由9得到1+8
	// 由10得到2+8
	// 由11得到1+2+8
	// 由12得到4+8
	// 由13得到1+4+8
	// ......
	// 也就是说，给定一个正整数，将它转换成若干个2的非负整数次幂的和，并要求这些幂不能重复。
	// ------------------------------------------------------------------------
	/**
	 * 只需要移位+判断就行了。<br>
	 * 整数是32位的，所以先定义一个32位的掩码 final int mask = 0x00000001<br>
	 * 用这个掩码与你的数相与，比如 int r = n & mask;<br>
	 * 判断结果是否为0，如果不是0，记下它是第几次移位（目前为0次）<br>
	 * 然后将n右移1位，重复上面的操作，直接n右移之后为0为止。<br>
	 * 
	 * @param num
	 */
	static void printBit(int num) {
		int mask = 1;
		System.out.print(num + " = ");
		while (true) {
			boolean flag = false;
			if ((num & mask) != 0) {
				flag = true;
				System.out.print(mask);
			}
			mask <<= 1;
			if (mask > num) {
				System.out.println();
				break;
			}
			if (flag) {
				System.out.print(" + ");
			}
		}
	}
}
