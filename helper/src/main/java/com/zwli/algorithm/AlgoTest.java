package com.zwli.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlgoTest {

    public static void main(String args[]) {
        String[] withDuplicates = new String[] { "one", "two", "three", "one" };
        String[] withoutDuplicates = new String[] { "one", "two", "three" };

        // System.out.println(Arrays.toString(bruteforce(withDuplicates)));
        // System.out.println(Arrays.toString(bruteforce(withoutDuplicates)));

        checkDuplicateUsingSet(withDuplicates);
        checkDuplicateUsingSet(withoutDuplicates);
    }

    public static void checkDuplicateUsingSet(String[] array) {
        List<String> list = new ArrayList<String>();
        Set<String> set = new HashSet<String>();
        for (String s : array) {
            if (!set.add(s)) {
                list.add(s);
            }
        }
        System.out.println(list);
    }

    public static String[] bruteforce(String[] array) {
        List<String> list = new ArrayList<String>();
        int listListSize = array.length;
        for (int i = 0; i < listListSize; i++) {
            String a = array[i];
            for (int j = 0; j < listListSize; j++) {
                if (i != j && a.equals(array[j])) {
                    list.add(array[j]);
                }
            }
        }
        return list.toArray(new String[0]);
    }

    public static void testBubble() {
        int[] array = new int[] { 223, 53, 61, 677, 34, 654, 123 };
        System.out.println(Arrays.toString(array));
        bubble(array);
        System.out.println(Arrays.toString(array));
    }

    public static void bubble(int[] array) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 1; j < length - i; j++) {
                if (array[j - 1] > array[j]) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
        }
    }
}
