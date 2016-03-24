package com.zwli.research.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ConcurrentPrimeFinder extends AbstractPrimeFinder {

    private final int poolSize;
    private final int numberOfParts;

    public ConcurrentPrimeFinder(final int poolSize, final int numberOfParts) {
        this.poolSize = poolSize;
        this.numberOfParts = numberOfParts;
    }

    @Override
    public long countPrimes(final int number) {
        int count = 0;
        final List<Callable<Integer>> partitions = new ArrayList<Callable<Integer>>();
        final int chunksPerPartition = number / numberOfParts;
        for (int i = 0; i < numberOfParts; i++) {
            final int lower = (i * chunksPerPartition) + 1;
            final int upper = (i == numberOfParts - 1) ? number : lower + chunksPerPartition - 1;
            // System.out.println(lower + " - " + upper);
            partitions.add(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return countPrimesInRange(lower, upper);
                }
            });
        }
        final ExecutorService executorPool = Executors.newFixedThreadPool(poolSize);
        try {
            final List<Future<Integer>> resultFromParts = executorPool.invokeAll(partitions, 10000, TimeUnit.SECONDS);
            executorPool.shutdown();
            for (final Future<Integer> result : resultFromParts) {
                count += result.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void main(final String[] args) {
        int poolSize = Runtime.getRuntime().availableProcessors();
        int numberOfParts = 512;
        new ConcurrentPrimeFinder(poolSize, numberOfParts).timeAndCompute(10000000);
    }

}
