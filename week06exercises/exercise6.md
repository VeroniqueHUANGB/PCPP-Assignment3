## Exercise 6.1

### Mandatory

#### 1

```j
> Task :app:AccountExperiments.main()
Number of transaction:  1      52854103.2 ns  835326.63          8
Number of transaction:  2     104817567.9 ns  963974.57          4
Number of transaction:  3     159318344.8 ns 2513536.25          2
Number of transaction:  4     210704710.9 ns 1586802.73          2
Number of transaction:  5     263075543.4 ns 3013539.96          2
Number of transaction:  6     316451908.6 ns 3513947.20          2
Number of transaction:  7     369437629.2 ns 4601904.02          2
Number of transaction:  8     423057743.9 ns 5267272.66          2
Number of transaction:  9     475652131.3 ns 3335256.38          2
Number of transaction: 10     526756613.8 ns 5515644.41          2
....
```



From the statistics, we can verify that the time it takes to run the program is proportional to the transaction time

#### 2

The results with of code with`min` and `max`

> Task :app:ThreadsAccountExperimentsMany.main()
> Transfer 1394 from 0 to 6
> Transfer 902 from 2 to 5
> Transfer 1409 from 3 to 6
> Transfer 1524 from 5 to 6
> Transfer 643 from 8 to 3
> Transfer 1157 from 6 to 8
> Transfer 104 from 0 to 8
> Transfer 1847 from 4 to 8
> Transfer 305 from 7 to 4
> Transfer 3035 from 2 to 9
> Transfer 3520 from 7 to 5
> Transfer 5020 from 0 to 3
> Transfer 1976 from 2 to 3
> Transfer 319 from 4 to 8
> Transfer 1483 from 1 to 9
> Transfer 3300 from 0 to 5
> Transfer 1774 from 2 to 5
> Transfer 2812 from 2 to 8
> Transfer 1605 from 1 to 2
> Transfer 3630 from 3 to 8
> Transfer 2945 from 8 to 5
> Transfer 2247 from 0 to 3
> Transfer 2677 from 2 to 8
> Transfer 4957 from 8 to 9
> Transfer 4072 from 7 to 3
> Transfer 3387 from 8 to 6
> Transfer 1045 from 6 to 7
> Transfer 3753 from 8 to 2
> Transfer 4848 from 0 to 8
> Transfer 322 from 1 to 9
> Transfer 2423 from 2 to 7
> Transfer 1006 from 8 to 2
> Transfer 4372 from 5 to 7
> Transfer 765 from 2 to 8
> Transfer 3439 from 8 to 4
> Transfer 1938 from 1 to 3
> Transfer 3599 from 8 to 5
> Transfer 2607 from 7 to 2
> Transfer 899 from 2 to 7
> Transfer 3598 from 1 to 7
> Transfer 4562 from 6 to 9
> Transfer 658 from 9 to 5
> Transfer 3330 from 2 to 4
> Transfer 4954 from 5 to 7
> Transfer 4926 from 5 to 9
> Transfer 4650 from 6 to 4
> Transfer 2674 from 3 to 7
> Transfer 1225 from 8 to 6
> Transfer 3424 from 8 to 9
> Transfer 2202 from 5 to 9

The results without of code with`min` and `max`



> Task :app:ThreadsAccountExperimentsMany.main()
> Transfer 626 from 7 to 8
> Transfer 4709 from 2 to 0
> Transfer 2371 from 3 to 1
> Transfer 3487 from 9 to 2
> Transfer 1365 from 1 to 4
> Transfer 3425 from 0 to 4
> Transfer 605 from 7 to 5
> Transfer 3269 from 4 to 1
> Transfer 5080 from 9 to 7
> Transfer 1289 from 3 to 1
> Transfer 1415 from 2 to 4
> Transfer 5074 from 7 to 5
> Transfer 396 from 5 to 7
> Transfer 2685 from 8 to 5
> Transfer 495 from 3 to 1
> Transfer 3738 from 0 to 7
> Transfer 2156 from 4 to 2
> Transfer 368 from 9 to 0
> Transfer 1894 from 6 to 0
> Transfer 4621 from 5 to 3
> Transfer 2909 from 1 to 6
> Transfer 4953 from 6 to 4
> Transfer 4413 from 7 to 9
> Transfer 2909 from 0 to 8
> Transfer 511 from 3 to 7
> Transfer 3084 from 6 to 7
> Transfer 3486 from 5 to 8
> Transfer 292 from 5 to 6
> Transfer 218 from 7 to 1
> Transfer 4184 from 3 to 1



Then the program suspend for so long so there must be something wrong with the program but I don't know why.

#### 3

```java
private static ExecutorService pool;
pool = Executors.newFixedThreadPool(NO_THREADS);
for( int i = 0; i<NO_THREADS; i++){
      try{
        pool.execute(()->doNTransactions(NO_TRANSACTION));
      } catch (Error e) {
        System.out.println("At i = " + i + " I got error: " + e);
        System.exit(0);
      }
```



## Exercise 6.2

### Mandatory

#### 1

Results I get from `TestCountPrimesThread.java`

```my
> Task :app:TestCountPrimesThreads.main()
countSequential                 7250479.0 ns  174607.62         64
countParallelN  1               7666317.3 ns   85975.02         64
countParallelNLocal  1          7528726.1 ns  174418.05         64
countParallelN  2               4837627.5 ns  113404.37         64
countParallelNLocal  2          4738107.2 ns   17424.58         64
countParallelN  3               3401851.8 ns   18933.37        128
countParallelNLocal  3          3423485.5 ns   20464.77        128
countParallelN  4               2903813.6 ns  129191.45        128
countParallelNLocal  4          2987131.1 ns   97221.26        128
countParallelN  5               2929598.1 ns   64839.90        128
countParallelNLocal  5          2805843.8 ns   44806.65        128
countParallelN  6               2938962.4 ns   96685.93        128
countParallelNLocal  6          2580615.8 ns   11424.17        128
countParallelN  7               2634607.4 ns   38242.41        128
countParallelNLocal  7          2543730.9 ns   39018.22        128
countParallelN  8               2694530.6 ns   90922.22        128
countParallelNLocal  8          2400828.4 ns   69825.78        128
countParallelN  9               2640546.5 ns  105753.43        128
countParallelNLocal  9          2700331.3 ns  189317.53        128
countParallelN 10               2598331.4 ns   34313.01        128
countParallelNLocal 10          2384479.5 ns   27837.79        128
countParallelN 11               2702802.1 ns   94880.90        128
countParallelNLocal 11          2634557.1 ns  137558.16        128
countParallelN 12               3341532.9 ns  350708.83        128
countParallelNLocal 12          2425988.0 ns   44313.37        128
countParallelN 13               2620259.7 ns   58272.76        128
countParallelNLocal 13          2334985.4 ns   34413.50        128
countParallelN 14               2543886.5 ns   42689.95        128
countParallelNLocal 14          2385675.1 ns   37913.63        128
countParallelN 15               2609300.7 ns   93699.04        128
countParallelNLocal 15          2397482.7 ns   63965.82        128
countParallelN 16               2529594.4 ns   35023.55        128
countParallelNLocal 16          2467806.2 ns   45967.41        128
countParallelN 17               2813092.2 ns  207558.01        128
countParallelNLocal 17          2516404.9 ns  100684.72        128
countParallelN 18               2630176.9 ns   72311.65        128
countParallelNLocal 18          2402355.9 ns   41580.77        128
countParallelN 19               2617787.7 ns   45457.26        128
countParallelNLocal 19          2579529.0 ns  187443.57        128
countParallelN 20               2617433.9 ns   38709.24        128
countParallelNLocal 20          2465776.5 ns   10729.55        128
countParallelN 21               2748576.3 ns   81046.04        128
countParallelNLocal 21          2508230.8 ns   46330.19        128
countParallelN 22               2689516.5 ns   58962.05        128
countParallelNLocal 22          2485583.1 ns   45213.07        128
countParallelN 23               2682211.8 ns   45603.44        128
countParallelNLocal 23          2581367.2 ns   72087.18        128
countParallelN 24               2765316.5 ns   34039.90        128
countParallelNLocal 24          2547942.4 ns   48144.32        128
countParallelN 25               2851329.0 ns  111690.01        128
countParallelNLocal 25          2596124.3 ns   62718.77        128
countParallelN 26               2787776.6 ns   37920.13        128
countParallelNLocal 26          2634897.0 ns   28063.03        128
countParallelN 27               2895361.2 ns   77855.90        128
countParallelNLocal 27          2658599.0 ns   94690.43        128
countParallelN 28               2835724.0 ns   49193.58        128
countParallelNLocal 28          2677892.2 ns   65969.04        128
countParallelN 29               2845785.0 ns   39106.08        128
countParallelNLocal 29          2712702.3 ns   65259.11        128
countParallelN 30               2909405.4 ns   73712.87        128
countParallelNLocal 30          2762098.9 ns   20303.99        128
countParallelN 31               3001193.4 ns   61673.94        128
countParallelNLocal 31          2786666.5 ns   15455.05        128
countParallelN 32               2968220.9 ns   43538.75        128
countParallelNLocal 32          2826165.1 ns   31222.29        128

```

From the result we can see the countParallelNLocal is always more efficient than countParalelN. This may because that when using AtomicLong, it is an atomic action, which means each thread need to read from the main memory instead of a local register. This will cost more time.



#### 2



## Exercise 6.3

### Mandatory

#### 1

I use `private final int[] counts; ` and `synchronized` for methods: `public synchronized void increment(int bin)`, `public synchronized int getCount(int bin)`, `public synchronized float getPercentage(int bin)`, `public synchronized int getTotal()`

The `getSpan` method does not need to be synchronized since there is no functions change the length of the span.



#### 2

Here, I use 

```java
private final int nrLocks = 5;
private final Object[] Locks;


public Histogram3(int span) {
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
......
  
```





#### 3

```java
// TODO: Replace below with an instance of Histogram2 exercise 6.3.1 (recall that Histogram1 is not thread-safe)
		final Histogram histogram = new Histogram2(25); // 25 bins sufficient for a range of 0..4_999_999

		// TODO: Run it using multiple threads, and check the countFactors function below (it might be useful)
		countFactorsTotal(histogram, 4999999, 10);


private static Histogram countFactorsTotal(Histogram histogram, int range, int threadCount){
		final int perThread = range / threadCount;
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
		return histogram;
	}
```

The output is

```java
Task :app:HistogramPrimesThreads.main()
   0:         2
   1:    348512
   2:    979274
   3:   1232881
   4:   1015979
   5:    660254
   6:    374791
   7:    197039
   8:     98949
   9:     48400
  10:     23251
  11:     11019
  12:      5199
  13:      2403
  14:      1124
  15:       510
  16:       233
  17:       102
  18:        45
  19:        21
  20:         7
  21:         3
  22:         1
  23:         0
  24:         0

```

#### 4

If there’s only one lock, then all the threads must compete for the same one. Even they may access different part of the array. This may cause a lot of useless waiting time. However, if there’re too many locks, it will also take a lot of time to lock the resources and unlock them. In this example, we have 25 positions, the performance is the best when we can find a good balance between the threads’ waiting time and the time used to maintain those locks.