package com.zwli.research.thread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ConcurrentTotalFileSize {

    class SubDirectoriesAndSize {
        public final long size;
        public final List<File> subDirectories;

        public SubDirectoriesAndSize(final long size, final List<File> subDirectories) {
            super();
            this.size = size;
            this.subDirectories = subDirectories;
        }
    }

    private SubDirectoriesAndSize getTotalAndSubDirs(final File file) {
        long total = 0;
        final List<File> subDirectories = new ArrayList<File>();
        if (file.isDirectory()) {
            final File[] children = file.listFiles();
            if (children != null) {
                for (final File child : children) {
                    if (child.isFile()) {
                        total += child.length();
                    } else {
                        subDirectories.add(child);
                    }
                }
            }
        }
        return new SubDirectoriesAndSize(total, subDirectories);
    }

    public long getTotalSizeOfFilesInDir(final File file) throws InterruptedException, ExecutionException,
            TimeoutException {
        final ExecutorService service = Executors.newFixedThreadPool(100);
        try {
            long total = 0;
            final List<File> directories = new ArrayList<File>();
            directories.add(file);
            while (!directories.isEmpty()) {
                final List<Future<SubDirectoriesAndSize>> partialResults = new ArrayList<Future<SubDirectoriesAndSize>>();
                for (final File directory : directories) {
                    partialResults.add(service.submit(new Callable<SubDirectoriesAndSize>() {
                        @Override
                        public SubDirectoriesAndSize call() throws Exception {
                            return getTotalAndSubDirs(directory);
                        }
                    }));
                }

                directories.clear();
                for (final Future<SubDirectoriesAndSize> future : partialResults) {
                    final SubDirectoriesAndSize subDirectoriesAndSize = future.get(100, TimeUnit.SECONDS);
                    directories.addAll(subDirectoriesAndSize.subDirectories);
                    total += subDirectoriesAndSize.size;
                }
            }
            return total;
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }
    public static void main(final String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        final long start = System.nanoTime();
        final File file = new File("D:/");
        final long total = new ConcurrentTotalFileSize().getTotalSizeOfFilesInDir(file);
        final long end = System.nanoTime();
        System.out.println("Total size: "+total);
        System.out.println("Time taken: "+(end-start)/1.0e9);
    }
}
