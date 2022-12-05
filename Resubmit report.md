### Exercise 5 correct some errors

###### 5.5.4

After running countParallelN method, we found that the occurance of ipsum in the gievn file is 1430.

###### 5.5.5

When we use Mark 7 to benchmark countParallelN method, we got the following result:

```txt
Mark 7 measurements for Paralle search
2 threads search                7790085.8 ns  627421.18         64
4 threads search                7170744.6 ns  175027.46         64
8 threads search                7412077.2 ns  199360.81         64
16 threads search               7983521.2 ns  362870.38         64
32 threads search               9096618.8 ns 1201940.17         32
```

As you can see from the result, the running time increases if the number of threads are more than 4 threads, this may because starting a new thread is also expensive.

#### Exercise 6

###### 6.1.4

We use awaitTermination before we use pool.shutdown() to end all executors.

###### 6.2.2

We rewrite the TestCountPrimesThreads.java using Future, you can see the code from TestCountPrimesThreadsFuture.java.

Here's the result

```txt
countSequential                10414415.6 ns  939677.85         32
countParallelN  1               9770689.6 ns   98294.28         32
countParallelNLocal  1         11377369.9 ns  692136.30         32
countParallelN  2               6579231.7 ns  187624.28         64
countParallelNLocal  2          6341230.4 ns  611961.27         64
countParallelN  3               7539076.3 ns  715349.35         64
countParallelNLocal  3          6159556.6 ns  304703.86         64
countParallelN  4               6739024.5 ns  764171.91         64
countParallelNLocal  4          5365200.1 ns  528936.62         64
countParallelN  5               7266667.3 ns  759380.67         64
countParallelNLocal  5          5569130.3 ns  333743.32         64
countParallelN  6               6089049.0 ns  464669.34         64
countParallelNLocal  6          5045672.1 ns  118732.21         64
countParallelN  7               6851339.3 ns  629344.12         64
countParallelNLocal  7          7321594.4 ns  903737.74         64
countParallelN  8               8736684.1 ns 1310043.95         32
countParallelNLocal  8          8677478.2 ns 3286243.67         16
countParallelN  9               7813486.5 ns  761236.15         64
countParallelNLocal  9          6712627.8 ns  907698.03         64
countParallelN 10               6998330.7 ns  962243.39         64
countParallelNLocal 10          6374917.8 ns  456101.77         64
```

