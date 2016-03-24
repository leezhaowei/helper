package com.zwli.algorithm;

/**
 * A number is called prime number if its not divisible by any number other than 1 or itself and you can use this logic
 * to check whether a number is prime or not. This program is slightly difficult than printing even or odd number which
 * are relatively easier Java exercises. This Simple Java program print prime number starting from 1 to 100 or any
 * specified number. It also has a method which checks if a number is prime or not.
 */
public class PrimeNumber {

    public static void main(String args[]) {
        System.out.println("Enter the number till which prime number to be printed: ");
        int limit = 200;

        System.out.println("Printing prime number from 1 to " + limit);
        for (int number = 2; number <= limit; number++) {
            if (isPrime(number)) {
                System.out.print(number + " ");
            }
        }
    }

    /**
     * Prime number is not divisible by any number other than 1 and itself
     *
     * @return true if number is prime
     */
    public static boolean isPrime(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
