package com.zwli.number;

public class NumberTest {

    public static void main(String[] args) {
        cronTriggerMaker();
    }

    private static void cronTriggerMaker() {
        int n = 60;
        for (int i = 0; i < n; i++) {
            if (i % 5 == 0) {
                System.out.print(i + ",");
            }
        }
    }
}
