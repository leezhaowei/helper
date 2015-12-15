package com.zwli.research.thread.waitnotify;

import java.text.SimpleDateFormat;
import java.util.Vector;

public class Producer1 extends Thread {

    static final int MAXQUEUE = 5;

    private Vector<String> messages = new Vector<String>();

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SSS");

    @Override
    public void run() {
        try {
            while (true) {
                putMessage();
                // sleep(5000);
            }
        } catch (InterruptedException e) {
        }
    }

    private synchronized void putMessage() throws InterruptedException {
        while (messages.size() == MAXQUEUE) {
            wait();
        }
        messages.addElement(sdf.format(new java.util.Date()));
        System.out.println("put message");
        notify();
        // Later, when the necessary event happens, the thread that is running it calls notify() from a block
        // synchronized on the same object.
    }

    // Called by Consumer
    public synchronized String getMessage() throws InterruptedException {
        notify();
        while (messages.size() == 0) {
            wait();// By executing wait() from a synchronized block, a thread gives up its hold on the lock and goes to
            // sleep.
        }
        String message = messages.firstElement();
        messages.removeElement(message);
        return message;
    }
}
