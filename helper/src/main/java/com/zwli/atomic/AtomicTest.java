package com.zwli.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    public static void main(String[] args) {
        a();
    }

    static void a() {
        AtomicInteger ai = new AtomicInteger(1);
        System.out.println(ai);
        ai.compareAndSet(10, 2);
        System.out.println(ai);
        ai.compareAndSet(1, 2);
        System.out.println(ai);
    }
}
