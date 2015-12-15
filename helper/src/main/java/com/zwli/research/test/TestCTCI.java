package com.zwli.research.test;

/**
 * Craking the coding interview<br/>
 * Created by zwli on 2015/11/30
 */
public class TestCTCI {

    public static void main(String[] args) {
        // testIsUniqueChars();
        // testRemoveDuplicates();
        // testAnagram();
        // testReplaceFun();
    }



    public static void testReplaceFun() {
        String s = "Welcome to Beijing.";
        char[] str = s.toCharArray();
        int length = str.length;
        replaceFun(str, length);
        System.out.println(String.valueOf(str));
    }

    public static void replaceFun(char[] str, int length) {
        int spaceCount = 0, newLength, i = 0;
        for (i = 0; i < length; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }
        newLength = length + spaceCount * 2;
        str[newLength] = '\0';
        for (i = length - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[newLength - 1] = '0';
                str[newLength - 2] = '2';
                str[newLength - 3] = '%';
                newLength = newLength - 3;
            } else {
                str[newLength - 1] = str[i];
                newLength = newLength - 1;
            }
        }
    }

    public static void testAnagram() {
        String s = "abcdefg";
        String t = "bcfagde";
        boolean r = anagram(s, t);
        System.out.println(r);
    }

    public static boolean anagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] letters = new int[256];
        int num_unique_chars = 0;
        int num_completed_t = 0;
        char[] s_array = s.toCharArray();
        for (char c : s_array) { // count number of each char in s.
            if (letters[c] == 0) {
                ++num_unique_chars;
            }
            ++letters[c];
        }
        for (int i = 0; i < t.length(); ++i) {
            int c = t.charAt(i);
            if (letters[c] == 0) { // Found more of char c in t than in s.
                return false;
            }
            --letters[c];
            if (letters[c] == 0) {
                ++num_completed_t;
                if (num_completed_t == num_unique_chars) {
                    // it’s a match if t has been processed completely
                    return i == t.length() - 1;
                }
            }
        }
        return false;
    }

    public static void testRemoveDuplicates() {
        String s = "Implement an algorithm to determine if a string has all unique characters. What if you can not use additional data structures"
                .toLowerCase().replaceAll("\\s", "");
        s = "Implement an algorithm to determine ".toLowerCase().replaceAll("\\s", "");
        System.out.println(s);
        char[] target = s.toCharArray();
        removeDuplicates(target);
        System.out.println(String.valueOf(target));
    }

    public static void removeDuplicates(char[] str) {
        if (str == null) {
            return;
        }
        int len = str.length;
        if (len < 2) {
            return;
        }

        int tail = 1;

        for (int i = 1; i < len; ++i) {
            int j;
            for (j = 0; j < tail; ++j) {
                if (str[i] == str[j]) {
                    break;
                }
            }
            if (j == tail) {
                str[tail] = str[i];
                ++tail;
            }
        }
        str[tail] = 0;
    }

    public static void testIsUniqueChars() {
        String s = "abcdefghigk";
        s = "We can reduce our space usage a little bit by using a bit vector. We will assume, in the below code, that the string is only lower case 'a' through 'z'. This will allow us to use just a single int."
                .toLowerCase();
        boolean isUniqueChars = isUniqueChars(s);
        isUniqueChars = isUniqueChars2(s);
        System.out.println(isUniqueChars);
    }

    public static boolean isUniqueChars2(String str) {
        boolean[] char_set = new boolean[256];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    public static boolean isUniqueChars(String str) {
        int checker = 0;
        for (int i = 0; i < str.length(); ++i) {
            int val = str.charAt(i) - 'a';
            int x = 1 << val;
            int y = checker & x;
            if (y > 0) {
                System.out.println(val);
                return false;
            }
            checker |= x;

            // if ((checker & (1 << val)) > 0) {
            // return false;
            // }
            // checker |= (1 << val);
        }
        return true;
    }
}
