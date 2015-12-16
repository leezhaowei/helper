package com.zwli.string;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zwli Create: 1:55:17 PM Mar 12, 2008
 */
public class TestUuid {

    public static void main(String[] args) {
        test();
    }

    static final int THERAD_SIZE = 10;

    static void test() {
        ExecutorService pool = Executors.newFixedThreadPool(THERAD_SIZE);
        CreatIDTask fet = null;
        int size = 1000;
        for (int i = 0; i < size; i++) {
            fet = new CreatIDTask();
            pool.submit(fet);
        }
        pool.shutdown();
    }
}

class CreatIDTask implements Runnable {
    @Override
    public void run() {
        System.out.println(UtilStr.uuid(27));
    }
}
