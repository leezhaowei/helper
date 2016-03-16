package com.zwli.research.thread.waitnotify;

import java.util.ArrayList;
import java.util.List;

public class Consumer2 implements Runnable {

    private final List<Integer> taskQueue;

    public Consumer2(final List<Integer> sharedQueue) {
        this.taskQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void consume() throws InterruptedException {
        synchronized (taskQueue) {
            while (taskQueue.isEmpty()) {
                System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting , size: "
                        + taskQueue.size());
                taskQueue.wait();
            }
            Thread.sleep(1000);
            int i = taskQueue.remove(0);
            System.out.println("Consumed: " + i);
            taskQueue.notifyAll();
        }
    }

    public static void main(final String[] args) {
        List<Integer> taskQueue = new ArrayList<Integer>();
        final int MAX_CAPACITY = 5;
        Thread tProducer = new Thread(new Producer2(taskQueue, MAX_CAPACITY), "Producer");
        Thread tConsumer = new Thread(new Consumer2(taskQueue), "Consumer");
        tProducer.start();
        tConsumer.start();
    }
}
