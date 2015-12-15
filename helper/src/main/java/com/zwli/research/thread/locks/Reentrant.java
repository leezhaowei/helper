package com.zwli.research.thread.locks;

public class Reentrant {

    public static void main(String[] args) {
        test();
    }

    static void test() {
        Reentrant r = new Reentrant();
        try {
            r.outer();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private Lock lock = new Lock();

    public synchronized void outer() throws InterruptedException {
        lock.lock();
        try {
            inner();
        } finally {
            lock.unlock();
        }
    }

    public synchronized void inner() throws InterruptedException {
        lock.lock();
        try {
            // do something
            System.out.println("Hello World!");
        } finally {
            lock.unlock();
        }
    }

}
