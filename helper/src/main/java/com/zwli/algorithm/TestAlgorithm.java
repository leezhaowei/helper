package com.zwli.algorithm;

public class TestAlgorithm {

    public static void main(String[] args) {
        testNumberReverse();
    }

    static void testNumberReverse() {
        int n = 12345;
        int r = n % 10;
        System.out.println(r);
        r = n / 10;
        System.out.println(r);
        double d = n / 10D;
        System.out.println(d);
    }
}
