package com.zwli.algorithm;

/**
 * Deadlock describes a situation where two or more threads are blocked forever, waiting for each other. Deadlocks can
 * occur in Java when the synchronized keyword causes the executing thread to block while waiting to get the lock,
 * associated with the specified object. Since the thread might already hold locks associated with other objects, two
 * threads could each be waiting for the other to release a lock. In such case, they will end up waiting forever.
 */
public class Deadlock {

    String lock1 = "Java";

    String lock2 = "UNIX";

    Thread t1 = new Thread("Thread 1") {

        @Override
        public void run() {
            while (true) {
                synchronized (lock1) {
                    synchronized (lock2) {
                        System.out.println("# " + lock1 + "/" + lock2);
                    }
                }
            }
        }
    };

    Thread t2 = new Thread("Thread 2") {

        @Override
        public void run() {
            while (true) {
                synchronized (lock2) {
                    synchronized (lock1) {
                        System.out.println("@ " + lock2 + "/" + lock1);
                    }
                }
            }
        }
    };

    public static void main(String a[]) {
        Deadlock mdl = new Deadlock();
        mdl.t1.start();
        mdl.t2.start();
    }
}
