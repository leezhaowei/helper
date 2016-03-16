package com.zwli.research.thread.waitnotify;

import java.text.SimpleDateFormat;
import java.util.Vector;

public class Producer1 extends Thread {

    static final int MAXQUEUE = 5;

    private final Vector<String> messages = new Vector<String>();
    private final ThreadLocal<SimpleDateFormat> fmt;

    public Producer1() {
        super();
        this.fmt = new ThreadLocal<SimpleDateFormat>();
        fmt.set(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SSS"));
    }

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
        messages.addElement(fmt.get().format(new java.util.Date()));
        System.out.println("put message");
        notify();
    }

    public synchronized String getMessage() throws InterruptedException {
        notify();
        while (messages.size() == 0) {
            wait();
        }
        String message = messages.firstElement();
        messages.removeElement(message);
        return message;
    }
}
