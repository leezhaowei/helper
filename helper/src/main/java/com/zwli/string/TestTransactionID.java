package com.zwli.string;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zwli Create: 1:55:17 PM Mar 12, 2008
 */
public class TestTransactionID {

    public static void main(String[] args) {
        test();
    }

    static final int THERAD_SIZE = 10; // 线程池大小

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

    public synchronized static String getTransactionID(int len) {
        if (len == 0) {
            len = 18;
        }
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer id = new StringBuffer();
        int index = 0;
        Random x = new Random(); // default seed is time in milliseconds
        final int N = chars.length();
        for (int i = 0; i < len; i++) { // generator six bit random astring;
            index = x.nextInt(N) + 1;
            id.append(chars.substring(index, (index + 1)));
        }
        return id.toString();
    }
}

class CreatIDTask implements Runnable {
    @Override
    public void run() {
        System.out.println("ID = " + TestTransactionID.getTransactionID(27));
    }
}
