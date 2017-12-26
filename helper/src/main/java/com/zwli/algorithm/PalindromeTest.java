package com.zwli.algorithm;

/**
 * A number is said to be a palindrome if number itself is equal to reverse of number e.g. 313 is a palindrome because reverse of this
 * number is also 313. On the other hand 123 is not a palindrome because reverse of 123 is 321 which is not equal to 123, i.e. original
 * number. In order to check if a number is a palindrome or not we can reuse the logic of How to reverse number in Java. Since in most of
 * interview, you are supposed to solve this question without taking help from API i.e. only using basic programming construct e.g. loop,
 * conditional statement, variables, operators and logic. I have also seen programmer solving this question by first converting integer to
 * String and than reversing String using reverse() method of StringBuffer and than converting String back to Integer, which is not a
 * correct way because you are using Java API. Some programmer may think that this is just a trivial programming exercise but it’s not.
 * Questions like this or Fibonacci series using recursion can easily separate programmers who can code and who can’t. So it’s always in
 * best interest to keep doing programing exercise and developing logic.<br>
 *
 * How to check if a number is palindrome or not in Java with ExampleHere is a simple Java program which finds if a number is a palindrome
 * or not. This program does not use any API method instead it uses division and remainder operator of Java programming language to
 * determine if number is palindrome or not. Programming logic to reverse a number is encapsulate in reverse() method and isPalindrome(int
 * number) reuse that logic to test if a number is palindrome or not.<br>
 */
public class PalindromeTest {

	public static void main(String args[]) {
		int[] numbers = { 1, 20, 22, 102, 101, 1221, 13321, 13331, 0, 11 };
		for (int number : numbers) {
			System.out.println("Does number : " + number + " is a palindrome? " + isPalindrome(number));
		}
	}

	private static boolean isPalindrome(int number) {
		if (number == reverse(number)) {
			return true;
		}
		return false;
	}

	private static int reverse(int number) {
		int reverse = 0;
		while (number != 0) {
			reverse = reverse * 10 + number % 10;
			number = number / 10;
		}
		return reverse;
	}
}
