package com.zwli.algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * There are 50 persons standing in a circle for a game, those who count the number 3 or multiple of 3 should leave from
 * the circle, who is the last one in the circle? Please implement a class to get the person's original position in the
 * circle.
 * </p>
 */
public class Circle {

    public static int circle(final int total, final int divisor) {
        final List<Integer> circleList = new LinkedList<Integer>();
        for (int i = 0; i < total; i++) {
            circleList.add(i + 1);
        }
        int index = -1;
        while (circleList.size() > 1) {
            index = (index + divisor) % circleList.size();
            circleList.remove(index--);
        }
        return circleList.get(0);
    }

    public static void main(final String[] args) {
        System.out.println(circle(50, 3));
    }
}
