package exercise63;

class Histogram3 implements Histogram {
    private final int[] counts;  // add final
    private int total=0;
    private final int nrLocks;  // default
    private final Object[] Locks;

//    public Histogram3(int span) {
//        this.counts = new int[span];
//        Locks = new Object[nrLocks];
//        for (int i = 0; i < nrLocks; i++){
//            Locks[i] = new Object();
//        }
//    }

    public Histogram3(int span, int numberOfLocks){
        this.nrLocks = numberOfLocks;
        this.counts = new int[span];
        Locks = new Object[nrLocks];
        for (int i = 0; i < nrLocks; i++){
            Locks[i] = new Object();
        }
    }

    public void increment(int bin) {
        synchronized (Locks[bin % nrLocks]){
            counts[bin] = counts[bin] + 1;
            total++;
        }
    }

    public synchronized int getCount(int bin) {
        synchronized (Locks[bin % nrLocks]){
            return counts[bin];
        }
    }

    public synchronized float getPercentage(int bin){
        synchronized (Locks[bin % nrLocks]){
            return getCount(bin) / getTotal() * 100;
        }
    }

    public int getSpan() {
        return counts.length;
    }

    public synchronized int getTotal(){
        return total;
    }
}
