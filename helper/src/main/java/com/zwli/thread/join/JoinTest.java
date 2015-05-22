package com.zwli.thread.join;

public class JoinTest implements Runnable {

    static class RunnableImpl implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("Begin sleep");
                // Thread.sleep(1000);
                Thread.sleep(2000);
                System.out.println("End sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static int a = 0;

    @Override
    public void run() {
        for (int k = 0; k < 5; k++) {
            a = a + 1;
        }
    }

    public static void main(String[] args) throws Exception {
        // a();
        // b();
        c();
    }

    static void c() {
        Thread t = new Thread(new RunnableImpl());
        t.start();
        try {
            t.join(1000);
            System.out.println("joinFinish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void b() {
        Runnable r = new JoinTest();
        Thread t = new Thread(r);
        t.start();
        for (int i = 0; i < 300; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(a);
    }

    static void a() {
        Runnable r = new JoinTest();
        Thread t = new Thread(r);
        t.start();
        // t.join(); // print a = 5;
        System.out.println(a);
    }

}
