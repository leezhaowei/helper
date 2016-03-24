package com.zwli.research.concurrent.racecondition;

public class RaceCondition {

    public static void main(String[] args) throws InterruptedException {
        // test1();
        test2();

    }

    // private static volatile boolean done; // solution 1
    public static void test1() throws InterruptedException {
        new Thread(new Runnable() {

            @Override
            public void run() {
                long i = 0L;
                while (!done) {
                    i++;
                }
                System.out.println("Done! " + i);
            }
        }).start();

        System.out.println("OS: " + System.getProperty("os.name"));
        Thread.sleep(2000);
        done = true;
        System.out.println("Flag done set to true");

    }

    private static boolean done;

    public static void test2() throws InterruptedException {
        new Thread(new Runnable() {

            @Override
            public void run() {
                long i = 0L;
                while (!getFlag()) {
                    i++;
                }
                System.out.println("Done! " + i);
            }
        }).start();

        System.out.println("OS: " + System.getProperty("os.name"));
        Thread.sleep(2000);
        setFlag(true);
        System.out.println("Flag done set to true");

    }

    // solution 2, setter & getter
    public static synchronized boolean getFlag() {
        return done;
    }

    public static synchronized void setFlag(boolean flag) {
        done = flag;
    }
}
