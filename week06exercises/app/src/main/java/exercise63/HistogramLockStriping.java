package exercise63;

import benchmarking.Benchmark;

public class HistogramLockStriping {
    public static void main(String[] args){
        new HistogramLockStriping();
    }
    public HistogramLockStriping(){
        final int range = 4999999;
        for (int c = 1; c < 26; c++){
            final int nrLocks = c;
            final Histogram histogram = new Histogram3(25, nrLocks);
            Benchmark.Mark7(String.format("Number of locks: %2d", nrLocks),
                    i -> countFactorsTotal(histogram, range, 25));
        }
    }

    public static long countFactorsTotal(Histogram histogram, int range, int threadCount){
        final int perThread = range / threadCount;
        long res = 0;
        Thread[] threads = new Thread[threadCount];
        for (int t = 0; t < threadCount; t++){
            final int from = perThread * t;
            final int to = (t+1 == threadCount) ? range : perThread * (t + 1);
            final int threadNo = t;
            threads[t] = new Thread(() -> {
                for (int i = from; i < to; i++){
                    histogram.increment(countFactors(i));
                }
            });
        }
        for (int t = 0; t < threadCount; t++){
            threads[t].start();
        }
        try{
            for (int t = 0; t < threadCount; t++){
                threads[t].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int t = 0; t < 25; t++){
            res += histogram.getCount(t);
        }
        return res;
    }

    // Returns the number of prime factors of `p`
    public static int countFactors(int p) {
        if (p < 2) return 0;
        int factorCount = 1, k = 2;
        while (p >= k * k) {
            if (p % k == 0) {
                factorCount++;
                p= p/k;
            } else
                k= k+1;
        }
        return factorCount;
    }

}
