package com.zwli.research.thread.waitnotify;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerExampleWithWaitAndNotify {

    public static void main(String[] args) {
        List<Integer> taskQueue = new ArrayList<Integer>();
        int MAX_CAPACITY = 5;
        Thread tProducer = new Thread(new Producer2(taskQueue, MAX_CAPACITY), "Producer");
        Thread tConsumer = new Thread(new Consumer2(taskQueue), "Consumer");
        tProducer.start();
        tConsumer.start();
    }
}
