package com.zwli.errorexample;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class IntegerTest {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<Integer>();
        List<Integer> list = new ArrayList<Integer>();

        for (int i = -3; i < 3; i++) {
            set.add(i);
            list.add(i);
        }
        System.out.println(set + " " + list);
        for (int i = 0; i < 3; i++) {
            set.remove(i);
            // list.remove((Integer)i); // this is correct way to remove an element
            list.remove(i); // should remove an element not the one pointed by index
            // when i = 0, list = [-3, -2, -1, 0, 1, 2], index 0 => -3; Removed;
            // when i = 1, list = [-2, -1, 0, 1, 2], index 1 => -1; Removed;
            // when i = 2, list = [-2, 0, 1, 2], index 1 => 1; Removed;
            // Finally list = [-2, 0, 2]
        }
        System.out.println(set + " " + list);
    }
}
