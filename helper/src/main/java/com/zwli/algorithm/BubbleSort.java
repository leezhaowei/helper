package com.zwli.algorithm;

import java.util.Arrays;

/**
 * In Bubble sort algorithm we sort an unsorted array by starting from first element and comparing with adjacent
 * element. If former is greater than later then we swap and by doing this we get the largest number at the end after
 * first iteration. So in order to sort n elements you require n-1 iteration and almost n-1 comparison. For recap here
 * is the logic for bubble sort sorting algorithm : <br>
 * <br>
 * 1) start comparing a[0] to a[1] <br>
 * 2) if a[0] > a[1] then swap numbers e.g. a[0]=a[1] and a[1]=a[0] <br>
 * 3) compare a[1] to a[2] and repeat till you compare last pair <br>
 * 4) This is referred as one pass and at the end of first pass largest number is at last <br>
 * 5) repeat this comparison again starting from a[0] but this time going till second last pair only
 */
public class BubbleSort {

    public static void main(String args[]) {
        // testing our bubble sort method in Java
        int[] unsorted = { 32, 39, 21, 45, 23, 3 };
        bubbleSort(unsorted);

        // one more testing of our bubble sort code logic in Java
        int[] test = { 5, 3, 2, 1 };
        bubbleSort(test);
    }

    /*
     * In bubble sort we need n-1 iteration to sort n elements at end of first iteration larget number is sorted and
     * subsequently numbers smaller than that.
     */
    public static void bubbleSort(int[] unsorted) {
        System.out.println("unsorted array before sorting : " + Arrays.toString(unsorted));
        // Outer loop - need n-1 iteration to sort n elements
        for (int i = 0; i < unsorted.length - 1; i++) {
            // Inner loop to perform comparision and swapping between adjacent numbers
            // After each iteration one index from last is sorted
            for (int j = 1; j < unsorted.length - i; j++) {
                // If current number is greater than swap those two
                if (unsorted[j - 1] > unsorted[j]) {
                    int temp = unsorted[j];
                    unsorted[j] = unsorted[j - 1];
                    unsorted[j - 1] = temp;
                }
            }
            System.out.printf("unsorted array after %d pass %s: %n", i + 1, Arrays.toString(unsorted));
        }
    }
}
