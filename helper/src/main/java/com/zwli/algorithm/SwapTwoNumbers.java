package com.zwli.algorithm;

/**
 * Swapping two numbers without using temp variable
 */
public class SwapTwoNumbers {

	public static void arithmetic(int a, int b) {
		// int a = 10;
		// int b = 20;

		System.out.println("value of a and b before swapping, a: " + a + " b: " + b);

		// swapping value of two numbers without using temp variable
		a = a + b; // now a is 30 and b is 20
		b = a - b; // now a is 30 but b is 10 (original value of a)
		a = a - b; // now a is 20 and b is 10, numbers are swapped

		System.out.println("value of a and b after swapping, a: " + a + " b: " + b);
	}

	/**
	 * Bitwise operators can also be used to swap two numbers without using third variable. XOR bitwise operator returns zero if both
	 * operand is same i.e. either 0 or 1 and returns 1 if both operands are different e.g. one operand is zero and other is one. By
	 * leveraging this property, we can swap two numbers in Java. Here is code example of swapping two numbers without using temp variable
	 * in Java using XOR bitwise operand: <br>
	 * <table border="1" width="300" cellspacing="0">
	 * <tr>
	 * <td width="10%">A</td>
	 * <td width="10%">B</td>
	 * <td>A^B (A XOR B)</td>
	 * </tr>
	 * <tr>
	 * <td>0</td>
	 * <td>0</td>
	 * <td>0 (zero because operands are same)</td>
	 * </tr>
	 * <tr>
	 * <td>0</td>
	 * <td>1</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>1</td>
	 * <td>0</td>
	 * <td>1 (one because operands are different)</td>
	 * </tr>
	 * <tr>
	 * <td>1</td>
	 * <td>1</td>
	 * <td>0</td>
	 * </tr>
	 * </table>
	 */
	public static void bitwise(int a, int b) {
		// int a = 2; //0010 in binary
		// int b = 4; //0100 in binary

		System.out.println("value of a and b before swapping, a: " + a + " b: " + b);

		// swapping value of two numbers without using temp variable and XOR bitwise operator
		a = a ^ b; // now a is 6 and b is 4
		b = a ^ b; // now a is 6 but b is 2 (original value of a)
		a = a ^ b; // now a is 4 and b is 2, numbers are swapped

		System.out.println("value of a and b after swapping using XOR bitwise operation, a: " + a + " b: " + b);
	}

	/**
	 * This is similar to first approach, where we have used + and - operator for swapping values of two numbers. Here is the code example
	 * to swap tow number without using third variable with division and multiplication operators in Java
	 */
	public static void divisionAndMultiplication(int a, int b) {
		// int a = 6;
		// int b = 3;

		System.out.println("value of a and b before swapping, a: " + a + " b: " + b);

		// swapping value of two numbers without using temp variable using multiplication and division
		a = a * b; // now a is 18 and b is 3
		b = a / b; // now a is 18 but b is 6 (original value of a)
		a = a / b; // now a is 3 and b is 6, numbers are swapped

		System.out.println("value of a and b after swapping using multiplication and division, a: " + a + " b: " + b);
	}

	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		arithmetic(a, b);

		a = 2;
		b = 4;
		bitwise(a, b);

		a = 6;
		b = 3;
		divisionAndMultiplication(a, b);
	}
}
