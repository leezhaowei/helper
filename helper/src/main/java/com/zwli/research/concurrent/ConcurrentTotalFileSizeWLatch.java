package com.zwli.research.concurrent;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentTotalFileSizeWLatch {

    private ExecutorService service;
    private final AtomicLong pendingFileVisits = new AtomicLong();
    private final AtomicLong totalSize = new AtomicLong();
    private final CountDownLatch latch = new CountDownLatch(1);

    private void updateTotalSizeOfFilesInDir(final File file) {
        long fileSize = 0;
        if (file.isFile()) {
            fileSize = file.length();
        } else {
            final File[] children = file.listFiles();
            if (children != null) {
                for (final File child : children) {
                    if (child.isFile()) {
                        fileSize += child.length();
                    } else {
                        pendingFileVisits.incrementAndGet();
                        service.execute(new Runnable() {

                            @Override
                            public void run() {
                                updateTotalSizeOfFilesInDir(child);
                            }
                        });
                    }
                }
            }
        }
        totalSize.addAndGet(fileSize);
        if (pendingFileVisits.decrementAndGet() == 0) {
            latch.countDown();
        }
    }

    public long getTotalSizeOfFile(final String fileName) throws InterruptedException {
        service = Executors.newFixedThreadPool(100);
        pendingFileVisits.incrementAndGet();
        try {
            updateTotalSizeOfFilesInDir(new File(fileName));
            latch.await(100, TimeUnit.SECONDS);
            return totalSize.longValue();
        } finally {
            service.shutdown();
        }
    }

    public static void main(final String[] args) throws InterruptedException {
        final long start = System.nanoTime();
        final long total = new ConcurrentTotalFileSizeWLatch().getTotalSizeOfFile("/Users/ZhaoweiLi");
        final long end = System.nanoTime();
        System.out.println("Total size: " + total);
        System.out.println("Time taken: " + (end - start) / 1.0e9);
    }

}