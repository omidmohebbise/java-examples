package com.omidmhebbi.parallel.advanced.practice07;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

//(L)0,1,2,3,4,5,6,7,8,9,10(H)
//Find all the integer between low and high value
class RecursiveSum extends RecursiveTask<Long>{
    private final long lo;
    private final long hi;

    public RecursiveSum(long lo, long hi) {
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    protected Long compute() {
        if(hi-lo <= 100 ){//base case threshold
            long total = 0;
            for(long i = 0 ; i <= hi; i++)
                total += i;
            return total;
        }else{
            long mid = (hi+lo)/2;

            RecursiveSum left = new RecursiveSum(lo,mid);
            RecursiveSum right = new RecursiveSum(mid+1,hi);
            System.out.printf("mid = %s new call => left ( %s , %s ) right( %s , %s )%n", mid , lo,mid,mid+1,hi);
            left.fork();
            return right.compute() + left.join();
        }

    }
}
public class DivideAndConquerExample {
    public static void main(String[] args) {
        ForkJoinPool pool = ForkJoinPool.commonPool() ;
        long total = pool.invoke(new RecursiveSum(0, 2_000));
        pool.shutdown();
        System.out.println("Total sim is  " + total);

    }
}
