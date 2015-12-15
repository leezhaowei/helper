package com.zwli.research.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {

    // public volatile static int count = 0;
    public static AtomicInteger count = new AtomicInteger(0);

    public static void inc() {

        // count++;
        count.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(Integer.MAX_VALUE);

        for (int i = 0; i < 1000; i++) {
            service.execute(new Runnable() {

                @Override
                public void run() {
                    inc();
                }
            });
        }

        service.shutdown();
        // 给予一个关闭时间（timeout），但是实际关闭时间应该会这个小
        service.awaitTermination(3000, TimeUnit.SECONDS);

        System.out.println("运行结果:Counter.count=" + VolatileTest.count);
    }
}
