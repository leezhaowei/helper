package com.zwli.research.concurrent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FileSize {

    private static ForkJoinPool forkJoinPool = new ForkJoinPool();

    private static class FileSizeFinder extends RecursiveTask<Long> {

        private static final long serialVersionUID = -4067468037655708694L;
        public final File file;

        public FileSizeFinder(final File file) {
            this.file = file;
        }

        @Override
        protected Long compute() {
            long size = 0;
            if (file.isFile()) {
                size = file.length();
            } else {
                final File[] children = file.listFiles();
                if (children != null) {
                    final List<ForkJoinTask<Long>> tasks = new ArrayList<ForkJoinTask<Long>>();
                    for (final File child : children) {
                        if (child.isFile()) {
                            size += child.length();
                        } else {
                            tasks.add(new FileSizeFinder(child));
                        }
                    }

                    for (final ForkJoinTask<Long> task : invokeAll(tasks)) {
                        size += task.join();
                    }
                }
            }
            return size;
        }
    }

    public static void main(final String[] args) {
        final long start = System.nanoTime();
        final long total = forkJoinPool.invoke(new FileSizeFinder(new File("/Users/ZhaoweiLi")));
        final long end = System.nanoTime();
        System.out.println("Total size: " + total);
        System.out.println("Time taken: " + (end - start) / 1.0e9);
    }
}
