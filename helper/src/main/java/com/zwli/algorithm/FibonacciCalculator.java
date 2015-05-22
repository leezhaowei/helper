package com.zwli.algorithm;

/**
 * Fibonacci series is series of natural number where next number is equivalent to sum of previous two number e.g. fn =
 * fn-1 + fn-2. First two numbers of Fibonacci series is always 1, 1. In this Java program example for Fibonacci series
 * we create function to calculate Fibonacci number and then print those numbers on Java console. <br>
 *
 * Java program to calculate and print Fibonacci number using both recursion and Iteration. Fibonacci number is sum of
 * previous two Fibonacci numbers fn= fn-1+ fn-2 first 10 Fibonacci numbers are 1, 1, 2, 3, 5, 8, 13, 21, 34, 55
 */
public class FibonacciCalculator {

    public static void main(String args[]) {
        // input to print Fibonacci series upto how many numbers
        System.out.println("Enter number upto which Fibonacci series to print: ");
        int number = 12;

        System.out.println("Fibonacci series upto " + number + " numbers : ");
        // printing Fibonacci series upto number
        for (int i = 1; i <= number; i++) {
            System.out.print(fibonacci2(i) + " ");
        }
    }

    /**
     * Java program for Fibonacci number using recursion. This program uses tail recursion to calculate Fibonacci number
     * for a given number
     */
    public static int fibonacci(int number) {
        if (number == 1 || number == 2) {
            return 1;
        }
        return fibonacci(number - 1) + fibonacci(number - 2); // tail recursion
    }

    /**
     * Java program to calculate Fibonacci number using loop or Iteration.
     */
    public static int fibonacci2(int number) {
        if (number == 1 || number == 2) {
            return 1;
        }
        int fibo1 = 1, fibo2 = 1, fibonacci = 1;
        for (int i = 3; i <= number; i++) {
            // Fibonacci number is sum of previous two Fibonacci number
            fibonacci = fibo1 + fibo2;
            fibo1 = fibo2;
            fibo2 = fibonacci;
        }
        return fibonacci;
    }

    // Fibonacci Number in Java with Memoization
    // Here is the code example of printing Fibonacci number with memoization technique :
    //
    // /*
    // * Java Program to calculate Fibonacci numbers with memorization
    // * This is quite fast as compared to previous Fibonacci function
    // * especially for calculating factorial of large numbers.
    // */
    // public static int improvedFibo(int number){
    // Integer fibonacci = cache.get(number);
    // if(fibonacci != null){
    // return fibonacci; //fibonacci number from cache
    // }
    // //fibonacci number not in cache, calculating it
    // fibonacci = fibonacci2(number);
    //
    // //putting fibonacci number in cache for future request
    // cache.put(number, fibonacci);
    // return fibonacci;
    // }
    //
    // Performance Comparison
    // //comparison of performance of Fibonacci number with memorization
    // int number = 100000000;
    // long startTime = System.nanoTime();
    // int result = fibonacci2(number); //fibonacci number with memorization
    // long elapsedTime = System.nanoTime() - startTime;
    // System.out.println("Time taken to calculate Fibonacci number upto 100M without memorization:" + elapsedTime);
    //
    // startTime = System.nanoTime();
    // result = improvedFibo(number); //Fibonacci number with memorization
    // elapsedTime = System.nanoTime() - startTime;
    // System.out.println("Time taken to calculate Fibonacci number upto 100M with memorization:" + elapsedTime);
    //
    // Output:
    // Time taken to calculate Fibonacci number upto 100M without memorization:149479613
    // Time taken to calculate Fibonacci number upto 100M with memorization:118972384
}
