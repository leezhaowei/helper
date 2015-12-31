package com.zwli.research.autoboxed;

public class TestInteger {

    public static void main(final String[] args) {
        testByValueOf(500);
        testByValueOf(50);
        testByNew(500);
        testByNew(50);
    }

    /**
     * In Java, values from -128 to 127 are cached, so the same objects are returned. The implementation of valueOf()
     * uses cached objects if the value is between -128 to 127.
     */
    public static void testByValueOf(final int N) {
        Integer a = N;
        Integer b = N;
        if (a == b) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    /**
     * If we explicitly create Integer objects using new operator, we get the output as “false”.
     */
    public static void testByNew(final int N) {
        Integer a = new Integer(N);
        Integer b = new Integer(N);
        if (a == b) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
