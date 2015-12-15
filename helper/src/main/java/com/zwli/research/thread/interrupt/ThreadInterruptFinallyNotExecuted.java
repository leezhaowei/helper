package com.zwli.research.thread.interrupt;

public class ThreadInterruptFinallyNotExecuted implements Runnable {

    public static void main(String[] args) {
        Thread t = new Thread(new ThreadInterruptFinallyNotExecuted());
        t.start();
        t.interrupt();
        // System.exit(-1);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Finally executed.");
        }
    }
}
