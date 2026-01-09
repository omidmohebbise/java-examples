package com.omidmhebbise.thread.executerservice.forkjoin;

import java.util.SplittableRandom;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Very simple Fork/Join example:
 *  1) Create a 3D tensor and fill it with random numbers
 *  2) Use Fork/Join to compute the sum of all elements
 *  3) Compare execution time: sequential vs parallel
 */
final class SumTensorTask extends RecursiveTask<Double> {

    private static final int THRESHOLD = 50_000;

    private final double[][][] tensor;
    private final int d1;
    private final int d2;
    private final int d3;
    private final int from;
    private final int to;

    SumTensorTask(double[][][] tensor, int d1, int d2, int d3, int from, int to) {
        this.tensor = tensor;
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Double compute() {
        int size = to - from;
        if (size <= THRESHOLD) {
            double sum = 0.0;
            for (int idx = from; idx < to; idx++) {
                int i = idx / (d2 * d3);
                int rem = idx % (d2 * d3);
                int j = rem / d3;
                int k = rem % d3;
                sum += tensor[i][j][k];
            }
            return sum;
        }

        int mid = from + size / 2;
        SumTensorTask left = new SumTensorTask(tensor, d1, d2, d3, from, mid);
        SumTensorTask right = new SumTensorTask(tensor, d1, d2, d3, mid, to);

        left.fork();
        double rightSum = right.compute();
        double leftSum = left.join();
        return leftSum + rightSum;
    }
}

public class DivideAndConquerExample {

    static void main() {
        // Tensor dimensions (increase them if you want to see a clearer speedup)
        int d1 = 96;
        int d2 = 96;
        int d3 = 96;

        // 1) Init tensor with random numbers
        double[][][] tensor = new double[d1][d2][d3];
        SplittableRandom rnd = new SplittableRandom(42);
        for (int i = 0; i < d1; i++) {
            for (int j = 0; j < d2; j++) {
                for (int k = 0; k < d3; k++) {
                    tensor[i][j][k] = rnd.nextDouble();
                }
            }
        }

        int totalElements = d1 * d2 * d3;
        ForkJoinPool pool = ForkJoinPool.commonPool();

        // Warmup (helps the JIT compile hot paths; timings become more stable)
        for (int i = 0; i < 3; i++) {
            sequentialSum(tensor);
            pool.invoke(new SumTensorTask(tensor, d1, d2, d3, 0, totalElements));
        }

        int runs = 5;

        // Time sequential
        double seq = 0.0;
        long seqNanos = timeNanos(() -> {
            double s = 0.0;
            for (int i = 0; i < runs; i++) {
                s = sequentialSum(tensor);
            }
            return s;
        });
        double seqSum = lastResult;

        // Time parallel (Fork/Join)
        long parNanos = timeNanos(() -> {
            double s = 0.0;
            for (int i = 0; i < runs; i++) {
                s = pool.invoke(new SumTensorTask(tensor, d1, d2, d3, 0, totalElements));
            }
            return s;
        });
        double parSum = lastResult;

        // Correctness check
        double diff = Math.abs(seqSum - parSum);

        System.out.println("Tensor dims        : " + d1 + "x" + d2 + "x" + d3);
        System.out.println("Total elements     : " + totalElements);
        System.out.println("Runs               : " + runs);
        System.out.println();
        System.out.println("Sequential sum     : " + seqSum);
        System.out.println("Parallel sum       : " + parSum);
        System.out.println("Difference         : " + diff);
        System.out.println();

        double seqMs = seqNanos / 1_000_000.0;
        double parMs = parNanos / 1_000_000.0;

        System.out.printf("Sequential time    : %.3f ms (avg %.3f ms/run)%n", seqMs, seqMs / runs);
        System.out.printf("Parallel time      : %.3f ms (avg %.3f ms/run)%n", parMs, parMs / runs);
        if (parMs > 0) {
            System.out.printf("Speedup            : %.2fx%n", seqMs / parMs);
        }
    }

    private static volatile double lastResult;

    private static long timeNanos(SupplierDouble supplier) {
        long start = System.nanoTime();
        lastResult = supplier.getAsDouble();
        return System.nanoTime() - start;
    }

    @FunctionalInterface
    private interface SupplierDouble {
        double getAsDouble();
    }

    private static double sequentialSum(double[][][] tensor) {
        double sum = 0.0;
        for (double[][] m2 : tensor) {
            for (double[] m1 : m2) {
                for (double v : m1) {
                    sum += v;
                }
            }
        }
        return sum;
    }
}

