package com.zwli.research.thread.deadlock3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MicroBlogNode implements SimpleMicroBlogNode {
    private final String ident;
    private final Lock lock = new ReentrantLock();

    public MicroBlogNode(String ident) {
        this.ident = ident;
    }

    public String getIdent() {
        return ident;
    }

    // *********************************************************
    // public synchronized void propagateUpdate(Update upd, MicroBlogNode backup) {
    // System.out.println(ident + ": recvd: " + upd.getUpdateText() + "; backup: " + backup.getIdent());
    // backup.confirmUpdate(this, upd);
    // }
    //
    // public synchronized void confirmUpdate(MicroBlogNode other, Update upd) {
    // System.out.println(ident + ": recvd confirm: " + upd.getUpdateText() + " from " + other.getIdent());
    // }
    // *********************************************************

    // =========================================================
    // public void propagateUpdate(Update upd, MicroBlogNode backup) {
    // lock.lock();
    // try {
    // System.out.println(ident + ": recvd: " + upd.getUpdateText() + "; backup: " + backup.getIdent());
    // backup.confirmUpdate(this, upd);
    // } finally {
    // lock.unlock();
    // }
    // }
    //
    // public void confirmUpdate(MicroBlogNode other, Update upd) {
    // lock.lock();
    // try {
    // System.out.println(ident + ": recvd confirm: " + upd.getUpdateText() + " from " + other.getIdent());
    // } finally {
    // lock.unlock();
    // }
    // }
    // =========================================================

    // +++ deadlock solved +++++++++++++++++++++++++++++++++++++
    public void propagateUpdate(Update upd, MicroBlogNode backup) {
        boolean acquired = false;
        boolean done = false;
        while (!done) {
            int wait = (int) (Math.random() * 10);
            try {
                acquired = lock.tryLock(wait, TimeUnit.MILLISECONDS);
                if (acquired) {
                    System.out.println(ident + ": recvd: " + upd.getUpdateText() + "; backup: " + backup.getIdent());
                    done = backup.confirmUpdate(this, upd);
                }
            } catch (InterruptedException e) {
                // nothing to do
            } finally {
                if (acquired) {
                    lock.unlock();
                }
            }
            if (!done) {
                try {
                    Thread.sleep(wait);
                } catch (InterruptedException e) {
                    // nothing to do
                }
            }
        }
    }

    public boolean confirmUpdate(MicroBlogNode other, Update upd) {
        long startTime = System.currentTimeMillis();
        boolean acquired = false;
        try {
            int wait = (int) (Math.random() * 10);
            acquired = lock.tryLock(wait, TimeUnit.MILLISECONDS);
            if (acquired) {
                long elapsed = System.currentTimeMillis() - startTime;
                System.out.println(ident + ": recvd confirm: " + upd.getUpdateText() + " from " + other.getIdent()
                        + " - took " + elapsed + " millis");
                return true;
            }
        } catch (InterruptedException e) {
            // nothing to do
        } finally {
            if (acquired) {
                lock.unlock();
            }
        }
        return false;
    }
    // +++ deadlock solved +++++++++++++++++++++++++++++++++++++
}
