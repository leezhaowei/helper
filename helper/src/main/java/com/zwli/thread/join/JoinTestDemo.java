package com.zwli.thread.join;

public class JoinTestDemo {

    public static void main(String[] args) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start.");
        CustomThreadA ta = new CustomThreadA();
        CustomThreadB tb = new CustomThreadB(ta);
        try {
            ta.start();
            Thread.sleep(2000);
            tb.start();
            tb.join(); // 在代碼2里，將此處注釋掉
        } catch (Exception e) {
            System.out.println("Exception from main");
        }
        System.out.println(threadName + " end!");
    }
}

class CustomThreadA extends Thread {

    @Override
    public void run() {
        // String threadName = Thread.currentThread().getName();
        String threadName = "CustomThreadA";
        System.out.println(threadName + " start.");
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(threadName + " loop at " + i);
                Thread.sleep(1000);
            }
            System.out.println(threadName + " end.");
        } catch (Exception e) {
            System.out.println("Exception from " + threadName + ".run");
        }
    }
}

class CustomThreadB extends Thread {

    CustomThreadA ta;

    public CustomThreadB(CustomThreadA ta) {
        this.ta = ta;
    }

    @Override
    public void run() {
        // String threadName = Thread.currentThread().getName();
        String threadName = "CustomThreadB";
        System.out.println(threadName + " start.");
        try {
            ta.join();
            System.out.println(threadName + " end.");
        } catch (Exception e) {
            System.out.println("Exception from " + threadName + ".run");
        }
    }
}
