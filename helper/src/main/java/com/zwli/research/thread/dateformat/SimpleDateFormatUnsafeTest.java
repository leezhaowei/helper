package com.zwli.research.thread.dateformat;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleDateFormatUnsafeTest {

    private static final Pattern dateCreateP = Pattern.compile("\\s*(.+)");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
    private static final ThreadLocal<SimpleDateFormat> sdfSafe = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        }
    };

    public static void main(String[] args) {
        // unsafe();
        safe();
    }

    static void safe() {
        ExecutorService executor = Executors.newFixedThreadPool(100);
        while (true) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    safeWorkConcurrently();
                }
            });
        }
    }

    static void unsafe() {
        ExecutorService executor = Executors.newFixedThreadPool(100);
        while (true) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    workConcurrently();
                }
            });
        }
    }

    public static void workConcurrently() {
        Matcher matcher = dateCreateP.matcher("19:30:55 03.05.2015");
        Timestamp startAdvDate = null;
        try {
            if (matcher.find()) {
                String dateCreate = matcher.group(1);
                startAdvDate = new Timestamp(sdf.parse(dateCreate).getTime());
                System.out.println("OK " + startAdvDate.toString());
                Thread.sleep(3000);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void safeWorkConcurrently() {
        Matcher matcher = dateCreateP.matcher("19:30:55 03.05.2015");
        Timestamp startAdvDate = null;
        try {
            if (matcher.find()) {
                String dateCreate = matcher.group(1);
                startAdvDate = new Timestamp(sdfSafe.get().parse(dateCreate).getTime());
                Thread.sleep(3000);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        System.out.println("OK " + startAdvDate.toString());
    }
}
