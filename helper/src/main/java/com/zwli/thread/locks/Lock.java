package com.zwli.thread.locks;

public class Lock {

    private boolean isLocked = false;

    private Thread lockedBy = null;

    private int lockedCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (isLocked && !lockedBy.equals(callingThread)) {
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockedBy = callingThread;
    }

    public synchronized void unlock() {
        isLocked = false;
        System.err.println(">>> Locked Count: " + lockedCount);
        notify();
    }
}
