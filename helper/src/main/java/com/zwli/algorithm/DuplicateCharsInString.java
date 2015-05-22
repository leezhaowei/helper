package com.zwli.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Write a program to find out duplicate or repeated characters in a string, and calculate the count of repeatation.
 */
public class DuplicateCharsInString {

    public void findDuplicateChars(String input) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        char[] chrs = input.toCharArray();
        for (Character c : chrs) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        Set<Character> keys = map.keySet();
        for (Character ch : keys) {
            if (map.get(ch) > 1) {
                System.out.println(ch + "--->" + map.get(ch));
            }
        }
    }

    public static void main(String a[]) {
        DuplicateCharsInString dcs = new DuplicateCharsInString();
        dcs.findDuplicateChars("Java2Novice");
    }
}
