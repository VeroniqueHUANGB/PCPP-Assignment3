package exercise63;

// first version by Kasper modified by jst@itu.dk 24-09-2021
// raup@itu.dk * 05/10/2022

public class HistogramPrimesThreads {
    public static void main(String[] args) { new HistogramPrimesThreads(); }

    public HistogramPrimesThreads() { 

		// TODO: Replace below with an instance of Histogram2 exercise 6.3.1 (recall that Histogram1 is not thread-safe)
		final Histogram histogram = new Histogram2(25); // 25 bins sufficient for a range of 0..4_999_999

		// TODO: Run it using multiple threads, and check the countFactors function below (it might be useful)
		countFactorsTotal(histogram, 4999999, 10);

		// Finally we plot the result to ensure that it looks as expected (see example output in the exercise script)
		dump(histogram);
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

    public static void dump(Histogram histogram) {
	for (int bin= 0; bin < histogram.getSpan(); bin= bin+1) {
	    System.out.printf("%4d: %9d%n", bin, histogram.getCount(bin));
	}
	System.out.printf("      %9d%n", histogram.getTotal() );
    }
}

