package com.zwli.algorithm;

/**
 * Write a program to identify common elements or numbers between two given arrays. You should not use any inbuilt
 * methods are list to find common values.
 */
public class CommonElementsInArray {

    public static void main(String a[]) {
        int[] arr1 = { 4, 7, 3, 9, 2 };
        int[] arr2 = { 3, 2, 12, 9, 40, 32, 4 };
        for (int e1 : arr1) {
            for (int e2 : arr2) {
                if (e1 == e2) {
                    System.out.println(e1);
                }
            }
        }
    }
}
