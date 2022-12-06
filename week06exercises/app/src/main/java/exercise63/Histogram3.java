package exercise63;

import java.util.concurrent.locks.ReentrantLock;

class Histogram3 implements Histogram {
    private final int[] counts;  // add final
    private int total=0;
    private final int nrLocks;  // default
    private final ReentrantLock[] Locks;
    private final ReentrantLock totalLock;


    public Histogram3(int span, int numberOfLocks){
        synchronized (this){
            this.nrLocks = numberOfLocks;
            this.counts = new int[span];
            this.Locks = new ReentrantLock[numberOfLocks];
            this.totalLock = new ReentrantLock();
            for (int i = 0; i < nrLocks; i++){
                this.Locks[i] = new ReentrantLock();
            }
        }

    }

    public void increment(int bin) {
        Locks[bin % nrLocks].lock();
        counts[bin] = counts[bin] + 1;
        Locks[bin % nrLocks].unlock();
        totalLock.lock();
        total++;
        totalLock.unlock();
    }

    public synchronized int getCount(int bin) {
        Locks[bin % nrLocks].lock();
        int res = counts[bin];
        Locks[bin % nrLocks].unlock();
        return res;
    }

    public synchronized float getPercentage(int bin){
        Locks[bin % nrLocks].lock();
        float res = getCount(bin) / getTotal() * 100;
        Locks[bin % nrLocks].unlock();
        return res;
    }

    public int getSpan() {
        return counts.length;
    }

    public synchronized int getTotal(){
        totalLock.lock();
        int res = total;
        totalLock.unlock();
        return total;
    }
}
